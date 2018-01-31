package io.moatwel.github.data.datasource

import io.moatwel.github.data.network.EventApi
import javax.inject.Inject

class CloudEventDataSource @Inject constructor(
  private val api: EventApi
) {

}