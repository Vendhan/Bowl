package data.mapper

interface Mapper<E, D, DM> {
    fun mapFromEntityToDomainModel(type: E): DM

    fun mapToDataEntity(type: D): E
}
