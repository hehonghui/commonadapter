package com.simple.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple.commonadapter.viewholders.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于RecyclerView的Adapter
 *
 * Created by mrsimple on 25/9/15.
 */
public abstract class RecyclerAdapter<D>
        extends RecyclerView.Adapter<RecyclerViewHolder> {
    /**
     * 数据集
     */
    protected final List<D> mDataSet = new ArrayList<>();
    /**
     * 单击事件
     */
    protected OnItemClickListener mOnItemClickListener;
    /**
     * Item Layout的资源id
     */
    private int mItemLayoutId;

    /**
     *
     * @param layoutId 布局id
     */
    public RecyclerAdapter(int layoutId) {
        mItemLayoutId = layoutId ;
    }

    /**
     *
     * @param layoutId 布局id
     * @param datas 数据集
     */
    public RecyclerAdapter(int layoutId, List<D> datas) {
        mItemLayoutId = layoutId ;
        addItems( datas );
    }

    /**
     * 追加单个数据
     *
     * @param item
     */
    public void addItem(D item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }


    /**
     * 追加数据集
     *
     * @param items
     */
    public void addItems(List<D> items) {
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 添加单个数据到列表头部
     *
     * @param item
     */
    public void addItemToHead(D item) {
        mDataSet.add(0, item);
        notifyDataSetChanged();
    }

    /**
     * 添加数据集到列表头部
     *
     * @param items
     */
    public void addItemsToHead(List<D> items) {
        mDataSet.addAll(0, items);
        notifyDataSetChanged();
    }

    /**
     * 移除某个数据
     *
     * @param position
     */
    public void remove(int position) {
        mDataSet.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 移除某个数据项
     *
     * @param item
     */
    public void remove(D item) {
        mDataSet.remove(item);
        notifyDataSetChanged();
    }

    /**
     * 获取指定位置的数据项
     *
     * @param position
     * @return
     */
    public D getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final D item = getItem(position);
        // 绑定数据
        onBindData(holder, position, item);
        // 设置单击事件
        setupItemClickListener(holder, position);
    }

    /**
     * 解析布局资源
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int viewType) {
        int itemLayout = getItemLayout(viewType);
        Context context = viewGroup.getContext() ;
        return LayoutInflater.from(context).inflate(itemLayout,
                viewGroup, false);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new RecyclerViewHolder(inflateItemView(parent, viewType)) ;
    }

    /**
     * 根据Type返回布局资源
     *
     * @param type
     * @return
     */
    protected  int getItemLayout(int type) {
        return  mItemLayoutId ;
    }


    /**
     * 绑定数据到Item View上
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    protected abstract void onBindData(RecyclerViewHolder viewHolder, int position, D item);


    /**
     * @param viewHolder
     * @param position
     */
    protected void setupItemClickListener(RecyclerViewHolder viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    /**
     * 点击事件Listener
     */
    public static interface OnItemClickListener {
        void onItemClick(int position);
    }

}
