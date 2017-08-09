package com.marsjiang.statustextview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.marsjiang.statustextview.R;

/**
 * 扫描状态展示
 * Created by Jiang on 2017-08-09.
 */

public class MyScanStateTextView extends FrameLayout {
    //待扫描TextView
    private TextView tv_unscaned;
    //已扫描TextView
    private TextView tv_scaned;
    //扫描完成颜色
    private int tv_scaned_color;
    //未扫描完成颜色
    private int tv_unscaned_color;
    //字体大小
    private float tv_textSize;
    //左边距
    private float padding_left;
    //右边距
    private float padding_right;
    //上边距
    private float padding_top;
    //下边距
    private float padding_botttom;
    //已扫描文字修改
    private String scanedText;
    //未扫描文字修改
    private String unScanedText;
    //显示扫描还是未扫描 true:扫描，false：未扫描
    private Boolean isScaned;

    public MyScanStateTextView(@NonNull Context context) {
        super(context);
        initViews(context, null);
    }

    public MyScanStateTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public MyScanStateTextView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        tv_scaned.setHeight(getMeasuredHeight());
        tv_scaned.setWidth(getMeasuredWidth());

        tv_unscaned.setHeight(getMeasuredHeight());
        tv_unscaned.setWidth(getMeasuredWidth());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //根据设定的默认覆盖方式来latyout
        if (!isScaned) {
            tv_scaned.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
            tv_unscaned.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        } else {
            tv_unscaned.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
            tv_scaned.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        }
        invalidate();
    }

    /**
     * 初始化布局
     */
    private void initViews(Context context, AttributeSet attrs) {
        initProperty(context, attrs);

        tv_unscaned = new TextView(context);
        if (TextUtils.isEmpty(scanedText)) {
            tv_unscaned.setText("已扫描");
        } else {
            tv_unscaned.setText(scanedText);
        }
        tv_unscaned.setTextColor(tv_unscaned_color);
        tv_unscaned.setTextSize(tv_textSize);
        tv_unscaned.setGravity(Gravity.CENTER_VERTICAL);

        tv_scaned = new TextView(context);
        if (TextUtils.isEmpty(unScanedText)) {
            tv_scaned.setText("待扫描");
        } else {
            tv_scaned.setText(unScanedText);
        }
        tv_scaned.setTextColor(tv_scaned_color);
        tv_scaned.setTextSize(tv_textSize);
        tv_scaned.setGravity(Gravity.CENTER_VERTICAL);

        tv_scaned.setPadding((int) padding_left, (int) padding_top, (int) padding_right, (int) padding_botttom);
        tv_unscaned.setPadding((int) padding_left, (int) padding_top, (int) padding_right, (int) padding_botttom);
        this.setPadding((int) padding_left, (int) padding_top, (int) padding_right, (int) padding_botttom);

        this.addView(tv_scaned);
        this.addView(tv_unscaned);
    }

    /**
     * 初始化自定义属性
     *
     * @param context
     * @param attrs
     */
    private void initProperty(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StateTextView);
        tv_scaned_color = ta.getColor(R.styleable.StateTextView_tv_scaned_color, Color.BLUE);
        tv_unscaned_color = ta.getColor(R.styleable.StateTextView_tv_unscaned_color, Color.GRAY);
        tv_textSize = ta.getDimension(R.styleable.StateTextView_tv_textSize, 18f);

        padding_left = ta.getDimension(R.styleable.StateTextView_padding_left, 5f);
        padding_right = ta.getDimension(R.styleable.StateTextView_padding_right, 5f);
        padding_top = ta.getDimension(R.styleable.StateTextView_padding_top, 5f);
        padding_botttom = ta.getDimension(R.styleable.StateTextView_padding_botttom, 5f);

        scanedText = ta.getString(R.styleable.StateTextView_scanedText);
        unScanedText = ta.getString(R.styleable.StateTextView_unScanedText);

        isScaned = ta.getBoolean(R.styleable.StateTextView_isScaned, false);

    }

    /**
     * 设定扫描状态
     */
    public void setScaned() {
        tv_unscaned.setVisibility(View.GONE);
        tv_scaned.setVisibility(View.VISIBLE);
    }

    /**
     * 设定未扫描状态
     */
    public void setUnscaned() {
        tv_unscaned.setVisibility(View.VISIBLE);
        tv_scaned.setVisibility(View.GONE);
    }

}
