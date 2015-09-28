package simple.com.demo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simple.commonadapter.ListViewAdapter;
import com.simple.commonadapter.viewholders.ListViewHolder;

import java.util.List;


/**
 * Created by mrsimple on 26/9/15.
 */
public class FeedListAdapter extends ListViewAdapter<String, FeedListAdapter.FeedViewHolder> {

    public FeedListAdapter(List<String> datas) {
        addItems(datas);
    }

    @Override
    protected void bindDataToItemView(FeedViewHolder viewHolder, int position, String item) {
        viewHolder.textView.setText(item);
    }

    @Override
    protected int getItemLayout(int type) {
        return R.layout.list_item_type_1;
    }

    @Override
    protected FeedViewHolder newViewHolder(View itemView) {
        return new FeedViewHolder(itemView);
    }

    /**
     * Feed ViewHolder
     */
    public static class FeedViewHolder extends ListViewHolder {
        public ImageView imageView;
        public TextView textView;

        public FeedViewHolder(View itemView) {
            super(itemView);

            imageView = findViewById(R.id.imageview);
            textView = findViewById(R.id.textview);
        }
    }

}
