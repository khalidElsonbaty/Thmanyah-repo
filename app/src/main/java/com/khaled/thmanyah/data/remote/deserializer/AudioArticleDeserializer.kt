package com.khaled.thmanyah.data.remote.deserializer

import com.khaled.thmanyah.data.remote.dto.AudioArticle
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

object AudioArticleDeserializer : ContentDeserializer<AudioArticle> {
    override fun deserialize(json: JsonElement, jsonDecoder: JsonDecoder): AudioArticle {
        return jsonDecoder.json.decodeFromJsonElement(AudioArticle.serializer(), json)
    }
}