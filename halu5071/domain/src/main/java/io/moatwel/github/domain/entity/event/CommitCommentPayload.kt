package io.moatwel.github.domain.entity.event

import io.moatwel.github.domain.entity.Comment
import io.moatwel.github.domain.entity.Repository
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class CommitCommentPayload(
  val comment: Comment
) : Payload(), Serializable