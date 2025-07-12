package com.khaled.thmanyah.data.remote.dto

import com.khaled.thmanyah.data.remote.deserializer.ContentDeserializerFactory
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object SectionSerializer : KSerializer<Section> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Section") {
        element("name", PrimitiveSerialDescriptor("name", PrimitiveKind.STRING))
        element("type", PrimitiveSerialDescriptor("type", PrimitiveKind.STRING))
        element("content_type", PrimitiveSerialDescriptor("content_type", PrimitiveKind.STRING))
        element("order", PrimitiveSerialDescriptor("order", PrimitiveKind.INT))
        element("content", ListSerializer(JsonElement.serializer()).descriptor, isOptional = true)
    }

    override fun deserialize(decoder: Decoder): Section {
        val input = decoder as? JsonDecoder ?: error("Expected JsonDecoder")
        val jsonObject = input.decodeJsonElement().jsonObject

        val name = jsonObject["name"]?.jsonPrimitive?.content ?: ""
        val type = jsonObject["type"]?.jsonPrimitive?.content ?: ""
        val contentType = jsonObject["content_type"]?.jsonPrimitive?.content ?: ""
        val order = jsonObject["order"]?.jsonPrimitive?.content ?: "0"
        val contentJson = jsonObject["content"]?.jsonArray ?: JsonArray(emptyList())

        val content = ContentDeserializerFactory.deserializeContent(contentType, contentJson, input)

        return Section(name, type, contentType, order, content)
    }

    override fun serialize(encoder: Encoder, value: Section) {
        // Optional: implement if needed
        throw UnsupportedOperationException("Serialization not implemented")
    }
}
