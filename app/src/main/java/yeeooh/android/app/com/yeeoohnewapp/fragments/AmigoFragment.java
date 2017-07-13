package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import yeeooh.android.app.com.yeeoohnewapp.R;

/**
 * Created by YEEOOH on 4/19/2017.
 */

public class AmigoFragment extends Fragment {
    private ArrayList<String> topicsList;
    private ListAdapter adapter;
    ListView view1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main, container, false);
        view1=(ListView)view.findViewById(R.id.list) ;
        topicsList = new ArrayList<String>();
        topicsList.add("topic1");
        topicsList.add("topic2");
        topicsList.add("topic3");
        topicsList.add("topic4");
        topicsList.add("topic5");
        topicsList.add("topic6");
        topicsList.add("topic1");
        topicsList.add("topic2");
        topicsList.add("topic3");
        topicsList.add("topic4");
        topicsList.add("topic5");
        topicsList.add("topic6");
        adapter = new ArrayAdapter(getActivity(), R.layout.people,R.id.people_time_textView, topicsList);
        view1.setAdapter(adapter);
        return view;

    }
}


