//package yumic.diverbob.love.multimediatest2;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.ImageView;
//
///**
// * Created by Oathkeeper on 2016/5/5.
// */
//public class ImageGalleryAdapter extends
//        RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder> {
//
//    private RecyclerView mRecyclerView;
//    private ArrayList<GalleryImage> mImages;
//    private ArrayList<GalleryImage> mSelected;
//
//    public ImageGalleryAdapter(RecyclerView recyclerView, ArrayList<GalleryImage> images) {
//        mRecyclerView = recyclerView;
//        mImages = images;
//        mSelected = new ArrayList<GalleryImage>(UploadOpusActivity.MAX_COUNT);
//    }
//
//    public ArrayList<GalleryImage> getSelected() {
//        return mSelected;
//    }
//
//    @Override
//    public int getItemCount() {
//        return mImages.size();
//    }
//
//    @Override
//    public void onBindViewHolder(final ImageViewHolder holder, int position) {
//        final GalleryImage image = mImages.get(holder.getLayoutPosition());
//        String uri = image.getUri();
//        if(!uri.startsWith("file://")) uri = "file://" + image.getUri();
//        ImageLoaderUtils.getInstance().displayImage(uri, holder.image);
//        holder.item.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isChecked = !image.isChecked();
//                // 如果是选择状态
//                if(isChecked) {
//                    // 则需要判断当前选择了几个
//                    if(mSelected.size() >= UploadOpusActivity.MAX_COUNT) return;
//                    // 添加到选择的列表中
//                    mSelected.add(image);
//                }else {
//                    // 取消选择
//                    mSelected.remove(image);
//                }
//
//                // 纯粹为了显示
//                holder.cb.setChecked(isChecked);
//                image.setChecked(isChecked);
//            }
//        });
//
//        holder.cb.setChecked(image.isChecked());
//    }
//
//    @Override
//    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int position) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.gallery_item, parent, false);
//        return new ImageViewHolder(view);
//    }
//
//    class ImageViewHolder extends RecyclerView.ViewHolder {
//        View item;
//        ImageView image;
//        CheckBox cb;
//        public ImageViewHolder(View view) {
//            super(view);
//            item = view;
//            image = (ImageView) view.findViewById(R.id.iv_gallery_item_image);
//            cb = (CheckBox) view.findViewById(R.id.cb_gallery_item_check);
//
//            int size = mRecyclerView.getMeasuredHeight() / 4;
//            ViewGroup.LayoutParams p = image.getLayoutParams();
//            p.width = size;
//            p.height = size;
//        }
//    }
//}
