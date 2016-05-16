package yumic.diverbob.love.multimediatest2.Providers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import yumic.diverbob.love.multimediatest2.Entities.Music;
import yumic.diverbob.love.multimediatest2.Utils.LogHelper;

/**
 * Created by Oathkeeper on 2016/3/2.
 */
public class MusicProvider implements AbstructProvider {
    private static final String TAG = LogHelper.makeLogTag(MusicProvider.class);

    private Context context;

    public MusicProvider(Context context) {
        this.context = context;
    }



    @Override
    public ArrayList getAllList() {
        ArrayList<Music> musicList = new ArrayList<Music>();
        ContentResolver contentResolver = context.getContentResolver();
        if(contentResolver != null){
            Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,
                    null,//查询条件
                    null,//查询条件中用到的数据
                    MediaStore.Audio.Media.DEFAULT_SORT_ORDER);// 查询结果的排序方式
            if(null == cursor) {
                return null;
            }
            if(cursor.moveToFirst()){
                String title;
                String artist;
                String album;
                String path;
                int count = 0;
                do{
                    title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    Log.d(TAG,title+artist+album);
                    Music music = new Music(title,artist,album,path);
                    musicList.add(music);
                    count++;
                }while(count<10&&cursor.moveToNext());
            }
        }

        return musicList;
    }

    public ArrayList getSomeList(String filePath ) {
        ArrayList<Music> musicList = new ArrayList<Music>();
        ContentResolver contentResolver = context.getContentResolver();
        if(contentResolver != null){
            Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,
                    MediaStore.Audio.Media.DATA+"=? ",//查询条件
                    new String[]{filePath},//查询条件中用到的数据
                    MediaStore.Audio.Media.DEFAULT_SORT_ORDER);// 查询结果的排序方式
            if(null == cursor) {
                return null;
            }
            if(cursor.moveToFirst()){
                String title;
                String artist;
                String album;
                String path;
                int count = 0;
                do{
                    title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    Log.d(TAG,title+artist+album);
                    Music music = new Music(title,artist,album,path);
                    musicList.add(music);
                    count++;
                }while(count<10&&cursor.moveToNext());
            }
        }

        return musicList;
    }
}
