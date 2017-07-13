package yeeooh.android.app.com.yeeoohnewapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by YEEOOH on 5/19/2017.
 */

public class InboxFullActivity extends AppCompatActivity implements View.OnClickListener{
ImageView wall_comment_send;
    RelativeLayout layout;
    TextView wall_comment_share;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wall_comment_screen);
        /*wall_comment_send=(ImageView)findViewById(R.id.wall_comment_send);
        wall_comment_share=(TextView) findViewById(R.id.wall_comment_share);*/
        layout=(RelativeLayout)findViewById(R.id.components) ;
        layout.setVisibility(View.GONE);
       /* wall_comment_share.setOnClickListener(this);
        wall_comment_send.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wall_comment_share:
                shareIntent();
                break;
            /*case R.id.wall_comment_send:
                Toast.makeText(this, "Developing", Toast.LENGTH_SHORT).show();*/
        }
    }


    public void shareIntent(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
        startActivity(Intent.createChooser(sharingIntent,"Share using"));
    }
}
