package mk.learner.daggerhiltlab.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.android.synthetic.main.movie_item_layout.view.*
import mk.learner.daggerhiltlab.R
import mk.learner.daggerhiltlab.datas.entities.MovieVO
import mk.learner.daggerhiltlab.utils.BASE_IMAGE_URL


class MovieAdapter(
    private val movies: ArrayList<MovieVO>
) : RecyclerView.Adapter<MovieAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieVO) {
            itemView.text_MovieName.text = movie.original_title
            itemView.text_MovieDescription.text = movie.overview
            itemView.image_MoviePoster.load("$BASE_IMAGE_URL${movie.poster_path}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(movies[position])

    fun addData(list: List<MovieVO>) {
        movies.addAll(list)
    }
}