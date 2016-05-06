package yumic.diverbob.love.multimediatest2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yumic.diverbob.love.multimediatest2.Activity.MusicPlayerActivity;
import yumic.diverbob.love.multimediatest2.Entities.Music;
import yumic.diverbob.love.multimediatest2.R;
import yumic.diverbob.love.multimediatest2.Utils.LogHelper;


/**
 * Created by Oathkeeper on 2016/3/2.
 */
public class MusicBrowserAdapter extends RecyclerView.Adapter<MusicBrowserAdapter.ViewHolder> {
    private static final String TAG = LogHelper.makeLogTag(MusicBrowserAdapter.class);

    private static ArrayList<Music> musicList;

    private CheckBox mSolvedCheckBox;

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    public MusicBrowserAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
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
        holder.checkBox.setChecked(musicList.get(position).isChecked());
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

    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewArtist;
        TextView textViewAlbum;
        CheckBox checkBox;
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
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);

        }


    }
}
