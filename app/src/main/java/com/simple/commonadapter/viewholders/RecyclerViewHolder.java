package com.simple.commonadapter.viewholders;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 适用于RecyclerView的ViewHolder
 * Created by mrsimple on 25/9/15.
 */
public abstract class RecyclerViewHolder extends RecyclerView.ViewHolder {

    GodViewHolder mGodViewHolder ;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mGodViewHolder = new GodViewHolder( itemView ) ;
    }

    public RecyclerViewHolder setText(int viewId, int stringId) {
        TextView textView = mGodViewHolder.findViewById(viewId);
        textView.setText(stringId);
        return this;
    }

    public RecyclerViewHolder setText(int viewId, String text) {
        TextView textView = mGodViewHolder.findViewById(viewId);
        textView.setText(text);
        return this;
    }

    public RecyclerViewHolder setTextColor(int viewId, int color) {
        TextView textView = mGodViewHolder.findViewById(viewId);
        textView.setTextColor(color);
        return this;
    }

    /**
     * @param viewId
     * @param color
     */
    public RecyclerViewHolder setBackgroundColor(int viewId, int color) {
        View target = mGodViewHolder.findViewById(viewId);
        target.setBackgroundColor(color);
        return this;
    }


    /**
     * @param viewId
     * @param resId
     */
    public RecyclerViewHolder setBackgroundResource(int viewId, int resId) {
        View target = mGodViewHolder.findViewById(viewId);
        target.setBackgroundResource(resId);
        return this;
    }


    /**
     * @param viewId
     * @param drawable
     */
    public RecyclerViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        View target = mGodViewHolder.findViewById(viewId);
        target.setBackgroundDrawable(drawable);
        return this;
    }

    /**
     * @param viewId
     * @param drawable
     */
    @TargetApi(16)
    public RecyclerViewHolder setBackground(int viewId, Drawable drawable) {
        View target = mGodViewHolder.findViewById(viewId);
        target.setBackground(drawable);
        return this;
    }


    public RecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView target = mGodViewHolder.findViewById(viewId);
        target.setImageBitmap(bitmap);
        return this;
    }


    public RecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView target = mGodViewHolder.findViewById(viewId);
        target.setImageResource(resId);
        return this;
    }


    public RecyclerViewHolder setImageDrawable(int viewId, Drawable bitmap) {
        ImageView target = mGodViewHolder.findViewById(viewId);
        target.setImageDrawable(bitmap);
        return this;
    }


    public RecyclerViewHolder setImageDrawable(int viewId, Uri uri) {
        ImageView target = mGodViewHolder.findViewById(viewId);
        target.setImageURI(uri);
        return this;
    }


    @TargetApi(16)
    public RecyclerViewHolder setImageAlpha(int viewId, int alpha) {
        ImageView target = mGodViewHolder.findViewById(viewId);
        target.setImageAlpha(alpha);
        return this;
    }

    public RecyclerViewHolder setChecked(int viewId, boolean checked) {
        Checkable checkable = mGodViewHolder.findViewById(viewId);
        checkable.setChecked(checked);
        return this;
    }


    public RecyclerViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = mGodViewHolder.findViewById(viewId);
        view.setProgress(progress);
        return this;
    }

    public RecyclerViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = mGodViewHolder.findViewById(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public RecyclerViewHolder setMax(int viewId, int max) {
        ProgressBar view = mGodViewHolder.findViewById(viewId);
        view.setMax(max);
        return this;
    }

    public RecyclerViewHolder setRating(int viewId, float rating) {
        RatingBar view = mGodViewHolder.findViewById(viewId);
        view.setRating(rating);
        return this;
    }


    public RecyclerViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = mGodViewHolder.findViewById(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public RecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = mGodViewHolder.findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public RecyclerViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = mGodViewHolder.findViewById(viewId);
        view.setOnTouchListener(listener);
        return this;
    }


    public RecyclerViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = mGodViewHolder.findViewById(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public RecyclerViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
        AdapterView view = mGodViewHolder.findViewById(viewId);
        view.setOnItemClickListener(listener);
        return this;
    }


    public RecyclerViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
        AdapterView view = mGodViewHolder.findViewById(viewId);
        view.setOnItemLongClickListener(listener);
        return this;
    }


    public RecyclerViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
        AdapterView view = mGodViewHolder.findViewById(viewId);
        view.setOnItemSelectedListener(listener);
        return this;
    }
}
