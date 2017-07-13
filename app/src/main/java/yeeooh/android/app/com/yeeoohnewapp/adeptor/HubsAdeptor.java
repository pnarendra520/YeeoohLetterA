package yeeooh.android.app.com.yeeoohnewapp.adeptor;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.model.HubsList;
/**
 * Created by YEEOOH on 5/17/2017.
 */

public class HubsAdeptor extends RecyclerView.Adapter<HubsAdeptor.FilterHolder> {
    private Context mContext;
    private ArrayList<HubsList> galleryList;


    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    int selectedPosition=1;

    public HubsAdeptor(Context c, ArrayList<HubsList> galleryList) {
        this.galleryList = galleryList;
        mContext = c;
    }


    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hubs_list, parent, false);
        return new FilterHolder(view);

    }

    @Override
    public void onBindViewHolder(FilterHolder holder, final int position) {
        holder.hubs_name.setText((galleryList.get(position).getImage_title()));
        holder.hubs_image.setImageResource((galleryList.get(position).getImage_ID()));


    }



    @Override
    public int getItemCount() {
        return galleryList.size();
    }


    public class FilterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView hubs_name,hubs_love_text,hubs_like_text,hubs_send_text;
        public ImageView hubs_image,hubs_love,hubs_like,hubs_send_letter;
int count=0;
        public FilterHolder(View itemView) {
            super(itemView);
            hubs_name = (TextView) itemView.findViewById(R.id.hubs_name);
            hubs_image = (ImageView) itemView.findViewById(R.id.hubs_image);
            hubs_love_text = (TextView) itemView.findViewById(R.id.hubs_love_text);
            hubs_love = (ImageView) itemView.findViewById(R.id.hubs_love);
            hubs_like_text = (TextView) itemView.findViewById(R.id.hubs_like_text);
            hubs_like = (ImageView) itemView.findViewById(R.id.hubs_like);
            hubs_send_text = (TextView) itemView.findViewById(R.id.hubs_send_text);
            hubs_send_letter = (ImageView) itemView.findViewById(R.id.hubs_send_letter);
            hubs_love.setOnClickListener(this);
        }
        public  String encodeTobase64(Bitmap image) {
            Bitmap immage = image;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

            Log.d("Image Log:", imageEncoded);
            return imageEncoded;
        }
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.hubs_love:
                    count++;
                    if (count == 1) {
                        hubs_love.setBackground(mContext.getResources().getDrawable(R.drawable.love_red));
                        Toast.makeText(mContext, "Thanks for likeing", Toast.LENGTH_SHORT).show();
                        hubs_love_text.setText("" + count);
                        Animation logoMoveAnimation = AnimationUtils.loadAnimation(mContext, R.anim.scale);
                        hubs_love.startAnimation(logoMoveAnimation);



                    } else if (count == 2) {
                        count--;
                        hubs_love.setBackground(mContext.getResources().getDrawable(R.drawable.like));
                        //hubs_love.setBackgroundColor(mContext.getResources().getColor(R.color.app_bottom_color));
                        Toast.makeText(mContext, " Not likeing", Toast.LENGTH_SHORT).show();
                        hubs_love_text.setText("" + count);
                        count = 0;
                    }


            }
        }

        public  Bitmap decodeToBase64(String input) {
                byte[] decodedByte = Base64.decode(input, 0);
                return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
            }
        }

    }




