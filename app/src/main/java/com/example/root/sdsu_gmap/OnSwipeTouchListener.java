package com.example.root.sdsu_gmap;

/**
 * Created by root on 11/8/17.
 */

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

    private boolean wasDown = false;
    private float lastX = 0;
    //private float lastY = 0;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                wasDown = true;
                lastX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if(wasDown)
                    onMoveStop();
                wasDown = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(wasDown)
                    onMoveSidewards(event.getX() - lastX);
                wasDown = true;
                lastX = event.getX();
                break;
        }

        return true;
    }

    public void onMoveSidewards(float dX)
    {

    }

    public void onMoveStop()
    {

    }
}
