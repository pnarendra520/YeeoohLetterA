package yeeooh.android.app.com.yeeoohnewapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Field;
import yeeooh.android.app.com.yeeoohnewapp.database.DBManager;
import yeeooh.android.app.com.yeeoohnewapp.database.Yeeoohdatabase;
import yeeooh.android.app.com.yeeoohnewapp.views.CircleImageView;

/**
 * Created by YEEOOH on 4/24/2017.
 */

public class AddressActivity extends AppCompatActivity {
    EditText name, pincode, address, landmark, city, state, phonenumber,email;
    TextView cancel, Save,Delete;
    String name1, pincode1, address1, landmark1, city1, state1, phonenumber1,address_Email;
    Toolbar toolbar, searchtollbar;
    Menu search_menu;
    MenuItem item_search;
    CircleImageView imageView;
    DBManager dbManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        toolbar = (Toolbar) findViewById(R.id.heder);
        setSupportActionBar(toolbar);
        imageView = (CircleImageView) findViewById(R.id.dash_profile);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, ProfileActivity.class));
            }
        });
        init();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setSearchtollbar();
        dbManager = new DBManager(this);
        dbManager.open();
    }

    public void init() {
        name = (EditText) findViewById(R.id.address_name);
        pincode = (EditText) findViewById(R.id.address_pincode);
        address = (EditText) findViewById(R.id.address_address);
        landmark = (EditText) findViewById(R.id.address_landmark);
        city = (EditText) findViewById(R.id.address_city);
        email=(EditText) findViewById(R.id.address_Email);
        state = (EditText) findViewById(R.id.address_state);
        phonenumber = (EditText) findViewById(R.id.address_phonenumber);
        cancel = (TextView) findViewById(R.id.address_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_right, R.anim.slide_right);
            }
        });
        Delete = (TextView) findViewById(R.id.delete);
        Save = (TextView) findViewById(R.id.address_save);
        Delete.setVisibility(View.GONE);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.action_search:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    circleReveal(R.id.searchtoolbar, 1, true, true);
                else
                    searchtollbar.setVisibility(View.VISIBLE);

                item_search.expandActionView();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
     /*   SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/

        return true;
    }

    public void setSearchtollbar() {
        searchtollbar = (Toolbar) findViewById(R.id.searchtoolbar);
        if (searchtollbar != null) {
            searchtollbar.inflateMenu(R.menu.menu_search);
            search_menu = searchtollbar.getMenu();

            searchtollbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(R.id.searchtoolbar, 1, true, false);
                    else
                        searchtollbar.setVisibility(View.GONE);
                }
            });

            item_search = search_menu.findItem(R.id.action_filter_search);

            MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    // Do something when collapsed
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(R.id.searchtoolbar, 1, true, false);
                    } else
                        searchtollbar.setVisibility(View.GONE);
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    // Do something when expanded
                    return true;
                }
            });

            initSearchView();


        } else
            Log.d("toolbar", "setSearchtollbar: NULL");
    }

    public void initSearchView() {
        final SearchView searchView =
                (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();

        // Enable/Disable Submit button in the keyboard

        searchView.setSubmitButtonEnabled(false);

        // Change search close button image

        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close);


        // set hint and the text colors

        EditText txtSearch = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint("Search..");
        txtSearch.setHintTextColor(Color.DKGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.colorPrimary));


        // set the cursor

        AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callSearch(newText);
                return true;
            }

            public void callSearch(String query) {
                //Do searching
                Log.i("query", "" + query);


            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void circleReveal(int viewID, int posFromRight, boolean containsOverflow, final boolean isShow) {
        final View myView = findViewById(viewID);

        int width = myView.getWidth();

        if (posFromRight > 0)
            width -= (posFromRight * getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)) - (getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) / 2);
        if (containsOverflow)
            width -= getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx = width;
        int cy = myView.getHeight() / 2;

        Animator anim;
        if (isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, (float) width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float) width, 0);

        anim.setDuration((long) 220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShow) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // make the view visible and start the animation
        if (isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();


    }

    public void save() {
        name1 = name.getText().toString();
        pincode1 = pincode.getText().toString();
        address_Email=email.getText().toString();
        address1 = address.getText().toString();
        landmark1 = landmark.getText().toString();
        city1 = city.getText().toString();
        state1 = state.getText().toString();
        phonenumber1 = phonenumber.getText().toString();

        if (name1.length()==0||pincode1.length()==0||address_Email.length()==0||address1.length()==0
                ||landmark1.length()==0||city1.length()==0||state1.length()==0||phonenumber1.length()==0){
            Toast.makeText(AddressActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            Toast.makeText(AddressActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
        }else if (phonenumber.getText().toString().length()!=10){
            Toast.makeText(AddressActivity.this,"Mobile number required 10 digits", Toast.LENGTH_SHORT).show();
        }
        else {
            dbManager.insert(name1,address_Email, address1, pincode1, city1, landmark1, phonenumber1, state1);
            Intent intent=new Intent(new Intent(AddressActivity.this,AddressListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            startActivity(intent);
        }

       // startActivity(new Intent(AddressActivity.this,AddressListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
/*if (name1.length()==0||pincode1.length()==0||address1.length()==0||landmark1.length()==0||city1.length()==0||state1.length()==0||phonenumber1.length()==0){
    Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show();
}
else if (phonenumber1.length()!=10){
    Toast.makeText(this, "Mobile number contains 10 digits", Toast.LENGTH_LONG).show();
}
else {
    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
     db.insert(1,"",name1,pincode1,address1,landmark1,city1,state1,phonenumber1);
}
}*/


