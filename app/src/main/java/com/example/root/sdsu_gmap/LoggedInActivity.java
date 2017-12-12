package com.example.root.sdsu_gmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.HorizontalScrollView;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        ScheduleScrollViewController.CreateListeners((HorizontalScrollView) findViewById(R.id.ScheduleScrollView));
    }


}
