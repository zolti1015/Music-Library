import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class LibraryModel {
	
	private Map<String, Album> albums; // assume no albums have same name
	private Map<String, ArrayList<Song>> songs; // list of songs, some songs have same name
	private Map<String, Playlist> playlists;
	private Map<String, ArrayList<Album>> ArtistAndAlbums; // artist name, list of albums
	private Map<String, ArrayList<Song>> ArtistAndSongs; // artist name, list of songs
	
	private Playlist favorites;
	private final Map<String, Integer> playCount;
	private final LinkedList<String> recentPlays; // automatic, user can query 
	
	private static final int MAX_TRACKED = 10;
	
	
	private final MusicStore store;
	
	public LibraryModel () throws IOException {
		this.store = new MusicStore();
		store.readInAlbumInfo(); // build music store to reference
		
		this.songs = new LinkedHashMap<>(); // linked version, assume order of songs matters
		this.albums = new HashMap<>();
		this.playlists = new HashMap<>();
		this.ArtistAndAlbums = new HashMap<>();
		this.ArtistAndSongs = new HashMap<>();
		
		favorites = new Playlist("Favorites");
		playCount = new HashMap<>();
		recentPlays = new LinkedList<>();
	}
	
		public String getSongInfoByTitle(String title) {
				
			String endInfo = "";
			ArrayList<Song> songList = songs.get(title);
				
			if (songList != null) {
				for (Song song : songList) {
					endInfo += (song.toString()) ;		
				}
			}
				
			if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
			endInfo += "\n";
				
			return endInfo; 
		}
		
		public String getSongInfoByArtist(String artist) {
			
			String endInfo = "";
			
			ArrayList<Song> songs = ArtistAndSongs.get(artist);
			
			if (songs != null) {
				for (Song song : songs) {
						endInfo += song.toString();		
					}
				}
			
			if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
			endInfo += "\n";
			return endInfo; 
		}
		
		public String getAlbumInfoByTitle(String title) {
				
				String endInfo = "";
				Album album = albums.get(title);
				if (album != null) endInfo += albums.get(title).toString(); 
					
				if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
				endInfo += "\n";
				return endInfo; 
			}
		
		// get album info and list of songs by artist name
		public String getAlbumInfoByArtist(String artist) {
					
				String endInfo = "";
				ArrayList<Album> albums = ArtistAndAlbums.get(artist);
				
				if (albums != null) {
					for (Album album : albums) {
						endInfo += album.toString(); 
					}
				}
				if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
				endInfo += "\n";
				return endInfo; 
			}
		
		// find playlist by provided name and print song details
		public String searchForPlaylist(String playlistName) {
			Playlist playlist = playlists.get(playlistName);
			return (playlist != null) ? playlist.toString() : "Playlist doesn't exist";
		}
		
		public void addSongToLibrary(String songName) { 
			ArrayList<Song> songList = store.getSongs().get(songName);
			if (songList != null) {
				
				this.songs.putIfAbsent(songName, new ArrayList<>());
		        this.songs.get(songName).addAll(songList);  
		        
		        for (Song song : songList) {
		        	this.ArtistAndSongs.putIfAbsent(song.getArtist(), new ArrayList<>());
		        	this.ArtistAndSongs.get(song.getArtist()).add(song); 
		        }
		        
			}
			else System.out.println("Song not in store!");
		}
		
		public void addAlbumToLibrary(String albumName) {
			Album album = store.getAlbums().get(albumName);
			if (album != null) {
				albums.put(albumName, album);
				this.ArtistAndAlbums.putIfAbsent(album.getArtist(), new ArrayList<>());
				this.ArtistAndAlbums.get(album.getArtist()).add(album); // assume no albums have same name
				
			}
			else System.out.println("Album not in store!");
		}
		
		public HashMap<String, ArrayList<Song>> getSongs () {
			return new HashMap<String, ArrayList<Song>>(songs); // copy list to avoid reference
		}
		
		public HashMap<String, Album> getAlbums () {
			return new HashMap<String, Album>(albums); // copy list to avoid reference
		}
		
		public HashMap<String, Playlist> getPlaylists() {
			return new HashMap<String, Playlist>(playlists); // copy list to avoid reference
		}
		
		public MusicStore getStore() {
			return store;
		}
		

		/*
		get a list of items from the library 
		● a list of song titles (any order) 
		● a list of artists (any order) 
		● a list of albums (any order) 
		● a list of playlists (any order) 
		● a list of “favorite” songs 
		*/
		public String listOfItems(String command) {
			String list = "";
			switch(command.toLowerCase()) {
			
				case "songs":
					list = Arrays.toString(songs.keySet().toArray());
					break;
				case "artists":
					list = Arrays.toString(ArtistAndAlbums.keySet().toArray());
					break;
				case "albums": 
					list = Arrays.toString(albums.keySet().toArray());
					break;
				case "playlists":
					list = Arrays.toString(playlists.keySet().toArray());
					break;
				case "favorites":
					for (ArrayList<Song> songs : songs.values()) {
						for (Song song : songs ) {
							if(song.getFavStatus()) list += song + "\n"; 
						}
					break;
					}
			}
			list += "\n";
			return list;
		}
		
	public void createNewPlaylist(String name) {
		playlists.put(name, new Playlist(name));
	}
	
	public void addSongToPlaylist(String playlistName, String songName) {
		Playlist playlist = playlists.get(playlistName);
		ArrayList<Song> songList = this.songs.get(songName);
		
		if (playlist != null && songList != null) {
			songList.forEach(song -> playlist.addSong(song));
		}   
	}
	
	public void removeSongFromPlaylist(String playlistName, String songName) {
		Playlist playlist = playlists.get(playlistName);
		ArrayList<Song> songList = this.songs.get(songName);
		
		if (playlist != null && songList != null) {
			songList.forEach(song -> playlist.removeSong(song));
		}   
	}
	
	public void markSongAsFavorite(String name) {
		ArrayList<Song> songs = this.songs.get(name);
		songs.forEach(song -> { song.makeFavorite(); 
								favorites.addSong(song); });
		
	}
	
	public void rateSong(String name, int rating) {
		ArrayList<Song> songs = this.songs.get(name);
		songs.forEach(song -> song.rateSong(rating));
	}
	
	
	
	public void getSongsByGenre(String genre) {
		Collection<ArrayList<Song>> listsOfSongs = songs.values();
		for (ArrayList<Song> songs : listsOfSongs) {
			
		}
	}
	
	
	 public void playSong(String song) {
	        System.out.println("Now playing: " + song);
	        if (songs.get(song) != null) {
	        	playCount.put(song, playCount.getOrDefault(song, 0) + 1);
	        	recentPlays.remove(song);
	        	recentPlays.addFirst(song);
	        	if (recentPlays.size() > MAX_TRACKED) {
	        		recentPlays.removeLast();
	        	}
	        }
	 }
	 
	 
	public void shuffleSongs() {
		List<String> list = new ArrayList<String>(songs.keySet());
	    Collections.shuffle(list);
	    list.forEach(songName->songs.put(songName, songs.get(songName)));
	}
}
