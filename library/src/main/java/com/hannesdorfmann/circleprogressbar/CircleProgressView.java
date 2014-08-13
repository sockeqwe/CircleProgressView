package com.hannesdorfmann.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Simplest custom view possible, using CircularProgressDrawable
 */
public class CircleProgressView extends View {

  private CircularProgressDrawable mDrawable;

  public CircleProgressView(Context context) {
    this(context, null);
  }

  public CircleProgressView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleProgressView);

    // The color size
    int color = a.getColor(R.styleable.CircleProgressView_cpvColor, Color.LTGRAY);

    // The stroke size
    int strokeSize =
        a.getDimensionPixelSize(R.styleable.CircleProgressView_cpvStrokeWidth, dpToPx(context, 6));

    // How long should it take to make a complete circle
    int circleAnimDuration = a.getInt(R.styleable.CircleProgressView_cpvCircleAnimDuration, 2000);

    // How long should it take to sweep the tail
    int sweepAnimDuration = a.getInt(R.styleable.CircleProgressView_cpvSweepAnimDuration, 600);

    a.recycle();

    mDrawable = new CircularProgressDrawable(Color.LTGRAY, strokeSize, circleAnimDuration,
        sweepAnimDuration);

    mDrawable.setCallback(this);
  }

  public int dpToPx(Context context, int dp) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return (int) ((dp * displayMetrics.density) + 0.5);
  }

  @Override
  protected void onVisibilityChanged(View changedView, int visibility) {
    super.onVisibilityChanged(changedView, visibility);
    if (visibility == VISIBLE) {
      mDrawable.start();
    } else {
      mDrawable.stop();
    }
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mDrawable.setBounds(0, 0, w, h);
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    mDrawable.draw(canvas);
  }

  @Override
  protected boolean verifyDrawable(Drawable who) {
    return who == mDrawable || super.verifyDrawable(who);
  }

  public void setColor(int color) {
    mDrawable.setColor(color);
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mDrawable.stop();
  }
}