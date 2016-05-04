package yumic.diverbob.love.multimediatest2;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private MyExpandableListViewAdapter adapter;
    private Toolbar toolbar;

    private Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button buttonConfirm = (Button) contentView.findViewById(R.id.buttonConfirm);
        Button buttonCancel = (Button) contentView.findViewById(R.id.buttonCancel);
        EditText editText = (EditText) contentView.findViewById(R.id.editText);


        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        expandableListView.setAdapter(adapter);
        //默认展开所有
        int groupCount = expandableListView.getCount();
        for (int i=0; i<groupCount; i++) {
            expandableListView.expandGroup(i);
        };
       // expandableListView.setGroupIndicator(null); //去掉ExpandableListView的默认箭头
       // expandableListView
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}