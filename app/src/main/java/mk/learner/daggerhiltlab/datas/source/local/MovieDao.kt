package mk.learner.daggerhiltlab.datas.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import mk.learner.daggerhiltlab.datas.entities.MovieVO

@Dao
interface MovieDao {

    @Query("select * FROM Movie")
    fun observeMovies(): LiveData<List<MovieVO>>

    @Query("select * FROM Movie WHERE  id=:movie_id")
    fun observeMovieById(movie_id: Int): LiveData<MovieVO>

    @Query("SELECT * FROM Movie")
    suspend fun getMoviesList(): List<MovieVO>

    @Query("SELECT * FROM Movie WHERE id = :movie_id")
    suspend fun getMovieById(movie_id: Int): MovieVO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies: List<MovieVO?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: MovieVO)

    @Update
    suspend fun updateMovie(movie: MovieVO): Int

    @Query("UPDATE Movie SET  original_title = :title WHERE id = :movie_id")
    suspend fun updateMovieCompleted(movie_id: Int, title: String)

    @Query("DELETE FROM Movie WHERE id = :movie_id")
    suspend fun deleteMovieById(movie_id: Int): Int

    @Query("DELETE FROM Movie")
    suspend fun deleteMovieDatas()

    @Query("DELETE FROM Movie WHERE id = :movie_id")
    suspend fun deleteCompletedMovie(movie_id: Int): Int
}