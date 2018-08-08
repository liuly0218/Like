package com.liuly.www.likelibrary.utils;

import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxDialogFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 个人RxView的工具类
 */
public class LikeRxViewUtils {

    /**
     * rxAppCompatActivity中的点击方法
     *
     * @param view                被点击的view
     * @param rxAppCompatActivity rxAppCompatActivity对象
     * @param onClickListener     onClickListener对象
     */
    public static void activityClick(final View view, RxAppCompatActivity rxAppCompatActivity, final OnClickListener onClickListener) {
        Disposable disposable = RxView
                .clicks(view)
                .throttleFirst(50, TimeUnit.MILLISECONDS)
                .compose(rxAppCompatActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer <Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onClickListener.onClick(view);
                    }
                });
    }

    /**
     * rxFragment中的点击方法
     *
     * @param view            被点击的view
     * @param rxFragment      rxFragment对象
     * @param onClickListener onClickListener对象
     */
    public static void fragmentClick(final View view, RxFragment rxFragment, final OnClickListener onClickListener) {
        Disposable disposable = RxView
                .clicks(view)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer <Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onClickListener.onClick(view);
                    }
                });
    }

    /**
     * rxDialogFragment中的点击方法
     *
     * @param view             被点击的view
     * @param rxDialogFragment rxDialogFragment对象
     * @param onClickListener  onClickListener对象
     */
    public static void dialogFragmentClick(final View view, RxDialogFragment rxDialogFragment, final OnClickListener onClickListener) {
        Disposable disposable = RxView
                .clicks(view)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .compose(rxDialogFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer <Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onClickListener.onClick(view);
                    }
                });
    }

    /**
     * rxAppCompatActivity中的双击方法
     *
     * @param view                  view对象
     * @param rxAppCompatActivity   rxAppCompatActivity对象
     * @param onDoubleClickListener onDoubleClickListener对象
     */
    public static void activityDoubleClick(final View view, RxAppCompatActivity rxAppCompatActivity, final OnDoubleClickListener onDoubleClickListener) {
        Observable <Object> observable = RxView
                .clicks(view)
                .compose(rxAppCompatActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .share();
        Disposable disposable = observable
                .buffer(observable.debounce(200, TimeUnit.MILLISECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer <List <Object>>() {
                    @Override
                    public void accept(List <Object> objects) throws Exception {
                        if (objects.size() >= 2) {
                            onDoubleClickListener.onClick(view);
                        }
                    }
                });
    }

    /**
     * rxFragment中的双击方法
     *
     * @param view                  view对象
     * @param rxFragment            rxFragment对象
     * @param onDoubleClickListener onDoubleClickListener对象
     */
    public static void fragmentDoubleClick(final View view, RxFragment rxFragment, final OnDoubleClickListener onDoubleClickListener) {
        Observable <Object> observable = RxView
                .clicks(view)
                .compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .share();
        Disposable disposable = observable
                .buffer(observable.debounce(200, TimeUnit.MILLISECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer <List <Object>>() {
                    @Override
                    public void accept(List <Object> objects) throws Exception {
                        if (objects.size() >= 2) {
                            onDoubleClickListener.onClick(view);
                        }
                    }
                });
    }

    /**
     * rxDialogFragment中的双击方法
     *
     * @param view                  view对象
     * @param rxDialogFragment      rxDialogFragment对象
     * @param onDoubleClickListener onDoubleClickListener对象
     */
    public static void dialogFragmentDoubleClick(final View view, RxDialogFragment rxDialogFragment, final OnDoubleClickListener onDoubleClickListener) {
        Observable <Object> observable = RxView
                .clicks(view)
                .compose(rxDialogFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .share();
        Disposable disposable = observable
                .buffer(observable.debounce(200, TimeUnit.MILLISECONDS))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer <List <Object>>() {
                    @Override
                    public void accept(List <Object> objects) throws Exception {
                        if (objects.size() >= 2) {
                            onDoubleClickListener.onClick(view);
                        }
                    }
                });
    }

    /**
     * rxAppCompatActivity中的长按方法
     *
     * @param view                 被长按的view
     * @param rxAppCompatActivity  rxAppCompatActivity对象
     * @param onLongClickListenter onLongClickListenter对象
     */
    public static void activityLongClick(final View view, RxAppCompatActivity rxAppCompatActivity, final OnLongClickListenter onLongClickListenter) {
        Disposable disposable = RxView
                .longClicks(view)
                .compose(rxAppCompatActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer <Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onLongClickListenter.onClick(view);
                    }
                });
    }

    /**
     * rxFragment中的长按方法
     *
     * @param view                 被长按的view
     * @param rxFragment           rxFragment对象
     * @param onLongClickListenter onLongClickListenter对象
     */
    public static void fragmentLongClick(final View view, RxFragment rxFragment, final OnLongClickListenter onLongClickListenter) {
        Disposable disposable = RxView
                .longClicks(view)
                .compose(rxFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer <Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onLongClickListenter.onClick(view);
                    }
                });
    }

    /**
     * rxFragment中的长按方法
     *
     * @param view                 被长按的view
     * @param rxDialogFragment     rxDialogFragment对象
     * @param onLongClickListenter onLongClickListenter对象
     */
    public static void dialogFragmentLongClick(final View view, RxDialogFragment rxDialogFragment, final OnLongClickListenter onLongClickListenter) {
        Disposable disposable = RxView
                .longClicks(view)
                .compose(rxDialogFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer <Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        onLongClickListenter.onClick(view);
                    }
                });
    }

    /**
     * rxAppCompatActivity中文字改变的方法
     *
     * @param view                  view对象
     * @param rxAppCompatActivity   rxAppCompatActivity对象
     * @param onTextChangesListener onTextChangesListener对象
     */
    public static void activityTextChanges(final TextView view, RxAppCompatActivity rxAppCompatActivity, final OnTextChangesListener onTextChangesListener) {
        Disposable disposable = RxTextView
                .textChanges(view)
                .compose(rxAppCompatActivity. <CharSequence>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer <CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        onTextChangesListener.onTextChanges(view);
                    }
                });
    }

    /**
     * rxDialogFragment中文字改变的方法
     *
     * @param view                  view对象
     * @param rxDialogFragment      rxDialogFragment对象
     * @param onTextChangesListener onTextChangesListener对象
     */
    public static void dialogFragmentTextChanges(final TextView view, RxDialogFragment rxDialogFragment, final OnTextChangesListener onTextChangesListener) {
        Disposable disposable = RxTextView
                .textChanges(view)
                .compose(rxDialogFragment. <CharSequence>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer <CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        onTextChangesListener.onTextChanges(view);
                    }
                });
    }

    /**
     * rxFragment中文字改变的方法
     *
     * @param view                  view对象
     * @param rxFragment            rxFragment对象
     * @param onTextChangesListener onTextChangesListener对象
     */
    public static void fragmentTextChanges(final TextView view, RxFragment rxFragment, final OnTextChangesListener onTextChangesListener) {
        Disposable disposable = RxTextView
                .textChanges(view)
                .compose(rxFragment. <CharSequence>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer <CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        onTextChangesListener.onTextChanges(view);
                    }
                });
    }

    /**
     * 点击监听器
     */
    public interface OnClickListener {
        /**
         * 点击回调
         *
         * @param view 被点击的view
         */
        void onClick(View view);
    }

    /**
     * 双击监听器
     */
    public interface OnDoubleClickListener {
        /**
         * 双击回调
         *
         * @param view 被双击的view
         */
        void onClick(View view);
    }

    /**
     * 长按监听器
     */
    public interface OnLongClickListenter {
        /**
         * 长按回调
         *
         * @param view 被长按的view
         */
        void onClick(View view);
    }

    /**
     * text改变监听器
     */
    public interface OnTextChangesListener {
        void onTextChanges(TextView textView);
    }
}
