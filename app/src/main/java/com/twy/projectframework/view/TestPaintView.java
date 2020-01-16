package com.twy.projectframework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;

import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author by twy, Email 499216359@qq.com, Date on 2019/4/11.
 * PS: Not easy to write code, please indicate.
 */
public class TestPaintView extends View {
    private final Bitmap srcBmp;//源图像
    private final Bitmap dstBmp;//目标图像
    private final Paint mPaint;
    private Region.Op op = Region.Op.INTERSECT;
    private final Paint paint2;

    public TestPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        srcBmp = makeSrc(400, 400);
        dstBmp = makeDst(400, 400);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setDither(true);
        paint2.setStrokeWidth(1);
        paint2.setTextSize(100);
        paint2.setAntiAlias(true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);
        canvas.drawColor(Color.BLUE);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(80,80,180,180),mPaint);
        canvas.save();
        //canvas.rotate(45);
        canvas.rotate(45,200,200);
        mPaint.setColor(Color.RED);
        canvas.drawRect(new RectF(80,80,180,180),mPaint);
        canvas.restore();
    }

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFE81E63);
        c.drawOval(new RectF(w/4, h/4, w/4+w/2, h/4+h/2), p);
        return bm;
    }
    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF2195F2);
        c.drawRect(0, h/2, w/2, h, p);
        return bm;
    }

    public void setPorterDuffXfermode(Region.Op op){
        this.op = op;
        postInvalidate();
    }
}
