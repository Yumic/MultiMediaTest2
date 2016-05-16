package yumic.diverbob.love.multimediatest2.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

import yumic.diverbob.love.multimediatest2.Entities.Video;
import yumic.diverbob.love.multimediatest2.R;
import yumic.diverbob.love.multimediatest2.Utils.LogHelper;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final String TAG = LogHelper.makeLogTag(VideoPlayerActivity.class);

    private VideoView video;
    private ArrayList<Video> videoList;

    private int listNum;
    private int nowNum;
    private int listCount;

    /** Called when the activity is firstcreated. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);


        video=(VideoView) findViewById(R.id.videoView);

        Intent intent= getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            videoList  = intent.getParcelableArrayListExtra("VideoPlayList");
            listNum = intent.getIntExtra("ListNumber",0);
            Log.d(TAG,"listNum:"+listNum);

            nowNum = listNum;
            listCount = videoList.size();
        }
        listCount = videoList.size();
        File file=new File(videoList.get(nowNum).getPath());
        MediaController mc=new MediaController(this);       // 创建一个MediaController对象
    //    mc.setPrevNextListeners(new P);
        if(file.exists()){
            video.setVideoPath(file.getAbsolutePath());
            video.setMediaController(mc);       // 将VideoView与MediaController关联起来
            video.requestFocus();       // 设置VideoView获取焦点
            try {
                video.start();      // 播放视频
            }catch(Exception e) {
                e.printStackTrace();
            }

            // 设置VideoView的Completion事件监听器
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(VideoPlayerActivity.this, "视频播放完毕！", Toast.LENGTH_SHORT).show();
                    //改为播放下一个视频
                    video.stopPlayback();
                    if(++nowNum>=listCount) nowNum-=listCount;
                    File file=new File(videoList.get(nowNum).getPath());
                    video.setVideoPath(file.getAbsolutePath());
                    video.requestFocus();       // 设置VideoView获取焦点
                    video.start();
                }
            });

            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                }
            });

        }else{
            Toast.makeText(this, "要播放的视频文件不存在", Toast.LENGTH_SHORT).show();
        }
    }
}
