package com.example.stuffy.core.utils

import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.domain.model.Product

object DataMapper {
    fun mapResponseToEntities(input: List<ProductResponse>): List<ProductEntity> {
        val movieList = ArrayList<ProductEntity>()
        input.map {
            val movie = ProductEntity(
            id =it.id,
                name = it.name,
                avatar = it.avatar,
                location = it.location,
                description = it.description,
                isFav = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<ProductEntity>): List<Product> =
        input.map {
            Product(
id = it.id,
                name = it.name,
                avatar = it.avatar,
                location = it.location,
                description = it.description,
                isFav = it.isFav
            )
        }

    fun mapDomainToEntity(input: Product) = ProductEntity(
id = input.id,
        name = input.name,
        avatar = input.avatar,
        location = input.location,
        description = input.description,

        isFav = input.isFav
    )
}