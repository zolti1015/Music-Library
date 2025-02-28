
public class Song {
	
	private final String title;
	private String artist;
	public enum Rating {ONE, TWO, THREE, FOUR, FIVE}
	private Rating rating;
	private boolean favorite;
	
	public Song(String title) {
		this.title = title;
	}
	
	public void rateSong(int ratingOneToFive) {
		Rating rating;
		switch (ratingOneToFive) {
			case 1: 
				rating = Rating.ONE;
			case 2: 
				rating = Rating.TWO;
			case 3: 
				rating = Rating.THREE;
			case 4: 
				rating = Rating.FOUR;
			case 5: 
				rating = Rating.FIVE;
		// 5 star rating makes song automatic favorite
		if (rating == Rating.FIVE) favorite = true;
		this.rating = rating;
		}
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
	
	public void makeFavorite() {
		this.favorite = true;
	}
	
	public boolean getFavStatus() {
		return this.favorite;
	}
	
	public boolean equals(Song song) {
		if (song.getTitle().equals(title) && song.getArtist().equals(artist)) {
			return true;
		}
		return false;
	}
	

}
