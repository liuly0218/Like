package com.liuly.www.likelibrary.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 个人工具类
 */
public class LikeUtils {

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private LikeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Init utils.
     * <p>Init it in the class of Application.</p>
     *
     * @param context context
     */
    public static void init(@NonNull final Context context) {
        init((Application) context.getApplicationContext());
    }

    /**
     * Init utils.
     * <p>Init it in the class of Application.</p>
     *
     * @param app application
     */
    public static void init(@NonNull final Application app) {
        if (sApplication == null) {
            LikeUtils.sApplication = app;
        }
    }

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    public static Application getApp() {
        if (sApplication != null) return sApplication;
        try {
            @SuppressLint("PrivateApi")
            Class <?> activityThread = Class.forName("android.app.ActivityThread");
            Object at = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(at);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            init((Application) app);
            return sApplication;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        return getApp();
    }

    /**
     * 获取resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 根据布局id获取view对象
     *
     * @param id
     * @return view对象
     */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    /**
     * long转换为MB
     *
     * @param size
     * @return MB
     */
    public static String formatFileSize(long size) {
        return Formatter.formatFileSize(getContext(), size);
    }

    /**
     * id转换为Drawable对象
     *
     * @param id
     * @return
     */
    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }

    /**
     * 将view从父布局中移除的方法
     *
     * @param view
     */
    public static void removeView(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
        }
    }

    /**
     * 从xml中获取颜色的方法
     *
     * @param colorId
     * @return
     */
    public static int getColor(int colorId) {
        int color = getResources().getColor(colorId);
        return color;
    }

    /**
     * id转换为String
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return getResources().getString(id);
    }

    /***
     * 根据资源id 获取 资源数组
     *
     * @param id
     * @return
     */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    /**
     * dip转换为px
     */
    public static int dp2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换为dip
     */
    public static int px2dp(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static String getText(TextView textView) {
        return textView.getText().toString().trim();
    }

    /**
     * 根据资源获取字符串数据集合
     *
     * @param id
     * @return
     */
    public static List <String> getStringList(int id) {
        List <String> stringList = Arrays.asList(getStringArray(id));
        List <String> list = new ArrayList <>();
        list.addAll(stringList);
        return list;
    }

    /**
     * X方向平移drawerLayout
     *
     * @param drawerLayout
     * @param startX
     * @param endX
     */
    public static void translateXDrawer(final DrawerLayout drawerLayout, float startX, float endX) {
        drawerLayout.animate().translationX(startX).translationX(endX).start();
    }

    /**
     * 显示布局(从左向右显示)
     */
    public static void showLayoutLeft2Right(final View view) {
        view
                .animate()
                .translationX(-dp2px(16))
                .translationX(0)
                .alpha(0.0f)
                .alpha(1.0f)
                .setDuration(200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    /**
     * 隐藏布局（从右向左隐藏）
     */
    public static void hideLayoutRight2Left(final View view) {
        view
                .animate()
                .translationX(0)
                .translationX(-dp2px(16))
                .alpha(1.0f)
                .alpha(0.0f)
                .setDuration(200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                })
                .start();
    }

    /**
     * 显示布局(从右向左显示)
     */
    public static void showLayoutRight2Left(final View view) {
        view
                .animate()
                .translationX(dp2px(16))
                .translationX(0)
                .alpha(0.0f)
                .alpha(1.0f)
                .setDuration(200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    /**
     * 隐藏布局（从左向右隐藏）
     */
    public static void hideLayoutLeft2Right(final View view) {
        view
                .animate()
                .translationX(0)
                .translationX(dp2px(16))
                .alpha(1.0f)
                .alpha(0.0f)
                .setDuration(200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                })
                .start();
    }

    /**
     * 显示布局(从上向下显示)
     */
    public static void showLayoutTop2Bottom(final View view) {
        view
                .animate()
                .translationY(-dp2px(16))
                .translationY(0)
                .alpha(0.0f)
                .alpha(1.0f)
                .setDuration(200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    /**
     * 隐藏布局（从下向上隐藏）
     */
    public static void hideLayoutBottom2Top(final View view) {
        view
                .animate()
                .translationY(0)
                .translationY(-dp2px(16))
                .alpha(1.0f)
                .alpha(0.0f)
                .setDuration(200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                })
                .start();
    }

    /**
     * 显示布局(从下向上显示)
     */
    public static void showLayoutBottom2Top(final View view) {
        view
                .animate()
                .translationY(dp2px(16))
                .translationY(0)
                .alpha(0.0f)
                .alpha(1.0f)
                .setDuration(200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    /**
     * 隐藏布局（从上向下隐藏）
     */
    public static void hideLayoutTop2Bottom(final View view) {
        view
                .animate()
                .translationY(0)
                .translationY(dp2px(16))
                .alpha(1.0f)
                .alpha(0.0f)
                .setDuration(200)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                })
                .start();
    }

    private static Toast toast;

    /**
     * 实时显示最新toast的方法
     *
     * @param msg
     */
    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApp(), msg, Toast.LENGTH_LONG);
        }
        toast.setText(msg);
        toast.show();
    }
}
