package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.ColorAdapter;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.HubsAdeptor;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.RecyclerItemClickListener;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.WallAdeptor;
import yeeooh.android.app.com.yeeoohnewapp.model.CreateList;
import yeeooh.android.app.com.yeeoohnewapp.model.HubsList;

/**
 * Created by YEEOOH on 4/19/2017.
 */

public class HubsFragment extends Fragment {
    private Integer[] mImageIds = {R.mipmap.one, R.mipmap.two, R.mipmap.skyblue,
            R.mipmap.four, R.mipmap.five, R.mipmap.blue, R.mipmap.yellow, R.mipmap.three,
            R.mipmap.brown, R.mipmap.cyent, R.mipmap.green, R.mipmap.grey
    };
    private GridLayoutManager mLayoutManager;

    RecyclerView recyclerView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_hubs, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view_hubs);
       /* LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);*/
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
      //  mRecyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
       ArrayList<HubsList> createLists = prepareData();
        ColorAdapter filterAdapter1 = new ColorAdapter(getActivity(),createLists);
        recyclerView.setAdapter(filterAdapter1);

        return view;
    }

    private ArrayList<HubsList> prepareData(){

        ArrayList<HubsList> theimage = new ArrayList<>();
        for(int i = 0; i< mImageIds.length; i++){
            HubsList createList = new HubsList();
          //  createList.setImage_title(itemData[i]);
            createList.setImage_ID(mImageIds[i]);
            theimage.add(createList);
        }
        return theimage;
    }

}
