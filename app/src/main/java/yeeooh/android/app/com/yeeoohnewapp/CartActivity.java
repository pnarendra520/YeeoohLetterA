package yeeooh.android.app.com.yeeoohnewapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by YEEOOH on 4/28/2017.
 */

public class CartActivity extends AppCompatActivity {
    private ArrayList<String> topicsList;
    private ListAdapter adapter;
    TextView increment,decrement,counttext,amount,cart_amount;
    ListView view1;
int count=0;
    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter1;
    Spinner paper,address;
    String[] selectpaper={"Select Paper","A1","A2","A3","A4"};
    String[] selectaddress={"Select Address","Address1","Address2","Address3","Address4"};
    int amount1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_screen);

        increment=(TextView)findViewById(R.id.cart_increment);
        decrement=(TextView)findViewById(R.id.cart_decrement);
        counttext=(TextView)findViewById(R.id.cart_count);
        amount=(TextView)findViewById(R.id.amount);
        paper=(Spinner)findViewById(R.id.select_paper);
        address=(Spinner)findViewById(R.id.select_address);
        cart_amount=(TextView)findViewById(R.id.cart_amount);
        arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,selectpaper);
        paper.setAdapter(arrayAdapter);
        arrayAdapter1=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,selectaddress);
        address.setAdapter(arrayAdapter1);
        increment.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        count++;

        counttext.setText(String.valueOf(count));
        amount.setText(String.valueOf(count*Float.parseFloat(cart_amount.getText().toString())));
    }
});
decrement.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        count--;

        counttext.setText(String.valueOf(count));
        amount.setText(String.valueOf(count*Float.parseFloat(cart_amount.getText().toString())));
    }
});

        view1=(ListView)findViewById(R.id.list) ;
        topicsList = new ArrayList<String>();
        topicsList.add("topic1");
        topicsList.add("topic2");
        topicsList.add("topic3");
        topicsList.add("topic4");
        topicsList.add("topic5");
        topicsList.add("topic6");

      //  adapter = new ArrayAdapter(getApplicationContext(), R.layout.cart_screen,R.id.holder_name, topicsList);
       // view1.setAdapter(adapter);
    }
}
