package yeeooh.android.app.com.yeeoohnewapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import yeeooh.android.app.com.yeeoohnewapp.database.DBManager;
import yeeooh.android.app.com.yeeoohnewapp.database.Yeeoohdatabase;

/**
 * Created by YEEOOH on 4/24/2017.
 */

public class AddressEditActivity extends AppCompatActivity implements View.OnClickListener{
    EditText name,pincode ,address,landmark,city,state,phonenumber,email;
    TextView cancel,Save,Delete;
    private long _id;
    String name1, pincode1, address1, landmark1, city1, state1, phonenumber1,address_Email;
    DBManager dbManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dbManager = new DBManager(this);
        dbManager.open();
    }

    public void init(){
        name=(EditText)findViewById(R.id.address_name);
        pincode=(EditText)findViewById(R.id.address_pincode);
        address=(EditText)findViewById(R.id.address_address);
        email=(EditText)findViewById(R.id.address_Email);
        landmark=(EditText)findViewById(R.id.address_landmark);
        city=(EditText)findViewById(R.id.address_city);
        state=(EditText)findViewById(R.id.address_state);
        phonenumber=(EditText)findViewById(R.id.address_phonenumber);
        cancel=(TextView)findViewById(R.id.address_cancel);
        Save=(TextView)findViewById(R.id.address_save);
        Save.setText("Update");
        Delete=(TextView)findViewById(R.id.delete);
        Intent intent = getIntent();
        String id1 = intent.getStringExtra("id");
        String name1 = intent.getStringExtra("name");
        String email1 = intent.getStringExtra("email");
        String address1 = intent.getStringExtra("address");
        String pincode1 = intent.getStringExtra("pincode");
        String location1 = intent.getStringExtra("location");
        String phone1 = intent.getStringExtra("phone");
        String city1 = intent.getStringExtra("city");
        String state1 = intent.getStringExtra("state");
        _id = Long.parseLong(id1);
        name.setText(name1);
        pincode.setText(pincode1);
        address.setText(address1);
        landmark.setText(location1);
        city.setText(city1);
        state.setText(state1);
        phonenumber.setText(phone1);
        email.setText(email1);
        Save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        Delete.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        name1 = name.getText().toString();
        pincode1 = pincode.getText().toString();
        address_Email=email.getText().toString();
        address1 = address.getText().toString();
        landmark1 = landmark.getText().toString();
        city1 = city.getText().toString();
        state1 = state.getText().toString();
        phonenumber1 = phonenumber.getText().toString();
        switch (v.getId()){

            case R.id.address_save:
                dbManager.update(_id,name1,address_Email, address1, pincode1, city1, landmark1, phonenumber1, state1);
              this.returnHome();
                break;

            case R.id.address_cancel:
                finish();
                break;

            case R.id.delete:
                dbManager.delete(_id);
                this.returnHome();
                break;

        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), AddressListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }








}
