package com.example.bookshelf.fake

import com.example.bookshelf.model.BookResponse
import com.example.bookshelf.network.BooksApiService

class FakeBooksApiService : BooksApiService {
    override suspend fun getBooks(query: String): BookResponse {
        return FakeDataSource.bookResponse
    }
}
