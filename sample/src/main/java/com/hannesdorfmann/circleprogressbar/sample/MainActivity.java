package com.hannesdorfmann.circleprogressbar.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hannesdorfmann.circleprogressbar.sample.color.ColorActivity;
import com.hannesdorfmann.circleprogressbar.sample.xml.XmlLayoutActivity;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
  }

  @OnClick(R.id.showThemed)
  public void onStyledThemeClicked() {

  }

  @OnClick(R.id.showXmlStyled)
  public void onStyledXmlClicked() {
    startActivity(new Intent(this, XmlLayoutActivity.class));
  }

  @OnClick(R.id.showDyanmic)
  public void onStyledDynamicClicked() {

    startActivity(new Intent(this, ColorActivity.class));
  }


}
