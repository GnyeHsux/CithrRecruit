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
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.JobFavoriteFragment;
import cn.cithr.jackdraw.cithrrecruit.ui.fragment.MainFragment;

public class MainActivity extends AppActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
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
        ButterKnife.bind(this);

        mCurrentMenuItem = R.id.menu_home;

        //禁止滑动返回
        getSwipeBackLayout().setEnableGesture(false);

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
        mDrawerLayout.closeDrawers();

        switch (id) {
            case R.id.menu_home:
                addFragment(new MainFragment());
                break;
            case R.id.menu_job_favorite:
                mCurrentMenuItem = id;
                addFragment(new JobFavoriteFragment());
                break;
            case R.id.menu_job_apply:
                mCurrentMenuItem = id;
                Toast.makeText(MainActivity.this, "menu_job_apply", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_5:
                mCurrentMenuItem = id;
                Toast.makeText(MainActivity.this, "image", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //设置Toolbar汉堡按钮
    public void setToolBar(Toolbar toolBar) {
        if (toolBar != null) {
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolBar, R.string.app_name, R.string.app_name);
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

}
