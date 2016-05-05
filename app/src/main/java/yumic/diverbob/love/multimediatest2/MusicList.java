package yumic.diverbob.love.multimediatest2;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import yumic.diverbob.love.multimediatest2.Utils.LogHelper;

/**
 * Created by Oathkeeper on 2016/3/2.
 */
public class MusicList {
    private static final String TAG = LogHelper.makeLogTag(MusicList.class);


    public static List<Music> getMusicList(Context context){
        List<Music> musicList = new ArrayList<Music>();
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
                int count = 0;
                do{
                    title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    Log.d(TAG,title+artist+album);
                    Music music = new Music(title,artist,album);
                    musicList.add(music);
                    count++;
                }while(count<10&&cursor.moveToNext());
            }
        }

        return musicList;
    }
}
