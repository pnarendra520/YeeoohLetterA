package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import yeeooh.android.app.com.yeeoohnewapp.AddressListActivity;
import yeeooh.android.app.com.yeeoohnewapp.R;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.FilterAdapterDrawable;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.FramAdeptor;
import yeeooh.android.app.com.yeeoohnewapp.adeptor.RecyclerItemClickListener;
import yeeooh.android.app.com.yeeoohnewapp.colour.ColorPanelView;
import yeeooh.android.app.com.yeeoohnewapp.colour.ColorPickerDialog;
import yeeooh.android.app.com.yeeoohnewapp.colour.ColorPickerDialogListener;
import yeeooh.android.app.com.yeeoohnewapp.model.FrameList;
import yeeooh.android.app.com.yeeoohnewapp.views.ColorPicker;
import yeeooh.android.app.com.yeeoohnewapp.views.Utility;

/**
 * Created by Narendra on 4/19/2017.
 */

public class LetterFragment extends Fragment implements View.OnClickListener,View.OnTouchListener,ColorPickerDialogListener
  {
      private int _xDelta;
      private int _yDelta;
      int lastAction;
      float dX, dY;
      int i=0;
      Uri uri;
      View memecontentView;
      View v;
      ByteArrayOutputStream bytes;
      private static final int DIALOG_ID = 0;
      private static final float STEP = 200;
ImageView imageView,send_letter,save,preview;
      yeeooh.android.app.com.yeeoohnewapp.colour.ColorPicker colorPicker;
      private float ratio = 1.0f;
      private int baseDistance;
      private float baseRatio;
      EditText letter_edittext;
    private FloatingActionButton fab,fab1,fab2,fab3;
    TextView letter_gradient_colour,letter_plane_colour,image_shades,image_background,image_rotate;
    LinearLayout fount1, fount2, fount3,image_layout;
    RelativeLayout letterone,lettertwo,letterthree;
    ImageView profile_imageview,preview15,preview5;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private Boolean isFabOpen = false;
      TextView textViewd;
    FrameLayout letter_fram;
    ImageView fram_list;
    View view;
    int REQUEST_CAMERA=1;
    int SELECT_FILE=2;
    String userChoosenTask;
    Bitmap thumbnail;
    TextView letter_add_text, text_background,three,four,five, texttype, textsize, text_align,digital,hardcopy,option_private,option_public;
TextView bould,utalic,underline,insetimage,image_crop;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    private static final String TAG = "Touch";
    private int currentBackgroundColor = 0xffffffff;
    private int mPickedColor = Color.WHITE;
      private ColorPickerView colorPickerView;
      private ColorPanelView newColorPanelView;
      private Integer[] mImageIds = {R.mipmap.one, R.mipmap.two, R.mipmap.vali,
              R.mipmap.four, R.mipmap.five, R.mipmap.blue, R.mipmap.yellow, R.mipmap.three,
              R.mipmap.brown, R.mipmap.cyent, R.mipmap.green, R.mipmap.grey,
              R.mipmap.pink, R.mipmap.purple,R.mipmap.limegreen, R.mipmap.grey,R.mipmap.orange, R.mipmap.red,R.mipmap.salmon, R.mipmap.silver,R.mipmap.skyblue, R.mipmap.voilet };
    @SuppressWarnings("unused")
    View colourwheel;
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    Spinner spinner;
    LinearLayout hidden_rcview,hidden_rcview1;
      int count = 0;
      View c;
      int position;
      int screenHight, screenWidth;
    RecyclerView rc_filter,rc_filter1;
      SharedPreferences prefs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.yeeooh_letter_heder, container, false);
       init();

        return view;
    }

    public void init(){
        fab = (FloatingActionButton)view.findViewById(R.id.letter_main_fab);
        preview15=(ImageView)view.findViewById(R.id.preview15);
        preview5=(ImageView)view.findViewById(R.id.preview5);
        colorPicker = (yeeooh.android.app.com.yeeoohnewapp.colour.ColorPicker)view. findViewById(R.id.color_picker);
        fab1 = (FloatingActionButton)view.findViewById(R.id.text_edit_fab);
        fab2 = (FloatingActionButton)view.findViewById(R.id.text_edit_colourfab);
        fab3 = (FloatingActionButton)view.findViewById(R.id.image_fab);
        profile_imageview=(ImageView)view.findViewById(R.id.profile_image);
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_close);
        letter_fram=(FrameLayout) view.findViewById(R.id.letter_fram);
        preview=(ImageView)view.findViewById(R.id.preview) ;
        fount1 =(LinearLayout) view.findViewById(R.id.fount);
        newColorPanelView = (ColorPanelView) view.findViewById(R.id.cpv_color_panel_new);
        fount2 =(LinearLayout) view.findViewById(R.id.font1);
        fount3 =(LinearLayout) view.findViewById(R.id.font3);
        send_letter=(ImageView)view.findViewById(R.id.send_letter);
        image_shades=(TextView)view.findViewById(R.id.image_shades);
        save=(ImageView)view.findViewById(R.id.save);
        letter_gradient_colour=(TextView)view.findViewById(R.id.letter_gradient_colour);
        image_background=(TextView)view.findViewById(R.id.image_background);
        image_rotate=(TextView)view.findViewById(R.id.image_rotate);
        image_crop=(TextView)view.findViewById(R.id.image_crop);
        image_layout=(LinearLayout)view.findViewById(R.id.image_layout);
