package yumic.diverbob.love.multimediatest2;


import yumic.diverbob.love.multimediatest2.Utils.LogHelper;

/**
 * Utility class to get a list of MusicTrack's based on a server-side JSON
 * configuration.
 */
public class Music {

    private static final String TAG = LogHelper.makeLogTag(Music.class);

    private String title;
    private String album;
    private String artist;
    private String source;
    private String image;
    //歌曲时长
    private String duration;


    public Music( String title,String artist,String album) {
        this.album = album;
        this.artist = artist;
        this.title = title;
    }

    public Music() {

    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
