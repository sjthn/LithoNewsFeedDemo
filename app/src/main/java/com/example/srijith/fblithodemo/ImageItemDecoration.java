package com.example.srijith.fblithodemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Srijith on 28-01-2018.
 */

public class ImageItemDecoration extends RecyclerView.ItemDecoration {

  private Paint paint;

  public ImageItemDecoration() {
    paint = new Paint();
  }

  @Override
  public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    paint.setColor(ContextCompat.getColor(parent.getContext(), R.color.white));
    int count = parent.getChildCount();
    for (int i = 0; i < count; i++) {
      View view = parent.getChildAt(i);
      int width = view.getRight();
      int height = view.getBottom();
      c.drawRect(width, 0, width + 4, height, paint);
    }
  }
}
