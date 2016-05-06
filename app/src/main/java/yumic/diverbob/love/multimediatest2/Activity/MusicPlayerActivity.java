package yumic.diverbob.love.multimediatest2.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import yumic.diverbob.love.multimediatest2.Entities.Music;
import yumic.diverbob.love.multimediatest2.R;
import yumic.diverbob.love.multimediatest2.Utils.LogHelper;

public class MusicPlayerActivity extends AppCompatActivity {

    private static final String TAG = LogHelper.makeLogTag(MusicPlayerActivity.class);

    private TextView textViewTitle;
    private TextView textViewArtist;
    private TextView textViewAlbum;
    private SeekBar seekBar;
    private Button buttonPre;
    private Button buttonNext;
    private Button buttonPlayPause;
    private ArrayList<Music> musicList;
    private int listNum;
    private int nowNum;
    private int listCount;
    private MediaPlayer mediaPlayer ;

    private boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Intent intent= getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            musicList  = intent.getParcelableArrayListExtra("MusicPlayList");
            listNum = intent.getIntExtra("ListNumber",0);
            Log.d(TAG,"listNum:"+listNum);


            nowNum = listNum;
            listCount = musicList.size();
        }
        playMusic(musicList.get(nowNum).getPath());
        initViews();



    }

    private void playMusic(String path){

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
            isPlaying = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateViews(){
        if(musicList!=null){
            Music nowMusic=musicList.get(nowNum);
            textViewAlbum.setText(nowMusic.getAlbum());
            textViewArtist.setText(nowMusic.getArtist());
            textViewTitle.setText(nowMusic.getTitle());
        }
    }

    private void initViews() {
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewArtist= (TextView) findViewById(R.id.textViewArtist);
        textViewAlbum= (TextView) findViewById(R.id.textViewAlbum);

        updateViews();


        seekBar= (SeekBar) findViewById(R.id.seekBar);
        buttonPre= (Button) findViewById(R.id.buttonPre);
        buttonNext= (Button) findViewById(R.id.buttonNext);
        buttonPlayPause= (Button) findViewById(R.id.buttonPlayPause);


        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying){
                    mediaPlayer.pause();
                    isPlaying = false;
                }else{
                    mediaPlayer.start();
                    isPlaying = true;
                }
            }


        });
        buttonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                isPlaying = false;
                nowNum=nowNum-1;
                if(nowNum<0) nowNum =nowNum+listCount;
                playMusic(musicList.get(nowNum).getPath());
                updateViews();

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                isPlaying = false;
                nowNum=nowNum+1;
                if(nowNum>=listCount) nowNum =nowNum-listCount;
                playMusic(musicList.get(nowNum).getPath());
                updateViews();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
