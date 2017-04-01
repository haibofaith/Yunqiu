package com.kball.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.kball.R;


public abstract class BaseDialog extends Dialog {

    /**
     * 用一个默认样式文件
     * @param context C
     * @param layoutResId 布局文件
     */
    public BaseDialog(Context context, int layoutResId) {
        super(context, R.style.kballTheme_Dialog);
        init(layoutResId);
    }

    /**
     *
     * @param context C
     * @param layoutResId 布局文件
     * @param styleId 样式文件
     */
    public BaseDialog(Context context, int layoutResId, int styleId) {
        super(context, styleId);
        init(layoutResId);
    }

    private void init(int layoutResId){
        setContentView(layoutResId);
        setProperty();
        initView();
        initData();
        setListener();
    }


    private void setProperty() {
        Window window = getWindow();
        WindowManager.LayoutParams p = window.getAttributes();
        Display d = getWindow().getWindowManager().getDefaultDisplay();

        // d.getHeight()在API13以上为废弃方法。因此出现废弃警告可不予理会
        p.height = (int) (d.getHeight() * 1);
        p.width = (int) (d.getWidth() * 1);
        window.setAttributes(p);

    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();
}
