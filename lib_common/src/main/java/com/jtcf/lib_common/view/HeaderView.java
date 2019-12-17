package com.jtcf.lib_common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jtcf.lib_common.R;

/**
 * Created by zhuj on 2017/6/8.
 */
public class HeaderView extends LinearLayout {

    TextView mTextHeaderBack;
    TextView mTextHeaderRight;
    TextView mTextHeaderTitle;
    FrameLayout mFrameLayoutHeaderBack;
    FrameLayout mFramelayoutHeaderRight;
    RelativeLayout mLayout;
    RelativeLayout mLayoutContent;
    ImageView mImageHeaderBack;
    ImageView mImageHeaderRight;
    TextView mTvDivide;
    private Context mContext;
    private boolean mShowRight;
    private boolean mShowBack;
    private boolean mShowDivide;
    private int mTitleResId;
    private int mRightResId;
    private int mLeftResId;
    private int mHeadColorId;
    private String mActivityTitleString;
    private String mRightString;
    private String mLeftString;

    public HeaderView(Context context) {
        super(context);
        init();
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getAttrs(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.com_view_header, this);

        mTextHeaderBack = view.findViewById(R.id.text_header_back);
        mTextHeaderRight = view.findViewById(R.id.text_header_right);
        mTextHeaderTitle = view.findViewById(R.id.text_header_title);
        mFrameLayoutHeaderBack = view.findViewById(R.id.layout_header_back);
        mFramelayoutHeaderRight = view.findViewById(R.id.layout_header_right);
        mTvDivide = view.findViewById(R.id.tv_divide);
        mImageHeaderBack = view.findViewById(R.id.image_header_back);
        mImageHeaderRight = view.findViewById(R.id.image_header_right);
        mLayout = view.findViewById(R.id.layout);
        mLayoutContent = view.findViewById(R.id.layout_content);

        mTextHeaderTitle.setText(mActivityTitleString);
        //mTextHeaderTitle.setTextColor(getResources().getColor(R.color.white));
        setShowRight(mShowRight);
        setShowBack(mShowBack);
        if (mTitleResId != 0) {
            mTextHeaderTitle.setBackgroundResource(mTitleResId);
            mImageHeaderRight.setVisibility(GONE);
        }
        if (mRightResId != 0) {
            mImageHeaderRight.setBackgroundResource(mRightResId);
            mTextHeaderRight.setVisibility(GONE);
        }
        if (mLeftResId != 0) {
            mImageHeaderBack.setBackgroundResource(mLeftResId);
        }
        if (mLeftString != null) {
            mImageHeaderBack.setVisibility(GONE);
            mTextHeaderBack.setText(mLeftString);
        }
        if (mHeadColorId != 0) {
            mLayoutContent.setBackgroundColor(ContextCompat.getColor(getContext(), mHeadColorId));
        }
        mTextHeaderRight.setText(mRightString);

        mTvDivide.setVisibility(mShowDivide ? View.VISIBLE : View.GONE);
    }

    /**
     * get attrs
     */
    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeaderView);
        mActivityTitleString = ta.getString(R.styleable.HeaderView_titleText);
        mRightString = ta.getString(R.styleable.HeaderView_rightText);
        mLeftString = ta.getString(R.styleable.HeaderView_leftText);
        mShowRight = ta.getBoolean(R.styleable.HeaderView_showRight, false);
        mShowBack = ta.getBoolean(R.styleable.HeaderView_showBack, true);
        mShowDivide = ta.getBoolean(R.styleable.HeaderView_showDivide, false);
        mTitleResId = ta.getResourceId(R.styleable.HeaderView_titleIcon, 0);
        mLeftResId = ta.getResourceId(R.styleable.HeaderView_leftIcon, 0);
        mHeadColorId = ta.getResourceId(R.styleable.HeaderView_headerColor, 0);
        mRightResId = ta.getResourceId(R.styleable.HeaderView_rightIcon, 0);
        ta.recycle();
    }

    public TextView getTextHeaderRight() {
        return mTextHeaderRight;
    }

    public TextView getTextHeaderTitle() {
        return mTextHeaderTitle;
    }

    public ImageView getImageHeaderRight() {
        return mImageHeaderRight;
    }

    /**
     * 设置title
     */
    public void setTitle(String title) {
        mTextHeaderTitle.setText(title);
    }

    /**
     * 设置title 文字字体
     */
    public void setTitleColor(int color) {
        mTextHeaderTitle.setTextColor(color);
    }

    /**
     * 设置title
     */
    public void setTitle(int title) {
        mTextHeaderTitle.setText(title);
    }

    //设置返回文字
    public void setBackText(int title) {
        mTextHeaderBack.setText(title);
        mImageHeaderBack.setVisibility(GONE);
    }  //设置返回文字

    public void setBackText(String title) {
        mTextHeaderBack.setText(title);
        mImageHeaderBack.setVisibility(GONE);
    }

    public void setRightText(String title) {
        mTextHeaderRight.setText(title);
        mTextHeaderRight.setVisibility(VISIBLE);
        mImageHeaderRight.setVisibility(GONE);
    }

    public void setRightText(int title) {
        mTextHeaderRight.setText(title);
        mTextHeaderRight.setVisibility(VISIBLE);
        mImageHeaderRight.setVisibility(GONE);
    }

    public void setRightIcon(int resId) {
        mTextHeaderRight.setVisibility(GONE);
        mImageHeaderRight.setBackgroundResource(resId);
        mImageHeaderRight.setVisibility(VISIBLE);
    }

    public void setBackIcon(int resId) {
        mImageHeaderBack.setBackgroundResource(resId);
        mImageHeaderBack.setVisibility(VISIBLE);
    }

    public void setShowBack(boolean backShow) {
        mShowBack = backShow;
        if (mShowBack) {
            mFrameLayoutHeaderBack.setVisibility(VISIBLE);
        } else {
            mFrameLayoutHeaderBack.setVisibility(View.INVISIBLE);
        }
    }

    public void setShowRight(boolean rightShow) {
        mShowRight = rightShow;
        if (mShowRight) {
            mFramelayoutHeaderRight.setVisibility(VISIBLE);
        } else {
            mFramelayoutHeaderRight.setVisibility(GONE);
        }
    }

    public void setHideBackGround() {
        mLayout.setBackgroundColor(Color.TRANSPARENT);
    }
}
