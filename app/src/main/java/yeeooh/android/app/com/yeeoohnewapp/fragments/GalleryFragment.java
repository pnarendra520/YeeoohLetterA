package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.ColorAdapter;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.SpacesItemDecoration;

/**
 * Created by YEEOOH on 5/16/2017.
 */

public class GalleryFragment extends Fragment {
    RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    String[] colors = {
            "Red","Green","Blue","Yellow","Magenta","Cyan","Orange",
            "Aqua","Azure","Beige","Bisque","Brown","Coral","Crimson"
    };
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new instance of RecyclerView Adapter instance
       // mAdapter = new ColorAdapter(getActivity(),colors);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        // Initialize a new String array
        //mRecyclerView.setNestedScrollingEnabled(false);
        return view;
    }
}