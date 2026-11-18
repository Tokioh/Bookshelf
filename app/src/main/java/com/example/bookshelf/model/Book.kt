package com.example.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    @SerialName("totalItems")
    val totalItems: Int = 0,
    @SerialName("items")
    val items: List<BookItem>? = null
)

@Serializable
data class BookItem(
    @SerialName("id")
    val id: String,
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    @SerialName("title")
    val title: String,
    @SerialName("authors")
    val authors: List<String>? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("imageLinks")
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    @SerialName("smallThumbnail")
    val smallThumbnail: String? = null,
    @SerialName("thumbnail")
    val thumbnail: String? = null
) {
    val httpsThumbnail: String?
        get() = (thumbnail ?: smallThumbnail)?.replace("http://", "https://")
}
