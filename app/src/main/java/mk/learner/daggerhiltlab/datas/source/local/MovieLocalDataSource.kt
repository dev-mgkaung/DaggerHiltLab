package mk.learner.daggerhiltlab.datas.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mk.learner.daggerhiltlab.datas.MovieDataSource
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.datas.responses.MovieListResponse
import mk.learner.daggerhiltlab.utils.Results
import retrofit2.Response

class MovieLocalDataSource internal constructor(
    private val movieDao: MovieDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieDataSource {

    override suspend fun observeMovies(): Response<MovieListResponse> {
        TODO("Not yet implemented")
    }


    override suspend fun getMovies(): Results<List<MovieVO>> = withContext(ioDispatcher)
    {
        return@withContext try {
            Results.success(movieDao.getMoviesList())
        } catch (e: Exception) {
            Results.error(e.message.toString(),null)
        }
    }

    override suspend fun refreshMovies() {
        //None
    }

    override suspend fun saveMovieList(movieVO: List<MovieVO>) = withContext(ioDispatcher) {
        movieDao.insertMovieList(movieVO)
    }

    override suspend fun deleteAllMovies() = withContext(ioDispatcher) {
        movieDao.deleteMovieDatas()
    }
}