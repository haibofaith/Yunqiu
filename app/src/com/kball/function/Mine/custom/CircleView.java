package com.kball.function.Mine.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/2/17.
 */

public class CircleView extends View {
    private Context mcontext;
    private int width;
    private int height;
    private int realRadius;
    private Paint paint;//底色
    private Paint paintP;//百分比色 胜利
    private Paint paintP2;//百分比色2 平
    private Paint paintP3;//百分比色3 失败
    private Paint paintF;//前景色
    private Paint paintT;//文本颜色及大小
    private Paint paintN;//数字文本颜色及大小
    private int color = Color.parseColor("#EE8026");//未录入
    private int pColor = Color.parseColor("#6DB247");//green 胜利
    private int pColor2 = Color.parseColor("#2D2D4A");//紫色 平
    private int pColor3 = Color.parseColor("#2C78B2");//blue 失败
    private int tColor = Color.parseColor("#333333");
    private int nColor = Color.parseColor("#545471");
    private float percent;//胜利

    private float percent2;//平
    private float percent3;//失败
    private String text = "0";
    private String numText = "--";
    private int textSize = 14;
    private int numTextSize = 16;
    private float sweepAngle;
    private float sweepAngle2;
    private float sweepAngle3;


    //圆环厚度：默认值12
    private int thickness = dp2px(6);


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

    public void setPercent2(float percent2) {
        this.percent2 = percent2;
    }

    public void setPercent3(float percent3) {
        this.percent3 = percent3;
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

        paintP2 = new Paint();
        paintP2.setColor(pColor2);
        paintP2.setAntiAlias(true);

        paintP3 = new Paint();
        paintP3.setColor(pColor3);
        paintP3.setAntiAlias(true);

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
        if (percent2 != 0) {
            sweepAngle2 = percent2 * 360;
        } else {
            sweepAngle2 = 0;
        }
        if (percent3 != 0) {
            sweepAngle3 = percent3 * 360;
        } else {
            sweepAngle3 = 0;
        }


        canvas.drawArc(new RectF(width - realRadius, height - realRadius, realRadius + width, realRadius + height), -90,
                sweepAngle, true, paintP);
        canvas.drawArc(new RectF(width - realRadius, height - realRadius, realRadius + width, realRadius + height), -90 + sweepAngle,
                sweepAngle2, true, paintP2);
        canvas.drawArc(new RectF(width - realRadius, height - realRadius, realRadius + width, realRadius + height), -90 + sweepAngle + sweepAngle2,
                sweepAngle3, true, paintP3);

        canvas.drawCircle(width, height, realRadius - thickness, paintF);
        canvas.drawText(text, width - textWidth, height, paintT);
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
