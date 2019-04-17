package com.paopao.chatproject.Util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

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

    private static Map<String, Integer> emotionMap = new HashMap<>();

    //第一个EmotionFragment的表情
    public static Integer[] emotions1 = {R.drawable.emotion1, R.drawable.emotion2, R.drawable.emotion3, R.drawable.emotion4, R.drawable.emotion5, R.drawable.emotion6, R.drawable.emotion7,
            R.drawable.emotion8, R.drawable.emotion9, R.drawable.emotion10, R.drawable.emotion11, R.drawable.emotion12, R.drawable.emotion13, R.drawable.emotion14,
            R.drawable.emotion15, R.drawable.emotion16, R.drawable.emotion17, R.drawable.emotion18, R.drawable.emotion19, R.drawable.emotion20, R.drawable.emotion21};

    //第二个EmotionFragment的表情
    public static Integer[] emotions2 = {R.drawable.emotion22, R.drawable.emotion23, R.drawable.emotion24, R.drawable.emotion25, R.drawable.emotion26, R.drawable.emotion27, R.drawable.emotion28,
            R.drawable.emotion29, R.drawable.emotion30, R.drawable.emotion31, R.drawable.emotion32, R.drawable.emotion33, R.drawable.emotion34, R.drawable.emotion35,
            R.drawable.emotion36, R.drawable.emotion37, R.drawable.emotion38, R.drawable.emotion39, R.drawable.emotion40, R.drawable.emotion41, R.drawable.emotion42};

    //第三个EmotionFragment的表情
    public static Integer[] emotions3 = {R.drawable.emotion43, R.drawable.emotion44, R.drawable.emotion45, R.drawable.emotion46, R.drawable.emotion47, R.drawable.emotion48, R.drawable.emotion49,
            R.drawable.emotion50, R.drawable.emotion51, R.drawable.emotion52, R.drawable.emotion53, R.drawable.emotion54, R.drawable.emotion55, R.drawable.emotion56,
            R.drawable.emotion57, R.drawable.emotion58, R.drawable.emotion59, R.drawable.emotion60, R.drawable.emotion61, R.drawable.emotion62, R.drawable.emotion63};


    //具体字符转换为表情图片
    public static SpannableString textToEmotion(Context context, String source) {
        SpannableString spannableString = new SpannableString(source);
        Resources resources = context.getResources();
        String regexEmotion = "\\[([emotion0-9])+\\]";
        Pattern pattern = Pattern.compile(regexEmotion);
        Matcher matcher = pattern.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            int start = matcher.start();
            Drawable drawable = resources.getDrawable(emotionMap.get(key));
            drawable.setBounds(0, 0, 50, 50);
            ImageSpan imageSpan = new ImageSpan(drawable);
            spannableString.setSpan(imageSpan, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        emotionMap.put("[emotion7]", R.drawable.emotion7);
        emotionMap.put("[emotion8]", R.drawable.emotion8);
        emotionMap.put("[emotion9]", R.drawable.emotion9);
        emotionMap.put("[emotion10]", R.drawable.emotion10);
        emotionMap.put("[emotion11]", R.drawable.emotion11);
        emotionMap.put("[emotion12]", R.drawable.emotion12);
        emotionMap.put("[emotion13]", R.drawable.emotion13);
        emotionMap.put("[emotion14]", R.drawable.emotion14);
        emotionMap.put("[emotion15]", R.drawable.emotion15);
        emotionMap.put("[emotion16]", R.drawable.emotion16);
        emotionMap.put("[emotion17]", R.drawable.emotion17);
        emotionMap.put("[emotion18]", R.drawable.emotion18);
        emotionMap.put("[emotion19]", R.drawable.emotion19);
        emotionMap.put("[emotion20]", R.drawable.emotion20);
        emotionMap.put("[emotion21]", R.drawable.emotion21);

        emotionMap.put("[emotion22]", R.drawable.emotion22);
        emotionMap.put("[emotion23]", R.drawable.emotion23);
        emotionMap.put("[emotion24]", R.drawable.emotion24);
        emotionMap.put("[emotion25]", R.drawable.emotion25);
        emotionMap.put("[emotion26]", R.drawable.emotion26);
        emotionMap.put("[emotion27]", R.drawable.emotion27);
        emotionMap.put("[emotion28]", R.drawable.emotion28);
        emotionMap.put("[emotion29]", R.drawable.emotion29);
        emotionMap.put("[emotion30]", R.drawable.emotion30);
        emotionMap.put("[emotion31]", R.drawable.emotion31);
        emotionMap.put("[emotion32]", R.drawable.emotion32);
        emotionMap.put("[emotion33]", R.drawable.emotion33);
        emotionMap.put("[emotion34]", R.drawable.emotion34);
        emotionMap.put("[emotion35]", R.drawable.emotion35);
        emotionMap.put("[emotion36]", R.drawable.emotion36);
        emotionMap.put("[emotion37]", R.drawable.emotion37);
        emotionMap.put("[emotion38]", R.drawable.emotion38);
        emotionMap.put("[emotion39]", R.drawable.emotion39);
        emotionMap.put("[emotion40]", R.drawable.emotion40);
        emotionMap.put("[emotion41]", R.drawable.emotion41);
        emotionMap.put("[emotion42]", R.drawable.emotion42);

        emotionMap.put("[emotion43]", R.drawable.emotion43);
        emotionMap.put("[emotion44]", R.drawable.emotion44);
        emotionMap.put("[emotion45]", R.drawable.emotion45);
        emotionMap.put("[emotion46]", R.drawable.emotion46);
        emotionMap.put("[emotion47]", R.drawable.emotion47);
        emotionMap.put("[emotion48]", R.drawable.emotion48);
        emotionMap.put("[emotion49]", R.drawable.emotion49);
        emotionMap.put("[emotion50]", R.drawable.emotion50);
        emotionMap.put("[emotion51]", R.drawable.emotion51);
        emotionMap.put("[emotion52]", R.drawable.emotion52);
        emotionMap.put("[emotion53]", R.drawable.emotion53);
        emotionMap.put("[emotion54]", R.drawable.emotion54);
        emotionMap.put("[emotion55]", R.drawable.emotion55);
        emotionMap.put("[emotion56]", R.drawable.emotion56);
        emotionMap.put("[emotion57]", R.drawable.emotion57);
        emotionMap.put("[emotion58]", R.drawable.emotion58);
        emotionMap.put("[emotion59]", R.drawable.emotion59);
        emotionMap.put("[emotion60]", R.drawable.emotion60);
        emotionMap.put("[emotion61]", R.drawable.emotion61);
        emotionMap.put("[emotion62]", R.drawable.emotion62);
        emotionMap.put("[emotion63]", R.drawable.emotion63);
    }

}
