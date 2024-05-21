package data.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BowlResponseModelWrapper(
    val foodItems: List<BowlResponseModel>,
)

@Serializable
data class BowlResponseModel(
    val id: Int,
    val name: String,
    val origin: String,
    @SerialName("image_url")
    val imageUrl: String,
    val description: String,
    val history: String,
    @SerialName("cooking_instructions")
    val cookingInstructions: List<String>,
)