spinner=(Spinner)view.findViewById(R.id.spinner);
        utalic=(TextView)view.findViewById(R.id.utalic);
        underline=(TextView)view.findViewById(R.id.line) ;
        letter_plane_colour=(TextView)view.findViewById(R.id.letter_plane_colour);
        rotate_forward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_backward);
        letterone=(RelativeLayout)view.findViewById(R.id.flating1);
        lettertwo=(RelativeLayout)view.findViewById(R.id.flating2);
        letterthree=(RelativeLayout)view.findViewById(R.id.floating3);
        rotate_backward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_backward);
        letter_add_text =(TextView)view.findViewById(R.id.letter_add_text);
        letter_edittext=(EditText)view.findViewById(R.id.letter_edittext);
        bould=(TextView)view.findViewById(R.id.bould);
        memecontentView = view.findViewById(R.id.letter_fram);
        v = memecontentView;
        prefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());




        letter_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {

                prefs.edit().putString("autoSave1", s.toString()).commit();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
                prefs.edit().putString("autoSave", s.toString()).commit();

            }

            @Override
            public void afterTextChanged(Editable s)
            {
            // prefs.edit().putString("autoSave2", s.toString()).commit();
            }
        });

        text_background =(TextView)view.findViewById(R.id.text_background);
        three=(TextView)view.findViewById(R.id.ome2);
        four=(TextView)view.findViewById(R.id.letter_gradient_colour);
        five=(TextView) view.findViewById(R.id.letter_plane_colour);
        texttype=(TextView)view.findViewById(R.id.texttype);
        textsize=(TextView)view.findViewById(R.id.textsize);
        text_align=(TextView)view.findViewById(R.id.text_align);
        hidden_rcview=(LinearLayout)view.findViewById(R.id.hidden_rcview);
        hidden_rcview1=(LinearLayout)view.findViewById(R.id.hidden_rcview1);
        rc_filter=(RecyclerView)view.findViewById(R.id.rc_filter);
        rc_filter1=(RecyclerView)view.findViewById(R.id.rc_filter1);
        insetimage=(TextView)view.findViewById(R.id.insetimage);
        fram_list=(ImageView)view.findViewById(R.id.fram_list);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rc_filter.setHasFixedSize(true);
        rc_filter.setLayoutManager(layoutManager);

        FilterAdapterDrawable filterAdapter = new FilterAdapterDrawable(getActivity());
        rc_filter.setAdapter(filterAdapter);
        rc_filter.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                updateImage(position);
            }
        }));
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        Log.e("screen", "" + screenHight + "\n" + screenWidth);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        letter_gradient_colour.setOnClickListener(this);
        profile_imageview.setOnTouchListener(this);
        letter_plane_colour.setOnClickListener(this);
        letter_add_text.setOnClickListener(this);
        text_background.setOnClickListener(this);
        preview15.setOnClickListener(this);
        preview5.setOnClickListener(this);
        bould.setOnClickListener(this);
        insetimage.setOnClickListener(this);
    utalic.setOnClickListener(this);
       underline.setOnClickListener(this);
        send_letter.setOnClickListener(this);
        image_shades.setOnClickListener(this);
        textsize.setOnClickListener(this);
        save.setOnClickListener(this);
        image_background.setOnClickListener(this);
        image_rotate.setOnClickListener(this);
        text_align.setOnClickListener(this);
        image_crop.setOnClickListener(this);
        texttype.setOnClickListener(this);
        fram_list.setOnClickListener(this);
        preview.setOnClickListener(this);
    }

    public void animateFAB(){

        if(isFabOpen){
            fount1.setVisibility(View.GONE);
            fount2.setVisibility(View.GONE);
            fount3.setVisibility(View.GONE);
            colorPicker.setVisibility(View.GONE);
            rc_filter.setVisibility(View.GONE);
        //    rc_filter1.setVisibility(View.GONE);
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = false;

            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            letterone.setVisibility(View.VISIBLE);
            lettertwo.setVisibility(View.VISIBLE);
            letterthree.setVisibility(View.VISIBLE);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }



    public void fab(){
       fount1.setVisibility(View.VISIBLE);
        fount2.setVisibility(View.GONE);
        fount3.setVisibility(View.GONE);
    }


    public void fab1(){
        fount1.setVisibility(View.GONE);
        fount3.setVisibility(View.GONE);
        fount2.setVisibility(View.VISIBLE);
    }

    public void fab2(){
        fount1.setVisibility(View.GONE);
        fount2.setVisibility(View.GONE);
        fount3.setVisibility(View.VISIBLE);
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(getActivity());
                if (items[item].equals("Take Photo")) {
                    userChoosenTask= "Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask= "Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
      private void selectImage1() {
          final CharSequence[] items = {"Test Background", "Test colour",
                  "Cancel" };
          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
          builder.setTitle("Add Colour!");
          builder.setItems(items, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int item) {
                  if (items[item].equals("Test Background")) {
                     textcolour();

                  } else if (items[item].equals("Test colour")) {
                      colour();

                  } else if (items[item].equals("Cancel")) {
                      dialog.dismiss();
                  }
              }
          });
          builder.show();
      }
      public void textcolour(){
          ColorPickerDialog dialog=  ColorPickerDialog.newBuilder().create();
          dialog.setColorPickerDialogListener(this);
          dialog.show(getActivity().getFragmentManager(),"color-picker-dialog");
      }
    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
//code for deny
                }
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        // uri= data.getData();

        thumbnail=null;
        if (data != null) {
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        profile_imageview.setImageBitmap(thumbnail);
      //  profile_imageview.setVisibility(View.VISIBLE);
    }




    private void onCaptureImageResult(Intent data) {
     //   uri=data.getData();
        thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        profile_imageview.setVisibility(View.VISIBLE);
       // profile_imageview.setImageBitmap(thumbnail);
    }

    public void degital() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.letter_options);
         option_private = (TextView) dialog.findViewById(R.id.option_private);
        option_public = (TextView) dialog.findViewById(R.id.option_public);

