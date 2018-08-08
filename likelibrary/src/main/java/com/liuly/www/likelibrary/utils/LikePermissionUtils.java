package com.liuly.www.likelibrary.utils;

import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 个人权限申请工具类
 */
public class LikePermissionUtils {

    /**
     * 申请权限的方法(可同时申请多个权限)
     *
     * @param activity                     activity对象
     * @param onRequestPermissionsListener 申请权限的监听器
     * @param permissions                  权限列表（权限之间使用","号隔开）
     */
    public static Disposable requestPermissions(AppCompatActivity activity, final OnRequestPermissionsListener onRequestPermissionsListener, final String... permissions) {
        return new RxPermissions(activity)
                .requestEachCombined(permissions)
                .subscribe(new Consumer <Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            //所有权限都被允许
                            onRequestPermissionsListener.onGranted(permission);
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            //至少有一个权限被拒绝
                            onRequestPermissionsListener.onDenied(permission);
                        } else {
                            //至少有一个权限被禁止fang
                            onRequestPermissionsListener.onDontAskAgain(permission);
                        }
                    }
                });
    }

    /**
     * 申请权限监听器
     */
    public interface OnRequestPermissionsListener {
        /**
         * 被允许的回调
         *
         * @param permission 权限对象
         */
        void onGranted(Permission permission);

        /**
         * 被拒绝的回调
         *
         * @param permission 权限对象
         */
        void onDenied(Permission permission);

        /**
         * 被永久拒绝的回调
         *
         * @param permission 权限对象
         */
        void onDontAskAgain(Permission permission);
    }
}
