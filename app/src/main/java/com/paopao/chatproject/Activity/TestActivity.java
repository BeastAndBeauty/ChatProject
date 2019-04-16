package com.paopao.chatproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.paopao.chatproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        PictureSelector.create(TestActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .minSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);

//        PictureSelector.create(TestActivity.this)
//                .openCamera(PictureMimeType.ofImage())
//                .maxSelectNum(1)
//                .minSelectNum(1)
//                .selectionMode(PictureConfig.SINGLE)
//                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("papa","resultCode="+resultCode);
        Log.i("papa","resultCode="+resultCode);
        Log.i("papa","requestCode="+requestCode);

        List<LocalMedia> images;
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case PictureConfig.CHOOSE_REQUEST:
                    Log.i("papa","aaaa");
                    images=PictureSelector.obtainMultipleResult(data);
                    LocalMedia media = images.get(0);
                    Log.i("papa","media.getPath()="+media.getPath());

            }
        }
    }
}