option_private.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "Pending", Toast.LENGTH_SHORT).show();
    }
});
        option_public.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Pending", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


    }
    public void option() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.letter_options1);
        digital = (TextView) dialog.findViewById(R.id.option_degital);
        hardcopy = (TextView) dialog.findViewById(R.id.option_hardcopy);

        digital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                degital();

            }
        });
        hardcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),AddressListActivity.class));
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


    }


Paint mPaint;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.letter_main_fab:

                animateFAB();
                break;
            case R.id.text_edit_fab:

                fab();
                break;
            case R.id.text_edit_colourfab:
                fab1();
                break;
            case R.id.image_fab:
                fab2();
                break;
            case R.id.letter_gradient_colour:
selectImage1();

               // Toast.makeText(getActivity(), "Nice", Toast.LENGTH_SHORT).show();

               // ColorPickerDialog dialog=new ColorPickerDialog();
/*
                ColorPickerDialog.newBuilder()
                        .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                        .setAllowPresets(false)
                        .setDialogId(DIALOG_ID)
                        .setColor(Color.BLACK)
                        .setShowAlphaSlider(true)
                        .show(getActivity());*/

              /*  ColorPickerDialog dialog=  ColorPickerDialog.newBuilder().create();
                dialog.setColorPickerDialogListener(this);
                dialog.show(getActivity().getFragmentManager(),"color-picker-dialog");*/
               // colour();
                break;
            case R.id.letter_plane_colour:
                   coloursheet();
                Toast.makeText(getActivity(), "Pending", Toast.LENGTH_SHORT).show();
                break;
            case R.id.letter_add_text:
                addTextviewtoView();
               // colorPicker.setVisibility(View.VISIBLE);
                text_align.setVisibility(View.VISIBLE);
                text_background.setVisibility(View.VISIBLE);
