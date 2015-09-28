
package com.simple.commonadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.simple.commonadapter.viewholders.ListViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mrsimple on 25/9/15.
 */
public abstract class ListViewAdapter<D, VH extends ListViewHolder> extends BaseAdapter {

    /**
     * 数据集
     */
    protected final List<D> mDataSet = new ArrayList<>();

    /**
     * @param item
     */
    public void addItem(D item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }

    /**
     * @param items
     */
    public void addItems(List<D> items) {
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * @param item
     */
    public void addItemToHead(D item) {
        mDataSet.add(0, item);
        notifyDataSetChanged();
    }

    /**
     * @param items
     */
    public void addItemsToHead(List<D> items) {
        mDataSet.addAll(0, items);
        notifyDataSetChanged();
    }

    /**
     * @param position
     */
    public void remove(int position) {
        mDataSet.remove(position);
        notifyDataSetChanged();
    }

    /**
     * @param item
     */
    public void remove(D item) {
        mDataSet.remove(item);
        notifyDataSetChanged();
    }

    /**
     * @return
     */
    @Override
    public int getCount() {
        return mDataSet.size();
    }

    /**
     * @param position
     * @return
     */
    @Override
    public D getItem(int position) {
        return mDataSet.get(position);
    }

    /**
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 根据View Type返回布局资源
     *
     * @param type
     * @return
     */
    protected abstract int getItemLayout(int type);

    /**
     * 解析布局资源
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int viewType) {
        int itemLayout = getItemLayout(viewType);
        return LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout,
                viewGroup, false);
    }

    /**
     * 封装getView逻辑,将根据viewType获取布局资源、解析布局资源、创建ViewHolder等逻辑封装起来,简化使用流程
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH viewHolder = null;
        if (convertView == null) {
            final int viewType = getItemViewType(position);
            convertView = inflateItemView(parent, viewType);
            viewHolder = newViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (VH) convertView.getTag();
        }
        // 绑定数据
        bindDataToItemView(viewHolder, position, getItem(position));
        return convertView;
    }

    /**
     * 创建具体的ViewHolder
     *
     * @param itemView ItemView根布局
     * @return
     */
    protected abstract VH newViewHolder(View itemView);

    /**
     * 绑定数据到Item View上
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    protected abstract void bindDataToItemView(VH viewHolder, int position, D item);

}
