package yumic.diverbob.love.multimediatest2.Activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import yumic.diverbob.love.multimediatest2.ListDao;
import yumic.diverbob.love.multimediatest2.Adapter.MyExpandableListViewAdapter;
import yumic.diverbob.love.multimediatest2.R;
import yumic.diverbob.love.multimediatest2.Utils.PermissionUtil;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="MainActivity.java";
        private ExpandableListView expandableListView;
    private MyExpandableListViewAdapter adapter;
    private Toolbar toolbar;

    private Context context = null;
    private ListDao listDao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.checkAndRequestPermissions(this);
        context = this;
        listDao = new ListDao(context);


        initExpandableList();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出新建列表的popupwindow
                showPopupWindow(view);
            }
        });
    }

    private void showPopupWindow(View view){
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_new_list,null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
      //  popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击其他地方消失
       // popupWindow.setListen
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));//设置背景颜色

        final Spinner spinner = (Spinner) contentView.findViewById(R.id.spinner);

        Button buttonConfirm = (Button) contentView.findViewById(R.id.buttonConfirm);
        Button buttonCancel = (Button) contentView.findViewById(R.id.buttonCancel);
        final EditText editText = (EditText) contentView.findViewById(R.id.editText);


        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String listName = editText.getText().toString();
                if(listName.equals("")){
                }else{
                    String category = (String) spinner.getSelectedItem();
                    listDao.insertList(category,listName);//向数据库插入
                    Log.d(TAG,"文件名——"+listName+"类别——"+category);

                    initData();
                    adapter.notifyDataSetChanged();
                    popupWindow.dismiss();
                }

            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });



        popupWindow.showAsDropDown(toolbar, Gravity.CENTER, Gravity.CENTER);

    }

    private void initExpandableList() {
        expandableListView = (ExpandableListView) findViewById(R.id.expendlist);
        adapter = new MyExpandableListViewAdapter(context);
        initData();
        expandableListView.setAdapter(adapter);
        //默认展开所有
        int groupCount = expandableListView.getCount();
        for (int i=0; i<groupCount; i++) {
            expandableListView.expandGroup(i);
        };

}

    void initData(){
        adapter.setItemToAdd(listDao.getList("视频"),0);
        adapter.setItemToAdd(listDao.getList("音频"),1);
        adapter.setItemToAdd(listDao.getList("图片"),2);

        adapter.initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_browse_video) {

            Intent intent = new Intent(MainActivity.this,VideoBrowserActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_browse_music) {
            //传递列表 读取数据库
                    Intent intent = new Intent(MainActivity.this,MusicBrowserActivity.class);
            ArrayList<String> allListName =  new ArrayList(listDao.getList("音频"));
            String[] arrString = (String[])allListName.toArray(new String[]{}) ;


        //    listDao.getMediaList("音频","rqye");

            intent.putExtra("allListName",arrString);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_browse_photo) {
            Intent intent = new Intent(MainActivity.this,GalleryActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
