package com.khaled.thmanyah.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("podcast")
data class Podcast(
    @SerialName("podcast_id") val podcastId: String,
    override val name: String,
    override val description: String,
    @SerialName("avatar_url") override val avatarUrl: String,
    @SerialName("episode_count") val episodeCount: String,
    val duration: String,
    val language: String?="en",
    val priority: String?,
    val popularityScore: String?,
    override val score: String
) : ContentItem() {
    override val id = podcastId
}

