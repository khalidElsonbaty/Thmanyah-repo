package com.khaled.thmanyah.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("episode")
data class Episode(
    @SerialName("episode_id") val episodeId: String,
    override val name: String,
    override val description: String,
    @SerialName("podcast_name") val podcastName: String,
    @SerialName("audio_url") val audioUrl: String,
    val duration: Int,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("avatar_url") override val avatarUrl: String,
    @SerialName("podcast_id") val podcastId: String,
    override val score: String
) : ContentItem() {
    override val id = episodeId
}