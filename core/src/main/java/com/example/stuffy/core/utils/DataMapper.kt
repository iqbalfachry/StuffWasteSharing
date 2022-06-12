package com.example.stuffy.core.utils

import com.example.stuffy.core.data.local.entity.ConfirmationEntity
import com.example.stuffy.core.data.local.entity.ProductEntity
import com.example.stuffy.core.data.local.entity.TransactionEntity
import com.example.stuffy.core.data.local.entity.UserEntity
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


    fun mapListProductEntityToDomain(input: List<ProductEntity>):List<Product> =
        input.map{
    mapProductEntitytoDomain(it)
    }

    private fun mapProductEntitytoDomain(input: ProductEntity) =Product(
        id =input.id,
        name=input.name,
        avatar= input.avatar,
        location=input.location,
        description = input.description,
        isFav = false
    )

    fun mapListTransactionEntityToDomain(input: List<TransactionEntity>):List<ConfirmationTransaction> =
        input.map{
            mapTransactionEntitytoDomain(it)
        }
    fun mapListShareEntityToDomain(input: List<TransactionEntity>):List<Share> =
        input.map{
            mapShareEntitytoDomain(it)
        }
    fun mapListTakeEntityToDomain(input: List<TransactionEntity>):List<Take> =
        input.map{
            mapTakeEntitytoDomain(it)
        }
    private fun mapTakeEntitytoDomain(input: TransactionEntity) =Take(
          id = input.id,
            image = input.product.avatar ,
            name = input.product.name,
            status = input.confirmation.map {confirmation->
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
            location = input.product.location ,


    )
    private fun mapShareEntitytoDomain(input: TransactionEntity) =Share(
        id =input.id,
        name=input.product.name,
        image=input.product.avatar,
       location = input.product.location,
        status=input.status,
        taker = input.confirmation.map { confirmation->
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
    private fun mapTransactionEntitytoDomain(input: TransactionEntity) =ConfirmationTransaction(
        id =input.id,
        name=input.product.name,
         image=input.product.avatar,
size=input.confirmation.size.toString(),
status=input.status,
    confirmation= input.confirmation.map {confirmation->
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

    fun mapTransactionResponseToEntity(input: TransactionResponse)=
        TransactionEntity (
            id =input.id,
            product = ProductEntity(
                id =input.product.id,
                name = input.product.name,
                avatar= input.product.avatar,
            location=input.product.location,
 description= input.product.description,

     isFav= false

            ),

       sharer = UserEntity(
           id= input.sharer.id,
           email = input.sharer.email,
           avatar = input.sharer.avatar,
           name = input.sharer.name
       ),
         confirmation =input.confirmation.map {
             ConfirmationEntity(
                 id =it.id,
             note =it.note,
                 status = it.status,
                 taker =  UserEntity(
                     id= input.sharer.id,
                     email = input.sharer.email,
                     avatar = input.sharer.avatar,
                     name = input.sharer.name
                 ),
             )

         },
         status =input.status,
        )



}