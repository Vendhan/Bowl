package domain.model

data class BowlDomainModel(
    val id: Int,
    val name: String,
    val origin: String,
    val imageUrl: String,
    val description: String,
    val history: String,
    val cookingInstructions: List<String>,
    val isFavorite: Boolean = false,
)
