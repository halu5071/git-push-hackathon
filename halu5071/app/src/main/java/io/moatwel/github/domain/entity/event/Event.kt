/*
 *  GitHub-Client
 *
 *  Event.kt
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

package io.moatwel.github.domain.entity.event

import com.squareup.moshi.Json
import io.moatwel.github.domain.entity.Actor
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable
import java.util.*

@JsonSerializable
data class Event(
  val id: Long,

  val type: String,

  val actor: Actor?,

  val repo: EventRepository?,

  val payload: Payload?,

  @Json(name = "created_at")
  val createdAt: Date?,

  @Json(name = "public")
  val isPublic: Boolean?,

  val org: Actor?
) : Serializable