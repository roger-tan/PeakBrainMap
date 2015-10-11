package fr.rogertan.challenge.peakbrainmap.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rogertan on 08/10/15.
 */
public class LineView extends View {
    private Context mContext;
    private Paint mPaint;
    private Point mStart;
    private Point mStop = new Point();
    private int mLength = 0;
    private int mAngle = 0;
    private int mColor = Color.BLACK;
    private float mStrokeWidth;

    public LineView(Context context) {
        super(context);
        init(context);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(getColor());
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3.0f);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLength == 0) {
            throw new AssertionError("Please set a length");
        }
        if (mStart == null) {
            throw new AssertionError("Please set start position");
        }

        mStop.x = mStart.x + (int)(mLength * Math.sin(Math.toRadians(mAngle)));
        mStop.y = mStart.y + (int)(mLength * Math.cos(Math.toRadians(mAngle)));
        canvas.drawLine(mStart.x, mStart.y, mStop.x, mStop.y, mPaint);
    }

    // Getters and setters

    public Point getStart() {
        return mStart;
    }

    public void setStart(Point start) {
        mStart = start;
    }

    public Point getStop() {
        return mStop;
    }

    public void setStop(Point stop) {
        mStop = stop;
    }

    public int getLength() {
        return mLength;
    }

    public void setLength(int length) {
        mLength = length;
    }

    public int getAngle() {
        return mAngle;
    }

    public void setAngle(int angle) {
        mAngle = angle;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        mPaint.setColor(getColor());
    }
}
