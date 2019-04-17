package com.paopao.chatproject.ChildFragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.paopao.chatproject.Activity.ChatActivity;
import com.paopao.chatproject.R;
import com.paopao.chatproject.Util.EmotionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：paopao on 2019/4/16 23:11
 * <p>
 * 作用:
 */
public abstract class EmotionBaseFragment extends Fragment {

    @BindView(R.id.grid_layout)
    GridLayout gridLayout;
    Unbinder unbinder;
    private int multiple;
    private int row=3;
    private int column=7;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emotion, container, false);
        unbinder = ButterKnife.bind(this, view);
        setData();
        return view;
    }

    protected abstract String getFragmentName();


    //向chatActivity的输入框输入表情
    private void setEdtTextInputText(int position) {
        ChatActivity.edtTextInputString += "[emotion" + (position+multiple*row*column) + "]";
        EditText e = getActivity().findViewById(R.id.edt_text_input);
        e.setText(EmotionUtil.textToEmotion(getActivity(), ChatActivity.edtTextInputString));
        e.setSelection(ChatActivity.edtTextInputString.length());

    }


    private void setData() {
        gridLayout.setRowCount(row);
        gridLayout.setColumnCount(column);
        Integer[] emotions;
        if (getFragmentName().equals("Emotion1Fragment")) {
            multiple = 0;
            emotions = EmotionUtil.emotions1;
        } else if (getFragmentName().equals("Emotion2Fragment")) {
            multiple = 1;
            emotions = EmotionUtil.emotions2;
        } else {
            multiple = 2;
            emotions = EmotionUtil.emotions3;
        }

        int position = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                ImageView imageView = new ImageView(getActivity());
                Drawable drawable = getActivity().getResources().getDrawable(emotions[position++]);
                final int finalPosition = position;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setEdtTextInputText(finalPosition);
                    }
                });
                imageView.setBackground(drawable);
                GridLayout.Spec rowSpec = GridLayout.spec(i);
                GridLayout.Spec columnSpec = GridLayout.spec(j, 1f);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                params.setMargins(40, 40, 40, 40);
                params.width = 65;
                params.height = 90;
                gridLayout.addView(imageView, params);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
