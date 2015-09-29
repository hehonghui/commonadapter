package com.simple.commonadapter.viewholders;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by mrsimple on 29/9/15.
 */
public class GodViewHolder {
    /**
     *
     */
    private SparseArray<View> mCahceViews = new SparseArray<View>();
    /**
     *
     */
    private View mItemView;


    /**
     * @param itemView
     */
    GodViewHolder(View itemView) {
        mItemView = itemView;
    }

    /**
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(int viewId) {
        View target = mCahceViews.get(viewId);
        if (target == null) {
            target = mItemView.findViewById(viewId);
            mCahceViews.put(viewId, target);
        }
        return (T) target;
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
        return mItemView;
    }

    public GodViewHolder setText(int viewId, int stringId) {
        TextView textView = findViewById(viewId);
        textView.setText(stringId);
        return this;
    }

    public GodViewHolder setText(int viewId, String text) {
        TextView textView = findViewById(viewId);
        textView.setText(text);
        return this;
    }

    public GodViewHolder setTextColor(int viewId, int color) {
        TextView textView = findViewById(viewId);
        textView.setTextColor(color);
        return this;
    }

    /**
     * @param viewId
     * @param color
     */
    public GodViewHolder setBackgroundColor(int viewId, int color) {
        View target = findViewById(viewId);
        target.setBackgroundColor(color);
        return this;
    }


    /**
     * @param viewId
     * @param resId
     */
    public GodViewHolder setBackgroundResource(int viewId, int resId) {
        View target = findViewById(viewId);
        target.setBackgroundResource(resId);
        return this;
    }


    /**
     * @param viewId
     * @param drawable
     */
    public GodViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        View target = findViewById(viewId);
        target.setBackgroundDrawable(drawable);
        return this;
    }

    /**
     * @param viewId
     * @param drawable
     */
    @TargetApi(16)
    public GodViewHolder setBackground(int viewId, Drawable drawable) {
        View target = findViewById(viewId);
        target.setBackground(drawable);
        return this;
    }


    public GodViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView target = findViewById(viewId);
        target.setImageBitmap(bitmap);
        return this;
    }


    public GodViewHolder setImageResource(int viewId, int resId) {
        ImageView target = findViewById(viewId);
        target.setImageResource(resId);
        return this;
    }


    public GodViewHolder setImageDrawable(int viewId, Drawable bitmap) {
        ImageView target = findViewById(viewId);
        target.setImageDrawable(bitmap);
        return this;
    }


    public GodViewHolder setImageDrawable(int viewId, Uri uri) {
        ImageView target = findViewById(viewId);
        target.setImageURI(uri);
        return this;
    }


    @TargetApi(16)
    public GodViewHolder setImageAlpha(int viewId, int alpha) {
        ImageView target = findViewById(viewId);
        target.setImageAlpha(alpha);
        return this;
    }

    public GodViewHolder setChecked(int viewId, boolean checked) {
        Checkable checkable = findViewById(viewId);
        checkable.setChecked(checked);
        return this;
    }


    public GodViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = findViewById(viewId);
        view.setProgress(progress);
        return this;
    }

    public GodViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = findViewById(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public GodViewHolder setMax(int viewId, int max) {
        ProgressBar view = findViewById(viewId);
        view.setMax(max);
        return this;
    }

    public GodViewHolder setRating(int viewId, float rating) {
        RatingBar view = findViewById(viewId);
        view.setRating(rating);
        return this;
    }


    public GodViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = findViewById(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public GodViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public GodViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = findViewById(viewId);
        view.setOnTouchListener(listener);
        return this;
    }


    public GodViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = findViewById(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public GodViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        AdapterView view = findViewById(viewId);
        view.setOnItemClickListener(listener);
        return this;
    }


    public GodViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView view = findViewById(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }


    public GodViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        AdapterView view = findViewById(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }
}
