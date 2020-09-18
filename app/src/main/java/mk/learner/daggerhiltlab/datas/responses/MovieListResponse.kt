package mk.learner.daggerhiltlab.datas.responses

import com.google.gson.annotations.SerializedName
import mk.learner.daggerhiltlab.datas.entities.MovieVO

data class MovieListResponse(
    @SerializedName("results")  val results: ArrayList<MovieVO?>? = null
)