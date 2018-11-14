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
import com.project.djoum.discovercomics.adapter.ComicsAdapter;
import com.project.djoum.discovercomics.databinding.FragmentMainBinding;
import com.project.djoum.discovercomics.model.comics.Comics;
import com.project.djoum.discovercomics.utilities.JsonUtils;
import com.project.djoum.discovercomics.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    int aRandomYear;
    
    public MainFragment() {
        // Required empty public constructor
    }
    
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aRandomYear = getARandomYear();
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMainBinding.inflate(inflater);
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);
        updateUi();
        return mBinding.getRoot();
    }
    
    private void updateUi() {
        if (NetworkUtils.isConnectionAvaillable(this.getActivity())) {
            try {
                Log.d(TAG, "updateUi: before calling updateUi " + aRandomYear);
                NetworkUtils.queryUrl(NetworkUtils.comicsUrl(aRandomYear, "title",
                        getString(R.string.public_key), getString(R.string.private_key))).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                    
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d(TAG, "response from the web " + response);
                        try {
                            mComics = JsonUtils.jsonToComics(response.body().string());
                            mBinding.setComics(mComics);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onRefresh() {
        aRandomYear = getARandomYear();
        List<Comics> testList = new ArrayList<>();
        if (mComics != null) {
            testList.addAll(mComics);
            ((ComicsAdapter) mBinding.mainList.getAdapter()).refreshListOfComics(testList);
            updateUi();
            (mBinding.mainList.getAdapter()).notifyDataSetChanged();
            mBinding.swipeRefreshLayout.setRefreshing(false);
        }
    }
    
    public int getARandomYear() {
        Random rand = new Random();
        int max = 2018;
        int min = 2008;
        return rand.nextInt((max - min) + 1) + min;
    }
}
