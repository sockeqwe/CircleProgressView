package com.hannesdorfmann;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * @author Hannes Dorfmann
 */
public class PopOutCircularProgressDrawable extends CircularProgressDrawable {

  private static float POPOUT_SHOW_DURATION = 8000;
  private static float POPOUT_HIDE_DURATION = ANGLE_ANIMATOR_DURATION;

  private boolean mPopOut = true;
  private RectF mPopOutTargetArea = new RectF();
  private RectF mPopOutCurrentArea = new RectF();
  private ObjectAnimator mShowPopOutAnimator;
  private ObjectAnimator mHidePopOutAnimator;
  private float mProgress = 0f;
  private int mHidePopOutProgress = 255;

  private Paint mPaint = new Paint();

  public PopOutCircularProgressDrawable(int popOutColor, int[] colors, float strokeWidth,
      float speed, int minSweepAngle, int maxSweepAngle, Style style) {

    super(colors, strokeWidth, speed, minSweepAngle, maxSweepAngle, style);

    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(strokeWidth);
    mPaint.setColor(popOutColor);
  }

  @Override
  protected void onBoundsChange(Rect bounds) {
    super.onBoundsChange(bounds);
    mPopOutTargetArea.left = bounds.left + getStrokeWidth() / 2f + .5f;
    mPopOutTargetArea.right = bounds.right - getStrokeWidth() / 2f - .5f;
    mPopOutTargetArea.top = bounds.top + getStrokeWidth() / 2f + .5f;
    mPopOutTargetArea.bottom = bounds.bottom - getStrokeWidth() / 2f - .5f;
  }

  public PopOutCircularProgressDrawable setPopOutColor(int color) {
    mPaint.setColor(color);
    return this;
  }

  public int getPopOutColor() {
    return mPaint.getColor();
  }

  public PopOutCircularProgressDrawable setPopOut(boolean enabled) {
    mPopOut = enabled;
    return this;
  }

  public boolean isPopOutEnabled() {
    return mPopOut;
  }

  @Override
  public void reset() {
    super.reset();
    mPopOutCurrentArea.left = 0;
    mPopOutCurrentArea.top = 0;
    mPopOutCurrentArea.right = 0;
    mPopOutCurrentArea.bottom = 0;
    mPaint.setAlpha(255);
  }

  private void startPopOutAnimation() {
    stop();

    mShowPopOutAnimator = ObjectAnimator.ofFloat(this, "progress", 0f, 1f)
        .setDuration((int) (POPOUT_SHOW_DURATION / getSpeed() + 0.5));

    mShowPopOutAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationCancel(Animator animation) {
        reset();
      }

      @Override public void onAnimationEnd(Animator animation) {
        startCircleAndPopHiding();
      }
    });

    mShowPopOutAnimator.start();
  }

  @Override
  public void draw(Canvas canvas) {
    super.draw(canvas);
    // TODO use drawCircle?
    canvas.drawArc(mPopOutCurrentArea, 0, 360, false, mPaint);
  }

  @Override
  public void setProgress(float progress) {
    mProgress = progress;

    mPopOutCurrentArea.left = mapPoint(progress, 0, 1, 0, mPopOutTargetArea.left);
    mPopOutCurrentArea.top = mapPoint(progress, 0, 1, 0, mPopOutTargetArea.top);
    mPopOutCurrentArea.right = mapPoint(progress, 0, 1, 0, mPopOutTargetArea.right);
    mPopOutCurrentArea.bottom = mapPoint(progress, 0, 1, 0, mPopOutTargetArea.bottom);

    invalidateSelf();
  }

  public float getProgress() {
    return mProgress;
  }

  public float getHidePopOut() {
    return mHidePopOutProgress;
  }

  public void setHidePopOut(int progress) {
    mHidePopOutProgress = progress;
    mPaint.setAlpha(progress);
    invalidateSelf();
  }

  protected void startCircleAndPopHiding() {
    super.start();
    mHidePopOutAnimator = ObjectAnimator.ofInt(this, "hidePopOut", 255, 0)
        .setDuration((int) (POPOUT_HIDE_DURATION / getSpeed() + 0.5f));

    mHidePopOutAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationCancel(Animator animation) {
        reset();
      }
    });
    mHidePopOutAnimator.start();
  }

  @Override
  public void start() {
    startPopOutAnimation();
  }

  @Override
  public void stop() {
    if (mShowPopOutAnimator != null) {
      mShowPopOutAnimator.cancel();
    }

    if (mHidePopOutAnimator != null) {
      mHidePopOutAnimator.cancel();
    }

    super.stop();
  }
}
