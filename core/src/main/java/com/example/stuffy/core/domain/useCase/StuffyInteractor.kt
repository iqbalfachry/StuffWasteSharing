package com.example.stuffy.core.domain.useCase

import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.domain.repository.StuffyRepository

class StuffyInteractor(private val movieRepository: StuffyRepository) :StuffyUseCase {
    override fun getListMovie() = movieRepository.getListMovie()

    override fun getFavMovie() = movieRepository.getFavoriteMovie()

    override fun setFavMovie(movie: Product, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}