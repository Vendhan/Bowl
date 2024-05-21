package presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import domain.model.BowlDomainModel
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalResourceApi::class)
@Composable
fun FoodListItem(
    foodItem: BowlDomainModel,
    showFavorite: Boolean,
    navigateToDetailScreen: (foodID: Int) -> Unit,
) {
    Card(
        onClick = {
            navigateToDetailScreen(foodItem.id)
        },
        modifier =
            Modifier.fillMaxWidth()
                .wrapContentHeight(),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(8.dp),
        ) {
            AsyncImage(
                modifier =
                    Modifier
                        .size(100.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                model = foodItem.imageUrl,
                contentDescription = "Food item image",
                contentScale = ContentScale.FillBounds,
//                error = Image(painter = painterResource(res = "food_placeholder.webp")),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = foodItem.name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = foodItem.description,
                    style = MaterialTheme.typography.body2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = foodItem.origin,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray,
                )
            }
        }
    }
}
