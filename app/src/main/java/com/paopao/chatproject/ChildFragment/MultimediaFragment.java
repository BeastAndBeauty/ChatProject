package com.paopao.chatproject.ChildFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paopao.chatproject.Activity.ChatActivity;
import com.paopao.chatproject.CallBack.OnViewClickListener;
import com.paopao.chatproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultimediaFragment extends Fragment {


    @BindView(R.id.text_album)
    TextView textAlbum;
    @BindView(R.id.text_camera)
    TextView textCamera;
    @BindView(R.id.text_video_call)
    TextView textVideoCall;
    @BindView(R.id.text_location)
    TextView textLocation;
    Unbinder unbinder;

    private OnViewClickListener onViewClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_multimedia, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.text_album, R.id.text_camera, R.id.text_video_call, R.id.text_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_album:
                if (getActivity() instanceof OnViewClickListener)
                    ((OnViewClickListener)getActivity()).viewClickListener(view);
                break;
            case R.id.text_camera:
                break;
            case R.id.text_video_call:
                break;
            case R.id.text_location:
                break;
        }
    }



}
