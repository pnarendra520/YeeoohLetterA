package yeeooh.android.app.com.yeeoohnewapp.adeptor;

/**
 * Created by Narendra on 5/16/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;
import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.model.HubsList;
public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.FilterHolder>{

FragmentActivity activity;
    ArrayList<HubsList> createLists;
    public ColorAdapter(FragmentActivity activity, ArrayList<HubsList> createLists) {
        this.activity=activity;
        this.createLists=createLists;

    }


    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listprofile, parent, false);
        return new ColorAdapter.FilterHolder(view);

    }

    @Override
    public void onBindViewHolder(FilterHolder holder, int position) {
        holder.hubs_image_view.setImageResource((createLists.get(position).getImage_ID()));
    }

    @Override
    public int getItemCount() {
        return createLists.size();
    }


    public class FilterHolder extends RecyclerView.ViewHolder {
        public ImageView hubs_image_view;
        public ImageView hubs_lile_image_view;

        public FilterHolder(View itemView) {
            super(itemView);
            hubs_image_view = (ImageView) itemView.findViewById(R.id.hubs_image_view);
            hubs_lile_image_view = (ImageView) itemView.findViewById(R.id.hubs_lile_image_view);


        }

    }
    }