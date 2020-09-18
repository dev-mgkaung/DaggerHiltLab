package mk.learner.daggerhiltlab.datas.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mk.learner.daggerhiltlab.datas.MovieDataSource
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.utils.Resource

class MovieLocalDataSource internal constructor(
    private val movieDao: MovieDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieDataSource {

    override fun observeMovies(): LiveData<Resource<List<MovieVO>>> {
        return movieDao.observeMovies().map {
            Resource.success(it)
        }
    }

    override suspend fun getMovies(): Resource<List<MovieVO>> = withContext(ioDispatcher)
    {
        return@withContext try {
            Resource.success(movieDao.getMoviesList())
        } catch (e: Exception) {
            Resource.error(e.message.toString(),null)
        }
    }

    override suspend fun refreshMovies() {
        //None
    }

    override suspend fun saveMovie(movieVO: MovieVO) = withContext(ioDispatcher) {
        movieDao.insertMovie(movieVO)
    }

    override suspend fun deleteAllMovies() = withContext(ioDispatcher) {
        movieDao.deleteMovieDatas()
    }
}