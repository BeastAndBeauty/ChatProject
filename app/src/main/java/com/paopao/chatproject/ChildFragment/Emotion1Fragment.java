package com.paopao.chatproject.ChildFragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.paopao.chatproject.Activity.ChatActivity;
import com.paopao.chatproject.R;
import com.paopao.chatproject.Util.EmotionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Emotion1Fragment extends EmotionBaseFragment {
    @Override
    protected String getFragmentName() {
        return "Emotion1Fragment";
    }


//    @BindView(R.id.grid_layout)
//    GridLayout gridLayout;
//    Unbinder unbinder;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_emotion, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        setData();
//        return view;
//    }
//
//
//    //向chatActivity的输入框输入表情
//    private void setEdtTextInputText(int position) {
//        ChatActivity.edtTextInputString += "[emotion" + position + "]";
//        TextView e = getActivity().findViewById(R.id.edt_text_input);
//        e.setText(EmotionUtil.textToEmotion(getActivity(), ChatActivity.edtTextInputString));
//    }
//
//    private Integer[] emotions = {R.drawable.emotion1, R.drawable.emotion2, R.drawable.emotion3, R.drawable.emotion4, R.drawable.emotion5, R.drawable.emotion6, R.drawable.emotion7,
//            R.drawable.emotion8, R.drawable.emotion9, R.drawable.emotion10, R.drawable.emotion11, R.drawable.emotion12, R.drawable.emotion13, R.drawable.emotion14,
//            R.drawable.emotion15, R.drawable.emotion16, R.drawable.emotion17, R.drawable.emotion18, R.drawable.emotion19, R.drawable.emotion20, R.drawable.emotion21};
//
//
//    private void setData() {
//        gridLayout.setRowCount(3);
//        gridLayout.setColumnCount(7);
//        int position = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 7; j++) {
//                ImageView imageView = new ImageView(getActivity());
//                Drawable drawable = getActivity().getResources().getDrawable(emotions[position++]);
//                final int finalPosition = position;
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        setEdtTextInputText(finalPosition);
//                    }
//                });
//                imageView.setBackground(drawable);
//                GridLayout.Spec rowSpec = GridLayout.spec(i);
//                GridLayout.Spec columnSpec = GridLayout.spec(j, 1f);
//                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
//                params.setMargins(40, 40, 40, 40);
//                params.width = 65;
//                params.height = 90;
//                gridLayout.addView(imageView, params);
//            }
//        }
//    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
}
