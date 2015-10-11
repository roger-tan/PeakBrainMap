package fr.rogertan.challenge.peakbrainmap.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rogertan on 07/10/15.
 */

public class CircleView extends View {
    private Context mContext;
    private Paint mPaint;
    private Point mPosition;
    private int mSize = 0;
    private int mColor = Color.BLACK;

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(getColor());
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2.0f);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getPosition() == null) {
            throw new AssertionError("Please set a position");
        }
        if (getSize() == 0) {
            throw new AssertionError("Please set a size");
        }
        canvas.drawCircle(getPosition().x, getPosition().y, getSize(), mPaint);
    }

    public Point getPosition() {
        return mPosition;
    }

    public void setPosition(Point position) {
        mPosition = position;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        mPaint.setColor(color);
    }

    // Getters and Setters


}
