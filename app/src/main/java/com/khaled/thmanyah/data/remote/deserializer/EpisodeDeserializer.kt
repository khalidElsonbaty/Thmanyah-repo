package com.khaled.thmanyah.data.remote.deserializer

import com.khaled.thmanyah.data.remote.dto.Episode
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

object EpisodeDeserializer : ContentDeserializer<Episode> {
    override fun deserialize(json: JsonElement, jsonDecoder: JsonDecoder): Episode {
        return jsonDecoder.json.decodeFromJsonElement(Episode.serializer(), json)
    }
}