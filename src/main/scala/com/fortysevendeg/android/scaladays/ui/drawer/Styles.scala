package com.fortysevendeg.android.scaladays.ui.drawer

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget.{AbsListView, LinearLayout}
import com.fortysevendeg.android.scaladays.R
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.AppContext
import macroid.FullDsl._

trait Styles {

  val menuStyle = vMatchParent + 
    llVertical

  def drawerMenuStyle(implicit appContext: AppContext) = lp[AbsListView](MATCH_PARENT, MATCH_PARENT) +
    vBackgroundColorResource(R.color.background_menu)

  def bigImageLayoutStyle(implicit appContext: AppContext) = lp[LinearLayout](MATCH_PARENT, 169 dp) +
    vBackgroundColorResource(R.color.background_header_menu)
  
  val bigImageStyle = vMatchParent
  
  def bigImageActionLayout(implicit appContext: AppContext) = vMatchWidth +
    llGravity(Gravity.CENTER_VERTICAL) +
    flLayoutGravity(Gravity.BOTTOM) +
    vPadding(16 dp, 15 dp, 16 dp, 15 dp)
  
  def conferenceTitleStyle(implicit appContext: AppContext) = llWrapWeightHorizontal +
    tvSize(14) +
    tvColor(Color.WHITE) + 
    tvBoldLight

  val conferenceSelectorStyle = vWrapContent +
    ivSrc(R.drawable.menu_header_select_arrow)

  def menuItemStyle(implicit appContext: AppContext) = lp[AbsListView](MATCH_PARENT, 48 dp) +
    tvSize(14) +
    tvColor(Color.WHITE) +
    tvGravity(Gravity.CENTER_VERTICAL) +
    vPadding(18 dp, 14 dp, 18 dp, 14 dp) +
    tvDrawablePadding(34 dp) +
    tvBoldLight +
    vBackground(R.drawable.background_list_menu)

}
