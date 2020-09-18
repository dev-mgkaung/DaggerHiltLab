package mk.learner.daggerhiltlab.datas.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movie")

data class MovieVO(

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val original_title: String,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val release_date: String,


)