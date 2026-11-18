package com.example.bookshelf.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.ui.BooksUiState

@Composable
fun HomeScreen(
    booksUiState: BooksUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedBook by remember { mutableStateOf<BookItem?>(null) }

    when (booksUiState) {
        is BooksUiState.Loading -> LoadingScreen(modifier = modifier)
        is BooksUiState.Success -> {
            BooksGridScreen(
                books = booksUiState.books,
                onBookClick = { book -> selectedBook = book },
                modifier = modifier
            )

            selectedBook?.let { book ->
                BookDetailDialog(
                    book = book,
                    onDismiss = { selectedBook = null }
                )
            }
        }
        is BooksUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier)
    }
}
