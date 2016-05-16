package yumic.diverbob.love.multimediatest2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import yumic.diverbob.love.multimediatest2.Activities.VideoPlayerActivity;
import yumic.diverbob.love.multimediatest2.Entities.Video;
import yumic.diverbob.love.multimediatest2.R;
import yumic.diverbob.love.multimediatest2.Utils.LogHelper;


/**
 * Created by Oathkeeper on 2016/3/2.
 */
public class VideoBrowserAdapter extends RecyclerView.Adapter<VideoBrowserAdapter.ViewHolder> {
    private static final String TAG = LogHelper.makeLogTag(VideoBrowserAdapter.class);

    private static ArrayList<Video> videoList;

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    public VideoBrowserAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_video_list, parent, false), mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //这里做一些类似holder.textView.setText之类的工作
        holder.textViewTitle.setText(videoList.get(position).getTitle());
        holder.textViewDuration.setText(""+videoList.get(position).getDuration());
        Log.d(TAG,"Name:"+ videoList.get(position).getTitle()+"   Path:"+ videoList.get(position).getPath());
    }



    @Override
    public int getItemCount() {
        return  videoList == null ? 0 : videoList.size();
    }

    public void setData(Video video) {
        videoList.add(video);
        notifyDataSetChanged();
    }

    public void clearAll() {
        videoList.clear();
    }

    public void setVideoList(ArrayList<Video> videoList) {
        this.videoList = videoList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewDuration;
        //这里声明控件
        public ViewHolder(final View itemView, final Context mContext) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG,"itemOnclick"+getAdapterPosition() );

                    //进入播放音乐的Activity
                    Intent intent = new Intent(mContext, VideoPlayerActivity.class);
                    intent.putExtra("ListNumber",getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("VideoPlayList", videoList);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);

                }
            });



            //这里进行findViewById的工作
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewDuration = (TextView) itemView.findViewById(R.id.textViewDuration);

        }


    }
}
