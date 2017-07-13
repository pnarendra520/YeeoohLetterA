package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;

import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.ImageAdapter;

/**
 * Created by YEEOOH on 5/17/2017.
 */

public class Commonhubs extends Fragment {
    private ArrayList<String> topicsList;
    private ListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gridview, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.grid);

        topicsList = new ArrayList<String>();
        topicsList.add("topic1");
        topicsList.add("topic2");
        topicsList.add("topic3");
        topicsList.add("topic4");
        topicsList.add("topic5");
        topicsList.add("topic6");

        adapter = new ArrayAdapter(getActivity(), R.layout.commonhubs,R.id.hubname, topicsList);
        gridView.setAdapter(adapter);

        return view ;
    }

}
