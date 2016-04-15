package simple.com.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.simple.commonadapter.RecyclerAdapter;
import com.simple.commonadapter.viewholders.RecyclerViewHolder;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import simple.com.demo.widgets.MultiImageView;


public class MultiImageActivityListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_list);
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
        ListView list = (ListView) findViewById(R.id.MyListView);
        mImages = mockImages();
        list.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 150;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView= LayoutInflater.from(MultiImageActivityListView.this).inflate(R.layout.list_item_type_3,null);
            }
            MultiImageView imageView = (MultiImageView) convertView.findViewById(R.id.multi);
            imageView.setImages(mImages.subList(position%5 * MultiImageView.ImageNumber, (position%5 + 1) * MultiImageView.ImageNumber));
            imageView.setImageClickListener(new MultiImageView.OnImageClickListener() {
                @Override
                public void onClick(int position) {
                    Toast.makeText(getApplicationContext(), "click image " + position, Toast.LENGTH_SHORT).show();
                }
            });
//          TextView t=  new TextView(MultiImageActivityListView.this);
//            t.setText("111111111111111");
//            return t;
            return convertView;
        }
    }
}
