package com.inveno.sharesdk.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.inveno.sharesdk.R;
import com.inveno.sharesdk.data.INPlatform;
import com.inveno.sharesdk.widget.adapter.ShareAdapter;

import java.util.ArrayList;

/**
 * 分享弹窗（默认）
 * Created by zheng.hu on 2016/4/8.
 */
public class DefaultSharePopupWindow extends PopupWindow {

    private ShareAdapter adapter;

    public DefaultSharePopupWindow(Activity context, INPlatform platform, final ArrayList<String> list) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout conentView = (LinearLayout) inflater.inflate(R.layout.default_popup_main, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
//        this.setWidth(w / 2 + 50);
        this.setWidth(w);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(h/2);
        // 设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimationPreview);
        adapter = new ShareAdapter(context, list);
        GridView gridview = (GridView) conentView
                .findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onShareListener!=null){
                    onShareListener.onShare(list.get(position));
                }
                DefaultSharePopupWindow.this.dismiss();
            }
        });
    }


    public void show(){
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAtLocation(getContentView(), Gravity.BOTTOM, 0, 0);
        } else {
            this.dismiss();
        }
    }

    private OnShareListener onShareListener;
    public void setOnShareListener(OnShareListener onShareListener) {
        this.onShareListener = onShareListener;
    }
    public interface OnShareListener{
        abstract void onShare(String name);
    }
}
