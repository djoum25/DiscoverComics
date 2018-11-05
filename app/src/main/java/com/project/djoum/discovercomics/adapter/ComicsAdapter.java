package com.project.djoum.discovercomics.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.djoum.discovercomics.R;
import com.project.djoum.discovercomics.adapter.ComicsAdapter.BindingHolder;
import com.project.djoum.discovercomics.databinding.ComicCellBinding;
import com.project.djoum.discovercomics.model.comics.Comics;

import java.util.ArrayList;
import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<BindingHolder> {
    private List<Comics> mComics = new ArrayList<>();
    private Context mContext;
    
    public ComicsAdapter(List<Comics> comics, Context context) {
        mComics = comics;
        mContext = context;
    }
    
    @NonNull
    @Override
    public BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ComicCellBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.comic_cell, parent, false);
        return new BindingHolder(binding.getRoot());
    }
    
    @Override
    public void onBindViewHolder(@NonNull BindingHolder holder, int position) {
        Comics comic = mComics.get(position);
        holder.mBinding.setComic(comic);
        holder.mBinding.executePendingBindings();
    }
    
    public void refreshListOfComics(List<Comics> comics) {
        mComics.clear();
        mComics.addAll(comics);
        notifyDataSetChanged();
    }
    
    @Override
    public int getItemCount() {
        return mComics.size();
    }
    
    class BindingHolder extends RecyclerView.ViewHolder {
        ComicCellBinding mBinding;
        
        BindingHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
