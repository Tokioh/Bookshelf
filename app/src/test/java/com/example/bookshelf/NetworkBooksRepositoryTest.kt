package com.example.bookshelf

import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.fake.FakeBooksApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {

    @Test
    fun networkBooksRepository_getBooks_verifyBooksList() = runTest {
        val repository = NetworkBooksRepository(
            booksApiService = FakeBooksApiService()
        )
        val result = repository.getBooks("jazz history")
        assertEquals(FakeDataSource.booksList, result)
    }

    @Test
    fun testIsbnCovers() = runTest {
        val client = okhttp3.OkHttpClient()
        val isbnMap = listOf(
            "Cien años de soledad" to "https://covers.openlibrary.org/b/isbn/9780307474728-L.jpg",
            "Don Quijote de la Mancha" to "https://covers.openlibrary.org/b/isbn/9788424116286-L.jpg",
            "El Principito" to "https://covers.openlibrary.org/b/isbn/9780156013987-L.jpg",
            "1984" to "https://covers.openlibrary.org/b/isbn/9780451524935-L.jpg",
            "Kotlin in Action" to "https://covers.openlibrary.org/b/isbn/9781617293290-L.jpg",
            "Clean Code" to "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg",
            "El Señor de los Anillos" to "https://covers.openlibrary.org/b/isbn/9780618640157-L.jpg",
            "Rayuela" to "https://covers.openlibrary.org/b/isbn/9788437604572-L.jpg"
        )

        for ((title, url) in isbnMap) {
            val req = okhttp3.Request.Builder().url(url).build()
            val res = client.newCall(req).execute()
            println("ISBN COVER CHECK FOR '$title': code=${res.code}, length=${res.body?.contentLength()}")
            res.close()
        }
    }
}
