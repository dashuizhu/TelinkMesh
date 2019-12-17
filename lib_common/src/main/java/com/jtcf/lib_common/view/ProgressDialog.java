package com.jtcf.lib_common.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jtcf.lib_common.R;


/**
 * 加载进度框
 *
 * @author zhuj
 *         2017/6/19 上午10:40.
 */
public class ProgressDialog extends Dialog {

  private TextView mTvContent;
  private String mContent;
  private LinearLayout mLl;

  public ProgressDialog(@NonNull Context context, String str) {
    super(context, R.style.MyDialog);
    //super(context);

    if (TextUtils.isEmpty(str)) {
      mContent = context.getString(R.string.com_label_loading);
    } else {
      mContent = str;
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.com_dialog_progress);
    mTvContent = findViewById(R.id.tv_content);
    mTvContent.setText(mContent);
    mLl = findViewById(R.id.ll_content);
  }

  public void setText(String content) {
    mContent = content;
    if (mTvContent != null) {
      mTvContent.setText(content);
    }
  }

  /**
   * 添加点击事件
   * @param listener
   */
  public void addClickListener(View.OnClickListener listener) {
    mLl.setOnClickListener(listener);
  }
}
