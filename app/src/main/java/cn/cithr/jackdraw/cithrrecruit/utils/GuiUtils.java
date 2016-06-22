package cn.cithr.jackdraw.cithrrecruit.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by xusha on 2016/6/16.
 */
public class GuiUtils {
    public interface OnRevealAnimationListener {
        void onRevealHide();

        void onRevealShow();
    }


    // 圆圈爆炸效果显示
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealShow(
            final Context context, final View view, final View fab,
            final int startRadius,
            final OnRevealAnimationListener listener) {
        int cx = (fab.getLeft() + fab.getRight()) / 2;
        int cy = (fab.getTop() + fab.getBottom()) / 2;

        float finalRadius = (float) Math.hypot(view.getWidth(), view.getHeight());

        // 设置圆形显示动画
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, startRadius, finalRadius);
        anim.setDuration(400);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.VISIBLE);
                listener.onRevealShow();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                //view.setBackgroundColor(ContextCompat.getColor(context,color));
            }
        });

        anim.start();
    }


    // 圆圈凝聚效果
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealHide(
            final Context context, final View view, final View fab,
            final int finalRadius,
            final OnRevealAnimationListener listener
    ) {
        int cx = (fab.getLeft() + fab.getRight()) / 2;
        int cy = (fab.getTop() + fab.getBottom()) / 2;
        int initialRadius = view.getWidth();
        // 与入场动画的区别就是圆圈起始和终止的半径相反
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, finalRadius);
        anim.setDuration(400);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                //view.setBackgroundColor(ContextCompat.getColor(context, color));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
                listener.onRevealHide();
            }
        });
        anim.start();
    }
}
