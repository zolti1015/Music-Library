public class Song {
    private String title;
    private String artist;
    private String albumTitle;

    public Song(String title, String artist, String albumTitle) {
        this.title = title;
        this.artist = artist;
        this.albumTitle = albumTitle;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbumTitle() { return albumTitle; }

    @Override
    public String toString() {
        return title + " - " + artist + " (Album: " + albumTitle + ")";
    }
}
