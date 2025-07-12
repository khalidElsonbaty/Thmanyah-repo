package com.khaled.thmanyah.data.remote.deserializer

import com.khaled.thmanyah.data.remote.dto.Podcast
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

object PodcastDeserializer : ContentDeserializer<Podcast> {
    override fun deserialize(json: JsonElement, jsonDecoder: JsonDecoder): Podcast {
        return jsonDecoder.json.decodeFromJsonElement(Podcast.serializer(), json)
    }
}