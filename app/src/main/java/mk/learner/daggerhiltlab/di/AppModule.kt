package mk.learner.daggerhiltlab.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import mk.learner.daggerhiltlab.BuildConfig
import mk.learner.daggerhiltlab.datas.DefaultMovieRepository
import mk.learner.daggerhiltlab.datas.MovieDataSource
import mk.learner.daggerhiltlab.datas.MovieRepository
import mk.learner.daggerhiltlab.datas.source.local.MovieDatabase
import mk.learner.daggerhiltlab.datas.source.local.MovieLocalDataSource
import mk.learner.daggerhiltlab.datas.source.remote.MovieApi
import mk.learner.daggerhiltlab.datas.source.remote.MovieApiHelper
import mk.learner.daggerhiltlab.datas.source.remote.MovieApiHelperImpl
import mk.learner.daggerhiltlab.datas.source.remote.MovieRemoteDataSource
import mk.learner.daggerhiltlab.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteMoviesDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalMoviesDataSource

    @Singleton
    @RemoteMoviesDataSource
    @Provides
    fun provideMoviesRemoteDataSource(movieApiHelperImpl: MovieApiHelperImpl): MovieDataSource {
        return MovieRemoteDataSource(movieApiHelperImpl)
    }

    @Singleton
    @LocalMoviesDataSource
    @Provides
    fun provideMoviesLocalDataSource(
        database: MovieDatabase,
        ioDispatcher: CoroutineDispatcher
    ): MovieDataSource {
        return MovieLocalDataSource(
            database.movieDao(), ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "Movies.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)


    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: MovieApiHelperImpl): MovieApiHelper = apiHelper
}


@Module
@InstallIn(ApplicationComponent::class)
object MoviesRepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        @AppModule.RemoteMoviesDataSource remoteMoviesDataSource: MovieDataSource,
        @AppModule.LocalMoviesDataSource localMoviesDataSource: MovieDataSource,
        ioDispatcher: CoroutineDispatcher
    ): MovieRepository {
        return DefaultMovieRepository(
            remoteMoviesDataSource, localMoviesDataSource, ioDispatcher
        )
    }
}