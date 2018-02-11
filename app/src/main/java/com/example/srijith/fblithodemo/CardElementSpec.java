package com.example.srijith.fblithodemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.ImageView;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.widget.ListRecyclerConfiguration;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.RenderInfo;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srijith on 22-11-2017.
 */

@LayoutSpec
public class CardElementSpec {

  @OnCreateLayout
  static ComponentLayout onCreateLayout(
    ComponentContext c,
    @Prop int id,
    @Prop Feed.FeedType type,
    @Prop String title,
    @Prop String description,
    @Prop int[] imageRes) {

    Component<Text> titleComp = Text.create(c, 0, R.style.TextAppearance_AppCompat_Title)
      .text(title)
      .marginDip(YogaEdge.TOP, 16)
      .marginDip(YogaEdge.BOTTOM, 8)
      .marginDip(YogaEdge.HORIZONTAL, 8)
      .typeface(Typeface.DEFAULT_BOLD)
      .textColor(Color.BLACK)
      .build();

    Component<Text> descComp = Text.create(c)
      .text(description)
      .maxLines(4)
      .ellipsize(TextUtils.TruncateAt.END)
      .textSizeSp(17)
      .paddingDip(YogaEdge.BOTTOM, 8)
      .marginDip(YogaEdge.VERTICAL, 16)
      .marginDip(YogaEdge.HORIZONTAL, 8)
      .build();

    return Column.create(c)
      .child(titleComp)
      .child((type == Feed.FeedType.NEWS_FEED || type == Feed.FeedType.AD_FEED) ?
        getImageComp(c, imageRes[0]) : getRecyclerComp(c, imageRes))
      .child(descComp)
      .build();

  }

  private static Component<Image> getImageComp(ComponentContext c, int imageRes) {
    return Image.create(c)
      .drawableRes(imageRes)
      .widthPercent(100)
      .heightDip(200)
      .scaleType(ImageView.ScaleType.CENTER_CROP)
      .build();
  }

  private static Component<RecyclerCollectionComponent> getRecyclerComp(ComponentContext c, int[] imageRes) {
    return RecyclerCollectionComponent.create(c)
      .heightDip(200)
      .itemDecoration(new ImageItemDecoration())
      .recyclerConfiguration(new ListRecyclerConfiguration<>(LinearLayoutManager.HORIZONTAL, false))
      .section(
        DataDiffSection.<Integer>create(new SectionContext(c))
          .data(CardElementSpec.getImageArray(imageRes))
          .renderEventHandler(CardElement.onRenderImages(c))
          .build()
      )
      .build();
  }

  @OnEvent(RenderEvent.class)
  static RenderInfo onRenderImages(final ComponentContext c, @FromEvent Integer model) {
    return ComponentRenderInfo.create()
      .component(
        Image.create(c)
          .drawableRes(model)
          .widthPercent(100)
          .heightDip(200)
          .scaleType(ImageView.ScaleType.CENTER_CROP)
          .build()
      )
      .build();
  }

  private static List<Integer> getImageArray(int[] imageRes) {
    List<Integer> images = new ArrayList<>(imageRes.length);
    for (int image : imageRes) {
      images.add(image);
    }
    return images;
  }

}
