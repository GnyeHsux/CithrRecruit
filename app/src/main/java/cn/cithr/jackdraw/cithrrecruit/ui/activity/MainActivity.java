package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.BaseFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.MainFragment;

public class MainActivity extends AppActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    private static DrawerLayout mDrawerLayout;
    boolean isLogin = false;
    int mCurrentMenuItem;

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ButterKnife.bind(this);

        //添加主页面Fragment
        this.addFragment(new MainFragment());

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.container;
    }

    //侧边菜单监控事件
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == mCurrentMenuItem) {
            mDrawerLayout.closeDrawers();
            return false;
        }
        switch (id) {
            case R.id.menu_item_1:
                Toast.makeText(MainActivity.this, "image", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_2:
                Toast.makeText(MainActivity.this, "image", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_3:
                Toast.makeText(MainActivity.this, "image", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_4:
                Toast.makeText(MainActivity.this, "image", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_5:
                Toast.makeText(MainActivity.this, "image", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //设置Toolbar汉堡按钮
    public void setToolBar(Toolbar toolBar) {
        if (toolBar != null) {
            final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolBar, R.string.app_name, R.string.app_name);
            mDrawerLayout.setDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }
    }

    //返回按键监控事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //点击头像判断是否已登录，已登录则打开个人信息页
    public void startLogin(View view) {
        isLogin = false;
        if (!isLogin) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {
            startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
        }
    }

    public static void isLockDrawer(boolean isLock) {
        if (isLock) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//关闭手势滑动
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED); //打开手势滑动
        }
    }
}
