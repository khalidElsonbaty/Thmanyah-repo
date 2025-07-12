package com.khaled.thmanyah.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("next_page") val nextPage: String? = null,
    @SerialName("total_pages") val totalPages: Int? = 0
)