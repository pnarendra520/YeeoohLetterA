package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import yeeooh.android.app.com.yeeoohnewapp.InboxFullActivity;
import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.WallCommentActivity;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.HubsAdeptor;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.InboxAdeptor;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.RecyclerItemClickListener;
import yeeooh.android.app.com.yeeoohnewapp.model.HubsList;
import yeeooh.android.app.com.yeeoohnewapp.model.InboxList;

/**
 * Created by YEEOOH on 4/19/2017.
 */

public class InboxFragment extends Fragment {
    private Integer[] mImageIds = {R.mipmap.one, R.mipmap.two, R.mipmap.skyblue,
            R.mipmap.four, R.mipmap.five, R.mipmap.blue, R.mipmap.yellow, R.mipmap.three,
            R.mipmap.brown, R.mipmap.cyent, R.mipmap.green, R.mipmap.grey
    };
    private Integer[] mImageIds1 = {R.mipmap.one, R.mipmap.two, R.mipmap.skyblue,
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
        View view = inflater.inflate(R.layout.activity_inbox, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_inbox);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<InboxList> createLists = prepareData();
        InboxAdeptor filterAdapter1 = new InboxAdeptor(getActivity(),createLists);
        recyclerView.setAdapter(filterAdapter1);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), InboxFullActivity.class);
                intent.putExtra("name",itemData[position]);
                startActivity(intent);
            }
        }));

        return view;
    }

    private ArrayList<InboxList> prepareData(){

        ArrayList<InboxList> theimage = new ArrayList<>();
        for(int i = 0; i< itemData.length; i++){
            InboxList createList = new InboxList();
            createList.setImage_title(itemData[i]);
            createList.setImage_ID(mImageIds[i]);
            createList.setImage_id1(mImageIds1[i]);
            theimage.add(createList);
        }
        return theimage;
    }

}