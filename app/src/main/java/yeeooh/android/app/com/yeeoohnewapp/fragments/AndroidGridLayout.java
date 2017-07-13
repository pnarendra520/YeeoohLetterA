package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.ImageAdapter;

/**
 * Created by YEEOOH on 5/16/2017.
 */

public class AndroidGridLayout extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gridview, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.grid);
        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(getActivity()));

        return view ;
    }
}
