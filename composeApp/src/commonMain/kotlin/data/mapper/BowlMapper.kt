package data.mapper

import data.database.entity.BowlEntity
import data.service.model.BowlResponseModel
import domain.model.BowlDomainModel

class BowlMapper : Mapper<BowlEntity, BowlResponseModel, BowlDomainModel> {
    override fun mapToDataEntity(type: BowlResponseModel): BowlEntity {
        return BowlEntity(
            id = type.id,
            name = type.name,
            description = type.description,
            imageUrl = type.imageUrl,
            history = type.history,
            cookingInstructions = type.cookingInstructions,
            origin = type.origin,
        )
    }

    override fun mapFromEntityToDomainModel(type: BowlEntity): BowlDomainModel {
        return BowlDomainModel(
            id = type.id,
            name = type.name,
            description = type.description,
            imageUrl = type.imageUrl,
            history = type.history,
            cookingInstructions = type.cookingInstructions,
            origin = type.origin,
            isFavorite = type.isFavorite,
        )
    }
}
