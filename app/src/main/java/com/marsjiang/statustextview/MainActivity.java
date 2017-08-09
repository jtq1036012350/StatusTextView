package com.marsjiang.statustextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marsjiang.statustextview.view.MyScanStateTextView;

public class MainActivity extends AppCompatActivity {
    private MyScanStateTextView myScanStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myScanStateTextView = (MyScanStateTextView) findViewById(R.id.tv_mystate);
        myScanStateTextView.setUnscaned();
    }
}
