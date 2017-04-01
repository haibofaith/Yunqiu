package com.kball.function.CloudBall.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 16/9/1.
 */
public class DataLineView extends View {

    private int width;
    private int height;

    private Paint xLinePaint;// 坐标轴 轴线 画笔：
    private Paint titlePaint;// 绘制月份的画笔
    private Paint paintYText;//画Y轴百分比

    private int xLineColor = Color.parseColor("#EEEEEE");
    //月份颜色
    private int titleColor = Color.parseColor("#A8BACF");
    //Y轴百分比颜色
    private int yTextColor = Color.parseColor("#45C064");
    //    进攻：3AD8FF
    //    防守：E45C86
    //    侵略性：F0BA44
    //    技术：5ACOA8
    //    体能：40A2F7
    private Paint paintLine;
    private Paint paintLine2;
    private Paint paintLine3;
    private Paint paintLine4;
    private Paint paintLine5;
    private int jgColor = Color.parseColor("#3AD8FF");
    private int fsColor = Color.parseColor("#E45C86");
    private int qlxColor = Color.parseColor("#F0BA44");
    private int jsColor = Color.parseColor("#5AC0A8");
    private int tnColor = Color.parseColor("#40A2F7");

    private Random random;
    Path path;
    Path path2;
    Path path3;
    Path path4;
    Path path5;


    public void setxMonths(ArrayList<String> xMonths) {
        this.xMonths = xMonths;
        invalidate();
    }

    public void setyPercents(ArrayList<Double> yPercents) {
        this.yPercents = yPercents;
         invalidate();
    }

    // 坐标轴底部的月份
    private ArrayList<String> xMonths;
    private ArrayList<String> xNotes;

    public void setyPercents2(ArrayList<Double> yPercents2) {
        this.yPercents2 = yPercents2;
        invalidate();
    }

    public void setyPercents3(ArrayList<Double> yPercents3) {
        this.yPercents3 = yPercents3;
        invalidate();
    }

    public void setyPercents4(ArrayList<Double> yPercents4) {
        this.yPercents4 = yPercents4;
        invalidate();
    }

    public void setyPercents5(ArrayList<Double> yPercents5) {
        this.yPercents5 = yPercents5;
        invalidate();
    }

    private ArrayList<Double> yPercents;
    private ArrayList<Double> yPercents2;//防守
    private ArrayList<Double> yPercents3;//倾略性
    private ArrayList<Double> yPercents4;//倾略性
    private ArrayList<Double> yPercents5;//倾略性
    private String[] strings = {"100", "80", "60", "40", "20", "0"};

    public DataLineView(Context context) {
        super(context);
        init();
    }

    public DataLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        xMonths = new ArrayList<>();
        xMonths.add("7月17日");
        xMonths.add("7月23日");
        xMonths.add("8月7日");
        xMonths.add("8月20日");
        xMonths.add("10月30日");

        xNotes = new ArrayList<>();
        xNotes.add("进攻");
        xNotes.add("防守");
        xNotes.add("侵略性");
        xNotes.add("技术");
        xNotes.add("体能");

        yPercents = new ArrayList<>();
        yPercents2 = new ArrayList<>();
        yPercents3 = new ArrayList<>();
        yPercents4 = new ArrayList<>();
        yPercents5 = new ArrayList<>();
        for (int i=0;i<5;i++){
            yPercents.add(0.3);
            yPercents2.add(0.3);
            yPercents3.add(0.3);
            yPercents4.add(0.3);
            yPercents5.add(0.3);
        }
        xLinePaint = new Paint();
        titlePaint = new Paint();

        paintLine = new Paint();
        paintLine2 = new Paint();
        paintLine3 = new Paint();
        paintLine4 = new Paint();
        paintLine5 = new Paint();

        paintLine.setColor(jgColor);
        paintLine2.setColor(fsColor);
        paintLine3.setColor(qlxColor);
        paintLine4.setColor(jsColor);
        paintLine5.setColor(tnColor);

        paintLine.setAntiAlias(true);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(dp2px(2));

        paintLine2.setAntiAlias(true);
        paintLine2.setStyle(Paint.Style.STROKE);
        paintLine2.setStrokeWidth(dp2px(2));

        paintLine3.setAntiAlias(true);
        paintLine3.setStyle(Paint.Style.STROKE);
        paintLine3.setStrokeWidth(dp2px(2));

        paintLine4.setAntiAlias(true);
        paintLine4.setStyle(Paint.Style.STROKE);
        paintLine4.setStrokeWidth(dp2px(2));

        paintLine5.setAntiAlias(true);
        paintLine5.setStyle(Paint.Style.STROKE);
        paintLine5.setStrokeWidth(dp2px(2));

        // 给画笔设置颜色
        xLinePaint.setColor(xLineColor);
        xLinePaint.setAntiAlias(true);
        titlePaint.setColor(titleColor);
        titlePaint.setAntiAlias(true);
        titlePaint.setTextSize(sp2px(12));

        //Y轴
        paintYText = new Paint();
        paintYText.setColor(yTextColor);
        paintYText.setAntiAlias(true);
        paintYText.setTextSize(sp2px(12));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        super.onDraw(canvas);
        //        绘制纵向百分比
        for (int i = 1; i < 7; i++) {
            canvas.drawText(strings[i - 1], dp2px(2), height * i / 8 + sp2px(6), paintYText);
        }
        //        绘制横向线条
        for (int i = 1; i < 7; i++) {
            canvas.drawLine(0 + dp2px(20), height * i / 8, width - dp2px(20), height * i / 8
                    , xLinePaint);
        }

