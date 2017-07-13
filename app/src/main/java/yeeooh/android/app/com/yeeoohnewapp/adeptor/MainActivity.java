package yeeooh.android.app.com.yeeoohnewapp.adeptor;

/**
 * Created by Narendra on 5/10/2017.
 */

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import yeeooh.android.app.com.yeeoohnewapp.R;
public class MainActivity extends Activity {
    String title2 = "developed by Google";
    String stringNormal = "Android is a mobile operating system developed by Google, based on the Linux kernel";
    SpannableString styledTitle, styledBody;

    TextView text_Title, text_Title2, text_Body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spanable);

        text_Title = (TextView) findViewById(R.id.text_title);
        text_Title2 = (TextView) findViewById(R.id.text_title2);
        text_Body = (TextView) findViewById(R.id.text_body);

        Typeface typeface_Google = Typeface.createFromAsset(getAssets(),"product-sans.ttf");
        Typeface typeface_Android = Typeface.createFromAsset(getAssets(),"IDroid.otf");
        text_Title.setText("android");
        text_Title.setTypeface(typeface_Android);
        text_Title.setTextColor(Color.parseColor("#34A853"));
        text_Title.setTextSize(30);

        text_Title2.setTypeface(typeface_Google);
        styledTitle = new SpannableString(title2);
        styledTitle.setSpan(new RelativeSizeSpan(1f), 0, 12, 0);//to increase the font size
        styledTitle.setSpan(new RelativeSizeSpan(1.5f), 13, 19, 0);
        styledTitle.setSpan(new ForegroundColorSpan(Color.parseColor("#4285F4")), 13, 14, 0);//to change the foreground color of text
        styledTitle.setSpan(new ForegroundColorSpan(Color.parseColor("#EA4335")), 14, 15, 0);
        styledTitle.setSpan(new ForegroundColorSpan(Color.parseColor("#FBBC05")), 15, 16, 0);
        styledTitle.setSpan(new ForegroundColorSpan(Color.parseColor("#4285F4")), 16, 17, 0);
        styledTitle.setSpan(new ForegroundColorSpan(Color.parseColor("#34A853")), 17, 18, 0);
        styledTitle.setSpan(new ForegroundColorSpan(Color.parseColor("#EA4335")), 18, 19, 0);
        text_Title2.setText(styledTitle);

        styledBody = new SpannableString(stringNormal);
        styledBody.setSpan(new StyleSpan(Typeface.BOLD), 0, 7, 0);//to make the text BOLD
        styledBody.setSpan(new StyleSpan(Typeface.ITALIC), 7, 12, 0);//to make the text ITALIC
        styledBody.setSpan(new UnderlineSpan(), 13, 36, 0);//to make underline
        styledBody.setSpan(new StrikethroughSpan(), 37, 49, 0);//to strike the text
        styledBody.setSpan(new BackgroundColorSpan(Color.YELLOW), 50, 56, 0);//to change the background color
        styledBody.setSpan(new SuperscriptSpan(), 57, 63, 0);//to add superscript
        styledBody.setSpan(new SubscriptSpan(), 64, 70, 0);//to add subscript
        styledBody.setSpan(new URLSpan("http://www.google.com"), 50, 56, 0);//URL
        ClickableSpan clickableSpan = new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        };
        styledBody.setSpan(clickableSpan, 71, 83, 0);//for clickable text
        text_Body.setMovementMethod(LinkMovementMethod.getInstance());
        text_Body.setText(styledBody);

    }

}