package yeeooh.android.app.com.yeeoohnewapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import yeeooh.android.app.com.yeeoohnewapp.database.DBManager;
import yeeooh.android.app.com.yeeoohnewapp.database.DatabaseHelper;
import yeeooh.android.app.com.yeeoohnewapp.views.CircleImageView;
/**
 * Created by Narendra on 4/25/2017.
 */

public class AddressListActivity extends AppCompatActivity {
    CircleImageView profile;
    private SimpleCursorAdapter adapter;
    DBManager dbManager;
    ListView listview;
    TextView new_address ,address_continue;
    ImageView cart;
     String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.EMAIL, DatabaseHelper.ADDRESS,
            DatabaseHelper.PINCODE, DatabaseHelper.CITY, DatabaseHelper.Location,
            DatabaseHelper.Number, DatabaseHelper.State};
     int[] to = new int[] {R.id.id, R.id.name, R.id.email, R.id.address,R.id.pincode,
             R.id.city,R.id.location, R.id.phone,R.id.state};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_order);
        new_address=(TextView)findViewById(R.id.new_address);
        profile=(CircleImageView)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressListActivity.this,ProfileActivity.class));
                overridePendingTransition(R.anim.slide_down, R.anim.slide_down);
            }
        });
        address_continue=(TextView)findViewById(R.id.address_continue);
        address_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddressListActivity.this, "Pending", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddressListActivity.this,FinalActivity.class));
            }
        });
        new_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressListActivity.this,AddressActivity.class));
                overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
            }
        });
       /* toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        cart=(ImageView)findViewById(R.id.cart) ;
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressListActivity.this,CartActivity.class));
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        listview = (ListView) findViewById(R.id.list_view);
        listview.setEmptyView(findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter(AddressListActivity.this,R.layout.review_order_list, cursor, from, to, 0);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                Toast.makeText(AddressListActivity.this, "Data", Toast.LENGTH_SHORT).show();
               /* TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView nameTextView = (TextView) view.findViewById(R.id.name);
                TextView emailTextView = (TextView) view.findViewById(R.id.email);
                TextView addressTextView = (TextView) view.findViewById(R.id.address);
                TextView pincodeTextView = (TextView) view.findViewById(R.id.pincode);
                TextView locationTextView = (TextView) view.findViewById(R.id.location);
                TextView numbwe = (TextView) view.findViewById(R.id.phone);
                TextView city = (TextView) view.findViewById(R.id.city);
                TextView stateTextView = (TextView) view.findViewById(R.id.state);
                String id = idTextView.getText().toString();
                String name = nameTextView.getText().toString();
                String email = emailTextView.getText().toString();
                String address = addressTextView.getText().toString();
                String pincode = pincodeTextView.getText().toString();
                String location = locationTextView.getText().toString();
                String phone = numbwe.getText().toString();
                String state = stateTextView.getText().toString();
                String city1 = city.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(), AddressEditActivity.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("email", email);
                modify_intent.putExtra("address", address);
                modify_intent.putExtra("pincode", pincode);
                modify_intent.putExtra("location", location);
                modify_intent.putExtra("phone", phone);
                modify_intent.putExtra("state", state);
                modify_intent.putExtra("state", state);
                modify_intent.putExtra("city", city1);
                startActivity(modify_intent);*/
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.heder, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.action_create_alarm1:
               startActivity(new Intent(AddressListActivity.this,AddressActivity.class));

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
