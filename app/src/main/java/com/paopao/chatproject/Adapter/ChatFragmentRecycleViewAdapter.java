package com.paopao.chatproject.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.paopao.chatproject.CallBack.RecycleViewOnItemClickListener;
import com.paopao.chatproject.Entity.ChatFragmentEntity;
import com.paopao.chatproject.Entity.ChatMessage;
import com.paopao.chatproject.R;

import java.util.List;

/**
 * 作者：paopao on 2019/4/10 22:45
 * <p>
 * 作用:
 */
public class ChatFragmentRecycleViewAdapter extends RecyclerView.Adapter<ChatFragmentRecycleViewAdapter.ViewHolder> implements View.OnClickListener {

    private List<ChatFragmentEntity> msgList;
    private RecycleViewOnItemClickListener recycleViewOnItemClickListener;

    public ChatFragmentRecycleViewAdapter(List<ChatFragmentEntity> msgList) {
        this.msgList = msgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_fragment_recycle_view_layout, viewGroup, false);
        return new ChatFragmentRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChatFragmentEntity entity=msgList.get(i);
        viewHolder.wrapper.setOnClickListener(this);
        viewHolder.wrapper.setTag(i);
        viewHolder.name.setText(entity.getName());
        viewHolder.message.setText(entity.getMessage());
        viewHolder.time.setText(entity.getTime());
        viewHolder.photo.setBackgroundResource(entity.getPhoto());
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    @Override
    public void onClick(View v) {
        recycleViewOnItemClickListener.onItemClick(v, (Integer) v.getTag());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout wrapper;
        TextView name;
        TextView message;
        TextView time;
        ImageView photo;


        public ViewHolder(View itemView) {
            super(itemView);
            wrapper = (RelativeLayout) itemView.findViewById(R.id.wrapper);
            name = (TextView) itemView.findViewById(R.id.name);
            message = (TextView) itemView.findViewById(R.id.message);
            time = (TextView) itemView.findViewById(R.id.time);
            photo = (ImageView) itemView.findViewById(R.id.photo);

        }
    }

    public void setOnItemClickListener(RecycleViewOnItemClickListener recycleViewOnItemClickListener){
        this.recycleViewOnItemClickListener=recycleViewOnItemClickListener;
    }

}
