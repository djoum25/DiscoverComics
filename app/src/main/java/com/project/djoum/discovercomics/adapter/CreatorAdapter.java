package com.project.djoum.discovercomics.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.djoum.discovercomics.R;
import com.project.djoum.discovercomics.adapter.CreatorAdapter.CreatorBindingHolder;
import com.project.djoum.discovercomics.databinding.ComicCreatorCellBinding;
import com.project.djoum.discovercomics.model.comics.Item;

import java.util.List;

public class CreatorAdapter extends RecyclerView.Adapter<CreatorBindingHolder> {
    private Context mContext;
    private List<Item> mItems;
    
    public CreatorAdapter(Context context, List<Item> items) {
        mContext = context;
        mItems = items;
    }
    
    @NonNull
    @Override
    public CreatorBindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComicCreatorCellBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.comic_creator_cell, parent, false);
        return new CreatorBindingHolder(binding.getRoot());
    }
    
    @Override
    public void onBindViewHolder(@NonNull CreatorBindingHolder holder, int position) {
        Item item = mItems.get(position);
        holder.mBinding.setItem(item);
        holder.mBinding.executePendingBindings();
    }
    
    @Override
    public int getItemCount() {
        return mItems.size();
    }
    
    class CreatorBindingHolder extends RecyclerView.ViewHolder {
        ComicCreatorCellBinding mBinding;
        
        public CreatorBindingHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
