package yeeooh.android.app.com.yeeoohnewapp.adeptor;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;

import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.model.FrameList;

/**
 * Created by YEEOOH on 5/17/2017.
 */

public class FramAdeptor extends RecyclerView.Adapter<FramAdeptor.FilterHolder> {
    private Context mContext;
    private Integer[] mImageIds;
    private ArrayList<FrameList> galleryList;
    private final ArrayList<Integer> selected = new ArrayList<>();
    FilterHolder viewHolder;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    int selectedPosition=1;

    public FramAdeptor(Context c, ArrayList<FrameList> galleryList) {
        this.galleryList = galleryList;
        mContext = c;
    }


    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imf_filter_item, parent, false);
        return new FilterHolder(view);

    }

    @Override
    public void onBindViewHolder(FilterHolder holder, final int position) {
        holder.imFilter.setImageResource((galleryList.get(position).getImage_ID()));


    }



    @Override
    public int getItemCount() {
        return galleryList.size();
    }


    public class FilterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imFilter;

        public FilterHolder(View itemView) {
            super(itemView);
            imFilter = (ImageView) itemView.findViewById(R.id.effectsviewimage_item);
        }

        @Override
        public void onClick(View v) {
            if (selectedItems.get(getAdapterPosition(), false)) {
                selectedItems.delete(getAdapterPosition());
                v.setSelected(false);
            }
            else {
                selectedItems.put(getAdapterPosition(), true);
                v.setSelected(true);
            }
        }
    }

}