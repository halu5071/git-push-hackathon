/*
 *  GitHub-Client
 *
 *  EventDataSourceFactory.kt
 *
 *  Copyright 2018 moatwel.io
 *  author : halu5071 (Yasunori Horii)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package io.moatwel.github.data.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.moatwel.github.data.network.retrofit.EventApi
import io.moatwel.github.domain.entity.event.Event
import io.moatwel.github.domain.repository.UserRepository

class EventDataSourceFactory (
  private val api: EventApi,
  private val userRepository: UserRepository
) : DataSource.Factory<Int, Event> {

  val sourceLiveData = MutableLiveData<CloudEventDataSource>()

  override fun create(): DataSource<Int, Event> {
    val dataSource = CloudEventDataSource(api, userRepository)
    sourceLiveData.postValue(dataSource)
    return dataSource
  }
}