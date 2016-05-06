package yumic.diverbob.love.multimediatest2.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import yumic.diverbob.love.multimediatest2.Adapter.MusicBrowserAdapter;
import yumic.diverbob.love.multimediatest2.Providers.MusicProvider;
import yumic.diverbob.love.multimediatest2.R;

/**
 * Created by Oathkeeper on 2016/3/2.
 */
public class MusicBrowserActivity extends Activity{
    private int[] _ids;//存放音乐文件的id数组
    private String[] _titles;//存放音乐文件的标题数组
    private String[] _path;//存放音乐文件的路径

    private RecyclerView recyclerView;
    private MusicProvider musicProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_list);
        musicProvider = new MusicProvider(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置布局管理器
        //new一个布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //用布局管理器设置布局方向
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //将布局管理器应用到recyclerView中
        recyclerView.setLayoutManager(linearLayoutManager);


        MusicBrowserAdapter musicBrowserAdapter = new MusicBrowserAdapter(this);
        musicBrowserAdapter.setMusicList(musicProvider.getAllList());
        recyclerView.setAdapter(musicBrowserAdapter);

        //点击开始扫描，扫描所有音乐文件
        //扫描完成后显示在ListView中
        //右上角还有编辑播放列表，点击后进入编辑播放列表模式

    }
}
