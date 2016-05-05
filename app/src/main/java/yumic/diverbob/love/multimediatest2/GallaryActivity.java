//package yumic.diverbob.love.multimediatest2;
//
//import android.content.Intent;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
///**
// * Created by Oathkeeper on 2016/5/5.
// */
//public class GallaryActivity extends Activity {
//
//    private RecyclerView mRecyclerView;
//    private GridLayoutManager mLayoutManager;
//    private ArrayList<GalleryImage> mImages; // 所有图片
//    private ImageGalleryAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.gallery_layout);
//        setupViews();
//
//        // 获取图片
//        new OpusBiz().getImages(new OnImagesListener() {
//            @Override
//            public void onResult(ArrayList<GalleryImage> images) {
//                if(images == null || images.isEmpty()) return;
//                mImages = images;
//                setAdapter();
//            }
//        });
//    }
//
//    @Override
//    protected void setupViews() {
//        super.setupViews();
//        setNormalTitle(R.string.select_image_text);
//        mTitleBar.setRightText(R.string.ok);
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.rv_gallery);
//        mLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.HORIZONTAL, false);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//    }
//
//    private void setAdapter() {
//        mAdapter = new ImageGalleryAdapter(mRecyclerView, mImages);
//        mRecyclerView.setAdapter(mAdapter);
//    }
//
//    @Override
//    protected void onRightClick() {
//        ArrayList<GalleryImage> images = mAdapter.getSelected();
//
//        Intent intent = getIntent();
//        intent.putExtra(Constants.EXTRA_GALLERY_IMAGE, images);
//        setResult(RESULT_OK, intent);
//        onLeftClick();
//    }
//}