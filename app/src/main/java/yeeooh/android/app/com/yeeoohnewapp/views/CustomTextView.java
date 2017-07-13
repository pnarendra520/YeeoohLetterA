package yeeooh.android.app.com.yeeoohnewapp.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Employee on 3/24/2017.
 */

public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context ctx)
    {
        Typeface tf=Typeface.createFromAsset(ctx.getAssets(),"helivatica_regular.ttf");
        this.setTypeface(tf);
    }

























}
