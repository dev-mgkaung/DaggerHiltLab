package mk.learner.daggerhiltlab.datas

import kotlinx.coroutines.*
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
    private val moviesRemoteDataSource: MovieDataSource,
    private val moviesLocalDataSource: MovieDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {


    override suspend fun observeMovies() = moviesRemoteDataSource.observeMovies()


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


