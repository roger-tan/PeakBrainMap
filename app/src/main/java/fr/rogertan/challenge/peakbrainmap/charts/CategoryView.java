package fr.rogertan.challenge.peakbrainmap.charts;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.rogertan.challenge.peakbrainmap.R;

/**
 * TODO: document your custom view class.
 */
public class CategoryView extends LinearLayout {
    private Context mContext;
    private String mTitle;
    private int mScore;
    private int mColor;
    private int mIsTop;
    private Point mPosition;
    private TextView mScoreTextView;
    private TextView mTitleTextView;
    private int maxWidth;
    private boolean mIsInversed = false;

    public CategoryView(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    public CategoryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.chart_category_view, this);

        mScoreTextView = (TextView)this.findViewById(R.id.legend_score);
        mScoreTextView.setTypeface(null, Typeface.BOLD);

        mTitleTextView = (TextView)this.findViewById(R.id.legend_title);
    }

    public void inverseTitleAndScore() {
        String title = this.getTitle();
        String score = Integer.toString(this.getScore());

        mScoreTextView.setText(title);
        mScoreTextView.setTypeface(null, Typeface.NORMAL);
        mTitleTextView.setText(score);
        mTitleTextView.setTypeface(null, Typeface.BOLD);
        this.mIsInversed = true;

    }


    public void countUpAnimate() {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, this.mScore);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                TextView view = (mIsInversed) ? mTitleTextView : mScoreTextView;
                view.setText(String.valueOf(animation.getAnimatedValue()));
            }
        });
        animator.setEvaluator(new TypeEvaluator<Integer>() {
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                return Math.round(startValue + (endValue - startValue) * fraction);
            }
        });
        animator.setDuration(1000);
        animator.start();
    }

    // Getters and setters

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitleTextView.setText(title);
        mTitle = title;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScoreTextView.setText(Integer.toString(score));
        mScore = score;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }


    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        mScoreTextView.setMaxWidth(maxWidth);
        mTitleTextView.setMaxWidth(maxWidth);
    }


}
