package com.paopao.chatproject.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paopao.chatproject.Application.App;
import com.paopao.chatproject.Entity.ChatMessage;
import com.paopao.chatproject.R;
import com.paopao.chatproject.Util.EmotionUtil;

import java.util.List;

/**
 * 作者：paopao on 2019/4/4 14:50
 * <p>
 * 作用:
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {


    private List<ChatMessage> msgList;
    private Context context;


    public RecycleViewAdapter(List<ChatMessage> msgList,Context context) {
        this.msgList = msgList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChatMessage msg = msgList.get(i);
        if (msg.getAccount().equals(App.get("UserAccount", "123"))) {
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.right_nickname.setText(msg.getAccount());
            if (msg.getMessageType().equals("Text")) {
                viewHolder.rightMsg.setText(EmotionUtil.textToEmotion(context,msg.getMessage()));
                viewHolder.right_image.setVisibility(View.GONE);
            } else {
                viewHolder.rightMsg.setVisibility(View.GONE);
                viewHolder.right_image.setImageBitmap(stringToBitmap(msg.getMessage()));
            }
        } else {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.left_nickname.setText(msg.getAccount());

            if (msg.getMessageType().equals("Text")) {
                viewHolder.leftMsg.setText(EmotionUtil.textToEmotion(context,msg.getMessage()));
                viewHolder.left_image.setVisibility(View.GONE);
            } else {
                viewHolder.leftMsg.setVisibility(View.GONE);
                viewHolder.left_image.setImageBitmap(stringToBitmap(msg.getMessage()));
            }
        }

    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout leftLayout;
        RelativeLayout rightLayout;

        TextView leftMsg;
        TextView rightMsg;
        TextView right_nickname;
        TextView left_nickname;
        ImageView right_image;
        ImageView left_image;


        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout = (RelativeLayout) itemView.findViewById(R.id.left_layout);
            rightLayout = (RelativeLayout) itemView.findViewById(R.id.right_layout);
            leftMsg = (TextView) itemView.findViewById(R.id.left_msg);
            left_nickname = (TextView) itemView.findViewById(R.id.left_nickname);
            rightMsg = (TextView) itemView.findViewById(R.id.right_msg);
            right_nickname = (TextView) itemView.findViewById(R.id.right_nickname);
            right_image = (ImageView) itemView.findViewById(R.id.right_image);
            left_image = (ImageView) itemView.findViewById(R.id.left_image);
        }
    }


    private Bitmap stringToBitmap(String string) {
        //将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
