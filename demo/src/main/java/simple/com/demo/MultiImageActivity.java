package simple.com.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.simple.commonadapter.RecyclerAdapter;
import com.simple.commonadapter.viewholders.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import simple.com.demo.widgets.MultiImageView;


public class MultiImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initRecyclerView();
        configUniversalImageLoader();
    }


    /**
     * 初始化UniversalImageLoader
     */
    private void configUniversalImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).
                diskCacheFileCount(500)
                .diskCacheSize(50 * 1024 * 1024).
                        imageDownloader(new BaseImageDownloader(this, 10 * 1000, 20 * 1000))
                .memoryCacheSize((int) Runtime.getRuntime().maxMemory() / 8)
                .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config);
    }


    List<ImageItem> mImages;

    private List<ImageItem> mockImages() {
        List<ImageItem> datas = new ArrayList<>();
        for (int i = 0; i < Images.imageThumbUrls.length / 3; i++) {
            ImageItem imageItem = new ImageItem();
            imageItem.url = Images.imageThumbUrls[i];
            datas.add(imageItem);
        }
        return datas;
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // 线性
        recyclerView.setLayoutManager(new LinearLayoutManager(MultiImageActivity.this));

        mImages = mockImages();
        // 初始化adapter
        final RecyclerAdapter<ImageItem> adapter = new RecyclerAdapter<ImageItem>(R.layout.multi_image_item, mImages) {
            @Override
            protected void onBindData(RecyclerViewHolder viewHolder, int position, ImageItem item) {
                MultiImageView imageView = (MultiImageView) viewHolder.itemView;
                imageView.setImages(mImages.subList(position * 3, (position + 1) * 3));
                imageView.setImageClickListener(new MultiImageView.OnImageClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(getApplicationContext(), "click image " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            protected int getItemLayout(int type) {
                return R.layout.multi_image_item;
            }
        };

        // 设置RecyclerView的点击事件
//        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(MultiImageActivity.this, "Click Recycler : "
//                        + adapter.getItem(position), Toast.LENGTH_SHORT).show();
//            }
//        });
        recyclerView.setAdapter(adapter);
    }
}
