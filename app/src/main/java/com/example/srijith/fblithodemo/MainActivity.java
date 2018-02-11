package com.example.srijith.fblithodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final ComponentContext c = new ComponentContext(this);
    final Component component = RecyclerCollectionComponent.create(c)
      .section(ListSection.create(new SectionContext(c)).build())
      .build();

    final LithoView lithoView = LithoView.create(c, component);

    setContentView(lithoView);

  }
}
