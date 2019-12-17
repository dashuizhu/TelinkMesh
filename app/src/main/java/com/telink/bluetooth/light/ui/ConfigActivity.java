package com.telink.bluetooth.light.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jtcf.lib_common.view.HeaderView;
import com.telink.bluetooth.light.R;
import com.telink.bluetooth.light.TelinkMeshErrorDealActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfigActivity extends TelinkMeshErrorDealActivity {

    @BindView(R.id.headerView)  HeaderView mHeaderView;
    @BindView(R.id.et_net)      EditText   mEtNet;
    @BindView(R.id.et_psd)      EditText   mEtPsd;
    @BindView(R.id.btn_confirm) Button     mBtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.layout_header_right)
    public void onBack() {
        finish();
    }

    @OnClick(R.id.btn_confirm)
    public void onConfrim() {
        String name = mEtNet.getText().toString();
        String psd = mEtPsd.getText().toString();
    }

    @Override
    protected void onLocationEnable() {

    }
}
