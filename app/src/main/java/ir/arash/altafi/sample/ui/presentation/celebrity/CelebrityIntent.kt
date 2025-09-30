package ir.arash.altafi.sample.ui.presentation.celebrity

sealed class CelebrityIntent {
    data class FetchCelebrities(val pageNumber: Int, val pageSize: Int) : CelebrityIntent()
}