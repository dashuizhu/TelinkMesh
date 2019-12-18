package com.telink.bluetooth.light.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.telink.bluetooth.light.R;
import com.telink.bluetooth.light.TelinkMeshErrorDealActivity;

public class GroupDetailActivity extends TelinkMeshErrorDealActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
    }

    @Override
    protected void onLocationEnable() {

    }
}
