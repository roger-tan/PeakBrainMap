package fr.rogertan.challenge.peakbrainmap.charts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.util.List;

import fr.rogertan.challenge.peakbrainmap.R;
import fr.rogertan.challenge.peakbrainmap.models.Stat;
import fr.rogertan.challenge.peakbrainmap.utils.ScreenUtils;

/**
 * Created by rogertan on 07/10/15.
 */
public class SpiderChartView extends RelativeLayout {
    // Constants
    private int CIRCLE_SIZE_SPACE = 10;
    private int CIRCLE_RADIUS = CIRCLE_SIZE_SPACE * 10;

    // Attributes
    private Context mContext;
    private List<Stat> mStats;
    private List<Stat> mCompetitorsStats;

    // Constructors
    public SpiderChartView(Context context) {
        super(context);
        init(mContext);
    }

    public SpiderChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public SpiderChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SpiderChartView(Context context, List<Stat> stats) {
        super(context);
        mStats = stats;
        init(context);
    }

    public SpiderChartView(Context context, List<Stat> userStats, List<Stat> competitorsStats) {
        super(context);
        mStats = userStats;
        mCompetitorsStats = competitorsStats;
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        Point origin = new Point();

        origin.x = ScreenUtils.dpToPx(mContext, 300) / 2;
        origin.y = ScreenUtils.dpToPx(mContext, 370) / 2;

        drawCircles(origin);
        drawAxisCircle(origin);
        drawCategories(origin);
        drawGraphStats(origin);
    }


    private void drawCircles(Point position) {
        for (int i = 1; i <= 10; i++) {
            int color = Color.rgb(105, 105, 105);
            if (i % 2 == 0) {
                color = Color.rgb(95, 95, 95);
            }
            CircleView circleView = new CircleView(mContext);
            circleView.setColor(color);
            circleView.setSize(ScreenUtils.dpToPx(mContext, CIRCLE_SIZE_SPACE * i));
            circleView.setPosition(position);
            this.addView(circleView);
        }

    }

    private void drawAxisCircle(Point position) {
        for (int i = 0; i < 6; i++) {
            LineView lineView = new LineView(mContext);
            lineView.setLength(ScreenUtils.dpToPx(mContext, CIRCLE_RADIUS + 10));
            lineView.setStart(position);
            lineView.setAngle(60 * i);
            lineView.setColor(Color.rgb(105, 105, 105));
            this.addView(lineView);
        }

        for (int i = 0; i < 6; i++) {
            Stat stat = getStats().get(i);
            Point newPosition = new Point();
            newPosition.x = position.x + (int)((ScreenUtils.dpToPx(mContext, CIRCLE_RADIUS + 15)) * Math.sin(Math.toRadians(60 * i)));
            newPosition.y = position.y + (int)((ScreenUtils.dpToPx(mContext, CIRCLE_RADIUS + 15)) * Math.cos(Math.toRadians(60 * i)));
            CircleView circleView = new CircleView(mContext);
            circleView.setColor(stat.color);
            circleView.setSize(ScreenUtils.dpToPx(mContext, 5));
            circleView.setPosition(newPosition);
            this.addView(circleView);
        }

    }

    private void drawCategories(Point position) {
        // Get size screen
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        for (int i = 0; i < 6; i++) {
            Stat stat = getStats().get(i);
            Point newPosition = new Point(position.x, position.y);
            newPosition.x = position.x + (int)((ScreenUtils.dpToPx(mContext, CIRCLE_RADIUS + 18)) * Math.sin(Math.toRadians(60 * i)));
            newPosition.y = position.y + (int)((ScreenUtils.dpToPx(mContext, CIRCLE_RADIUS + 18)) * Math.cos(Math.toRadians(60 * i)));
            CategoryView categoryView = new CategoryView(mContext);
            categoryView.setTitle(stat.title);
            categoryView.setScore(stat.score);
            categoryView.measure(width, height);

            this.addView(categoryView);
            if (Math.toRadians(60 * i) == 0) {
                categoryView.setMaxWidth(ScreenUtils.dpToPx(mContext, 150));
            }
            int h = categoryView.getMeasuredHeight();
            int w = categoryView.getMeasuredWidth();
            int moveX = -w / 2;
            int moveY = 0;
            if (newPosition.y <= position.y) {
                moveY = -(h + 20);
            } else {
                moveY = 20;
                categoryView.inverseTitleAndScore();
            }
            categoryView.setX(newPosition.x + moveX);
            categoryView.setY(newPosition.y + moveY);
            this.removeView(categoryView);
            this.addView(categoryView);
            categoryView.countUpAnimate();
        }
    }

    private void drawGraphStats(Point position) {
        GraphView graphView = new GraphView(mContext);
        graphView.setPosition(position);
        graphView.setMaxRadius((float) ScreenUtils.dpToPx(mContext, CIRCLE_RADIUS));
        graphView.setStats(mStats);
        this.addView(graphView);
        if (mCompetitorsStats != null) {
            GraphView competitorGraphView = new GraphView(mContext);
            competitorGraphView.setStats(mCompetitorsStats);
            competitorGraphView.setPosition(position);
            competitorGraphView.setColor(R.color.pink);
            competitorGraphView.setMaxRadius((float) ScreenUtils.dpToPx(mContext, CIRCLE_RADIUS));
            this.addView(competitorGraphView);

            Animation zoomInAnimation = AnimationUtils.loadAnimation(mContext, R.anim.zoom_in);
            zoomInAnimation.setDuration(1000);
            competitorGraphView.setAnimation(zoomInAnimation);
            competitorGraphView.animate();
        }
        Animation zoomInAnimation = AnimationUtils.loadAnimation(mContext, R.anim.zoom_in);
        graphView.setAnimation(zoomInAnimation);
        graphView.animate();
    }


    // Getters and Setters
    public List<Stat> getCompetitorsStats() {
        return mCompetitorsStats;
    }

    public void setCompetitorsStats(List<Stat> competitorsStats) {
        mCompetitorsStats = competitorsStats;
    }

    public List<Stat> getStats() {
        return mStats;
    }

    public void setStats(List<Stat> stats) {
        mStats = stats;
    }

}
