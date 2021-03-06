package mk.learner.daggerhiltlab.datas.source.remote

import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.datas.responses.MovieListResponse
import mk.learner.daggerhiltlab.utils.GET_POPULAR
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi  {

    @GET(GET_POPULAR)
    suspend  fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>

}