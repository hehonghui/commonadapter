package com.simple.commonadapter.viewholders;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * Created by mrsimple on 29/9/15.
 */
public class GodViewHolder {
    /**
     * ViewHolder实现类,桥接模式适配AbsListView与RecyclerView的二维变化
     */
    ViewHolderImpl mHolderImpl ;


    /**
     * @param itemView
     */
    GodViewHolder(View itemView) {
        mHolderImpl = new ViewHolderImpl( itemView ) ;
    }


    /**
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(int viewId) {
        return mHolderImpl.findViewById(viewId);
    }

    public Context getContext() {
        return  mHolderImpl.mItemView.getContext() ;
    }

    /**
     * 获取GodViewHolder
     *
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static GodViewHolder get(View convertView, ViewGroup parent, int layoutId) {
        GodViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            viewHolder = new GodViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GodViewHolder) convertView.getTag();
        }

        return viewHolder;
    }


    public View getItemView() {
        return mHolderImpl.getItemView();
    }

    public GodViewHolder setText(int viewId, int stringId) {
        mHolderImpl.setText(viewId, stringId);
        return this;
    }

    public GodViewHolder setText(int viewId, String text) {
        mHolderImpl.setText(viewId, text);
        return this;
    }

    public GodViewHolder setTextColor(int viewId, int color) {
        mHolderImpl.setTextColor(viewId, color);
        return this;
    }

    /**
     * @param viewId
     * @param color
     */
    public GodViewHolder setBackgroundColor(int viewId, int color) {
        mHolderImpl.setBackgroundColor(viewId, color);
        return this;
    }


    /**
     * @param viewId
     * @param resId
     */
    public GodViewHolder setBackgroundResource(int viewId, int resId) {
        mHolderImpl.setBackgroundResource(viewId, resId);
        return this;
    }


    /**
     * @param viewId
     * @param drawable
     */
    public GodViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        mHolderImpl.setBackgroundDrawable(viewId, drawable);
        return this;
    }

    /**
     * @param viewId
     * @param drawable
     */
    @TargetApi(16)
    public GodViewHolder setBackground(int viewId, Drawable drawable) {
        mHolderImpl.setBackground(viewId, drawable);
        return this;
    }


    public GodViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        mHolderImpl.setImageBitmap(viewId, bitmap);
        return this;
    }


    public GodViewHolder setImageResource(int viewId, int resId) {
        mHolderImpl.setImageResource(viewId, resId);
        return this;
    }

    public GodViewHolder setImageDrawable(int viewId, Drawable drawable) {
        mHolderImpl.setImageDrawable(viewId, drawable);
        return this;
    }


    public GodViewHolder setImageDrawable(int viewId, Uri uri) {
        mHolderImpl.setImageDrawable(viewId, uri);
        return this;
    }


    @TargetApi(16)
    public GodViewHolder setImageAlpha(int viewId, int alpha) {
        mHolderImpl.setImageAlpha(viewId, alpha);
        return this;
    }

    public GodViewHolder setChecked(int viewId, boolean checked) {
        mHolderImpl.setChecked(viewId, checked);
        return this;
    }


    public GodViewHolder setProgress(int viewId, int progress) {
        mHolderImpl.setProgress(viewId, progress);
        return this;
    }

    public GodViewHolder setProgress(int viewId, int progress, int max) {
        mHolderImpl.setProgress(viewId, progress, max);
        return this;
    }

    public GodViewHolder setMax(int viewId, int max) {
        mHolderImpl.setMax(viewId, max);
        return this;
    }

    public GodViewHolder setRating(int viewId, float rating) {
        mHolderImpl.setRating(viewId, rating);
        return this;
    }


    public GodViewHolder setRating(int viewId, float rating, int max) {
        mHolderImpl.setRating(viewId, rating, max);
        return this;
    }

    public GodViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        mHolderImpl.setOnClickListener(viewId, listener);
        return this;
    }

    public GodViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        mHolderImpl.setOnTouchListener(viewId, listener);
        return this;
    }

    public GodViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        mHolderImpl.setOnLongClickListener(viewId, listener);
        return this;
    }

    public GodViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        mHolderImpl.setOnItemClickListener(viewId, listener);
        return this;
    }


    public GodViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        mHolderImpl.setOnItemLongClickListener(viewId, listener);
        return this;
    }


    public GodViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        mHolderImpl.setOnItemSelectedClickListener(viewId, listener);
        return this;
    }
}
