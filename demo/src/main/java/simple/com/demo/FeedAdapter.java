package simple.com.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrsimple on 28/9/15.
 */
public class FeedAdapter extends BaseAdapter {


    /**
     * 数据集
     */
    protected List<String> mDataSet = new ArrayList<String>();

    public FeedAdapter(List<String> datas) {
        mDataSet = datas;
    }


    @Override
    public String getItem(int position) {
        return mDataSet.get(position);
    }


    @Override
    public int getCount() {
        return mDataSet.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeedViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_type_1, parent, false);
            viewHolder = new FeedViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FeedViewHolder) convertView.getTag();
        }
        // 设置数据
        viewHolder.textView.setText( getItem(position));
        return convertView;
    }

    /**
     * Feed ViewHolder
     */
    public static class FeedViewHolder {
        public ImageView imageView;
        public TextView textView;

        public FeedViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
