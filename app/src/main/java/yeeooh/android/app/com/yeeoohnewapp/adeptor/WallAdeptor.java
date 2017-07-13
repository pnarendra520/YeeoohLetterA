package yeeooh.android.app.com.yeeoohnewapp.adeptor;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.WallCommentActivity;
import yeeooh.android.app.com.yeeoohnewapp.model.CreateList;
import yeeooh.android.app.com.yeeoohnewapp.model.wallmodel;

/**
 * Created by YEEOOH on 5/17/2017.
 */

public class WallAdeptor extends RecyclerView.Adapter<WallAdeptor.FilterHolder> {
    private Context mContext;
    private Integer[] mImageIds;
    private ArrayList<CreateList> galleryList;
    private final ArrayList<Integer> selected = new ArrayList<>();
    FilterHolder viewHolder;
    private Integer[] mImageIds1 = {R.mipmap.one, R.mipmap.two, R.mipmap.three,
            R.mipmap.four, R.mipmap.five, R.mipmap.six, R.mipmap.seven, R.mipmap.eight,
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

    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    int selectedPosition=1;

    public WallAdeptor(Context c, ArrayList<CreateList> galleryList) {
        this.galleryList = galleryList;
        mContext = c;
    }


    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_list, parent, false);
        return new FilterHolder(view);

    }

    @Override
    public void onBindViewHolder(FilterHolder holder, final int position) {
        holder.wall_name.setText((galleryList.get(position).getImage_title()));
        holder.wall_specificimage.setImageResource((galleryList.get(position).getImage_ID()));
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
       //holder.pager.setHasFixedSize(true);
        holder.pager.setLayoutManager(layoutManager);
        ArrayList<wallmodel> createLists =holder.prepareData();
        WallImagesAdeptor filterAdapter1 = new WallImagesAdeptor(mContext,createLists);
        holder.pager.setAdapter(filterAdapter1);



    }



    @Override
    public int getItemCount() {
        return galleryList.size();
    }


    public class FilterHolder extends RecyclerView.ViewHolder {
        public TextView wall_name;
        public ImageView wall_specificimage;
        RecyclerView pager;
        public FilterHolder(View itemView) {
            super(itemView);
            wall_name = (TextView) itemView.findViewById(R.id.wall_name);
            wall_specificimage = (ImageView) itemView.findViewById(R.id.wall_specificimage);
            pager = (RecyclerView) itemView.findViewById(R.id.viewpager_wall);
        }

        private ArrayList<wallmodel> prepareData(){

            ArrayList<wallmodel> theimage = new ArrayList<>();
            for(int i = 0; i< itemData.length; i++){
                wallmodel createList = new wallmodel();
                createList.setImage_title(itemData[i]);
                createList.setImage_ID(mImageIds1[i]);
                theimage.add(createList);
            }
            return theimage;
        }



    }

}