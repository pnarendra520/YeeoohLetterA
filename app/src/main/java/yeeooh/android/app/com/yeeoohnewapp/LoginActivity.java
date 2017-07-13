package yeeooh.android.app.com.yeeoohnewapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
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

import java.util.HashMap;
import java.util.Map;

import yeeooh.android.app.com.yeeoohnewapp.api.YeoohServices;

/**
 * Created by YEEOOH on 4/18/2017.
 */

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView forgot_password,login_signin, facebook, google,register;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeeooh_app_login);

init();
        Log.e("network", "" + isNetworkAvailable());

        if (isNetworkAvailable()) {

            Toast.makeText(this, "internet connection evalable", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Cheek internet connection ", Toast.LENGTH_SHORT).show();
        }
    }

    public void init() {
        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);
        forgot_password=(TextView) findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpass();
            }
        });




        google = (TextView) findViewById(R.id.google);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Pending", Toast.LENGTH_SHORT).show();
            }
        });
        facebook = (TextView) findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Pending", Toast.LENGTH_SHORT).show();
            }
        });
        register = (TextView) findViewById(R.id.register);
        login_signin = (TextView) findViewById(R.id.login_signin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Registration.class));
                overridePendingTransition(R.anim.slide_left, R.anim.slide_left);
            }
        });
        login_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*if (email.getText().toString().length()==0||password.getText().toString().length()==0){
   Toast.makeText(LoginActivity.this, "Fields Not Empty", Toast.LENGTH_LONG).show();
}
                else if (!isValidEmail(email.getText().toString())){
    email.setError("Valid Email");
                }
                else {
    //server();
                }*/

              startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
        });
    }
    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public void forgotpass() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_forgot_pass);
        final EditText editText = (EditText) dialog.findViewById(R.id.for_edit);
        final EditText editText1 = (EditText) dialog.findViewById(R.id.for_edit1);
        editText1.setVisibility(View.GONE);
        TextView button = (TextView) dialog.findViewById(R.id.for_buttoun);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValidEmail(editText.getText().toString())) {
                    Toast toast = Toast.makeText(LoginActivity.this, "Please Enter Valid email", Toast.LENGTH_LONG);
                    toast.show();

                } else {
                 fotgotpassserver(editText.getText().toString());
                 /*Toast toast = Toast.makeText(LoginActivity.this, "Pending", Toast.LENGTH_LONG);
                  toast.show();*/

                }
            }
        });
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

    }



    public void fotgotpassserver(final String s){
        requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, YeoohServices.Yee_Forgotpass,
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
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                            else if (status==0){
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();




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
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
               // params.put("password",password.getText().toString());
                params.put("email",s);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public void server(){
         requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, YeoohServices.Yee_LOGIN,
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
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            else if (status==0){
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                            else {
                                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();




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
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("password",password.getText().toString());
                params.put("email",email.getText().toString());

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
