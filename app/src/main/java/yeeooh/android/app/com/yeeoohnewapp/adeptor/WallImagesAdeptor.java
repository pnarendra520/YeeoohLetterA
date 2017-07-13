package yeeooh.android.app.com.yeeoohnewapp.adeptor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class WallImagesAdeptor extends RecyclerView.Adapter<WallImagesAdeptor.FilterHolder> {
    private Context mContext;
    private Integer[] mImageIds;
    private ArrayList<wallmodel> galleryList;
    private final ArrayList<Integer> selected = new ArrayList<>();
    FilterHolder viewHolder;



    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    int selectedPosition=1;

    public WallImagesAdeptor(Context c, ArrayList<wallmodel> galleryList) {
        this.galleryList = galleryList;
        mContext = c;
    }


    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wall_viewpager_items, parent, false);
        return new FilterHolder(view);

    }

    @Override
    public void onBindViewHolder(FilterHolder holder, final int position) {
        holder.wall_item_textview.setText((galleryList.get(position).getImage_title()));
        holder.wall_specificimage.setImageResource((galleryList.get(position).getImage_ID()));
        holder.wall_specificimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,""+position,Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext,WallCommentActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }


    public class FilterHolder extends RecyclerView.ViewHolder {
        public TextView wall_item_textview;
        public ImageView wall_specificimage;
        RecyclerView pager;

        public FilterHolder(View itemView) {
            super(itemView);
            wall_item_textview = (TextView) itemView.findViewById(R.id.wall_item_textview);
            wall_specificimage = (ImageView) itemView.findViewById(R.id.wall_item_imageView);
        }


    }
}