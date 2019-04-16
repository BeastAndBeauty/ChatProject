package com.paopao.chatproject.ChildFragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paopao.chatproject.Activity.ChatActivity;
import com.paopao.chatproject.R;
import com.paopao.chatproject.Util.EmotionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Emotion1Fragment extends Fragment {


    @BindView(R.id.image_emotion1)
    ImageView imageEmotion1;
    @BindView(R.id.image_emotion2)
    ImageView imageEmotion2;
    @BindView(R.id.image_emotion3)
    ImageView imageEmotion3;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emotion, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.image_emotion1, R.id.image_emotion2, R.id.image_emotion3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_emotion1:

//                ChatActivity.spannableString=new SpannableString(ChatActivity.spannableString+"表情");
////                Drawable drawable=imageEmotion1.getDrawable();
//                Drawable drawable = getResources().getDrawable(R.drawable.emotion1);
//                drawable.setBounds(0, 0, 42, 42);
//                ImageSpan imageSpan = new ImageSpan(drawable);
//                Log.d("papa","ChatActivity.spannableString.length()="+ChatActivity.spannableString.length());
//                ChatActivity.spannableString.setSpan(imageSpan, ChatActivity.spannableString.length()-2, ChatActivity.spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                TextView e=getActivity().findViewById(R.id.edt_text_input);
//                e.setText(ChatActivity.spannableString);

                String str=ChatActivity.spannableString+"[emotion1]";
                TextView e=getActivity().findViewById(R.id.edt_text_input);
                e.setText(EmotionUtil.textToEmotion(getActivity(),str));




                break;
            case R.id.image_emotion2:
                break;
            case R.id.image_emotion3:
                break;
        }
    }
}
