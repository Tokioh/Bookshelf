package com.example.bookshelf.fake

import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.BookItem

class FakeNetworkBooksRepository : BooksRepository {
    override suspend fun getBooks(query: String): List<BookItem> {
        return FakeDataSource.booksList
    }
}
