package io.moatwel.github.data.repository

import io.moatwel.github.data.datasource.CloudEventDataSource
import io.moatwel.github.domain.entity.event.Event
import io.moatwel.github.domain.repository.EventRepository
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class EventDataRepository @Inject constructor(
  private val cloudEventDataSource: CloudEventDataSource
): EventRepository {

  override fun eventList(page: Int): Observable<Response<Event>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}