        // 设置底部的月份
        for (int i = 0; i < 5; i++) {
            float textWidth = titlePaint.measureText(xMonths.get(i) + "");
            canvas.drawText(xMonths.get(i), width * (i + 1) / 6 - textWidth / 2, height * 6 / 8 + height / 16
                    , titlePaint);
        }

        // 设置底部的标注
        for (int i = 0; i < 5; i++) {
            float textWidth = titlePaint.measureText(xNotes.get(i) + "");
            canvas.drawText(xNotes.get(i), width * (i + 1) / 6 - textWidth / 2, height * 6 / 8 + height * 3/ 16
                    , titlePaint);
        }

        canvas.drawLine(width * (0 + 1) /12+dp2px(8),height * 6 / 8 + height * 2/ 16,width * (1 + 2) / 12-dp2px(8),height * 6 / 8 + height * 2/ 16,paintLine);
        canvas.drawLine(width * (1 + 2) /12+dp2px(8),height * 6 / 8 + height * 2/ 16,width * (3 + 2) / 12-dp2px(8),height * 6 / 8 + height * 2/ 16,paintLine2);
        canvas.drawLine(width * (3 + 2) /12+dp2px(8),height * 6 / 8 + height * 2/ 16,width * (5 + 2) / 12-dp2px(8),height * 6 / 8 + height * 2/ 16,paintLine3);
        canvas.drawLine(width * (5 + 2) /12+dp2px(8),height * 6 / 8 + height * 2/ 16,width * (7 + 2) / 12-dp2px(8),height * 6 / 8 + height * 2/ 16,paintLine4);
        canvas.drawLine(width * (7 + 2) /12+dp2px(8),height * 6 / 8 + height * 2/ 16,width * (9 + 2) / 12-dp2px(8),height * 6 / 8 + height * 2/ 16,paintLine5);

        //进攻
        path = new Path();
        path.reset();
        for (int i = 0; i < 5; i++) {
            if (i + 1 < 5) {
                float startX = width * (i + 1) / 6;
                float startY = (float) (height * 6 / 8 - height * yPercents.get(i) * 5 / 8);
                float endX = width * (i + 2) / 6;
                float endY = (float) (height * 6 / 8 - height * yPercents.get(i + 1) * 5 / 8);
                if (i ==0 ){
                    path.moveTo(startX,startY);
                    path.lineTo(endX,endY);
                }else{
                    path.lineTo(endX,endY);
                }
            }
        }
        canvas.drawPath(path,paintLine);

        //防守
        path2 = new Path();
        path2.reset();
        for (int i = 0; i < 5; i++) {
            if (i + 1 < 5) {
                float startX = width * (i + 1) / 6;
                float startY = (float) (height * 6 / 8 - height * yPercents2.get(i) * 5 / 8);
                float endX = width * (i + 2) / 6;
                float endY = (float) (height * 6 / 8 - height * yPercents2.get(i + 1) * 5 / 8);
                if (i ==0 ){
                    path2.moveTo(startX,startY);
                    path2.lineTo(endX,endY);
                }else{
                    path2.lineTo(endX,endY);
                }
            }
        }
        canvas.drawPath(path2,paintLine2);

        //防守
        path3 = new Path();
        path3.reset();
        for (int i = 0; i < 5; i++) {
            if (i + 1 < 5) {
                float startX = width * (i + 1) / 6;
                float startY = (float) (height * 6 / 8 - height * yPercents3.get(i) * 5 / 8);
                float endX = width * (i + 2) / 6;
                float endY = (float) (height * 6 / 8 - height * yPercents3.get(i + 1) * 5 / 8);
                if (i ==0 ){
                    path3.moveTo(startX,startY);
                    path3.lineTo(endX,endY);
                }else{
                    path3.lineTo(endX,endY);
                }
            }
        }
        canvas.drawPath(path3,paintLine3);

        //防守
        path4 = new Path();
        path4.reset();
        for (int i = 0; i < 5; i++) {
            if (i + 1 < 5) {
                float startX = width * (i + 1) / 6;
                float startY = (float) (height * 6 / 8 - height * yPercents4.get(i) * 5 / 8);
                float endX = width * (i + 2) / 6;
                float endY = (float) (height * 6 / 8 - height * yPercents4.get(i + 1) * 5 / 8);
                if (i ==0 ){
                    path4.moveTo(startX,startY);
                    path4.lineTo(endX,endY);
                }else{
                    path4.lineTo(endX,endY);
                }
            }
        }
        canvas.drawPath(path4,paintLine4);

        //防守
        path5 = new Path();
        path5.reset();
        for (int i = 0; i < 5; i++) {
            if (i + 1 < 5) {
                float startX = width * (i + 1) / 6;
                float startY = (float) (height * 6 / 8 - height * yPercents5.get(i) * 5 / 8);
                float endX = width * (i + 2) / 6;
                float endY = (float) (height * 6 / 8 - height * yPercents5.get(i + 1) * 5 / 8);
                if (i ==0 ){
                    path5.moveTo(startX,startY);
                    path5.lineTo(endX,endY);
                }else{
                    path5.lineTo(endX,endY);
                }
            }
        }
        canvas.drawPath(path5,paintLine5);


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
