package yeeooh.android.app.com.yeeoohnewapp;

import  android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yeeooh.android.app.com.yeeoohnewapp.api.YeoohServices;
import yeeooh.android.app.com.yeeoohnewapp.quickActions.ActionItem;
import yeeooh.android.app.com.yeeoohnewapp.quickActions.QuickAction;

/**
 * Created by Narendra on 4/18/2017.
 */

public class Registration extends AppCompatActivity implements View.OnClickListener{
    EditText name,email,phone,password,con_password,date,gender;
    CheckBox male,female,c;
    TextView submit;
    Spinner spinner;
    ImageView back;
    RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private int mYear, mMonth, mDay;
    String[] sex={"Gender","Male","Female","C"};
    ArrayAdapter arrayAdapter;
    QuickAction action;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeeooh_app_registration);
       //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        initPopUpWIndow();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    public void init(){
        name=(EditText)findViewById(R.id.editText_name);
        email=(EditText)findViewById(R.id.editText_email);
        phone=(EditText)findViewById(R.id.editText_phone);
        password=(EditText)findViewById(R.id.editText_password);
        con_password=(EditText)findViewById(R.id.editText_con_password);
        date=(EditText)findViewById(R.id.editText_date);
        gender=(EditText)findViewById(R.id.editText_gender );
        submit=(TextView)findViewById(R.id.editText_submit);
        back=(ImageView)findViewById(R.id.back1);
        radioSexGroup=(RadioGroup)findViewById(R.id.gender);
        int selectedId=radioSexGroup.getCheckedRadioButtonId();
        radioSexButton=(RadioButton)findViewById(selectedId);
       /* arrayAdapter=new ArrayAdapter(Registration.this,android.R.layout.simple_spinner_dropdown_item,sex);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Registration.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        date.setOnClickListener(this);
        gender.setOnClickListener(this);
    }

    private void initPopUpWIndow()
    {
        action=new QuickAction(getApplicationContext(),QuickAction.VERTICAL);
        action.addActionItem(new ActionItem(0,"Male"));
        action.addActionItem(new ActionItem(1,"Female"));
        action.addActionItem(new ActionItem(2,"C"));

        action.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override
            public void onItemClick(QuickAction source, int pos, int actionId) {
                gender.setText(source.getActionItem(pos).getTitle());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back1:
                finish();
                overridePendingTransition(R.anim.slide_right, R.anim.slide_right);
                break;
            case R.id.editText_submit:


                Validation();
                break;
            case R.id.editText_date:
                date();
                break;
            case R.id.editText_gender:
               action.show(v);
                break;
        }
    }



    public void Validation(){

        if (name.getText().toString().length()==0||email.getText().toString().length()==0||
                phone.getText().toString().length()==0||password.getText().toString().length()==0
                ||con_password.getText().toString().length()==0||date.getText().toString().length()==0
                ||radioSexGroup.getCheckedRadioButtonId() == -1){
            name.setError("name");
            Toast.makeText(Registration.this,"All fields are required", Toast.LENGTH_SHORT).show();

        }else if (email.getText().toString().length()==0||!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            Toast.makeText(Registration.this,"Enter valid email", Toast.LENGTH_SHORT).show();
        }else if (phone.getText().toString().length()!=10){
            Toast.makeText(Registration.this,"Mobile number required 10 digits", Toast.LENGTH_SHORT).show();
        }
        else if (!isValidPassword(password.getText().toString().trim())){
            Toast.makeText(Registration.this,"No son permitidos los espacios ni los caracteres especiales ", Toast.LENGTH_SHORT).show();
        }

        else if (!password.getText().toString().matches(con_password.getText().toString())) {
            Toast.makeText(Registration.this,"Password Not match", Toast.LENGTH_SHORT).show();

        }
        else {
            server();
        }


    }



    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    public void date(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(Registration.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

public void server(){
    RequestQueue requestQueue= Volley.newRequestQueue(this);
    StringRequest stringRequest = new StringRequest(Request.Method.POST, YeoohServices.Yee_REGISTER,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject object;
                    int status;
                    String message;
                    try {
                        object=new JSONObject(response);
                        status=object.getInt("status");
                        message=object.getString("message");
                        if (status==1){
                            Toast.makeText(Registration.this, message, Toast.LENGTH_LONG).show();
                        }
                        else if (status==0){
                            Toast.makeText(Registration.this, message, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(Registration.this, message, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Registration.this, response, Toast.LENGTH_LONG).show();




                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";

                    } else if (error instanceof ServerError) {
                        message = "The server could not be found. Please try again after some time!!";

                    } else if (error instanceof AuthFailureError) {
                        message = "Cannot connect to Internet...Please check your connection!";

                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";

                    }
                     else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";

                    }
                    Toast.makeText(Registration.this, message, Toast.LENGTH_LONG).show();
                }
            }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params = new HashMap<String, String>();
            params.put("first_name", name.getText().toString());
            params.put("email",email.getText().toString());
            params.put("password",password.getText().toString());
            params.put("mobile_number",phone.getText().toString());
            params.put("registred_with","u");
            return params;
        }
    };

    requestQueue.add(stringRequest);
}
}



