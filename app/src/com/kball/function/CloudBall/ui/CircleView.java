package com.kball.function.CloudBall.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/2/15.
 * 首次使用：首页买断数据圆形图
 */

public class CircleView extends View {
    private Context mcontext;
    private int width;
    private int height;
    private int realRadius;
    private Paint paint;//底色
    private Paint paintP;//百分比色
    private Paint paintF;//前景色
    private Paint paintT;//文本颜色及大小
    private Paint paintN;//数字文本颜色及大小
    private int color = Color.parseColor("#e8e8e8");
    private int pColor = Color.parseColor("#f8c432");
    private int tColor = Color.parseColor("#333333");
    private int nColor = Color.parseColor("#333333");
    private float percent;
    private String text = "";
    private String numText = "--";
    private int textSize = 14;
    private int numTextSize = 16;
    private float sweepAngle;


    //圆环厚度：默认值12
    private int thickness = dp2px(12);


    private int paddingtext = sp2px(14);

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mcontext = context;
    }

    /**
     * 设置圆环厚度
     */
    public void setThickness(int thickness) {
        this.thickness = dp2px(thickness);
    }

    /**
     * 上下文字间距
     */
    public void setPaddingtext(int paddingtext) {
        this.paddingtext = sp2px(paddingtext);
    }

    /**
     * 对外提供设置数据
     */
    public void setPercent(float percent) {
        this.percent = percent;
    }

    /**
     * 对外提供设置文字
     */
    public void setDetailText(String text) {
        this.text = text;
    }

    /**
     * 对外提供设置文字颜色
     */
    public void setDetailTextSize(int textSize) {
        this.textSize = textSize;
    }

    /**
     * 对外提供设置数字
     */
    public void setNumText(String numText) {
        this.numText = numText;
    }

    /**
     * 对外提供设置数字颜色
     */
    public void setNumTextSize(int numTextSize) {
        this.numTextSize = numTextSize;
    }

    /**
     * 设置背景颜色：默认为灰色
     */
    public void setbgColor(int color) {
        this.color = color;
    }

    /**
     * 设置百分比颜色：默认为绿
     */
    public void setpColor(int pColor) {
        this.pColor = pColor;
    }

    /**
     * 设置文本颜色：默认为浅灰色
     */
    public void settColor(int tColor) {
        this.tColor = tColor;
    }

    /**
     * 设置文本数字颜色：默认为深灰色
     */
    public void setnColor(int nColor) {
        this.nColor = nColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth() / 2;
        height = getMeasuredHeight() / 2;
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);

        paintP = new Paint();
        paintP.setColor(pColor);
        paintP.setAntiAlias(true);

        paintF = new Paint();
        paintF.setColor(Color.WHITE);
        paintF.setAntiAlias(true);

        paintT = new Paint();
        paintT.setColor(tColor);
        paintT.setTextSize(sp2px(textSize));
        paintT.setAntiAlias(true);

        paintN = new Paint();
        paintN.setColor(nColor);
        paintN.setTextSize(sp2px(numTextSize));
        paintN.setAntiAlias(true);

        float textWidth = paintT.measureText(text) / 2;
        float numWidth = paintN.measureText(numText) / 2;

        //一个巧妙的适配算法

        if (height > width) {
            realRadius = width;
        } else {
            realRadius = height;
        }
        canvas.drawCircle(width, height, realRadius, paint);

        if (percent != 0) {
            sweepAngle = percent * 360;
        } else {
            sweepAngle = 0;
        }

        canvas.drawArc(new RectF(width - realRadius, height - realRadius, realRadius + width, realRadius + height),
                -90, sweepAngle, true, paintP);
        canvas.drawCircle(width, height, realRadius - thickness, paintF);
        canvas.drawText(text, width - textWidth, height - sp2px(2), paintT);
        canvas.drawText(numText, width - numWidth, height + 3 + sp2px(2), paintN);

    }

    private int dp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    private int sp2px(int value) {
        float v = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (v * value + 0.5f);
    }
}
