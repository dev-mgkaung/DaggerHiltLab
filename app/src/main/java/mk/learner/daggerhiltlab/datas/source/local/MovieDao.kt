package mk.learner.daggerhiltlab.datas.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import mk.learner.daggerhiltlab.datas.vo.PopularMovieVO

@Dao
interface MovieDao {

    @Query("select * FROM popular_movies")
    fun observePopularMovies(): LiveData<List<PopularMovieVO>>

    @Query("select * FROM popular_movies WHERE  id=:movie_id")
    fun observePopularMovieById(movie_id: Int): LiveData<PopularMovieVO>

    @Query("SELECT * FROM popular_movies")
    suspend fun getPopularMoviesList(): List<PopularMovieVO>

    @Query("SELECT * FROM popular_movies WHERE id = :movie_id")
    suspend fun getPopularMovieById(movie_id: Int): PopularMovieVO?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovieList(popular_movies: List<PopularMovieVO?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovie(popular_movies: PopularMovieVO)

    @Update
    suspend fun updatePopularMovie(movie: PopularMovieVO): Int

    @Query("UPDATE popular_movies SET  original_title = :title WHERE id = :movie_id")
    suspend fun updateMovieCompleted(movie_id: Int, title: String)

    @Query("DELETE FROM popular_movies WHERE id = :movie_id")
    suspend fun deletePopularMovieById(movie_id: Int): Int

    @Query("DELETE FROM popular_movies")
    suspend fun deletePopularMovieDatas()

    @Query("DELETE FROM popular_movies WHERE id = :movie_id")
    suspend fun deleteCompletedMovie(movie_id: Int): Int
}