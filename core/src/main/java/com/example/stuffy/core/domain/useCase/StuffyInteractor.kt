package com.example.stuffy.core.domain.useCase

import com.example.stuffy.core.data.Resource
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.domain.repository.StuffyRepository
import kotlinx.coroutines.flow.Flow

class StuffyInteractor(private val movieRepository: StuffyRepository) :StuffyUseCase {
    override fun getListMovie() = movieRepository.getListMovie()
    override fun getCategory()=
        movieRepository.getCategory()


    override fun getFavMovie() = movieRepository.getFavoriteMovie()

    override fun setFavMovie(movie: Product, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}