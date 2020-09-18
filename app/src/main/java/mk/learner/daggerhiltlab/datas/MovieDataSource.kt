package mk.learner.daggerhiltlab.datas

import androidx.lifecycle.LiveData
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.utils.Resource

interface MovieDataSource {

    fun observeMovies(): LiveData<Resource<List<MovieVO>>>

  //  fun observeMovieById(movie_id: Int): LiveData<Results<MovieVO>>

    suspend fun getMovies(): Resource<List<MovieVO>>

    suspend fun refreshMovies()

    suspend fun saveMovie(movieVO: MovieVO)

    suspend fun deleteAllMovies()


}