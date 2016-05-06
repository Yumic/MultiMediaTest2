package yumic.diverbob.love.multimediatest2.Activity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import yumic.diverbob.love.multimediatest2.R;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView video;

    /** Called when the activity is firstcreated. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        video=(VideoView) findViewById(R.id.videoView);
        File file=new File("/storage/01A4-1AE0/1.mp4");
        MediaController mc=new MediaController(this);       // 创建一个MediaController对象
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
                }
            });
        }else{
            Toast.makeText(this, "要播放的视频文件不存在", Toast.LENGTH_SHORT).show();
        }
    }
}
