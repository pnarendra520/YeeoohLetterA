package yeeooh.android.app.com.yeeoohnewapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by YEEOOH on 5/13/2017.
 */

public class ProgressActivity extends AppCompatActivity {
    SeekBar customSeekbar;
    TextView progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        customSeekbar = (SeekBar)findViewById(R.id.seekBar_textsize);
        progress = (TextView)findViewById(R.id.textView1);

        customSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                progress.setText( " " + i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