//setUp();

                break;
            case R.id.text_background:

                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                textViewd.setBackgroundColor(color);
                break;
            case R.id.preview15:

                letter_edittext.setText(prefs.getString("autoSave", ""));
                letter_edittext.setText(prefs.getString("autoSave1", ""));
               // letter_edittext.setText(prefs.getString("autoSave2", ""));

                break;
            case R.id.preview5:
                letter_edittext.setText("");
                break;
            case R.id.insetimage:
                selectImage();
                break;
            case R.id.send_letter:
                option();
                break;
            case R.id.image_shades:
                profile_imageview.setVisibility(View.VISIBLE);
                hidden_rcview.setVisibility(View.VISIBLE);
                rc_filter.setVisibility(View.VISIBLE);
                break;
            case R.id.textsize:
                bottoumsheet();
                break;
            case R.id.image_background:
                  coloursheet1();
                break;
            case R.id.save:
            takeScreenshot();
                Toast.makeText(getActivity(), "Frame Captured Successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_rotate:
                i++;
                if(i==1){
                    profile_imageview.setScaleY(-1);
                }
                else if (i==2){
                    profile_imageview.setScaleY(1);
                }
                else if (i==3){
                    profile_imageview.setScaleX(-1);
                }
                else if (i==4){
                    profile_imageview.setScaleX(1);
                    i=0;
                }

break;
            case R.id.text_align:
                i++;
                if(i==1){
                    textViewd.setRotation(90);
                }
                else if (i==2){
                    textViewd.setRotation(180);
                }
                else if (i==3){
                    textViewd.setRotation(45);
                }
                else if (i==4){
                    textViewd.setRotation(275);
                }
                else if (i==5){
                    textViewd.setRotation(360);
                    i=0;
                }
                break;
            case R.id.image_crop:
                Toast.makeText(getActivity(), "Pending crop", Toast.LENGTH_SHORT).show();
               // ImageCropFunction();
break;
            case R.id.texttype:

                textfount();
                break;
            case R.id.fram_list:
               i++;
                if (i==1){
                    hidden_rcview1.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Click frame", Toast.LENGTH_SHORT).show();
                    //rc_filter1.setBackgroundColor(getResources().getColor(R.color.app_top_color));
                    fram_list.setBackgroundColor(getResources().getColor(R.color.app_top_color));
                    frames();
                }
                else if (i==2){
                    fram_list.setBackgroundResource((R.drawable.frame));
                  //  rc_filter1.setBackgroundColor(getResources().getColor(R.color.white));
                    hidden_rcview1.setVisibility(View.GONE);
                    i=0;

                }
                break;
            case R.id.preview:
                viewToBitmap(letter_fram);
                Toast.makeText(getActivity(), "Click frame", Toast.LENGTH_SHORT).show();

        }

    }
      private void setUp() {
          colorPicker.setOnColorChangeListener(new yeeooh.android.app.com.yeeoohnewapp.colour.ColorPicker.OnColorChangeListener() {
              @Override
              public void onColorChange(int selectedColor) {
                      textViewd.setBackgroundColor(selectedColor);
              }
          });

      }
      @Override
      public void onColorSelected(int dialogId, @ColorInt int color) {
          switch (dialogId) {
              case DIALOG_ID:
                  // We got result from the dialog that is shown when clicking on the icon in the action bar.
                  Toast.makeText(getActivity(), "Selected Color: #" + Integer.toHexString(color), Toast.LENGTH_SHORT).show();
                  changeBackgroundColor2(color);
                  break;
          }
      }
      private void changeBackgroundColor2(int newColor) {
          currentBackgroundColor = newColor;
          letter_edittext.setBackgroundColor(newColor);
      }
      @Override
      public void onDialogDismissed(int dialogId) {

      }

      private void takeScreenshot() {

          v.setDrawingCacheEnabled(true);
          v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                  View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
          v.layout(0, 0, v.getWidth(), v.getMeasuredHeight());

          v.buildDrawingCache(true);
          Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
          ByteArrayOutputStream bytes = new ByteArrayOutputStream();
          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
    v.destroyDrawingCache();
          Date now = new Date();
          android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
          String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
          File f=new File(mPath);
          try {
              f.createNewFile();
              // write the bytes in file
              FileOutputStream fo = new FileOutputStream(f);
              fo.write(bytes.toByteArray());
              fo.close();
              openScreenshot(f);
          }catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          Toast.makeText(getActivity(),"Image Saved at " + f.getPath(), Toast.LENGTH_SHORT).show();

      }

      private void openScreenshot(File imageFile) {
          Intent intent = new Intent();
          intent.setAction(Intent.ACTION_VIEW);
          Uri uri = Uri.fromFile(imageFile);
          intent.setDataAndType(uri, "image/*");
          startActivity(intent);
      }
      private void addTextviewtoView() {

          textViewd = new TextView(getActivity());

          RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
          //   RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
          textViewd.setLayoutParams(parms);
          // textViewd.setGravity(Gravity.);
          textViewd.setPadding(15, 15, 15, 15);
          //textViewd.setBackgroundColor(R.color.app_color);
          textViewd.setMaxHeight(screenHight / 2);
          textViewd. setBackgroundColor(getActivity().getResources().getColor(android.R.color.holo_green_light));
          // textViewd.setMaxWidth(screenWidth/2);
          Log.e("width ", "" + screenWidth);
          textViewd.setMaxWidth(screenWidth - 100);
          // textViewd.setMaxLines(5);
          // textViewd = new GradientTextview(this);
          //set the gravity too
          textViewd.setText("Text");
          //textViewd.setClickable(true);
          //textViewd.setTag(count++);
          textViewd.setId(count++);
          textViewd.setTextSize(16);
          //adding text
          textViewd.setTextColor(getResources().getColor(R.color.colorAccent));
          //textViewd.setTextColor(selectedColorpicker);
          letter_fram.addView(textViewd);


         textViewd.setOnClickListener(onClickListener);

          //textViewd.setOnTouchListener(otl);
          // textViewd.setOnLongClickListener(longclick);
          // textViewd.setOnDragListener(draglistener);
          textViewd.setOnTouchListener(otl);


      }


      @SuppressLint("AppCompatCustomView")
      public View.OnTouchListener otl = new View.OnTouchListener() {
          public boolean onTouch(View v, MotionEvent event) {


              final int X = (int) event.getRawX();
              final int Y = (int) event.getRawY();

              Log.e("XY", "" + X + "\n" + Y);

              float newX, newY;



              switch (event.getAction() & MotionEvent.ACTION_MASK) {
                  case MotionEvent.ACTION_DOWN:
                      FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) v.getLayoutParams();
                      _xDelta = X - lParams.leftMargin;
                      _yDelta = Y - lParams.topMargin;
                      //  Log.e("_xDelta", "" + _xDelta + "\n" + _yDelta);

                      //  dX = v.getX() - X;
                      //  dY = v.getY() - Y;
                      // lastAction = MotionEvent.ACTION_DOWN;


                      break;


                  case MotionEvent.ACTION_UP:
                      break;
                  case MotionEvent.ACTION_POINTER_DOWN:
                      break;
                  case MotionEvent.ACTION_POINTER_UP:
                      break;
                  case MotionEvent.ACTION_MOVE:
                      FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) v
                              .getLayoutParams();
                      layoutParams.leftMargin = X - _xDelta;
                      layoutParams.topMargin = Y - _yDelta;
                      //   layoutParams.leftMargin = 250;
                      // layoutParams.topMargin = 250;

                      // Log.e("_xDelta",""+_xDelta+"\n"+_yDelta);

                      layoutParams.rightMargin = -250;
                      layoutParams.bottomMargin = -250;
                      // layoutParams.rightMargin = 0;
                      // layoutParams.bottomMargin = 0;


                      newX = event.getRawX() + dX;
                      newY = event.getRawY() + dY;

                      // check if the view out of screen
                      if ((newX <= 0 || newX >= screenWidth - v.getWidth()) || (newY <= 0 || newY >= screenHight - v.getHeight())) {
                          lastAction = MotionEvent.ACTION_MOVE;
                          break;
                      }

                      // v.setX(newX);
                      //  v.setY(newY);

                      //  lastAction = MotionEvent.ACTION_MOVE;
                      v.setLayoutParams(layoutParams);

                      break;
              }


              if (event.getPointerCount() == 2) {
                  int action = event.getAction();
                  int distance = getDistance(event);
                  int pureAction = action & MotionEvent.ACTION_MASK;
                  if (pureAction == MotionEvent.ACTION_POINTER_DOWN) {
                      baseDistance = distance;
                      baseRatio = ratio;
                  } else {
                      float delta = (distance - baseDistance) / STEP;
                      float multi = (float) Math.pow(2, delta);
                      ratio = Math.min(1024.0f, Math.max(0.1f, baseRatio * multi));


                      View c = letter_fram.getChildAt(v.getId());
                      textViewd = (TextView) c;
                      textViewd.setTextSize(ratio + 13);


                  }
              }




              return false;
          }
      };


      /**
       * Returns the distance between text_background pointers on the screen.
       */
      private int getDistance(MotionEvent event) {
          int dx = (int) (event.getX(0) - event.getX(1));
          int dy = (int) (event.getY(0) - event.getY(1));
          return (int) (Math.sqrt(dx * dx + dy * dy));
      }



      View.OnClickListener onClickListener = new View.OnClickListener() {

          @Override
          public void onClick(View v) {

              //  Toast.makeText(MainActivity.this, "dfdsffsfd", Toast.LENGTH_SHORT).show();
              if (v.getTag() != "img") {

                  position = (Integer) v.getId();//layoutViewDealsList.indexOfChild(layoutView);



          }

      };










          /** Calculate the mid point of the first text_background fingers */

      };



      private void createtextDialog(String childTextViewText, final int position) {
          final Dialog d = new Dialog(getActivity());
          d.setContentView(R.layout.layout_font);
          final EditText edt = (EditText) d.findViewById(R.id.editText_font);
          Button btn = (Button) d.findViewById(R.id.button);

          edt.setText(childTextViewText);
          Log.d("Main",childTextViewText);

          btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  c = letter_fram.getChildAt(position);
                  textViewd = (TextView) c;
                  // tv_auto = (TextView) c;
                  // tv_zoom = (TextView) c;
                  textViewd.setText(edt.getText().toString());
                  //  textViewd.setTextColor(selectedColorpicker);

                  //tv_auto.setText(edt.getText().toString());
                  // tv_zoom.setText(edt.getText().toString());
                  //tv_zoom.setZoomEnabled(true);
                  //tv_zoom=new PinchZoomTextView(getApplicationContext()).setZoomEnabled(true);


                  d.dismiss();

              }
          });

          d.show();


      }


      public void colour(){

    final Context context = getActivity();

    ColorPickerDialogBuilder
            .with(context)
            .setTitle("Choose color")
            .initialColor(currentBackgroundColor)
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            .density(12)
            .setOnColorChangedListener(new OnColorChangedListener() {
                @Override
                public void onColorChanged(int selectedColor) {
                    // Handle on color change
                    Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));
                }
            })
            .setOnColorSelectedListener(new OnColorSelectedListener() {
                @Override
                public void onColorSelected(int selectedColor) {
                    //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                }
            })
            .setPositiveButton("ok", new ColorPickerClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                    changeBackgroundColor(selectedColor);
                    if (allColors != null) {
                        StringBuilder sb = null;

                        for (Integer color : allColors) {
                            if (color == null)
                                continue;
                            if (sb == null)
                                sb = new StringBuilder("Color List:");
                            sb.append("\r\n#" + Integer.toHexString(color).toUpperCase());
                        }

                        if (sb != null)
                            Toast.makeText(getActivity(), sb.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            })
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            })
            .showColorEdit(true)
            .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
            .build()
            .show();

}

    private void changeBackgroundColor(int selectedColor) {
        currentBackgroundColor = selectedColor;
        letter_edittext.setTextColor(selectedColor);
    }


      public Bitmap viewToBitmap(View view) {
          Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
          Canvas canvas = new Canvas(bitmap);
          view.draw(canvas);
          return bitmap;
      }





   @Override
    public boolean onTouch(final View v, MotionEvent event) {
        PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
        PointF StartPT = new PointF(); // Record Start Position of 'img'
        final float xc = profile_imageview.getWidth() / 2;
        final float yc = profile_imageview.getHeight() / 2;
        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                profile_imageview.clearAnimation();
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animate(mPrevAngle, mCurrAngle, 0);
                System.out.println(mCurrAngle);
                break;
            }
            case MotionEvent.ACTION_UP : {
                mPrevAngle = mCurrAngle = 0;
                break;
            }
        }
        return true;
    }

    private void animate(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        profile_imageview.startAnimation(rotate);
        System.out.println(mCurrAngle);
    }



    public void updateImage(int position){
        Resources r = getResources();
        Drawable[] layers = new Drawable[2];
        Drawable drawable = new BitmapDrawable(getResources(), thumbnail);
      layers[0] = drawable;
        switch (position) {
            case 0:
                layers[1] = r.getDrawable(R.drawable.wall1box);
                layers[1].setAlpha(90);
                break;

            case 1:
                layers[1] = r.getDrawable(R.drawable.wall2box);
                layers[1].setAlpha(90);
                break;

            case 2:
                layers[1] = r.getDrawable(R.drawable.wall3box);
                layers[1].setAlpha(90);
                break;

            case 3:
                layers[1] = r.getDrawable(R.drawable.wall4box);
                layers[1].setAlpha(90);
                break;

            case 4:
                layers[1] = r.getDrawable(R.drawable.wall5box);
                layers[1].setAlpha(90);
                break;

            case 5:
                layers[1] = r.getDrawable(R.drawable.wall6box);
                layers[1].setAlpha(90);
                break;

            case 6:
                layers[1] = r.getDrawable(R.drawable.wall7box);
                layers[1].setAlpha(90);
                break;

            case 7:
                layers[1] = r.getDrawable(R.drawable.wall8box);
                layers[1].setAlpha(90);
                break;

            case 8:
                layers[1] = r.getDrawable(R.drawable.wall9box);
                layers[1].setAlpha(90);
                break;

            case 9:
                layers[1] = r.getDrawable(R.drawable.wall10box);
                layers[1].setAlpha(90);
                break;

            case 10:
                layers[1] = r.getDrawable(R.drawable.wall11box);
                layers[1].setAlpha(90);
                break;

            case 11:
                layers[1] = r.getDrawable(R.drawable.wall12box);
                layers[1].setAlpha(90);
                break;
        }
        android.graphics.drawable.LayerDrawable layerDrawable = new android.graphics.drawable.LayerDrawable(layers);
        profile_imageview.setImageDrawable(layerDrawable);
        profile_imageview.destroyDrawingCache();
       //rc_filter.setVisibility(View.INVISIBLE);
    }
