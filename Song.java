
public class Song {
	
	private String title;
	private String artist;
	public enum Rating {ONE, TWO, THREE, FOUR, FIVE}
	private Rating rating;
	private boolean favorite;
	
	public Song(String title) {
		this.title = title;
	}
	
	public void rateSong(Rating rating) {
		// 5 star rating makes song automatic favorite
		if (rating == Rating.FIVE) favorite = true;
		this.rating = rating;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String toString() {
		
		return "Title: " + title + 
				"\nArtist: " + artist;
	}
	

}
