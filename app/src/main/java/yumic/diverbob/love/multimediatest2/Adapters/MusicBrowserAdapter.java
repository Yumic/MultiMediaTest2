package yumic.diverbob.love.multimediatest2.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import yumic.diverbob.love.multimediatest2.Activities.MusicPlayerActivity;
import yumic.diverbob.love.multimediatest2.Entities.Music;
import yumic.diverbob.love.multimediatest2.ListDao;
import yumic.diverbob.love.multimediatest2.R;
import yumic.diverbob.love.multimediatest2.Utils.LogHelper;


/**
 * Created by Oathkeeper on 2016/3/2.
 */
public class MusicBrowserAdapter extends RecyclerView.Adapter<MusicBrowserAdapter.ViewHolder> {
    private static final String TAG = LogHelper.makeLogTag(MusicBrowserAdapter.class);

    private static ArrayList<Music> musicList;
    private static String[] allListName;
    private static ListDao listDao;

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    public MusicBrowserAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        listDao = new ListDao(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_music_list, parent, false), mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //这里做一些类似holder.textView.setText之类的工作
        holder.textViewTitle.setText(musicList.get(position).getTitle());
        holder.textViewAlbum.setText(musicList.get(position).getAlbum());
        holder.textViewArtist.setText(musicList.get(position).getArtist());

        Log.d(TAG,"Name:"+musicList.get(position).getTitle()+"   Path:"+musicList.get(position).getPath());
    }



    @Override
    public int getItemCount() {
        return  musicList == null ? 0 : musicList.size();
    }

    public void setData(Music music) {
        musicList.add(music);
        notifyDataSetChanged();
    }

    public void clearAll() {
        musicList.clear();
    }

    public void setAllListName(String[] allListName) {
        this.allListName = allListName;
    }

    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewArtist;
        TextView textViewAlbum;
        Button buttonAdd;
        //这里声明控件
        public ViewHolder(final View itemView, final Context mContext) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG,"itemOnclick"+getAdapterPosition() );

                    //进入播放音乐的Activity
                    Intent intent = new Intent(mContext, MusicPlayerActivity.class);
                    intent.putExtra("ListNumber",getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("MusicPlayList",musicList);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);

                }
            });



            //这里进行findViewById的工作
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewArtist = (TextView) itemView.findViewById(R.id.textViewArtist);
            textViewAlbum  = (TextView) itemView.findViewById(R.id.textViewAlbum);
            buttonAdd = (Button) itemView.findViewById(R.id.buttonAdd);

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获得当前该类下的所有列表

                    new AlertDialog.Builder(mContext).setTitle("添加到播放列表").setItems(
                            allListName, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //添加到对应的数据库中
                                    listDao.insertFile(musicList.get(getAdapterPosition()).getPath(),allListName[which]);
                                }
                            }).setNegativeButton(
                           "确定", null).show();
                    //Log.d(TAG,"列表如下："+allListName.toString());
                    //弹出添加至列表的对话框

                }
            });


        }


    }
}
