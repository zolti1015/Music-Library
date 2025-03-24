
public class Song {
	
	private final String title;
	private final String artist;
	private final String albumOn;
	private final String genre;
	public enum Rating {ONE, TWO, THREE, FOUR, FIVE}
	private Rating rating;
	private boolean favorite;
	
	public Song(String title, String artist, String albumOn, String genre) {
		this.title = title;
		this.artist = artist;
		this.albumOn = albumOn;
		this.favorite = false;
		this.genre = genre;
	}
	
	public void rateSong(int ratingOneToFive) {
		Rating rating = null;
		switch (ratingOneToFive) {
			case 1: 
				rating = Rating.ONE;
				break;
			case 2: 
				rating = Rating.TWO;
				break;
			case 3: 
				rating = Rating.THREE;
				break;
			case 4: 
				rating = Rating.FOUR;
				break;
			case 5: 
				rating = Rating.FIVE;
				favorite = true; // 5 star rating makes song automatic favorite
			default:
	                throw new IllegalArgumentException("Invalid rating value.");
		}
		this.rating = rating;
		
	}
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbumOn() {
		return albumOn;
	}
	public String toString() {
		return String.format("\nTitle: %s by %s, On the Album: %s \n", title, artist, albumOn);
	}
	
	public void makeFavorite() {
		this.favorite = true;
	}
	
	public boolean getFavStatus() {
		return this.favorite;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public Rating getRating() {
		return this.rating;
	}
}
