package com.project.djoum.discovercomics.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.project.djoum.discovercomics.R;
import com.squareup.picasso.Picasso;

public class PictureBinding {
    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, String url) {
        Context context = view.getContext();
        
        Picasso.with(context)
                .load(url).fit().centerCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(view);
        
    }
    
}
