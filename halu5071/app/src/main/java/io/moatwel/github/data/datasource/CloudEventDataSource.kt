package io.moatwel.github.data.datasource

import io.moatwel.github.data.network.EventApi
import io.moatwel.github.domain.entity.event.Event
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class CloudEventDataSource @Inject constructor(
  private val api: EventApi
) {

  fun get(name: String, page: Int): Observable<Response<List<Event>>> {
    return api.getEventList(name, page)
  }
}