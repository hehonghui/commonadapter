package simple.com.demo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simple.commonadapter.RecyclerAdapter;
import com.simple.commonadapter.viewholders.RecyclerViewHolder;

import java.util.List;


/**
 * Created by mrsimple on 26/9/15.
 */
public class FeedRecyclerAdapter extends RecyclerAdapter<String, FeedRecyclerAdapter.FeedRecyclerViewHolder> {

    public FeedRecyclerAdapter(List<String> datas) {
        addItems( datas );
    }

    @Override
    protected void bindDataToItemView(FeedRecyclerViewHolder viewHolder, int position, String item) {
        viewHolder.textView.setText(item);
    }


    @Override
    public int getItemViewType(int position) {
        return position % 5 == 0 ? 2 : 1;
    }

    @Override
    protected int getItemLayout(int type) {
        if ( type == 2 ) {
            return  R.layout.list_item_type_2 ;
        }
        return R.layout.list_item_type_1;
    }

    @Override
    protected FeedRecyclerViewHolder newViewHolder(View itemView) {
        return new FeedRecyclerViewHolder(itemView);
    }

    /**
     * Feed ViewHolder
     */
    public static class FeedRecyclerViewHolder extends RecyclerViewHolder {
        public ImageView imageView;
        public TextView textView;

        public FeedRecyclerViewHolder(View itemView) {
            super(itemView);

            imageView = findViewById(R.id.imageview);
            textView = findViewById(R.id.textview);
        }
    }

}
