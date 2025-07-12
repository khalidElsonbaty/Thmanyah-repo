package com.khaled.thmanyah.data.remote.deserializer

import com.khaled.thmanyah.data.remote.dto.ContentItem
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder

object ContentDeserializerFactory {
    private val deserializers: Map<String, ContentDeserializer<out ContentItem>> = mapOf(
        "episode" to EpisodeDeserializer,
        "audio_book" to AudioBookDeserializer,
        "audio_article" to AudioArticleDeserializer,
        "podcast" to PodcastDeserializer
    )

    fun getDeserializer(type: String): ContentDeserializer<out ContentItem> {
        return deserializers[type] ?: PodcastDeserializer
    }

    fun deserializeContent(
        type: String,
        contentJson: JsonArray,
        jsonDecoder: JsonDecoder
    ): List<ContentItem> {
        val deserializer = getDeserializer(type)
        return contentJson.map { deserializer.deserialize(it, jsonDecoder) as ContentItem }
    }
}
