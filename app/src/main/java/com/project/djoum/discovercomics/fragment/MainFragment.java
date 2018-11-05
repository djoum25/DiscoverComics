package com.project.djoum.discovercomics.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.djoum.discovercomics.R;
import com.project.djoum.discovercomics.databinding.FragmentMainBinding;
import com.project.djoum.discovercomics.model.comics.Comics;
import com.project.djoum.discovercomics.utilities.JsonUtils;
import com.project.djoum.discovercomics.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements OnRefreshListener {
    private static final String TAG = "MainFragment";
    private FragmentMainBinding mBinding;
    private List<Comics> mComics;
    
    public MainFragment() {
        // Required empty public constructor
    }
    
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMainBinding.inflate(inflater);
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        if (NetworkUtils.isConnectionAvaillable(this.getActivity())) {
            try {
                NetworkUtils.queryUrl(NetworkUtils.comicsUrl(2018, "title",
                        getString(R.string.public_key), getString(R.string.private_key))).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                    
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        try {
                            List<Comics> comics = JsonUtils.jsonToComics(response.body().string());
                            mBinding.setComics(comics);
                            for (Comics comic : comics) {
                                Log.d(TAG, "onResponse: id " + comic.getId());
                                Log.d(TAG, "onResponse: " + comic.getTitle());
                                long timeStamp = System.currentTimeMillis();
                                URL comicsResourceUri = NetworkUtils.comicsResourceUri(comic.getResourceURI(),
                                        timeStamp, getString(R.string.public_key), getString(R.string.private_key));
                            }
                        } catch (JSONException | NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return mBinding.getRoot();
    }
    
    @Override
    public void onRefresh() {
        (mBinding.mainList.getAdapter()).notifyDataSetChanged();
        mBinding.swipeRefreshLayout.setRefreshing(false);
    }
}
