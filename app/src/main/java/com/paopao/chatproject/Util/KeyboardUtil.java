package com.paopao.chatproject.Util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 作者：paopao on 2019/4/17 10:14
 * <p>
 * 作用:软件盘工具类
 */
public class KeyboardUtil {

    /**
     * 打开软键盘
     * @param editText
     * @param context
     */
    public static void openKeyboard(EditText editText, Context context){
        InputMethodManager imm= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText,InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     * @param editText
     * @param context
     */
    public static void closeKeyboard(EditText editText,Context context){
        InputMethodManager imm= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }

    /**
     * 隐藏软键盘 参数是activity
     * @param activity
     */
    public static void hideInputFore(Activity activity){
        if (activity==null||activity.getCurrentFocus()==null)
            return;
        ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打开软键盘
     * @param context
     * @param view
     */
    public static void showInput(Context context, View view){
        InputMethodManager imm= (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm!=null){
            view.requestFocus();
            imm.showSoftInput(view,0);
        }
    }


}
