package com.khaled.thmanyah.data.remote.deserializer

import com.khaled.thmanyah.data.remote.dto.ContentItem
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

interface ContentDeserializer <T : ContentItem> {
    fun deserialize(json: JsonElement, jsonDecoder: JsonDecoder): T
}
