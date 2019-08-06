package com.wldev.expandablecardviewlist.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/**
 * Created by Divya Gupta on 05-Aug-19.
 **/
public class ExpandedStateItem extends BaseObservable {

    private float margin;

    private boolean expanded;

    private boolean fast;

    public ExpandedStateItem(boolean expanded) {
        this.expanded = expanded;
    }

    public ExpandedStateItem(boolean expanded, float margin) {
        this.expanded = expanded;
        this.margin = margin;
    }

    @Bindable
    public boolean isFast() {
        return fast;
    }

    public void setFast(boolean fast) {
        this.fast = fast;
        notifyPropertyChanged(BR.fast);
    }

    @Bindable
    public boolean isExpanded() {
        return expanded;
    }

    @Bindable
    public float getMargin() {
        return margin;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
        notifyPropertyChanged(BR.expanded);
    }

    public void setMargin(float margin) {
        this.margin = margin;
        notifyPropertyChanged(BR.margin);
    }
}
