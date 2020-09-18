package mk.learner.daggerhiltlab.datas.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mk.learner.daggerhiltlab.datas.MovieDataSource
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.utils.Resource
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieApi
):  MovieDataSource
{

    private var MOVIE_SERVICE_DATA = LinkedHashMap<String, MovieVO>(0)

    init{}

    private val observableMovies = MutableLiveData<Resource<List<MovieVO>>>()

    override suspend fun refreshMovies() {
        observableMovies.value = getMovies()
    }

    override suspend fun saveMovie(movieVO: MovieVO) {
        MOVIE_SERVICE_DATA[movieVO.id.toString()] = movieVO
    }

    override suspend fun deleteAllMovies() {
      MOVIE_SERVICE_DATA.clear()
    }

    override fun observeMovies(): LiveData<Resource<List<MovieVO>>> {
        return observableMovies
    }

       override suspend fun getMovies(): Resource<List<MovieVO>> {
        // val moviesList=   movieService.getPopularMovies(PARAM_API_ACCESS_TOKEN, LANGUAGE,PAGE_ID)
        val moviesList = observableMovies.value?.data
         return Resource.success(moviesList)
    }

}