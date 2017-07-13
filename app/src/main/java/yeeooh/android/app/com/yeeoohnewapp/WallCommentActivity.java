package yeeooh.android.app.com.yeeoohnewapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.data5tream.emojilib.EmojiEditText;
import com.github.data5tream.emojilib.EmojiGridView;
import com.github.data5tream.emojilib.EmojiPopup;
import com.github.data5tream.emojilib.emoji.Emojicon;

/**
 * Created by YEEOOH on 5/19/2017.
 */

public class WallCommentActivity extends AppCompatActivity implements View.OnClickListener{
ImageView wall_comment_send;
    TextView wall_comment_share,wall_comment,wall_love;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wall_comment_screen);
       // wall_comment_send=(ImageView)findViewById(R.id.wall_comment_send);
        wall_comment_share=(TextView) findViewById(R.id.wall_comment_share);
        wall_comment=(TextView) findViewById(R.id.wall_comment);
        wall_love=(TextView) findViewById(R.id.wall_love);

        final EmojiEditText emojiEditText = (EmojiEditText) findViewById(R.id.emojicon_edit_text);
        final View rootView = findViewById(R.id.root_view);
        final ImageButton emojiButton = (ImageButton) findViewById(R.id.emoji_btn);
        final EmojiPopup popup = new EmojiPopup(rootView, this, getResources().getColor(R.color.app_bottom_color));

        emojiEditText.requestFocus();

        popup.setSizeForSoftKeyboard();

        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                changeEmojiKeyboardIcon(emojiButton, R.drawable.emoji_1f600);
            }
        });

        popup.setOnSoftKeyboardOpenCloseListener(new EmojiPopup.OnSoftKeyboardOpenCloseListener() {

            @Override
            public void onKeyboardOpen(int keyBoardHeight) {

            }

            @Override
            public void onKeyboardClose() {
                if (popup.isShowing())
                    popup.dismiss();
            }
        });

        popup.setOnEmojiconClickedListener(new EmojiGridView.OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                if (emojicon == null) {
                    return;
                }

                int start = emojiEditText.getSelectionStart();
                int end = emojiEditText.getSelectionEnd();
                if (start < 0) {
                    emojiEditText.append(emojicon.getEmoji());
                } else {
                    emojiEditText.getText().replace(Math.min(start, end),
                            Math.max(start, end), emojicon.getEmoji(), 0,
                            emojicon.getEmoji().length());
                }
            }
        });

        popup.setOnEmojiconBackspaceClickedListener(new EmojiPopup.OnEmojiconBackspaceClickedListener() {

            @Override
            public void onEmojiconBackspaceClicked(View v) {
                KeyEvent event = new KeyEvent(
                        0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                emojiEditText.dispatchKeyEvent(event);
            }
        });

        emojiButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!popup.isShowing()){

                    if(popup.isKeyBoardOpen()){
                        popup.showAtBottom();
                        changeEmojiKeyboardIcon(emojiButton, R.drawable.emoji_2328);
                    }

                    else{
                        emojiEditText.setFocusableInTouchMode(true);
                        emojiEditText.requestFocus();
                        popup.showAtBottomPending();
                        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(emojiEditText, InputMethodManager.SHOW_IMPLICIT);
                        changeEmojiKeyboardIcon(emojiButton, R.drawable.emoji_2328);
                    }
                }

                else{
                    popup.dismiss();
                }
            }
        });
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

       /* wall_comment_share.setOnClickListener(this);
        wall_comment_send.setOnClickListener(this);
        wall_love.setOnClickListener(this);
        wall_comment.setOnClickListener(this);*/
    }
    private void changeEmojiKeyboardIcon(ImageButton iconToBeChanged, int drawableResourceId){
        iconToBeChanged.setImageResource(drawableResourceId);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wall_comment_share:
                shareIntent();
                break;
          /*  case R.id.wall_comment_send:
                Toast.makeText(this, "Developing", Toast.LENGTH_SHORT).show();*/

            case R.id.wall_comment:
                bottoumsheetComment();
                break;
            case R.id.wall_love:
                Toast.makeText(this, "Developing", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public void shareIntent(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
        startActivity(Intent.createChooser(sharingIntent,"Share using"));
    }


    public void bottoumsheetComment(){
        final SeekBar customSeekbar;
        final TextView progress;
        ImageView test_size_close;

        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(WallCommentActivity.this);
        View sheetView = getLayoutInflater().inflate(R.layout.people, null);
        mBottomSheetDialog.setContentView(sheetView);

        mBottomSheetDialog.show();

    }





}
