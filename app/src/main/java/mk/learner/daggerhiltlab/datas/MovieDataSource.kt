package mk.learner.daggerhiltlab.datas

import androidx.lifecycle.LiveData
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.datas.responses.MovieListResponse
import mk.learner.daggerhiltlab.utils.Results
import retrofit2.Response

interface MovieDataSource {

     suspend  fun observeMovies(): Response<MovieListResponse>

     suspend fun getMovies(): Results<List<MovieVO>>

     suspend fun refreshMovies()

     suspend fun saveMovie(movieVO: MovieVO)

     suspend fun deleteAllMovies()

}