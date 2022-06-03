package com.example.stuffy.core.domain.useCase

import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.domain.repository.StuffyRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StuffyInteractor(private val movieRepository: StuffyRepository) : StuffyUseCase {
    override fun getListMovie() = movieRepository.getListMovie()
    override fun getCategory() =
        movieRepository.getCategory()


    override fun getFavMovie() = movieRepository.getFavoriteMovie()
    override fun createProduct(
        files: MultipartBody.Part,
        description: RequestBody,
        name: RequestBody,
        location: RequestBody
    ) =
        movieRepository.createProduct(files, description, name, location)


    override fun setFavMovie(movie: Product, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}