package yumic.diverbob.love.multimediatest2.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import yumic.diverbob.love.multimediatest2.Adapters.MusicBrowserAdapter;
import yumic.diverbob.love.multimediatest2.Providers.MusicProvider;
import yumic.diverbob.love.multimediatest2.R;
import yumic.diverbob.love.multimediatest2.Utils.LogHelper;

/**
 * Created by Oathkeeper on 2016/3/2.
 */
public class MusicBrowserActivity extends Activity{
    private static final String TAG = LogHelper.makeLogTag(MusicBrowserActivity.class);
    private RecyclerView recyclerView;
    private MusicProvider musicProvider;

    private String[] allListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_list);
        Intent intent= getIntent();
        if(intent != null){

            Bundle bundle = intent.getExtras();
            if(bundle.getStringArrayList("allListName")!=null) allListName  = bundle.getStringArrayList("allListName").toArray(new String[]{});

        }


        musicProvider = new MusicProvider(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置布局管理器
        //new一个布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //用布局管理器设置布局方向
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //将布局管理器应用到recyclerView中
        recyclerView.setLayoutManager(linearLayoutManager);


        MusicBrowserAdapter musicBrowserAdapter = new MusicBrowserAdapter(this);
        musicBrowserAdapter.setMusicList(musicProvider.getAllList());
        musicBrowserAdapter.setAllListName(allListName);
        recyclerView.setAdapter(musicBrowserAdapter);

        //点击开始扫描，扫描所有音乐文件
        //扫描完成后显示在ListView中
        //右上角还有编辑播放列表，点击后进入编辑播放列表模式

    }
}
