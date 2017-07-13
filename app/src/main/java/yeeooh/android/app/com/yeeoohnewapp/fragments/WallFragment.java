package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.WallAdeptor;
import yeeooh.android.app.com.yeeoohnewapp.model.CreateList;

/**
 * Created by YEEOOH on 4/19/2017.
 */

public class WallFragment extends Fragment {
    private Integer[] mImageIds = {R.mipmap.one, R.mipmap.two, R.mipmap.skyblue,
            R.mipmap.four, R.mipmap.five, R.mipmap.blue, R.mipmap.yellow, R.mipmap.three,
            R.mipmap.brown, R.mipmap.cyent, R.mipmap.green, R.mipmap.grey
            };
    private String itemData[] = {
            "Autumn",
            "Layering",
            "Pattern",
            "Contrast",
            "Violet",
            "Pattern2",
            "RockPattern",
            "Angsa",
            "Pasir",
            "Smooth",
            "Relief",
            "AquaBlue"};
    RecyclerView recyclerView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         view=inflater.inflate(R.layout.activity_wall, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_wall);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<CreateList> createLists = prepareData();
        WallAdeptor filterAdapter1 = new WallAdeptor(getActivity(),createLists);
        recyclerView.setAdapter(filterAdapter1);


        return view ;
    }

    private ArrayList<CreateList> prepareData(){

        ArrayList<CreateList> theimage = new ArrayList<>();
        for(int i = 0; i< itemData.length; i++){
            CreateList createList = new CreateList();
            createList.setImage_title(itemData[i]);
            createList.setImage_ID(mImageIds[i]);
            theimage.add(createList);
        }
        return theimage;
    }

}