package com.example.stuffy.core.utils

import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.remote.response.CategoryResponse
import com.example.stuffy.core.data.remote.response.ConfirmationResponse
import com.example.stuffy.core.data.remote.response.ProductResponse
import com.example.stuffy.core.data.remote.response.TransactionResponse
import com.example.stuffy.core.domain.model.*

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
    fun mapTransactionResponseToDomain(
        input: TransactionResponse?,

        ): ConfirmationTransaction {
        return ConfirmationTransaction(
                id = input?.id ?: "Unknown",
                image = input?.product?.avatar ?: "Unknown",
                name = input?.product?.name ?: "Unknown",
                size = input?.confirmation?.size.toString(),
                status = input?.status?: "Unknown",
                confirmation = input?.confirmation?.map {confirmation->
                    ConfirmationTaker(
                        id = confirmation.taker.id,
                        image = confirmation.taker.avatar,
                        name = confirmation.taker.name,
                        note = confirmation.note,
                        status=confirmation.status,
                        email=confirmation.taker.email,
                        confirmationId = confirmation.id
                    )
                }
            )
        }


    fun mapTransactionResponseToDomainShare(
        input: TransactionResponse?,

        ): Share {
        return Share(
            id = input?.id ?: "Unknown",
            image = input?.product?.avatar ?: "Unknown",
            name = input?.product?.name ?: "Unknown",
            status = input?.status?: "Unknown",
          location = input?.product?.location ?: "Unknown",
            taker = input?.confirmation?.map { confirmation->
                ConfirmationTaker(
                    id = confirmation.taker.id,
                    image = confirmation.taker.avatar,
                    name = confirmation.taker.name,
                    note = confirmation.note,
                    status=confirmation.status,
                    email=confirmation.taker.email,
                    confirmationId = confirmation.id
                )
            },
        )
            }

    fun mapTransactionResponseToDomainTake(
        input: TransactionResponse?,

        ): Take {
        return Take(
            id = input?.id ?: "Unknown",
            image = input?.product?.avatar ?: "Unknown",
            name = input?.product?.name ?: "Unknown",
            status = input?.confirmation?.map {confirmation->
                ConfirmationTaker(
                    id = confirmation.taker.id,
                    image = confirmation.taker.avatar,
                    name = confirmation.taker.name,
                    note = confirmation.note,
                    status=confirmation.status,
                email=confirmation.taker.email,
                    confirmationId = confirmation.id
                )
            },
            location = input?.product?.location ?: "Unknown",

        )
    }



    fun mapConfirmationResponseToDomain(
        input: ConfirmationResponse?,

        ): ConfirmationTaker? {
        return input?.taker?.id?.let {
            ConfirmationTaker(
                id = it,
                image = input.taker.avatar,
                name = input.taker.name,
                note = input.note,
                status = input.status,
                email = input.taker.email,
            confirmationId = input.id
            )
        }
    }




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

    fun mapProductResponseToEntity(input: ProductResponse)=

            ProductEntity (
            id =input.id,
                name=input.name,
                avatar= input.avatar,
                location=input.location,
                description = input.description,
            )


    fun mapListEntityToDomain(input: List<ProductEntity>):List<Product> =
        input.map{
    mapEntitytoDomain(it)
    }

    private fun mapEntitytoDomain(input: ProductEntity) =Product(
        id =input.id,
        name=input.name,
        avatar= input.avatar,
        location=input.location,
        description = input.description,
        isFav = false
    )


}