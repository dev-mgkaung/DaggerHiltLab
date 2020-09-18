package mk.learner.daggerhiltlab.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import mk.learner.daggerhiltlab.datas.MovieRepository
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.utils.NetworkHelper
import mk.learner.daggerhiltlab.utils.Resource

class MovieViewModel @ViewModelInject constructor(
    private val moviesRepository: MovieRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _movies = MutableLiveData<Resource<List<MovieVO>>>()

    val movies: LiveData<Resource<List<MovieVO>>>
        get() = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
           _movies.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                moviesRepository.observeMovies().let {
                    if (it.isSuccessful) {
                        _movies.postValue(Resource.success(it.body()))
                    } else _movies.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
            else _movies.postValue(Resource.error("No Internet connection",null))
        }
    }
}