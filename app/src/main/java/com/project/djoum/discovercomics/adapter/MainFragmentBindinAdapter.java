package com.project.djoum.discovercomics.adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.djoum.discovercomics.model.comics.Comics;
import com.project.djoum.discovercomics.model.comics.Item;

import java.util.List;

public class MainFragmentBindinAdapter {
    private static final int NUM_COL = 2;
    
    @BindingAdapter("comicsList")
    public static void setComicList(RecyclerView view, List<Comics> comics) {
        if (comics == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if (layoutManager == null) {
            view.setLayoutManager(new GridLayoutManager(view.getContext(), NUM_COL));
        }
        
        ComicsAdapter adapter = (ComicsAdapter) view.getAdapter();
        if (adapter == null) {
            adapter = new ComicsAdapter(comics, view.getContext());
            view.setAdapter(adapter);
        }
    }
    
    /**
     * this adapter will serve for the creators
     *
     * @param view
     * @param items
     */
    @BindingAdapter("creatorList")
    public static void setCreator(RecyclerView view, List<Item> items) {
        if (items == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if (layoutManager == null) {
            view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        }
        
        CreatorAdapter adapter = (CreatorAdapter) view.getAdapter();
        if (adapter == null) {
            adapter = new CreatorAdapter(view.getContext(), items);
            view.setAdapter(adapter);
        }
    }
}
