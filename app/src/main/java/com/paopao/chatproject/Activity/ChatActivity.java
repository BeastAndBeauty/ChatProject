package com.paopao.chatproject.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.paopao.chatproject.Adapter.ChatFragmentViewPagerAdapter;
import com.paopao.chatproject.Adapter.RecycleViewAdapter;
import com.paopao.chatproject.Application.App;
import com.paopao.chatproject.ChildFragment.Emotion1Fragment;
import com.paopao.chatproject.ChildFragment.Emotion2Fragment;
import com.paopao.chatproject.ChildFragment.Emotion3Fragment;
import com.paopao.chatproject.Entity.ChatMessage;
import com.paopao.chatproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    //    @BindView(R.id.edt_input_message)
//    EditText edtInputMessage;
//    @BindView(R.id.btn_send_message)
//    Button btnSendMessage;
//    @BindView(R.id.btn_send_image)
//    Button btnSendImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.image_text_to_voice)
    ImageView imageTextToVoice;
    @BindView(R.id.image_voice_to_text)
    ImageView imageVoiceToText;
    @BindView(R.id.btn_voice_input)
    Button btnVoiceInput;
    @BindView(R.id.edt_text_input)
    EditText edtTextInput;
    @BindView(R.id.image_emotion)
    ImageView imageEmotion;
    @BindView(R.id.image_multimedia)
    ImageView imageMultimedia;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.rl_multi_and_send)
    RelativeLayout rlMultiAndSend;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ll_point_group)
    LinearLayout llPointGroup;
    @BindView(R.id.bottom_rel)
    RelativeLayout bottomRel;

    private List<ChatMessage> msgList;
    private RecycleViewAdapter adapter;

    private List<ImageView> pointList;
    private List<Fragment> fragmentList;

    private static final String HOST = "192.168.0.6";
    private static final int PORT = 8888;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private ExecutorService executorService = null;
    private String receiveMessage;

    public static SpannableString spannableString=new SpannableString("你好");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);


        init();
        initViewPager();
        initPointGroup();
        connectSocketService();

    }

    private void initPointGroup() {
        pointList = new ArrayList<>();
        for (int i = 0; i < fragmentList.size(); i++) {

            //创建点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.gray_point);
            if (i == 0) {
                point.setBackgroundResource(R.drawable.black_point);
            }
            pointList.add(point);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            point.setLayoutParams(params);
            if (i != 0) {
                //不包括第0个点，所有点距离左边有10个像素
                params.leftMargin = 20;
            }
            //添加到线性布局里面
            llPointGroup.addView(point);
        }
    }

    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new Emotion1Fragment());
        fragmentList.add(new Emotion2Fragment());
        fragmentList.add(new Emotion3Fragment());
        viewPager.setAdapter(new ChatFragmentViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setOnPageChangeListener(this);
    }

    //连接服务器
    private void connectSocketService() {
        executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(HOST, PORT);
                    socket.setSoTimeout(180 * 1000);
                    printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8")), true);
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    while (true) {
                        if ((receiveMessage = bufferedReader.readLine()) != null) {
                            Log.i("papa", "receiveMsg=" + receiveMessage);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Log.i("papa", "receiveMsg=" + receiveMessage);
                                        JSONObject jsonObject = new JSONObject(receiveMessage);
                                        msgList.add(new ChatMessage(jsonObject.get("Account").toString(), jsonObject.get("MessageType").toString(), jsonObject.get("Text").toString(), jsonObject.get("Image").toString()));
                                        //如果有新消息，则设置适配器的长度（通知适配器，有新的数据被插入），并让 RecyclerView 定位到最后一行
                                        int newSize = msgList.size() - 1;
                                        adapter.notifyItemInserted(newSize);
                                        recycleView.scrollToPosition(newSize);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void init() {
        Bundle bundle = this.getIntent().getExtras();
        name.setText(bundle.getString("name"));
        msgList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(msgList);
        recycleView.setAdapter(adapter);
    }


    //发送文本
    private void sendText(String content) {

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Account", App.get("UserAccount", "123"));
            jsonObject.put("MessageType", "Text");
            jsonObject.put("Text", content);
            jsonObject.put("Image", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if ("".equals(content))
            return;
        edtTextInput.setText("");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                printWriter.println(jsonObject.toString());
            }
        });
    }

    //发送图片
    private void sendImage(String image_path) {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.head_picture01);
        Bitmap bitmap = BitmapFactory.decodeFile(image_path);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] bytes = out.toByteArray();
        String image_str = Base64.encodeToString(bytes, Base64.DEFAULT);
        //写入字节的长度，再写入图片的字节
        long len = out.size();

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Account", App.get("UserAccount", "123"));
            jsonObject.put("MessageType", "Image");
            jsonObject.put("Text", "");
            jsonObject.put("Image", image_str);

//            jsonObject.put("Account", App.get("UserAccount", "123"));
//            jsonObject.put("Message", out.toByteArray());
            Log.i("papa", "out.toByteArray()=" + out.toByteArray());
//            ImageView imageView=findViewById(R.id.test);
//            String s= (String) jsonObject.get("Message");
//            imageView.setImageBitmap(getBitmapFromByte((byte[]) jsonObject.get("Message")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                printWriter.println(jsonObject.toString());
            }
        });

    }

    //调用相册
    private void intentToAlbum() {
        PictureSelector.create(ChatActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .minSelectNum(1)
                .selectionMode(PictureConfig.SINGLE)
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    Log.i("papa", "aaaa");
                    images = PictureSelector.obtainMultipleResult(data);
                    LocalMedia media = images.get(0);
                    sendImage(media.getPath());
                    Log.i("papa", "media.getPath()=" + media.getPath());

            }
        }
    }


    @OnClick({R.id.back, R.id.image_text_to_voice, R.id.image_voice_to_text, R.id.btn_voice_input, R.id.image_emotion, R.id.image_multimedia, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.image_text_to_voice:
                break;
            case R.id.image_voice_to_text:
                break;
            case R.id.btn_voice_input:
                break;
            case R.id.image_emotion:
                bottomRel.setVisibility(View.VISIBLE);
                break;
            case R.id.image_multimedia:
                break;
            case R.id.btn_send:
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        for (int j = 0; j < fragmentList.size(); j++) {
            if (j == i)
                pointList.get(j).setBackgroundResource(R.drawable.black_point);
            else
                pointList.get(j).setBackgroundResource(R.drawable.gray_point);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
