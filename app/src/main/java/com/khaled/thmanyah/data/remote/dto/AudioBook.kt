package com.khaled.thmanyah.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("audio_book")
data class AudioBook(
    @SerialName("audiobook_id") val audiobookId: String,
    override val name: String,
    @SerialName("author_name") val authorName: String,
    override val description: String,
    @SerialName("avatar_url") override val avatarUrl: String,
    val duration: String,
    val language: String,
    @SerialName("release_date") val releaseDate: String,
    override val score: String
) : ContentItem() {
    override val id = audiobookId
}
