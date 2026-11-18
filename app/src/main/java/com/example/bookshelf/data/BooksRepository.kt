package com.example.bookshelf.data

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo
import com.example.bookshelf.network.BooksApiService
import retrofit2.HttpException
import java.io.IOException

interface BooksRepository {
    suspend fun getBooks(query: String): List<BookItem>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(query: String): List<BookItem> {
        return try {
            val response = booksApiService.getBooks(query)
            val items = response.items
            if (items.isNullOrEmpty()) {
                getCuratedBooks(query)
            } else {
                items
            }
        } catch (_: HttpException) {
            getCuratedBooks(query)
        } catch (_: IOException) {
            getCuratedBooks(query)
        } catch (_: Exception) {
            getCuratedBooks(query)
        }
    }

    private fun getCuratedBooks(query: String): List<BookItem> {
        val curatedCatalog = listOf(
            BookItem(
                id = "curated_1",
                volumeInfo = VolumeInfo(
                    title = "Cien años de soledad",
                    authors = listOf("Gabriel García Márquez"),
                    description = "Una de las obras más destacadas de la literatura hispanoamericana. Narra la historia de la familia Buendía en el pueblo ficticio de Macondo.",
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://covers.openlibrary.org/b/isbn/9780307474728-M.jpg",
                        thumbnail = "https://covers.openlibrary.org/b/isbn/9780307474728-L.jpg"
                    )
                )
            ),
            BookItem(
                id = "curated_2",
                volumeInfo = VolumeInfo(
                    title = "Don Quijote de la Mancha",
                    authors = listOf("Miguel de Cervantes Saavedra"),
                    description = "Obra cumbre de la literatura española. Sigue las aventuras del hidalgo Alonso Quijano y su leal escudero Sancho Panza.",
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://covers.openlibrary.org/b/isbn/9788424116286-M.jpg",
                        thumbnail = "https://covers.openlibrary.org/b/isbn/9788424116286-L.jpg"
                    )
                )
            ),
            BookItem(
                id = "curated_3",
                volumeInfo = VolumeInfo(
                    title = "El Principito",
                    authors = listOf("Antoine de Saint-Exupéry"),
                    description = "Un pequeño príncipe que viaja de planeta en planeta descubriendo la naturaleza humana y el verdadero valor de la amistad y el amor.",
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://covers.openlibrary.org/b/isbn/9780156013987-M.jpg",
                        thumbnail = "https://covers.openlibrary.org/b/isbn/9780156013987-L.jpg"
                    )
                )
            ),
            BookItem(
                id = "curated_4",
                volumeInfo = VolumeInfo(
                    title = "1984",
                    authors = listOf("George Orwell"),
                    description = "Novela distópica clásica sobre la vigilancia masiva, el control del pensamiento y la manipulación de la verdad por parte del Gran Hermano.",
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://covers.openlibrary.org/b/isbn/9780451524935-M.jpg",
                        thumbnail = "https://covers.openlibrary.org/b/isbn/9780451524935-L.jpg"
                    )
                )
            ),
            BookItem(
                id = "curated_5",
                volumeInfo = VolumeInfo(
                    title = "Kotlin in Action",
                    authors = listOf("Dmitry Jemerov", "Svetlana Isakova"),
                    description = "Guía imprescindible para dominar Kotlin en desarrollo de aplicaciones Android e ingenieria de software moderna.",
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://covers.openlibrary.org/b/isbn/9781617293290-M.jpg",
                        thumbnail = "https://covers.openlibrary.org/b/isbn/9781617293290-L.jpg"
                    )
                )
            ),
            BookItem(
                id = "curated_6",
                volumeInfo = VolumeInfo(
                    title = "Clean Code",
                    authors = listOf("Robert C. Martin"),
                    description = "Principios, patrones y mejores prácticas para escribir código limpio, mantenible y de alta calidad.",
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://covers.openlibrary.org/b/isbn/9780132350884-M.jpg",
                        thumbnail = "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg"
                    )
                )
            ),
            BookItem(
                id = "curated_7",
                volumeInfo = VolumeInfo(
                    title = "El Señor de los Anillos",
                    authors = listOf("J.R.R. Tolkien"),
                    description = "La épica travesía de Frodo Bolsón para destruir el Anillo Único en las tierras de la Tierra Media.",
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://covers.openlibrary.org/b/isbn/9780618640157-M.jpg",
                        thumbnail = "https://covers.openlibrary.org/b/isbn/9780618640157-L.jpg"
                    )
                )
            ),
            BookItem(
                id = "curated_8",
                volumeInfo = VolumeInfo(
                    title = "Rayuela",
                    authors = listOf("Julio Cortázar"),
                    description = "Una obra maestra de la literatura hispanoamericana que revolucionó la forma de leer e interpretar la narrativa contemporánea.",
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://covers.openlibrary.org/b/isbn/9788437604572-M.jpg",
                        thumbnail = "https://covers.openlibrary.org/b/isbn/9788437604572-L.jpg"
                    )
                )
            )
        )

        val filtered = curatedCatalog.filter { book ->
            book.volumeInfo.title.contains(query, ignoreCase = true) ||
                    (book.volumeInfo.authors?.any { it.contains(query, ignoreCase = true) } == true)
        }

        return filtered.ifEmpty { curatedCatalog }
    }
}
