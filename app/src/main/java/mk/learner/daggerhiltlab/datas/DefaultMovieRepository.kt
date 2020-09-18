package mk.learner.daggerhiltlab.datas

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.utils.Resource

class DefaultMovieRepository(
    private val moviesRemoteDataSource: MovieDataSource,
    private val moviesLocalDataSource: MovieDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {
    override fun observeMovies(): LiveData<Resource<List<MovieVO>>> {
        return moviesLocalDataSource.observeMovies()
    }
    override suspend fun refreshMovies() {
        updateMoviesFromRemoteDataSource()
    }
    private suspend fun updateMoviesFromRemoteDataSource() {
        val remoteMovies = moviesRemoteDataSource.getMovies()

//        if (remoteMovies is Resource.success) {
//
//            moviesLocalDataSource.deleteAllMovies()
//
//            remoteMovies.data.forEach { movie ->
//                moviesLocalDataSource.saveMovie(movie)
//            }
//
//        } else if (remoteMovies is Resource.e) {
//            throw remoteMovies.exception
//        }
    }

    override suspend fun saveMovies(movie: MovieVO) {
        coroutineScope {
            launch { moviesRemoteDataSource.saveMovie(movie) }
            launch { moviesLocalDataSource.saveMovie(movie) }
        }
    }

    override suspend fun deleteAllMovies() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch { moviesRemoteDataSource.deleteAllMovies() }
                launch { moviesLocalDataSource.deleteAllMovies() }
            }
        }
    }
}


