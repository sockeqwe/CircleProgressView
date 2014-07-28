package com.hannesdorfmann.circleprogressbar.sample.color;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.hannesdorfmann.circleprogressbar.CircleProgressView;
import com.hannesdorfmann.circleprogressbar.sample.R;

public class ColorActivity extends Activity {

  static final int REQ_PICKER = 42;

  @InjectView(R.id.circleProgressView) CircleProgressView progressView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_color);
    ButterKnife.inject(this);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQ_PICKER && resultCode == RESULT_OK){
      int color = data.getIntExtra(ColorPickerActivity.COLOR, 0);
        progressView.setColor(color);
    }
  }

  @OnClick(R.id.changeColorButton)
  public void pickColorClicked(){
    startActivityForResult(new Intent(this, ColorPickerActivity.class), REQ_PICKER);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.color, menu);
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
