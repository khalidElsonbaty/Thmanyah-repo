package com.khaled.thmanyah.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = SectionSerializer::class)
data class Section(
    val name: String,
    val type: String,
    @SerialName("content_type") val contentType: String,
    val order: String,
    val content: List<ContentItem>
)