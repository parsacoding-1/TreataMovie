package ir.pmoslem.treatamovie.view.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.button.MaterialButton
import ir.pmoslem.treatamovie.R
import ir.pmoslem.treatamovie.model.Movie

lateinit var itemChangeListener: ItemChangeListener
class MoviesAdapter constructor(val context: Context): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private lateinit var  movies: List<Movie>

    fun setMovies(itemListener: ItemChangeListener, movieList: List<Movie>){
        movies = movieList
        itemChangeListener = itemListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val thumbIv: ImageView = itemView.findViewById(R.id.iv_thumbPic_item)
        private val title: TextView = itemView.findViewById(R.id.tv_title_item)
        private val type: TextView = itemView.findViewById(R.id.tv_type_item)
        private val favoriteIv: ImageView = itemView.findViewById(R.id.animation_favorite_item)
        private val moreDetailBtn: MaterialButton = itemView.findViewById(R.id.btn_moreDetails_item)

        fun bind(movie:Movie){
            title.text = movie.title
            type.text = if (movie.zoneId == 3) "نوع فیلم: سریال" else "نوع فیلم: سینمایی"
            thumbIv.load(movie.thumbImgLink)
            if (movie.favoriteStatus) favoriteIv.setImageResource(R.drawable.ic_round_favorite_24)
            else favoriteIv.setImageResource(R.drawable.ic_round_favorite_border_24)
            favoriteIv.setOnClickListener {
                itemChangeListener.onFavoriteButtonClicked(movie)
            }

            moreDetailBtn.setOnClickListener{
                itemChangeListener.onDetailButtonClicked(movie)
            }

        }

    }


}

interface ItemChangeListener{
    fun onFavoriteButtonClicked(movie: Movie)
    fun onDetailButtonClicked(movie: Movie)
}