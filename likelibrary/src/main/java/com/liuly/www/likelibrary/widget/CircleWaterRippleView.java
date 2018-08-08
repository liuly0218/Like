package com.liuly.www.likelibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.liuly.www.likelibrary.R;
import com.liuly.www.likelibrary.utils.LikeUtils;
import com.wei.android.lib.colorview.view.ColorImageView;

/**
 * 圆形水波纹view
 */
public class CircleWaterRippleView extends RelativeLayout {
    public RelativeLayout relativeLayout;
    public ColorImageView colorImageView;

    public CircleWaterRippleView(Context context) {
        this(context, null);
    }

    public CircleWaterRippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleWaterRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //1.初始化布局，查找控件
        inflate(context, R.layout.widget_circle_water_ripple, this);
        relativeLayout = findViewById(R.id.rl_circle_water_ripple);
        colorImageView = findViewById(R.id.civ_circle_water_ripple);
        //2.查找自定义属性
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleWaterRippleView);
        Drawable normalDrawable = ta.getDrawable(R.styleable.CircleWaterRippleView_circle_water_ripple_srcNormal);
        Drawable darkDrawable = ta.getDrawable(R.styleable.CircleWaterRippleView_circle_water_ripple_srcDark);
        float dimension = ta.getDimension(R.styleable.CircleWaterRippleView_circle_water_ripple_icon_size, LikeUtils.dp2px(24));
        //3.赋值
        colorImageView.getColorImageViewHelper().setSrcDrawableNormal(normalDrawable);
        colorImageView.getColorImageViewHelper().setSrcDrawablePressed(darkDrawable);
        colorImageView.getColorImageViewHelper().setSrcDrawableSelected(darkDrawable);
        LayoutParams layoutParams = (LayoutParams) colorImageView.getLayoutParams();
        layoutParams.width = (int) dimension;
        layoutParams.height = (int) dimension;
        colorImageView.setLayoutParams(layoutParams);
        //4.回收资源
        ta.recycle();
    }
}
