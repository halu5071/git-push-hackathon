/*
 *  GitHub-Client
 *
 *  CloudEventDataSource.kt
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
import android.arch.paging.PageKeyedDataSource
import io.moatwel.github.domain.NetworkState
import io.moatwel.github.data.network.retrofit.EventApi
import io.moatwel.github.domain.entity.event.Event
import io.moatwel.github.domain.repository.UserRepository
import timber.log.Timber

class CloudEventDataSource (
  private val api: EventApi,
  private val userRepository: UserRepository
) : PageKeyedDataSource<Int, Event>(){

  val networkState: MutableLiveData<NetworkState> = MutableLiveData()

  private fun getList(name: String,
                      page: Int,
                      callback: (events: List<Event>, next: Int?) -> Unit) {
    Timber.d("page: $page")
    api.getList(name, page)
      .subscribe({
        var next: Int? = null
        it.headers().get("Link")?.let {
          val regex = Regex("rel=\"next\"")
          if (regex.containsMatchIn(it)) {
            next = page + 1
            Timber.d("Great! This response contains next!! next: $next")
          }
        }
        callback(it.body()!!, next)
        this.networkState.postValue(NetworkState.SUCCESS)
      }, {
        Timber.e(it)
        this.networkState.postValue(NetworkState.FAILED)
      })
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
    // do nothing
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
    getList(userRepository.me()?.login ?: "", params.key) { events, next ->
      Timber.d("next: $next")
      callback.onResult(events, next)
    }
  }

  override fun loadInitial(params: LoadInitialParams<Int>,
                           callback: LoadInitialCallback<Int, Event>) {
    this.networkState.postValue(NetworkState.LOADING)
    getList(userRepository.me()?.login ?: "", 1) { events, next ->
      callback.onResult(events, null, next)
    }
  }
}