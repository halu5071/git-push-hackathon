package io.moatwel.github.domain.repository

import io.moatwel.github.domain.entity.event.Event
import io.reactivex.Observable
import retrofit2.Response

interface EventRepository {

  fun eventList(page: Int = 1): Observable<Response<Event>>
}