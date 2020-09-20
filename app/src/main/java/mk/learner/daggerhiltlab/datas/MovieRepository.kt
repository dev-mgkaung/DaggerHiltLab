package mk.learner.daggerhiltlab.datas

import androidx.lifecycle.LiveData
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.datas.responses.MovieListResponse
import mk.learner.daggerhiltlab.utils.Results
import retrofit2.Response


interface MovieRepository
{
    suspend  fun observeMoviesFromNewtwork(): Response<MovieListResponse>

    suspend fun observeMoviesFromDB() : Results<List<MovieVO>>

    suspend fun refreshMovies()

    suspend fun saveMovies(movie: MovieVO)

    suspend fun deleteAllMovies()

}