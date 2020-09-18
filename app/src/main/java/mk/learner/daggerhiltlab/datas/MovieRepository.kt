package mk.learner.daggerhiltlab.datas

import androidx.lifecycle.LiveData
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.datas.responses.MovieListResponse
import mk.learner.daggerhiltlab.utils.Resource


interface MovieRepository
{
    fun observeMovies(): LiveData<Resource<List<MovieVO>>>

    suspend fun refreshMovies()

    suspend fun saveMovies(movie: MovieVO)

    suspend fun deleteAllMovies()

//    fun observeMovieById(movie_id: Int): LiveData<Results<MovieVO>>
}