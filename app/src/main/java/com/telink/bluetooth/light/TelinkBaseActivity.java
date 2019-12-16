/********************************************************************************************************
 * @file     TelinkBaseActivity.java 
 *
 * @brief    for TLSR chips
 *
 * @author	 telink
 * @date     Sep. 30, 2010
 *
 * @par      Copyright (c) 2010, Telink Semiconductor (Shanghai) Co., Ltd.
 *           All rights reserved.
 *           
 *			 The information contained herein is confidential and proprietary property of Telink 
 * 		     Semiconductor (Shanghai) Co., Ltd. and is available under the terms 
 *			 of Commercial License Agreement between Telink Semiconductor (Shanghai) 
 *			 Co., Ltd. and the licensee in separate contract or the terms described here-in. 
 *           This heading MUST NOT be removed from this file.
 *
 * 			 Licensees are granted free, non-transferable use of the information in this 
 *			 file under Mutual Non-Disclosure Agreement. NO WARRENTY of ANY KIND is provided. 
 *           
 *******************************************************************************************************/
package com.telink.bluetooth.light;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.telink.bluetooth.light.util.ToastUtils;

public class TelinkBaseActivity extends AppCompatActivity {

    protected boolean foreground = false;

    @Override
    @SuppressLint("ShowToast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foreground = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        foreground = false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        foreground = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showToast(CharSequence s) {
        ToastUtils.toast(s.toString());
    }

    protected void saveLog(String log) {
        ((TelinkLightApplication) getApplication()).saveLog(log);
    }
}
