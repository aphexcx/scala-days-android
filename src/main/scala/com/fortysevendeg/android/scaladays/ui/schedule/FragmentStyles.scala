/*
 * Copyright (C) 2015 47 Degrees, LLC http://47deg.com hello@47deg.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may
 *  not use this file except in compliance with the License. You may obtain
 *  a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.fortysevendeg.android.scaladays.ui.schedule

import android.view.Gravity
import android.view.ViewGroup.LayoutParams._
import android.widget._
import com.fortysevendeg.android.scaladays.R
import com.fortysevendeg.macroid.extras.FrameLayoutTweaks._
import com.fortysevendeg.macroid.extras.LinearLayoutTweaks._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.TextTweaks._
import com.fortysevendeg.macroid.extras.ImageViewTweaks._
import com.fortysevendeg.macroid.extras.ViewTweaks._
import macroid.FullDsl._
import macroid.{AppContext, Tweak}

import scala.language.postfixOps

trait SpeakersLayoutStyles {

  def speakerNameItemStyle(name: String)(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
      tvSize(resGetInteger(R.integer.text_medium)) +
      vPadding(0, 0, resGetDimensionPixelSize(R.dimen.padding_default_extra_small), 0) +
      tvColorResource(R.color.text_schedule_name) +
      tvText(name)

  def speakerTwitterItemStyle(twitter: Option[String])(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
      tvSize(resGetInteger(R.integer.text_medium)) +
      tvColorResource(R.color.text_schedule_twitter) +
      twitter.map(tvText(_) + vVisible).getOrElse(vGone)

  def itemSpeakerContentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    vMatchWidth +
      llHorizontal +
      vPadding(0, resGetDimensionPixelSize(R.dimen.padding_default_extra_small), 0, 0)
}

trait AdapterStyles {

  def itemRootContentStyle(implicit appContext: AppContext): Tweak[FrameLayout] =
    vMatchParent +
      flForeground(resGetDrawable(R.drawable.foreground_list_dark))

  def tagFavoriteStyle(implicit appContext: AppContext): Tweak[ImageView] =
    vWrapContent +
      ivSrc(R.drawable.shedule_tag_favorite) +
      flLayoutGravity(Gravity.RIGHT) +
      vPadding(paddingRight = resGetDimensionPixelSize(R.dimen.padding_default_small))

  def itemContentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    vMatchParent +
      llHorizontal

  def hourStyle(implicit appContext: AppContext): Tweak[TextView] =
    lp[LinearLayout](resGetDimensionPixelSize(R.dimen.width_schedule_hour), MATCH_PARENT) +
      tvSize(resGetInteger(R.integer.text_small)) +
      vPadding(0, resGetDimensionPixelSize(R.dimen.padding_default_small), 0, 0) +
      vBackgroundColorResource(R.color.background_list_schedule_hour) +
      tvGravity(Gravity.CENTER_HORIZONTAL) +
      tvColorResource(R.color.text_schedule_name) +
      tvBold

  def itemInfoContentStyle(implicit appContext: AppContext): Tweak[LinearLayout] =
    vMatchWidth +
      llVertical +
      vPaddings(resGetDimensionPixelSize(R.dimen.padding_default), resGetDimensionPixelSize(R.dimen.padding_default_small)) +
      vBackgroundColorResource(R.color.background_list_schedule_info)

  val itemSpeakersContentStyle: Tweak[LinearLayout] =
    vMatchWidth +
      llVertical

  def roomItemStyle(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
      tvSize(resGetInteger(R.integer.text_small)) +
      tvColorResource(R.color.text_schedule_room) +
      vPadding(0, 0, 0, resGetDimensionPixelSize(R.dimen.padding_default_extra_small))

  def trackItemStyle(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
      tvSize(resGetInteger(R.integer.text_small)) +
      tvColorResource(R.color.text_twitter_default) +
      vPadding(0, 0, 0, resGetDimensionPixelSize(R.dimen.padding_default_extra_small))

  def nameItemStyle(implicit appContext: AppContext): Tweak[TextView] =
    vWrapContent +
      tvSize(resGetInteger(R.integer.text_medium)) +
      tvColorResource(R.color.text_schedule_name) +
      tvBold

}
