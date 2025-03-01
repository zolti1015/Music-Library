import java.util.ArrayList;
import java.io.IOException;


public class LibraryModel {
	
	private ArrayList<Song> songs;
	private ArrayList<Album> albums;
	private ArrayList<Playlist> playlists;
	
	private final MusicStore store;
	
	public LibraryModel () throws IOException {
		this.store = new MusicStore();
		store.readInAlbumInfo(); // build music store to reference
		this.songs = new ArrayList<>();
		this.albums = new ArrayList<>();
		this.playlists = new ArrayList<>();
	}
	
	   // get song info by title or artist
		public String getSongInfo(String titleOrArtist) {
			String endInfo = "";
			for (Album album : store.getAlbums()) 
			{
				for (Song song : album.getSongs())
			    {
					if (song.getArtist().equals(titleOrArtist) ||
						song.getTitle().equals(titleOrArtist) ) 
					{
							endInfo += song.toString() +
									"\nAlbum: " + album.getTitle();
				    }
			    }
		    }
		if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
		endInfo += "\n";
		return endInfo; 
		}
		
		// get album info and list of songs by title or Artist
		public String getAlbumInfo(String titleOrArtist) {
			String endInfo = "";
			for (Album album : albums) {
				if (album.getTitle().equals(titleOrArtist) ||
					album.getArtist().equals(titleOrArtist)) {
					endInfo += album.toString(); // printing here, so if multiple results loop will continue to search
				}
			}
			if (endInfo.equals("")) endInfo += "Searched for Data is not in the database.";
			endInfo += "\n";
			return endInfo; 
		}
		
		
		// find playlist by provided name and print song details
		public String searchForPlaylist(String playlistName) {
			for (Playlist playlist : playlists) {
				if(playlist.getName().equals(playlistName)) {
					return playlist.toString();
				}
			}
		return "Playlist doesn't exist";
		}
		
		public void addSongToLibrary(String songName) { 
			for(Song song : store.getSongs()) {
				if (store.isInStore(song.getTitle())) {
					songs.add(song);
			}
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
		
	public void createNewPlaylist(String name) {
		playlists.add(new Playlist(name));
	}
	
	public void addSongToPlaylist(String playlistName, String songName) {
		 for (Playlist playlist : playlists) {
			   if(playlist.getName().equals(playlistName)) {
				   for (Song song : songs) {
					   if(song.getTitle().equals(songName)) {
						   playlist.addSong(songName, song.getArtist());
					   }
				   }
			   }
		   }
	}
	
	public void removeSongFromPlaylist(String playlistName, String songName) {
		for (Playlist playlist : playlists) {
			   if(playlist.getName().equals(playlistName)) {
				   for (Song song : songs) {
					   if(song.getTitle().equals(songName)) {
						   playlist.removeSong(songName);
					   }
}
			   }
		}
	}
	
	public void markSongAsFavorite(String name) {
		for (Song song : songs) {
			if (song.getTitle().equals(name)) {
				song.makeFavorite();
			}
		}
	}
	
	public void rateSong(String name, int rating) {
		for (Song song : songs) {
			if (song.getTitle().equals(name)) {
				song.rateSong(rating);
			}
		}
	}
}
