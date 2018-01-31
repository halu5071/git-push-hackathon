package io.moatwel.github.domain.usecase

import io.moatwel.github.domain.entity.event.Event
import io.moatwel.github.domain.repository.EventRepository
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class EventUseCase @Inject constructor(
  private val eventRepository: EventRepository
) {

  fun getList(name: String, page: Int): Observable<Response<List<Event>>> {
    return eventRepository.eventList(name, page)
  }
}