public void bottoumsheet(){
    final SeekBar customSeekbar;
    final TextView progress;
    ImageView test_size_close;

    final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getActivity());
    View sheetView = getActivity().getLayoutInflater().inflate(R.layout.bottoumsheet, null);
    mBottomSheetDialog.setContentView(sheetView);
    customSeekbar = (SeekBar)sheetView.findViewById(R.id.seekBar_textsize);
    progress = (TextView)sheetView.findViewById(R.id.textView1);
    test_size_close=(ImageView)sheetView.findViewById(R.id.test_size_close);
    test_size_close.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mBottomSheetDialog.cancel();
        }
    });
  //  customSeekbar.setProgress(15);
   // progress.setText("10");
    customSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            letter_edittext.setTextSize(i);
            progress.setText(""+i);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
           /* int p=0;
            if(p<30)
            {
                p=30;
                customSeekbar.setProgress(p);
            }*/
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });
    mBottomSheetDialog.show();
    mBottomSheetDialog.setCanceledOnTouchOutside(false);
    mBottomSheetDialog.setCancelable(true);
}

      public void ImageCropFunction() {

          // Image Crop Code
          try {
             Intent CropIntent = new Intent("com.android.camera.action.CROP");

              CropIntent.setDataAndType(uri, "image/*");

              CropIntent.putExtra("crop", "true");
              CropIntent.putExtra("outputX", 180);
              CropIntent.putExtra("outputY", 180);
              CropIntent.putExtra("aspectX", 3);
              CropIntent.putExtra("aspectY", 4);
              CropIntent.putExtra("scaleUpIfNeeded", true);
              CropIntent.putExtra("return-data", true);

              startActivityForResult(CropIntent, 1);

          } catch (ActivityNotFoundException e) {

          }
      }


      public void coloursheet(){

             ColorPickerDialog dialog=  ColorPickerDialog.newBuilder().create();

                dialog.show(getActivity().getFragmentManager(),"color-picker-dialog");
          dialog.setColorPickerDialogListener(new ColorPickerDialogListener() {
              @Override
              public void onColorSelected(int dialogId, @ColorInt int color) {
                  changeBackgroundColor1(color);
              }

              @Override
              public void onDialogDismissed(int dialogId) {

              }
          });


      }

      private void changeBackgroundColor1(int newColor) {
          currentBackgroundColor = newColor;
          letter_fram.setBackgroundColor(newColor);
      }
      public void coloursheet1() {
          final yeeooh.android.app.com.yeeoohnewapp.colour.ColorPickerView colorPickerView;
          final ColorPanelView newColorPanelView;
          final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getActivity());
          View sheetView = getActivity().getLayoutInflater().inflate(R.layout.activity_color_picker, null);
          mBottomSheetDialog.setContentView(sheetView);

          colorPickerView = ( yeeooh.android.app.com.yeeoohnewapp.colour.ColorPickerView)sheetView. findViewById(R.id.cpv_color_picker_view);
          ColorPanelView colorPanelView = (ColorPanelView) sheetView. findViewById(R.id.cpv_color_panel_old);
          newColorPanelView = (ColorPanelView) sheetView. findViewById(R.id.cpv_color_panel_new);

          Button btnOK = (Button)sheetView.  findViewById(R.id.okButton);
          Button btnCancel = (Button) sheetView. findViewById(R.id.cancelButton);
