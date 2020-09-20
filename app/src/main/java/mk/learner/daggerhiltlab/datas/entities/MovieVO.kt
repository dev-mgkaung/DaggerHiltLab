package mk.learner.daggerhiltlab.datas.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movie")
data class MovieVO(

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("original_title")
    val original_title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val release_date: String,

)