package mk.learner.daggerhiltlab.datas.source.remote

import mk.learner.daggerhiltlab.datas.responses.MovieListResponse
import mk.learner.daggerhiltlab.utils.LANGUAGE
import mk.learner.daggerhiltlab.utils.PAGE_ID
import mk.learner.daggerhiltlab.utils.PARAM_API_ACCESS_TOKEN
import retrofit2.Response
import javax.inject.Inject

class MovieApiHelperImpl @Inject constructor(private val apiService: MovieApi) : MovieApiHelper {

    override suspend fun getMovies(): Response<MovieListResponse> =  apiService.getPopularMovies(PARAM_API_ACCESS_TOKEN, LANGUAGE,PAGE_ID)

}