package yumic.diverbob.love.multimediatest2.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import yumic.diverbob.love.multimediatest2.Providers.AbstructProvider;
import yumic.diverbob.love.multimediatest2.Adapters.VideoBrowserAdapter;
import yumic.diverbob.love.multimediatest2.Entities.Video;
import yumic.diverbob.love.multimediatest2.Providers.VideoProvider;
import yumic.diverbob.love.multimediatest2.R;

/**
 * Created by Oathkeeper on 2016/3/2.
 */
public class VideoBrowserActivity extends Activity{


    private RecyclerView recyclerView;


    private VideoProvider videoProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_list);
        videoProvider = new VideoProvider(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置布局管理器
        //new一个布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //用布局管理器设置布局方向
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //将布局管理器应用到recyclerView中
        recyclerView.setLayoutManager(linearLayoutManager);

        AbstructProvider provider = new VideoProvider(this);
        List<Video> listVideos = provider.getAllList();
        for(Video video:listVideos){
           // Log.d(TAG,"path:"+video.getPath());
        }
        VideoBrowserAdapter videoBrowserAdapter = new VideoBrowserAdapter(this);
        videoBrowserAdapter.setVideoList(videoProvider.getAllList());
        recyclerView.setAdapter(videoBrowserAdapter);

        //点击开始扫描，扫描所有音乐文件
        //扫描完成后显示在ListView中
        //右上角还有编辑播放列表，点击后进入编辑播放列表模式

    }
}
