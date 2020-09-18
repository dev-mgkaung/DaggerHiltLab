package mk.learner.daggerhiltlab.ui.home

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import mk.learner.daggerhiltlab.R
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.utils.Status

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MovieViewModel by viewModels()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        setupObserver()
    }
    private fun setupRecyclerView() {
        movie_recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(arrayListOf())
        movie_recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.movies.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { movie_list -> renderList(movie_list) }
                    movie_recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    movie_recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<MovieVO>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}