package com.simple.commonadapter.viewholders;

import android.view.View;

/**
 * 适用于AbsListView的ViewHolder
 * Created by mrsimple on 25/9/15.
 */
public abstract class ListViewHolder {
    public View itemView;

    public ListViewHolder(View rootView) {
        this.itemView = rootView;
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
