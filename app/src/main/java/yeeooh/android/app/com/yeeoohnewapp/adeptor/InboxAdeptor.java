package yeeooh.android.app.com.yeeoohnewapp.adeptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.model.HubsList;
import yeeooh.android.app.com.yeeoohnewapp.model.InboxList;
/**
 * Created by YEEOOH on 5/17/2017.
 */

public class InboxAdeptor extends RecyclerView.Adapter<InboxAdeptor.FilterHolder> {
    private Context mContext;
    private ArrayList<InboxList> galleryList;


    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    int selectedPosition=1;

    public InboxAdeptor(Context c, ArrayList<InboxList> galleryList) {
        this.galleryList = galleryList;
        mContext = c;
    }


    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yeeooh_message, parent, false);
        return new FilterHolder(view);

    }

    @Override
    public void onBindViewHolder(FilterHolder holder, final int position) {
        holder.inbox_name.setText((galleryList.get(position).getImage_title()));
        holder.inbox_letter.setImageResource((galleryList.get(position).getImage_ID()));
        holder.message_profile.setImageResource((galleryList.get(position).getImage_id1()));

    }



    @Override
    public int getItemCount() {
        return galleryList.size();
    }


    public class FilterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView inbox_name;
        public ImageView inbox_letter;
        public ImageView message_profile;
        public FilterHolder(View itemView) {
            super(itemView);
            inbox_name = (TextView) itemView.findViewById(R.id.inbox_name);
            inbox_letter = (ImageView) itemView.findViewById(R.id.inbox_letter);
            message_profile = (ImageView) itemView.findViewById(R.id.message_profile);

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

  /*  public class MyCustomPagerAdapter extends PagerAdapter {
        Context context;
        Integer[] mImageIds1;
        LayoutInflater layoutInflater;
        String[] itemData;

        public MyCustomPagerAdapter(Context context, int images[]) {

        }

        public MyCustomPagerAdapter(Context mContext, Integer[] mImageIds1, String[] itemData) {
            this.context = mContext;
            this.mImageIds1 = mImageIds1;
            this.itemData = itemData;
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mImageIds1.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = layoutInflater.inflate(R.layout.wall_viewpager_items, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.wall_item_imageView);
            TextView wall_item_textview = (TextView) itemView.findViewById(R.id.wall_item_textview);
            imageView.setImageResource(mImageIds1[position]);
wall_item_textview.setText(itemData[position]);
            container.addView(itemView);

            //listening to image click
           *//* imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
                }
            });*//*

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }*/

























    /*@Override
    public int getCount() {

        return mImageIds.length;
    }

    @Override
    public Object getItem(int pos) {

        return mImageIds[pos];
    }

    @Override
    public long getItemId(int pos) {

        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Adding dynamic image simillarly we had added to Image Switcher
        ImageView i = new ImageView(mContext);
        i.setImageResource(mImageIds[position]);
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        // Setting background resource
        i.setBackgroundResource(mImageIds[0]);
      //  i.setBackgroundColor(mContext.getColor(R.i));

        return i;
    }*/

}