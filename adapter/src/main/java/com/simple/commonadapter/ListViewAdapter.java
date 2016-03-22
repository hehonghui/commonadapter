
package com.simple.commonadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.simple.commonadapter.viewholders.GodViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 适用于ListView的 通用 Adapter
 *
 * Created by mrsimple on 25/9/15.
 */
public abstract class ListViewAdapter<D> extends BaseAdapter {
    /**
     * 数据集
     */
    protected final List<D> mDataSet = new ArrayList<>();
    /**
     * Item Layout
     */
    private int mItemLayoutId;

    /**
     *
     * @param layoutId
     */
    public ListViewAdapter(int layoutId) {
        mItemLayoutId = layoutId;
    }

    public ListViewAdapter(int layoutId, List<D> datas) {
        mItemLayoutId = layoutId;
        mDataSet.addAll(datas) ;
    }

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
     * clear all data
     */
    public void clear() {
        mDataSet.clear();
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
    protected int getItemLayout(int type) {
        return mItemLayoutId;
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
        int viewType = getItemViewType( position ) ;
        GodViewHolder viewHolder = GodViewHolder.get(convertView, parent, getItemLayout(viewType));
        // 绑定数据
        onBindData(viewHolder, position, getItem(position));
        return viewHolder.getItemView();
    }

    /**
     * 绑定数据到Item View上
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    protected abstract void onBindData(GodViewHolder viewHolder, int position, D item);

}
