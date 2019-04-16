package com.paopao.chatproject.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.paopao.chatproject.Activity.ChatActivity;
import com.paopao.chatproject.Adapter.ChatFragmentRecycleViewAdapter;
import com.paopao.chatproject.Adapter.RecycleViewAdapter;
import com.paopao.chatproject.CallBack.RecycleViewOnItemClickListener;
import com.paopao.chatproject.Entity.ChatFragmentEntity;
import com.paopao.chatproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment implements RecycleViewOnItemClickListener {


    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    Unbinder unbinder;

    private List<ChatFragmentEntity> msgList;
    private ChatFragmentRecycleViewAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        unbinder = ButterKnife.bind(this, view);

        initRecycleView();


        return view;
    }

    private void initRecycleView() {
        msgList = new ArrayList<>();
        msgList.add(new ChatFragmentEntity("669大家庭","没有灵魂的程序员","02:21",R.drawable.picture1));
        msgList.add(new ChatFragmentEntity("仰佳儿子","Idiot boy","08:20",R.drawable.picture2));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);
        adapter = new ChatFragmentRecycleViewAdapter(msgList);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle=new Bundle();
        bundle.putString("name",msgList.get(position).getName());
        startActivity(new Intent(getActivity(),ChatActivity.class).putExtras(bundle));
    }
}
