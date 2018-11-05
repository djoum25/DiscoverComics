package com.project.djoum.discovercomics.adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.djoum.discovercomics.model.comics.Comics;

import java.util.List;

public class MainFragmentBindinAdapter {
    @BindingAdapter("comicsList")
    public static void setComicList(RecyclerView view, List<Comics> comics) {
        if (comics == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if (layoutManager == null) {
            view.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        }
        
        ComicsAdapter adapter = (ComicsAdapter) view.getAdapter();
        if (adapter == null) {
            adapter = new ComicsAdapter(comics, view.getContext());
            view.setAdapter(adapter);
        }
    }
}