/*
btnCancel.setBackgroundColor(getResources().getColor(R.color.app_color));
          btnOK.setBackgroundColor(getResources().getColor(R.color.app_top_color));
*/
          ((LinearLayout) colorPanelView.getParent())
                  .setPadding(colorPickerView.getPaddingLeft(), 0, colorPickerView.getPaddingRight(), 0);


          colorPickerView.setOnColorChangedListener(new yeeooh.android.app.com.yeeoohnewapp.colour.ColorPickerView.OnColorChangedListener() {
              @Override
              public void onColorChanged(int newColor) {
                  newColorPanelView.setColor(colorPickerView.getColor());
                  //changeBackgroundColor1(newColor);
              }
          });
          btnOK.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  // newColorPanelView.setColor(colorPickerView.getColor());
                  changeBackgroundColor3(colorPickerView.getColor());
                  mBottomSheetDialog.dismiss();

              }
          });
          btnCancel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mBottomSheetDialog.dismiss();
              }
          });
          mBottomSheetDialog.show();
          mBottomSheetDialog.setCanceledOnTouchOutside(false);
      }

      private void changeBackgroundColor3(int newColor) {
          currentBackgroundColor = newColor;
          profile_imageview.setBackgroundColor(newColor);
      }
       BottomSheetDialog mBottomSheetDialog;
