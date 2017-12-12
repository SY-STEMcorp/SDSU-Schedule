package com.example.root.sdsu_gmap;

import android.widget.HorizontalScrollView;

/**
 * Created by root on 11/8/17.
 */

public class ScheduleScrollViewController {

    private static final int stepSize = 180;

    public static void CreateListeners(final HorizontalScrollView scrollView)
    {
        scrollView.setOnTouchListener(new OnSwipeTouchListener() {

            private double remain = 0;

            @Override
            public void onMoveSidewards(float dX)
            {
                remain += (-dX * 3 / scrollView.getWidth()) * stepSize;
                if(Math.abs(remain) >= 1.0) {
                    scrollView.smoothScrollBy((int) remain, 0);
                    remain -= Math.floor(remain);
                }
            }

            @Override
            public void onMoveStop()
            {
                int newX = (int)Math.round((double)scrollView.getScrollX() / (double)stepSize) * stepSize;
                scrollView.smoothScrollTo(newX, 0);
            }

        });

    }

}
