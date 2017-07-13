package yeeooh.android.app.com.yeeoohnewapp.views;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by YEEOOH on 3/30/2017.
 */

public class RotationGestureDetector extends FrameLayout {

    private static final int INVALID_POINTER_ID = -1;
    private float fX, fY, sX, sY;
    private int ptrID1, ptrID2;
    private float mAngle;

    private double centerX, centerY;

    private OnRotationGestureListener mListener;



    public float getAngle() {
        return mAngle;
    }

    public RotationGestureDetector(OnRotationGestureListener listener){
        super((Context) listener);
        mListener = listener;
        ptrID1 = INVALID_POINTER_ID;
        ptrID2 = INVALID_POINTER_ID;
    }




    public boolean onTouchEvent(MotionEvent event){

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                ptrID1 = event.getPointerId(event.getActionIndex());

             /*   centerX = RotationGestureDetector.this.getX()+
                        ((View)RotationGestureDetector.this.getParent()).getX()+
                        (float)RotationGestureDetector.this.getWidth()/2;
*/

             /*   //double statusBarHeight = Math.ceil(25 * getContext().getResources().getDisplayMetrics().density);
                int result = 0;
                int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    result = getResources().getDimensionPixelSize(resourceId);
                }
                double statusBarHeight = result;
                centerY = RotationGestureDetector.this.getY()+
                        ((View)RotationGestureDetector.this.getParent()).getY()+
                        statusBarHeight+
                        (float)RotationGestureDetector.this.getHeight()/2;*/



                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                ptrID2 = event.getPointerId(event.getActionIndex());
                sX = event.getX(event.findPointerIndex(ptrID1));
                sY = event.getY(event.findPointerIndex(ptrID1));
                fX = event.getX(event.findPointerIndex(ptrID2));
                fY = event.getY(event.findPointerIndex(ptrID2));
                break;
            case MotionEvent.ACTION_MOVE:
                if(ptrID1 != INVALID_POINTER_ID && ptrID2 != INVALID_POINTER_ID){
                    float nfX, nfY, nsX, nsY;
                    nsX = event.getX(event.findPointerIndex(ptrID1));
                    nsY = event.getY(event.findPointerIndex(ptrID1));
                    nfX = event.getX(event.findPointerIndex(ptrID2));
                    nfY = event.getY(event.findPointerIndex(ptrID2));

                    mAngle = angleBetweenLines(fX, fY, sX, sY, nfX, nfY, nsX, nsY);




                   /* double angle = Math.atan2(event.getRawY() - centerY, event.getRawX() - centerX) * 180 / Math.PI;
                    Log.v("asdad", "log angle: " + angle);

                     mAngle=(float)angle;
*/



                    if (mListener != null) {
                        mListener.OnRotation(this);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                ptrID1 = INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                ptrID2 = INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_CANCEL:
                ptrID1 = INVALID_POINTER_ID;
                ptrID2 = INVALID_POINTER_ID;
                break;
        }

        return true;
    }



    private float angleBetweenLines (float fX, float fY, float sX, float sY, float nfX, float nfY, float nsX, float nsY)
    {
       // float angle1 = (float) Math.atan2( (fY - sY), (fX - sX) );
        //float angle2 = (float) Math.atan2( (nfY - nsY), (nfX - nsX) );



        float angle1 = (float) Math.atan2( (fY - sY), (fX - sX) );
        float angle2 = (float) Math.atan2( (nfY - nsY), (nfX - nsX) );




        float angle = ((float)Math.toDegrees(angle1 - angle2)) % 360;
        if (angle < -180.f) angle += 360.0f;
        if (angle > 180.f) angle -= 360.0f;
        return angle;
    }


    public  interface OnRotationGestureListener{

        void OnRotation(RotationGestureDetector rotationGestureDetector);
    }

}
