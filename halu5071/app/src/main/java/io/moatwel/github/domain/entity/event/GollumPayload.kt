package io.moatwel.github.domain.entity.event

import io.moatwel.github.domain.entity.Repository
import java.io.Serializable

data class GollumPayload(
  val repository: Repository
) : Payload(), Serializable