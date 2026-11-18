package com.example.bookshelf.fake

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BookResponse
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

object FakeDataSource {
    val bookOne = BookItem(
        id = "1",
        volumeInfo = VolumeInfo(
            title = "Book One",
            authors = listOf("Author One"),
            description = "Description One",
            imageLinks = ImageLinks(
                smallThumbnail = "http://books.google.com/books/content?id=1&zoom=5",
                thumbnail = "http://books.google.com/books/content?id=1&zoom=1"
            )
        )
    )

    val bookTwo = BookItem(
        id = "2",
        volumeInfo = VolumeInfo(
            title = "Book Two",
            authors = listOf("Author Two"),
            description = "Description Two",
            imageLinks = ImageLinks(
                smallThumbnail = "http://books.google.com/books/content?id=2&zoom=5",
                thumbnail = "http://books.google.com/books/content?id=2&zoom=1"
            )
        )
    )

    val booksList = listOf(bookOne, bookTwo)

    val bookResponse = BookResponse(
        totalItems = 2,
        items = booksList
    )
}
