package mk.learner.daggerhiltlab.datas.source.remote

import mk.learner.daggerhiltlab.datas.MovieDataSource
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.datas.responses.MovieListResponse
import mk.learner.daggerhiltlab.utils.Results
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApiHelperImpl: MovieApiHelperImpl
):  MovieDataSource
{
    override suspend fun observeMovies(): Response<MovieListResponse> = movieApiHelperImpl.getMovies()


    override suspend fun getMovies(): Results<List<MovieVO>> {
        TODO("Not yet implemented")
        }

    override suspend fun refreshMovies() {}

    override suspend fun saveMovieList(movieVO: List<MovieVO>) {}

    override suspend fun deleteAllMovies() {}


}