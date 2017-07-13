package yeeooh.android.app.com.yeeoohnewapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by YEEOOH on 4/27/2017.
 */

public class FinalActivity extends AppCompatActivity {
    ImageView cart;
    ImageView gif;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finallayout);
       /* cart=(ImageView)findViewById(R.id.cart) ;
        gif=(ImageView)findViewById(R.id.gif) ;
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalActivity.this,CartActivity.class));
            }
        });*/
    }
}
