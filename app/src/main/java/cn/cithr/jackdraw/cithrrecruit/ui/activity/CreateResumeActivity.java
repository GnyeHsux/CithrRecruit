package cn.cithr.jackdraw.cithrrecruit.ui.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.cithr.jackdraw.cithrrecruit.R;
import cn.cithr.jackdraw.cithrrecruit.utils.GuiUtils;
import cn.cithr.jackdraw.cithrrecruit.utils.ToastUtils;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xusha on 2016/6/16.
 */
public class CreateResumeActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.rl_container)
    RelativeLayout mRlContainer;
    @BindView(R.id.img_head)
    CircleImageView cImgHead;
    @BindView(R.id.cardView1)
    CardView mCardView1;
    @BindView(R.id.cardView2)
    CardView mCardView2;
    @BindView(R.id.switch_isOpen)
    Switch mSwitchIsOpen;
    @BindView(R.id.textview)
    TextView mTextView;

    private Bitmap headImg;
    private static String path = Environment.getExternalStorageDirectory().getPath() + "/CithrRecruit/";// sd路径


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_resume);
        ButterKnife.bind(this);

        mToolbar.setTitle(R.string.title_create_resume);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterAnimation(); // 入场动画
            setupExitAnimation(); // 退场动画
        } else {
            initViews();
        }

        mFab.setOnClickListener(this);
        cImgHead.setOnClickListener(this);
    }


    //初始化界面
    private void initViews() {
        new Handler(Looper.getMainLooper()).post(() -> {
            Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
            animation.setDuration(400);
            cImgHead.startAnimation(animation);
            cImgHead.setVisibility(View.VISIBLE);
            mCardView1.startAnimation(animation);
            mCardView1.setVisibility(View.VISIBLE);
            mCardView2.startAnimation(animation);
            mCardView2.setVisibility(View.VISIBLE);
            mSwitchIsOpen.startAnimation(animation);
            mSwitchIsOpen.setVisibility(View.VISIBLE);
            mTextView.startAnimation(animation);
            mTextView.setVisibility(View.VISIBLE);
        });

        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            cImgHead.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             */

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_resume_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_preview_resume) {
            ToastUtils.showShort(this, "预览简历");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //悬浮按钮done监听，保存简历
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    backActivity();
                } else {
                    saveResume();
                }
                break;
            //更换头像
            case R.id.img_head:
                showTypeDialog();
                break;
        }
    }

    //显示更换头像对话框
    private void showTypeDialog() {
        String[] mDatas = {"从图库中选取", "拍摄照片"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(mDatas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    //从相册中选取
                    case 0:
                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent1, 1);
                        dialog.dismiss();
                        break;
                    //调用相机
                    case 1:
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                        startActivityForResult(intent2, 2);
                        dialog.dismiss();
                        break;
                }
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;

            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }
                break;

            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    headImg = extras.getParcelable("data");
                    if (headImg != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(headImg);//保存在SD卡中
                        cImgHead.setImageBitmap(headImg); //在CirclImageView中显示出来
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    //更新用户头像
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 退出事件
    @Override
    public void onBackPressed() {
        //点击Toolbar后退箭头和实体返回健，弹出提示框是否保存该简历信息
        //提示保存简历对话框
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("提示");
        builder.setMessage("是否保存简历");
        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtils.showShort(CreateResumeActivity.this, "保存");
                /**
                 * 保存简历
                 */

                //退出
                backActivity();
            }
        });
        builder.setNegativeButton("放弃", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //退出
                backActivity();
            }
        });
        builder.create().show();
    }

    //退出
    public void backActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lollipopBack();
        } else {
            saveResume();
        }
    }

    // Fab按钮退出动画 sdk>21
    public void lollipopBack() {
        GuiUtils.animateRevealHide(
                this, mRlContainer, mFab,
                mFab.getWidth() / 2,
                new GuiUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        //保存简历，并退出
                        saveResume();
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }

    //保存简历
    public void saveResume() {
        //提交简历名称
        ToastUtils.showShort(this, "输入简历名称");
        super.onBackPressed();
    }

    // 入场动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    // 动画展示
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow() {
        GuiUtils.animateRevealShow(
                this, mRlContainer, mFab,
                mFab.getWidth() / 2,
                new GuiUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {
                        initViews();
                    }
                });
    }


    // 退出动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupExitAnimation() {
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(400);
    }


    //转至用户基本信息界面
    public void goToUserInfo(View view) {
        startActivity(new Intent(this, UserInfoActivity.class));
    }

    //转至用户教育经历界面
    public void goToEducation(View view) {
        startActivity(new Intent(this, EducationActivity.class));
    }

    //转至用户工作经验界面
    public void goToWorkExperience(View view) {
        startActivity(new Intent(this, WorkExperienceActivity.class));
    }

    //转至用户培训经历界面
    public void goToTrainExperience(View view) {
        startActivity(new Intent(this, TrainExperienceActivity.class));
    }

    //转至用户求职意向界面
    public void goToJobObjective(View view) {
        startActivity(new Intent(this, JobObjectiveActivity.class));
    }

    //转至用户语言能力界面
    public void goToLanguageAbility(View view) {
        startActivity(new Intent(this, LanguageAbilityActivity.class));
    }

}
