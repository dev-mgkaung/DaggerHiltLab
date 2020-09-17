package mk.learner.daggerhiltlab.datas

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popularmovies")

data class PopularMovieVO(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path")  val poster_path: String,
    @SerializedName("release_date")  val release_date: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("vote_count") val vote_count: Int
)