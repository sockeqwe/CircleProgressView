package com.hannesdorfmann.circleprogressview.sample.color;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.hannesdorfmann.circleprogressview.sample.R;
import com.larswerkman.holocolorpicker.ColorPicker;

public class ColorPickerActivity extends Activity {

  public static final String COLOR = "color";

  @InjectView(R.id.picker)
  ColorPicker picker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_color_picker);
    ButterKnife.inject(this);
  }

  @OnClick(R.id.pickButton)
  public void onPickClicked(){
    Intent data = new Intent();
    data.putExtra(COLOR, picker.getColor());
    setResult(Activity.RESULT_OK, data);
    finish();
  }




  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.color_picker, menu);
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
