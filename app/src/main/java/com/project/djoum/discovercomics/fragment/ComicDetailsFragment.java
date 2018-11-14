package com.project.djoum.discovercomics.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.djoum.discovercomics.R;
import com.project.djoum.discovercomics.databinding.FragmentComicDetailsBinding;
import com.project.djoum.discovercomics.model.comics.Comics;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnComicDetailsFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComicDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComicDetailsFragment extends Fragment {
    private static final String TAG = "ComicDetailsFragment";
    private static final String ARG_PARAM1 = "param1";
    private Comics mComic;
    private OnComicDetailsFragmentInteractionListener mListener;
    
    private FragmentComicDetailsBinding mBinding;
    
    public ComicDetailsFragment() {
        // Required empty public constructor
    }
    
    public static ComicDetailsFragment newInstance(Comics comics) {
        ComicDetailsFragment fragment = new ComicDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, comics);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mComic = getArguments().getParcelable(ARG_PARAM1);
        }
        setHasOptionsMenu(true);
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(getString(R.string.favorites))
                .setIcon(R.drawable.add_favorite).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_settings:
                Toast.makeText(getContext(), "this is the setting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_sign_out:
                return true;
        }
        return true;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentComicDetailsBinding.inflate(inflater);
        if (mComic != null) {
            mBinding.setComic(mComic);
            mBinding.setItems(mComic.getCreators().getItems());
        }
        return mBinding.getRoot();
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnComicDetailsFragmentInteractionListener) {
            mListener = (OnComicDetailsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                                               + " must implement OnComicDetailsFragmentInteractionListener");
        }
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    
    public interface OnComicDetailsFragmentInteractionListener {
    
    }
}