public void textfount(){
    String[] fount = new String[]{"Roboto","RobotoThin","RobotoItalic","Barrio","Source Sans Pro","Raleway","LoraBold","GloriaHallelujah","Pacifico","ShadowsLight",
            "Righteous","Courgette","Cookie","Chewy","Handlee","Cardo","JosefinSlab","Vollkorn","Ubuntu","OldStandardTT",
    "LoraItalic","CardoItalic","JosefinSlabBold","JosefinSlabItalic","StandardItalic"};
    //initializing an ArrayList from array
    List<String> list = new ArrayList<String>(Arrays.asList(fount));


      mBottomSheetDialog = new BottomSheetDialog(getActivity());
    View sheetView = getActivity().getLayoutInflater().inflate(R.layout.gridview, null);
    mBottomSheetDialog.setContentView(sheetView);
    GridView gd = (GridView)mBottomSheetDialog. findViewById(R.id.grid);
    ImageView close = (ImageView) mBottomSheetDialog. findViewById(R.id.close);
    close.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mBottomSheetDialog.dismiss();
        }
    });
    //data bind GridView with ArrayAdapter
    FountAdeptor adeptor=new FountAdeptor(getActivity(),list);
    gd.setAdapter(adeptor);

gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String string = (parent.getItemAtPosition(position).toString());
        int i=Integer.parseInt(string);
        fountposition(i);
        //Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();
    }
});
    mBottomSheetDialog.setCanceledOnTouchOutside(false);
    mBottomSheetDialog.show();


}
      Typeface tf;
