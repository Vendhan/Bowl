package domain.util

sealed class DomainResult<out R> {
    data class Success<out T>(val data: T) : DomainResult<T>()

    data class Error(val message: String) : DomainResult<Nothing>()

    data object Loading : DomainResult<Nothing>()
}
