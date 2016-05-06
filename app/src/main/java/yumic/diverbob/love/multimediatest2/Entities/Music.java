package yumic.diverbob.love.multimediatest2.Entities;


import android.os.Parcel;
import android.os.Parcelable;

import yumic.diverbob.love.multimediatest2.Utils.LogHelper;

/**
 * Utility class to get a list of MusicTrack's based on a server-side JSON
 * configuration.
 */
public class Music  implements Parcelable {

    private static final String TAG = LogHelper.makeLogTag(Music.class);

    private String title;
    private String album;
    private String artist;
    private String image;
    //歌曲时长
    private String duration;
    private String path;

    private transient  boolean  isChecked = false;


    public Music( String title,String artist,String album,String path) {
        this.album = album;
        this.artist = artist;
        this.title = title;
        this.path = path;
    }

    public Music() {

    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 内容接口描述
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(album);
        dest.writeString(artist);
        dest.writeString(image);
        dest.writeString(duration);
        dest.writeString(path);

    }
    /**
     *  .必须实现Parcelable.Creator接口,否则在获取Person数据的时候，会报错
     *  如下：android.os.BadParcelableException:Parcelable protocol requires a
     *  Parcelable.Creator object called  CREATOR on class com.um.demo.Person
     *  这个接口实现了从Percel容器读取Person数据，并返回Person对象给逻辑层使用
     *  实现Parcelable.Creator接口对象名必须为CREATOR，不如同样会报错上面所提到的错；
     *  在读取Parcel容器里的数据时，必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
     *  反序列化对象
     */

    public static final Parcelable.Creator<Music> CREATOR = new Creator(){

        @Override
        public Music createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            Music music = new Music();
            music.setTitle(source.readString());
            music.setAlbum(source.readString());
            music.setArtist(source.readString());
            music.setImage(source.readString());
            music.setDuration(source.readString());
            music.setPath(source.readString());
            return music;
        }

        @Override
        public Music[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Music[size];
        }
    };

}
