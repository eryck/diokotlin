package com.br.natanfc.filmesflix.api

import com.br.natanfc.filmesflix.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    //https://raw.githubusercontent.com/natanfelipe/FilmesFlixJson/master/moviesList

    @GET("natanfelipe/FilmesFlixJson/master/moviesList")
    fun getAllMovies():Call<List<Movie>>
}