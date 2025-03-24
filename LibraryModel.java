import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
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
	private Map<String, ArrayList<Song>> GenreAndSongs; // genre, list of songs
	// private Map<String, ArrayList<Song>> AlbumAndSongs; // artist name, list of songs
	
	private final Playlist favorites;
	private final Playlist topRated;
	private final Map<String, Integer> playCount; // all plays, to then find most played
	private final LinkedList<String> recentPlays; // 10 most recently played songs
	
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
		
		favorites = new Playlist("favorites");
		topRated = new Playlist("top rated");
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
			 if(playlistName.toLowerCase().equals("most played")) {
	        	 return getMostFrequentlyPlayed().toString();
	         }
			 if(playlistName.toLowerCase().equals("recently played"))  { // special automatic 
	        	 return new ArrayList<>(recentPlays).toString();
	         }
			 
			 if (playlistName.toLowerCase().equals("favorites")) {
				 return new ArrayList<>(favorites.getSongs()).toString();
			 }
			 
			 if (playlistName.toLowerCase().equals("top rated")) {
				 return new ArrayList<>(topRated.getSongs()).toString();
			 }
			 
			 if (playlistName.toLowerCase().equals("at least 10 songs genres")) {
				 return getGenrePlaylists();
				 
			 }
			 
			Playlist playlist = playlists.get(playlistName);
			return (playlist != null) ? playlist.toString() : "Playlist doesn't exist";
		}
		
		public void addSongToLibrary(String songName) { 
			ArrayList<Song> songList = store.getSongs().get(songName);
			if (songList != null) {
				
				this.songs.putIfAbsent(songName, new ArrayList<>());
		        this.songs.get(songName).addAll(songList);  
		        
		        for (Song song : songList) {
		        	// add to artist linked map
		        	this.ArtistAndSongs.putIfAbsent(song.getArtist(), new ArrayList<>());
		        	this.ArtistAndSongs.get(song.getArtist()).add(song); 
		        	
		        	// add to genre linked map
		        	this.GenreAndSongs.putIfAbsent(song.getGenre(), new ArrayList<>());
			        this.GenreAndSongs.get(song.getGenre()).add(song); 
			        
			        
		        }
		        
			}
			else System.out.println("Song not in store!");
		}
		
		public void addAlbumToLibrary(String albumName) {
			Album album = store.getAlbums().get(albumName);
			if (album != null) {
				albums.put(albumName, album);
				album.getSongs().values().forEach(song -> {
					this.songs.putIfAbsent(song.getArtist(), new ArrayList<>());
		        	this.songs.get(song.getArtist()).add(song); // album added, so add all its songs to library
				});
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
		if (songs.size() > 1) {
			System.out.println("Which artist?");
			Scanner scanner = new Scanner(System.in);
			String artist = scanner.nextLine();
			for (Song song: songs) {
				if (song.getArtist().equals(artist))
					song.rateSong(rating);
					if (rating == 5) favorites.addSong(song);
					if (rating == 4 || rating == 5) topRated.addSong(song);
					return;
			}
		}
		// if only one song with that name
		songs.get(0).rateSong(rating);
		if (rating == 4 || rating == 5) topRated.addSong(songs.get(0));
		
		
	}
	
	public void removeSongFromLibrary(String name) {
		songs.remove(name);
	}
	
	public void removeAlbumFromLibrary(String albumName) {
		albums.remove(albumName);
	}
	
	public void getSongsByGenre(String genre) {
		Collection<ArrayList<Song>> listsOfSongs = songs.values();
		for (ArrayList<Song> songs : listsOfSongs) {
			
		}
	}
	
	public String getGenrePlaylists() {
		String list = "";
		for (ArrayList<Song> songs : GenreAndSongs.values()) {
			if (songs.size() >= 10) {
				list += "Genre: " + songs.get(0).getGenre() + "\n";
				for (Song song : songs) {
					list += song.toString();
				}
			}
		}
		return list;
}

	
	 public void playSong(String song) {
	        if (songs.get(song) != null) {
	        	System.out.println("Now playing: " + song);
	        	playCount.put(song, playCount.getOrDefault(song, 0) + 1);
	        	recentPlays.remove(song);
	        	recentPlays.addFirst(song);
	        	if (recentPlays.size() > MAX_TRACKED) {
	        		recentPlays.removeLast();
	        	}
	        }
	 }
	 
	 public List<String> getMostFrequentlyPlayed() {
	        return playCount.entrySet().stream()
	                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
	                .limit(MAX_TRACKED)
	                .map(Map.Entry::getKey)
	                .toList();
	 }
	 
	 public void printSortedSongs(int selection) {
		 switch (selection) {
		 
		 	// sort songs by rating
		 	case 1: 
		 		List<Song> allSongs = new ArrayList<>();
	            for (ArrayList<Song> songList : songs.values()) {
	                allSongs.addAll(songList);
	            }
	            
	            allSongs.sort((song1, song2) -> { 
	            	// since rating is an enum, if it hasnt been rated yet we use zero else its position
	            	Integer rating1 = (song1.getRating() != null) ? song1.getRating().ordinal() : 0;
	            	Integer rating2 = (song2.getRating() != null) ? song2.getRating().ordinal() : 0;
	            	return Integer.compare(rating1, rating2);
	            });
	            allSongs.forEach(song -> System.out.println(song.toString()));
	            break;
		 	// sort by artist alphabetical
		 	case 2:
		 		List<String> sortedArtists = ArtistAndSongs.keySet().stream().sorted().toList();
		 		for (String artist : sortedArtists) {
		 			System.out.println(getSongInfoByArtist(artist));
		 		}
		 		break;
		 	// sort by song title alphabetical
		 	case 3:
		 		System.out.println(songs.keySet().stream().sorted().toList().toString());
		 		break;
		 	default: 
		 		System.out.println("Invalid selection. Try again."); 
		 }
	 }
	 
	 public void searchForSongByGenre(String genre) {
		 GenreAndSongs.get(genre).forEach(song -> song.toString());
	 }
	 
	public void shuffleSongs() {
		List<String> list = new ArrayList<String>(songs.keySet());
	    Collections.shuffle(list);
	    list.forEach(songName->songs.put(songName, songs.get(songName)));
	}
	
	
}
