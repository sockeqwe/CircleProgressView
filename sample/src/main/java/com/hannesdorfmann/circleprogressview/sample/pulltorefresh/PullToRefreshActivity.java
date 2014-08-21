package com.hannesdorfmann.circleprogressview.sample.pulltorefresh;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.hannesdorfmann.CirclePullToRefreshView;
import com.hannesdorfmann.circleprogressview.sample.R;

public class PullToRefreshActivity extends Activity {

  @InjectView(R.id.seekBar) SeekBar seekBar;
  @InjectView(R.id.circleView) CirclePullToRefreshView circleView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pull_to_refresh);
    ButterKnife.inject(this);

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float proF = (float) progress;
        circleView.setProgress(progress / 100f);
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }

  @OnClick(R.id.resetButton)
  public void onResetClicked() {
    circleView.reset();
    seekBar.setProgress(0);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.pull_to_refresh, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