public void fountposition(int string){
   switch (string){
       case 0:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Roboto-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();

           break;
       case 1:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Roboto-Thin.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 2:
      tf=Typeface.createFromAsset(getActivity().getAssets(),"Roboto-ThinItalic.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 3:
            tf=Typeface.createFromAsset(getActivity().getAssets(),"Barrio-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();

           break;
       case 4:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"SourceSansPro-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 5:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Raleway-Light.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 6:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Lora-BoldItalic.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 7:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"GloriaHallelujah.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 8:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Pacifico-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 9:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"ShadowsIntoLight.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 10:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Righteous-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 11:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Courgette-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 12:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Cookie-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 13:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Chewy.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 14:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Handlee-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 15:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Cardo-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 16:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"JosefinSlab-Bold.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 17:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Vollkorn-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 18:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Ubuntu-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 19:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"OldStandard-Regular.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 20:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Lora-Italic.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 21:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"Cardo-Italic.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 22:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"JosefinSlab-Light.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 23:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"JosefinSlab-Italic.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;
       case 24:
           tf=Typeface.createFromAsset(getActivity().getAssets(),"OldStandard-Italic.ttf");
           letter_edittext.setTypeface(tf);
           mBottomSheetDialog.dismiss();
           break;


   }

}

public class FountAdeptor extends BaseAdapter{
FragmentActivity activity;
    List<String>fount;
    public FountAdeptor(FragmentActivity activity, List<String> fount) {
        this.activity=activity;
        this.fount=fount;

    }

    @Override
    public int getCount() {
        return fount.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.
                from(activity).
                inflate(R.layout.fount_list, parent, false);

TextView normal= (TextView) itemView.findViewById(R.id.normala);

        normal.setText(fount.get(position));


        switch (position)
        {
        case 0:
        tf=Typeface.createFromAsset(activity.getAssets(),"Roboto-Regular.ttf");
            normal.setTypeface(tf);


        break;
        case 1:
        tf=Typeface.createFromAsset(activity.getAssets(),"Roboto-Thin.ttf");
            normal.setTypeface(tf);

        break;
        case 2:
        tf=Typeface.createFromAsset(activity.getAssets(),"Roboto-ThinItalic.ttf");
            normal.setTypeface(tf);

        break;
        case 3:
        tf=Typeface.createFromAsset(activity.getAssets(),"Barrio-Regular.ttf");
            normal.setTypeface(tf);


        break;
        case 4:
        tf=Typeface.createFromAsset(activity.getAssets(),"SourceSansPro-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 5:
        tf=Typeface.createFromAsset(activity.getAssets(),"Raleway-Light.ttf");
            normal.setTypeface(tf);

        break;
        case 6:
        tf=Typeface.createFromAsset(activity.getAssets(),"Lora-BoldItalic.ttf");
            normal.setTypeface(tf);

        break;
        case 7:
        tf=Typeface.createFromAsset(activity.getAssets(),"GloriaHallelujah.ttf");
            normal.setTypeface(tf);

        break;
        case 8:
        tf=Typeface.createFromAsset(activity.getAssets(),"Pacifico-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 9:
        tf=Typeface.createFromAsset(activity.getAssets(),"ShadowsIntoLight.ttf");
            normal.setTypeface(tf);

        break;
        case 10:
        tf=Typeface.createFromAsset(activity.getAssets(),"Righteous-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 11:
        tf=Typeface.createFromAsset(activity.getAssets(),"Courgette-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 12:
        tf=Typeface.createFromAsset(activity.getAssets(),"Cookie-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 13:
        tf=Typeface.createFromAsset(activity.getAssets(),"Chewy.ttf");
            normal.setTypeface(tf);

        break;
        case 14:
        tf=Typeface.createFromAsset(activity.getAssets(),"Handlee-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 15:
        tf=Typeface.createFromAsset(activity.getAssets(),"Cardo-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 16:
        tf=Typeface.createFromAsset(activity.getAssets(),"JosefinSlab-Bold.ttf");
            normal.setTypeface(tf);

        break;
        case 17:
        tf=Typeface.createFromAsset(activity.getAssets(),"Vollkorn-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 18:
        tf=Typeface.createFromAsset(activity.getAssets(),"Ubuntu-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 19:
        tf=Typeface.createFromAsset(activity.getAssets(),"OldStandard-Regular.ttf");
            normal.setTypeface(tf);

        break;
        case 20:
        tf=Typeface.createFromAsset(activity.getAssets(),"Lora-Italic.ttf");
            normal.setTypeface(tf);

        break;
        case 21:
        tf=Typeface.createFromAsset(activity.getAssets(),"Cardo-Italic.ttf");
            normal.setTypeface(tf);

        break;
        case 22:
        tf=Typeface.createFromAsset(activity.getAssets(),"JosefinSlab-Light.ttf");
            normal.setTypeface(tf);

        break;
        case 23:
        tf=Typeface.createFromAsset(activity.getAssets(),"JosefinSlab-Italic.ttf");
            normal.setTypeface(tf);

        break;
        case 24:
        tf=Typeface.createFromAsset(activity.getAssets(),"OldStandard-Italic.ttf");
            normal.setTypeface(tf);

        break;


    }


        return itemView;
    }
}
public void frames(){
    //Gallery g = (Gallery) view.findViewById(R.id.gallery);


   // g.setAdapter(new FramAdeptor(getActivity(), mImageIds));
    LinearLayoutManager layoutManager
            = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
    rc_filter1.setHasFixedSize(true);
    rc_filter1.setLayoutManager(layoutManager);
    ArrayList<FrameList> createLists = prepareData();
    FramAdeptor filterAdapter1 = new FramAdeptor(getActivity(),createLists);
    rc_filter1.setAdapter(filterAdapter1);

    rc_filter1.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            letter_fram.setBackgroundResource(mImageIds[position]);

        }
    }));



}
      private ArrayList<FrameList> prepareData(){

          ArrayList<FrameList> theimage = new ArrayList<>();
          for(int i = 0; i< mImageIds.length; i++){
              FrameList createList = new FrameList();
              //createList.setImage_title(image_titles[i]);
              createList.setImage_ID(mImageIds[i]);
              theimage.add(createList);
          }
          return theimage;
      }
  }





