package com.kball.function.Mine.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by user on 2017/2/26.
 */

public class RadarView extends View {
    private int width;
    private int height;
    private int realRadius;
    private Paint paint;//
    private Paint paintPercent;
    private int color = Color.parseColor("#C8DDEB");
    private int colorPercent = Color.parseColor("#A6D5DF");

    public void setPercents(float[] percents) {
        this.percents = percents;
    }
    //进攻，技术，倾略性，体能，防守
    private float[] percents = {(float) 0.5, (float) 0.7, (float) 0.8, (float) 0.6, (float) 0.4};

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth() / 2;
        height = getMeasuredHeight() / 2;
        //一个巧妙的适配算法

        if (height > width) {
            realRadius = width;
        } else {
            realRadius = height;
        }

        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth((float) 1);              //线宽
        paint.setStyle(Paint.Style.STROKE);

        paintPercent = new Paint();
        paintPercent.setColor(colorPercent);
        paintPercent.setAntiAlias(true);
        paintPercent.setAlpha(100);
        paintPercent.setStyle(Paint.Style.FILL_AND_STROKE);

        drawWebLine(canvas);

        drawRadarLine(canvas);

        drawArea(canvas);
    }

    private void drawWebLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < 5; i++) {
            path.moveTo(realRadius, realRadius - realRadius * (5 - i) / 5);
            path.lineTo((float) (realRadius + ((realRadius * Math.sin(72 * Math.PI / 180))) * (5 - i) / 5),
                    (float) (realRadius - (realRadius * Math.cos(72 * Math.PI / 180)) * (5 - i) / 5));
            path.lineTo((float) (realRadius + (realRadius * Math.sin(36 * Math.PI / 180) * (5 - i) / 5)), (float) (realRadius + (realRadius * Math.cos(36 * Math.PI / 180)) * (5 - i) / 5));
            path.lineTo((float) (realRadius - (realRadius * Math.sin(36 * Math.PI / 180) * (5 - i) / 5)), (float) (realRadius + (realRadius * Math.cos(36 * Math.PI / 180)) * (5 - i) / 5));
            path.lineTo((float) (realRadius - (realRadius * Math.sin(72 * Math.PI / 180) * (5 - i) / 5)), (float) (realRadius - (realRadius * Math.cos(72 * Math.PI / 180)) * (5 - i) / 5));
            path.lineTo(realRadius, realRadius - realRadius * (5 - i) / 5);
            canvas.drawPath(path, paint);
        }
    }

    private void drawRadarLine(Canvas canvas) {
        int i = 0;
        canvas.drawLine(realRadius, realRadius, realRadius, realRadius - realRadius * (5 - i) / 5, paint);
        canvas.drawLine(realRadius, realRadius, (float) (realRadius + ((realRadius * Math.sin(72 * Math.PI / 180))) * (5 - i) / 5),
                (float) (realRadius - (realRadius * Math.cos(72 * Math.PI / 180)) * (5 - i) / 5), paint);
        canvas.drawLine(realRadius, realRadius, (float) (realRadius + (realRadius * Math.sin(36 * Math.PI / 180) * (5 - i) / 5)),
                (float) (realRadius + (realRadius * Math.cos(36 * Math.PI / 180)) * (5 - i) / 5), paint);
        canvas.drawLine(realRadius, realRadius, (float) (realRadius - (realRadius * Math.sin(36 * Math.PI / 180) * (5 - i) / 5)),
                (float) (realRadius + (realRadius * Math.cos(36 * Math.PI / 180)) * (5 - i) / 5), paint);
        canvas.drawLine(realRadius, realRadius, (float) (realRadius - (realRadius * Math.sin(72 * Math.PI / 180) * (5 - i) / 5)),
                (float) (realRadius - (realRadius * Math.cos(72 * Math.PI / 180)) * (5 - i) / 5), paint);
    }



    private void drawArea(Canvas canvas) {
        Path path = new Path();
        path.moveTo(realRadius, realRadius - realRadius * percents[0]);
        path.lineTo((float) (realRadius + ((realRadius * Math.sin(72 * Math.PI / 180))) * percents[1]),
                (float) (realRadius - (realRadius * Math.cos(72 * Math.PI / 180)) * percents[1]));
        path.lineTo((float) (realRadius + (realRadius * Math.sin(36 * Math.PI / 180) * percents[2])),
                (float) (realRadius + (realRadius * Math.cos(36 * Math.PI / 180)) * percents[2]));
        path.lineTo((float) (realRadius - (realRadius * Math.sin(36 * Math.PI / 180) * percents[3])),
                (float) (realRadius + (realRadius * Math.cos(36 * Math.PI / 180)) * percents[3]));
        path.lineTo((float) (realRadius - (realRadius * Math.sin(72 * Math.PI / 180) * percents[4])),
                (float) (realRadius - (realRadius * Math.cos(72 * Math.PI / 180)) * percents[4]));
        path.lineTo(realRadius, realRadius - realRadius * percents[0]);
        canvas.drawPath(path, paintPercent);
    }

}
