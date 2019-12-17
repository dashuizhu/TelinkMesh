package com.telink.bluetooth.light.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jtcf.lib_common.view.HeaderView;
import com.telink.bluetooth.light.R;
import com.telink.bluetooth.light.TelinkMeshErrorDealActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends TelinkMeshErrorDealActivity {

    public final int REQUEST_CONFIG = 11;

    @BindView(R.id.headerView)  HeaderView mHeaderView;
    @BindView(R.id.btn_add)     Button     mBtnAdd;
    @BindView(R.id.btn_config)  Button     mBtnConfig;
    @BindView(R.id.btn_devices) Button     mBtnDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @Override
    protected void onLocationEnable() {

    }



    @OnClick({R.id.btn_add, R.id.btn_config, R.id.btn_devices, R.id.layout_header_back, R.id.layout_header_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                break;
            case R.id.btn_config:
                startActivityForResult(new Intent(this, ConfigActivity.class), REQUEST_CONFIG);
                break;
            case R.id.btn_devices:
                break;
            case R.id.layout_header_back:
                break;
            case R.id.layout_header_right:
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CONFIG:
                break;
            default:
        }
    }
}
