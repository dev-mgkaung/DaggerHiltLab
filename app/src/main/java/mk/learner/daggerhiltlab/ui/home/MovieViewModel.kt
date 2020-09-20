package mk.learner.daggerhiltlab.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import mk.learner.daggerhiltlab.datas.MovieRepository
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.utils.Results

class MovieViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val moviesLiveData : MutableLiveData<Results<List<MovieVO>>> = MutableLiveData()

    val movies: LiveData<Results<List<MovieVO>>>
        get() = moviesLiveData

    init {
        fetchMovies()
    }

    private fun fetchMovies() {

        viewModelScope.launch {
            moviesLiveData.postValue(Results.loading(null))
            movieRepository.refreshMovies()
            moviesLiveData.postValue(movieRepository.observeMoviesFromDB())
        }
    }


}

