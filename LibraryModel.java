import java.util.ArrayList;
import java.io.IOException;

public class LibraryModel {
	
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlists;
	
	private final MusicStore store;
	
	public LibraryModel () {
		this.store = new MusicStore();
		this.songs = new ArrayList<>();
		this.albums = new ArrayList<>();
		this.playlists = new ArrayList<>();
	}
	
	   // get song info by title or artist
		public String getSongInfo(String titleOrArtist) {
			for (Album album : getAlbums()) 
			{
				for (Song song : album.getSongs())
			    {
					if (song.getArtist().equals(titleOrArtist) ||
						song.getTitle().equals(titleOrArtist) ) 
					{
							System.out.println(song.toString() +
									"\nAlbum: " + album.getTitle());
				    }
			    }
		    }
		return "Searched for Data is not in the database."; // need thing not found message
		}
		
		// get album info and list of songs by title or Artist
		public String getAlbumInfo(String titleOrArtist) {
			for (Album album : albums) {
				if (album.getTitle().equals(titleOrArtist) ||
					album.getArtist().equals(titleOrArtist)) {
					System.out.println(album.toString()); // printing here, so if multiple results loop will continue to search
				}
			}
		return "Searched for Data is not in the database.";
		}
		
		
		// find playlist by provided name and print song details
		public void searchForPlaylist(String playlistName) {
			for (Playlist playlist : playlists) {
				if(playlist.getName().equals(playlistName)) {
					System.out.println(playlist.toString());
				}
			}
		}
		
		public void addSongToLibrary(String songName) { 
			// check if song in music store
			if (store.isInStore(songName)) {
				songs.add(new Song(songName));
			}
		}
		
		public void addAlbumToLibrary(String albumName) {
			for (Album album : store.getAlbums()) {
				if(album.getTitle().equals(albumName)) {
					albums.add(album);
				}
			}
		}
		
		public ArrayList<Song> getSongs () {
			return new ArrayList<Song>(songs); // copy list to avoid reference
		}
		
		public ArrayList<Album> getAlbums () {
			return new ArrayList<Album>(albums); // copy list to avoid reference
		}

		public ArrayList<Playlist> getPlaylists() {
			return new ArrayList<Playlist>(playlists); // copy list to avoid reference
		}
		
		public MusicStore getStore() {
			return store;
		}

		public String creatPlaylist(String name) {
			playlists.add(new Playlist(name));
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
					for (Song song : songs) {
						list += song.toString(); }
					break;
				case "artists":
					for (Song song : songs) {
						list += song.getArtist() + "\n"; }
					break;
				case "albums": 
					for (Album album : albums) {
						list += album.getTitle() + "\n"; }
					break;
				case "playlists":
					for (Playlist playlist : playlists) {
						list += playlist.getName() + "\n"; }
					break;
				case "favorites":
					for (Song song : songs) {
						if(song.getFavStatus()) 
							list += song.toString() + "\n"; }
					break;
			}
			return list;
		}
	}
