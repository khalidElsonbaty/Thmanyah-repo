package com.khaled.thmanyah.data.remote.dto

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
sealed class ContentItem {
        abstract val id: String
        abstract val score: String
        abstract val name: String
        abstract val avatarUrl: String?
        abstract val description: String?
}