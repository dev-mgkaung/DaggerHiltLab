package mk.learner.daggerhiltlab.datas.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mk.learner.daggerhiltlab.datas.entities.MovieVO

@Database(entities = [MovieVO::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}