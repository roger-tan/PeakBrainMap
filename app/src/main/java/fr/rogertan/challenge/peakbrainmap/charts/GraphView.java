package fr.rogertan.challenge.peakbrainmap.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.rogertan.challenge.peakbrainmap.models.Stat;

/**
 * Created by rogertan on 08/10/15.
 */
public class GraphView extends View{
    private Context mContext;
    private List<Stat> mStats;
    private Paint mPaint;
    private Point mPosition;
    private int mColor = Color.argb(200, 255, 255, 255);
    private float mMaxRadius = 0;
    private float mStrokeWidth = 3.0f;

    GraphView(Context context) {
        super(context);
        init(context);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

//
//    GraphView(Context context, Point origin, List<Stat> stats) {
//        super(context);
//        mContext = context;
//        setStats(stats);
//        setPosition(origin);
//        commonInit();
//    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(getColor());
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(getStrokeWidth());
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mStats == null) {
            throw new AssertionError("Please add a list of stats");
        }
        if (mPosition == null) {
            throw new AssertionError("Please set a position");
        }
        if (mMaxRadius == 0) {
            throw new AssertionError("Please set a maximum radius");
        }

        Path graphPath = makePath();
        canvas.drawPath(graphPath, mPaint);
    }



    private Path makePath() {
        Point firstPoint = new Point(0, 0);
        Path path = new Path();
        List<Point> positions = new ArrayList<Point>();
        for (int i = 0; i < getStats().size(); i++) {
            Stat stat = getStats().get(i);
            Point coordinate = new Point();
            coordinate.x = getPosition().x + (int)((stat.score * getMaxRadius() / 1000) * Math.sin(Math.toRadians(60 * i)));
            coordinate.y = getPosition().y + (int)((stat.score * getMaxRadius() / 1000) * Math.cos(Math.toRadians(60 * i)));
            if (i == 0) {
                firstPoint = new Point(coordinate.x, coordinate.y);
                path.moveTo(coordinate.x, coordinate.y);
            } else {
                positions.add(coordinate);
                path.lineTo(coordinate.x, coordinate.y);
            }
        }
        path.lineTo(firstPoint.x, firstPoint.y);
        path.close();
        return path;
    }

    public List<Stat> getStats() {
        return mStats;
    }

    public void setStats(List<Stat> stats) {
        mStats = stats;
    }

    public Point getPosition() {
        return mPosition;
    }

    public void setPosition(Point position) {
        mPosition = position;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        mPaint.setColor(color);
    }

    public float getMaxRadius() {
        return mMaxRadius;
    }

    public void setMaxRadius(float maxRadius) {
        mMaxRadius = maxRadius;
    }

    public float getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        mStrokeWidth = strokeWidth;
    }
}
