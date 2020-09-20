package mk.learner.daggerhiltlab.datas

import kotlinx.coroutines.*
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
    private val moviesRemoteDataSource: MovieDataSource,
    private val moviesLocalDataSource: MovieDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    init { }

    override suspend fun observeMoviesFromNewtwork() = moviesRemoteDataSource.observeMovies()

    override suspend fun observeMoviesFromDB() = moviesLocalDataSource.getMovies()

    override suspend fun refreshMovies() {
        updateMoviesFromRemoteDataSource()
    }

    private suspend fun updateMoviesFromRemoteDataSource() {
        try {
            val remoteMovies = moviesRemoteDataSource.observeMovies()
            if (remoteMovies.isSuccessful) {
                remoteMovies?.body()?.results?.let { moviesLocalDataSource.saveMovieList(it) }
            }
        }catch (e : Exception)
        {}
    }

    override suspend fun saveMovies(movie: MovieVO) {}

    override suspend fun deleteAllMovies() {}
}


