package com.wldev.expandablecardviewlist.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Divya Gupta on 05-Aug-19.
 **/
public class BindingViewHolder<ViewBinding extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private ViewBinding viewBinding;
    public BindingViewHolder(@NonNull ViewBinding binding) {
        super(binding.getRoot());
        this.viewBinding = binding;
    }

    public BindingViewHolder(@LayoutRes int layout, ViewGroup parent) {
        this(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout, parent, false));
    }

    public ViewBinding getBinding() {
        return viewBinding;
    }
}
