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

package com.fortysevendeg.android.scaladays.ui.speakers

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.{LayoutInflater, View, ViewGroup}
import com.fortysevendeg.android.scaladays.R
import com.fortysevendeg.android.scaladays.model.Speaker
import com.fortysevendeg.android.scaladays.modules.ComponentRegistryImpl
import com.fortysevendeg.android.scaladays.ui.commons.AnalyticStrings._
import com.fortysevendeg.android.scaladays.ui.commons.{ListLayout, UiServices, LineItemDecorator}
import macroid.{Ui, AppContext, Contexts}
import scala.concurrent.ExecutionContext.Implicits.global
import macroid.FullDsl._
import com.fortysevendeg.macroid.extras.ResourcesExtras._
import com.fortysevendeg.macroid.extras.RecyclerViewTweaks._

class SpeakersFragment
  extends Fragment
  with Contexts[Fragment]
  with ComponentRegistryImpl
  with UiServices
  with ListLayout {

  override implicit lazy val appContextProvider: AppContext = fragmentAppContext

  override def onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View = {
    analyticsServices.sendScreenName(analyticsSpeakersScreen)
    content
  }

  override def onViewCreated(view: View, savedInstanceState: Bundle): Unit = {
    super.onViewCreated(view, savedInstanceState)
    runUi(
      (recyclerView
        <~ rvLayoutManager(new LinearLayoutManager(appContextProvider.get))
        <~ rvAddItemDecoration(new LineItemDecorator())) ~
        (reloadButton <~ On.click(Ui {
          loadSpeakers(forceDownload = true)
        })))
    loadSpeakers()
  }

  def loadSpeakers(forceDownload: Boolean = false): Unit = {
    loading()
    val result = for {
      conference <- loadSelectedConference(forceDownload)
    } yield reloadList(conference.speakers)

    result recover {
      case _ => failed()
    }
  }

  def reloadList(speakers: Seq[Speaker]) = {
    speakers.length match {
      case 0 => empty()
      case _ =>
        val speakersAdapter = new SpeakersAdapter(speakers, new RecyclerClickListener {
          override def onClick(speaker: Speaker): Unit = {
            speaker.twitter map {
              twitterName =>
                val twitterUser = if (twitterName.startsWith("@")) twitterName.substring(1) else twitterName
                analyticsServices.sendEvent(
                  screenName = Some(analyticsSpeakersScreen),
                  category = analyticsCategoryNavigate,
                  action = analyticsSpeakersActionGoToUser)
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(resGetString(R.string.url_twitter_user, twitterUser))))
            }
          }
        })
        adapter(speakersAdapter)
    }
  }

}
