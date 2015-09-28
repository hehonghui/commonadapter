package com.simple.commonadapter.viewholders;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 适用于RecyclerView的ViewHolder
 * Created by mrsimple on 25/9/15.
 */
public abstract class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public RecyclerViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T findViewById(int viewId) {
        return (T) itemView.findViewById(viewId);
    }

}
