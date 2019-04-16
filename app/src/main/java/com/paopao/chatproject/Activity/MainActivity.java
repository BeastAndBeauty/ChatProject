package com.paopao.chatproject.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.paopao.chatproject.Fragment.ChatFragment;
import com.paopao.chatproject.Fragment.ContactFragment;
import com.paopao.chatproject.Fragment.FindFragment;
import com.paopao.chatproject.Fragment.MyFragment;
import com.paopao.chatproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.tool_bar_center_text)
    TextView toolBarCenterText;
    @BindView(R.id.tool_bar)
    RelativeLayout toolBar;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    private ChatFragment chatFragment;
    private ContactFragment contactFragment;
    private FindFragment findFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomNavigationBar();
        setDefaultFragment();
    }

    private void initBottomNavigationBar() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        bottomNavigationBar //值得一提，模式跟背景的设置都要在添加tab前面，不然不会有效果。
                .setActiveColor(R.color.bottomNavigationBarSelected)//选中颜色 图标和文字
//                .setInActiveColor("#8e8e8e")//默认未选择颜色
                .setBarBackgroundColor("#ECECEC");//默认背景色

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.weixin_pressed, "聊啥").setInactiveIconResource(R.drawable.weixin_normal))
                .addItem(new BottomNavigationItem(R.drawable.contact_list_pressed, "通讯录").setInactiveIconResource(R.drawable.contact_list_normal))
                .addItem(new BottomNavigationItem(R.drawable.find_pressed, "发现").setInactiveIconResource(R.drawable.find_normal))
                .addItem(new BottomNavigationItem(R.drawable.profile_pressed, "我").setInactiveIconResource(R.drawable.profile_normal))
                .initialise();//所有的设置需在调用该方法前完成

    }

    @OnClick({R.id.tool_bar_center_text, R.id.frame_layout, R.id.bottom_navigation_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tool_bar_center_text:
                break;
            case R.id.frame_layout:
                break;
            case R.id.bottom_navigation_bar:
                break;
        }
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment currentFragment=new ChatFragment();

        switch (position) {
            case 0:
                if (chatFragment == null)
                    currentFragment = new ChatFragment();
                break;
            case 1:
                if (contactFragment == null)
                    currentFragment = new ContactFragment();
                break;
            case 2:
                if (findFragment == null)
                    currentFragment = new FindFragment();
                break;
            case 3:
                if (myFragment == null)
                    currentFragment = new MyFragment();
                break;

        }
        transaction.replace(R.id.frame_layout,currentFragment);
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void setDefaultFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frame_layout, new ChatFragment());
        transaction.commit();
    }
}
