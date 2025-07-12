package com.khaled.thmanyah.data.remote.deserializer

import com.khaled.thmanyah.data.remote.dto.AudioBook
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

object AudioBookDeserializer : ContentDeserializer<AudioBook> {
    override fun deserialize(json: JsonElement, jsonDecoder: JsonDecoder): AudioBook {
        return jsonDecoder.json.decodeFromJsonElement(AudioBook.serializer(), json)
    }
}