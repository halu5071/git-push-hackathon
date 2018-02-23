package io.moatwel.github.domain.entity.event

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class CreatePayload(
  @Json(name = "ref_type")
  val refType: String,

  val ref: String?,

  @Json(name = "master_branch")
  val masterBranch: String,

  val description: String?
) : Payload(), Serializable