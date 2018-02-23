package io.moatwel.github.domain.entity.event

import io.moatwel.github.domain.entity.Comment
import io.moatwel.github.domain.entity.Issue
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

@JsonSerializable
data class IssueCommentPayload(
  val action: String,

  val issue: Issue,

  val comment: Comment
) : Payload(), Serializable