package com.example.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenLibraryResponse(
    @SerialName("numFound")
    val numFound: Int = 0,
    @SerialName("docs")
    val docs: List<OpenLibraryDoc>? = null
)

@Serializable
data class OpenLibraryDoc(
    @SerialName("key")
    val key: String,
    @SerialName("title")
    val title: String,
    @SerialName("author_name")
    val authorName: List<String>? = null,
    @SerialName("cover_i")
    val coverI: Long? = null,
    @SerialName("first_publish_year")
    val firstPublishYear: Int? = null
) {
    fun toBookItem(): BookItem {
        val coverUrl = if (coverI != null && coverI > 0) {
            "https://covers.openlibrary.org/b/id/$coverI-L.jpg"
        } else {
            null
        }
        val authorsList = authorName ?: listOf("Autor desconocido")
        val pubYear = if (firstPublishYear != null) " (Publicado en $firstPublishYear)" else ""

        return BookItem(
            id = key.replace("/", "_"),
            volumeInfo = VolumeInfo(
                title = title,
                authors = authorsList,
                description = "Obra catalogada en la biblioteca mundial Open Library$pubYear.",
                imageLinks = ImageLinks(
                    smallThumbnail = coverUrl,
                    thumbnail = coverUrl
                )
            )
        )
    }
}
