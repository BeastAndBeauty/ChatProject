package com.paopao.chatproject.Util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;

import com.paopao.chatproject.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：paopao on 2019/4/16 20:14
 * <p>
 * 作用:
 */
public class EmotionUtil {

    private static Map<String,Integer> emotionMap=new HashMap<>();

    //具体字符转换为表情图片
    public static SpannableString textToEmotion(Context context,String source){
        SpannableString spannableString=new SpannableString(source);
        Resources resources=context.getResources();
        String regexEmotion="\\[([emotion0-9])+\\]";
        Pattern pattern=Pattern.compile(regexEmotion);
        Matcher matcher=pattern.matcher(spannableString);
        while (matcher.find()){
            String key=matcher.group();
            int start=matcher.start();
            Drawable drawable=resources.getDrawable(emotionMap.get(key));
            drawable.setBounds(0, 0, 42, 42);
            ImageSpan imageSpan=new ImageSpan(drawable);
            spannableString.setSpan(imageSpan,start,start+key.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;

    }

    static {
        emotionMap.put("[emotion1]", R.drawable.emotion1);
        emotionMap.put("[emotion2]", R.drawable.emotion2);
        emotionMap.put("[emotion3]", R.drawable.emotion3);
        emotionMap.put("[emotion4]", R.drawable.emotion4);
        emotionMap.put("[emotion5]", R.drawable.emotion5);
        emotionMap.put("[emotion6]", R.drawable.emotion6);
    }

}
