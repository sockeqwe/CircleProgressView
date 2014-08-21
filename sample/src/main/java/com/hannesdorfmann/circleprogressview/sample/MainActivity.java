package com.hannesdorfmann.circleprogressview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hannesdorfmann.circleprogressview.sample.color.ColorActivity;
import com.hannesdorfmann.circleprogressview.sample.pulltorefresh.PullToRefreshActivity;
import com.hannesdorfmann.circleprogressview.sample.theme.ThemeActivity;
import com.hannesdorfmann.circleprogressview.sample.xml.XmlLayoutActivity;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
  }

  @OnClick(R.id.showThemed)
  public void onStyledThemeClicked() {
    startActivity(new Intent(this, ThemeActivity.class));
  }

  @OnClick(R.id.showXmlStyled)
  public void onStyledXmlClicked() {
    startActivity(new Intent(this, XmlLayoutActivity.class));
  }

  @OnClick(R.id.showDyanmic)
  public void onStyledDynamicClicked() {
    startActivity(new Intent(this, ColorActivity.class));
  }

  @OnClick(R.id.showPullToRefresh)
  public void onPullToRefreshClicked(){
    startActivity(new Intent(this, PullToRefreshActivity.class));
  }


}
