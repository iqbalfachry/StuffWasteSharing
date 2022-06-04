package com.example.stuffy.core.utils

import android.util.Log
import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product

object DataMapper {
    fun mapResponseToEntities(input: List<ProductResponse>): List<ProductEntity> {
        val movieList = ArrayList<ProductEntity>()
        input.map {
            val movie = it.avatar?.let { it1 ->

                ProductEntity(
                    id = it.id,
                    name = it.name,
                    avatar = it1,
                    location = it.location,
                    description = it.description,
                    isFav = false
                )
            }
            if (movie != null) {
                movieList.add(movie)
            }
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

    fun mapDomainToEntity(input: Product) = input.avatar?.let {
        ProductEntity(
        id = input.id,
        name = input.name,
        avatar = it,
        location = input.location,
        description = input.description,

        isFav = input.isFav
    )
    }
    fun mapCategoryResponseToDomain(
        input: CategoryResponse?,

    ) = Filter(
        id = input?.id ?: "Unknown",
     image =input?.image ?: "Unknown",
        filterName = input?.name?:"Unknown",
    )
    fun mapListProductResponseToDomain(
        input:  List<ProductResponse>

        ) : List<Product> = input.map {
        Product(
            id = it.id,
            name = it.name,
            avatar = it.avatar,
            location = it.location,
            description = it.description,

            isFav = false
        )

    }
        fun mapProductResponseToDomain(
            input: ProductResponse,

            ) = Product(
            id = input.id,
            name = input.name,
            avatar = input.avatar,
            location = input.location,
            description = input.description,

            isFav = false
        )

}