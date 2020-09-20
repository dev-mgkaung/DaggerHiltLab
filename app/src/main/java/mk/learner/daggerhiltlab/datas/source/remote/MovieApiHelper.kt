package mk.learner.daggerhiltlab.datas.source.remote

import mk.learner.daggerhiltlab.datas.responses.MovieListResponse
import retrofit2.Response

interface MovieApiHelper {
    suspend fun getMovies(): Response<MovieListResponse>
}