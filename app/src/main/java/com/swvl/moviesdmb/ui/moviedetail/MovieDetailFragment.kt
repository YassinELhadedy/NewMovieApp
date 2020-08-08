package com.swvl.moviesdmb.ui.moviedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.retrofit.coroutines.utils.Status
import com.swvl.moviesdmb.databinding.FragmentMovieDetailBinding
import com.swvl.moviesdmb.models.Movie
import com.swvl.moviesdmb.ui.moviedetail.adapter.CastAdapter
import com.swvl.moviesdmb.ui.moviedetail.adapter.ReviewAdapter
import com.swvl.moviesdmb.ui.moviedetail.adapter.TrailerAdapter
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import org.koin.android.ext.android.inject

class MovieDetailFragment : Fragment(), TrailerAdapter.OnYouTubeLinkClickListener {
    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var trailerAdapter: TrailerAdapter
    private lateinit var castAdapter: CastAdapter
    private lateinit var reviewAdapter: ReviewAdapter
    private val movieDetailViewModel: MovieDetailViewModel by inject()
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val safeArgs: MovieDetailFragmentArgs by navArgs()
        movie = safeArgs.movie
        binding.item = movie
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI(movie)
        setupObservers(movie.id)
        actions()
    }

    private fun actions() {
        //Navigation back
        binding.onSubmitClick = View.OnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupUI(movie: Movie) {
        binding.root.overview.text = movie.overview
        //TrailerAdapter
        binding.root.recycler_view_trailers.layoutManager = LinearLayoutManager(requireContext())
        trailerAdapter = TrailerAdapter(mutableListOf(), this)
        binding.root.recycler_view_trailers.addItemDecoration(
            DividerItemDecoration(
                binding.root.recycler_view_trailers.context,
                (binding.root.recycler_view_trailers.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.root.recycler_view_trailers.adapter = trailerAdapter

        //CastAdapter
        binding.root.recycler_view_casts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        castAdapter = CastAdapter(mutableListOf())
        binding.root.recycler_view_casts.addItemDecoration(
            DividerItemDecoration(
                binding.root.recycler_view_casts.context,
                (binding.root.recycler_view_casts.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.root.recycler_view_casts.adapter = castAdapter

        //ReviewAdapter
        binding.root.recycler_view_reviews.layoutManager = LinearLayoutManager(requireContext())
        reviewAdapter = ReviewAdapter(mutableListOf())
        binding.root.recycler_view_reviews.addItemDecoration(
            DividerItemDecoration(
                binding.root.recycler_view_reviews.context,
                (binding.root.recycler_view_reviews.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.root.recycler_view_reviews.adapter = reviewAdapter
    }

    private fun retrieveList(movies: DetailItemViewModel) {
        trailerAdapter.apply {
            addTrailers(movies.trailers)
            notifyDataSetChanged()
        }

        castAdapter.apply {
            addCasts(movies.casts)
            notifyDataSetChanged()
        }

        reviewAdapter.apply {
            addReviews(movies.reviews)
            notifyDataSetChanged()
        }
    }


    private fun setupObservers(movieId: String) {
        movieDetailViewModel.getMovieDetail(movieId).observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { movies -> retrieveList(movies) }
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    override fun onClickYouTubeTrailerMovie(trailerUrl: String) {
        onThumbnailClick(trailerUrl)
    }

    private fun onThumbnailClick(trailerUrl: String) {
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
        startActivity(playVideoIntent)
    }

    companion object {
        const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v=%1\$s"
        const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/%1\$s/0.jpg"
    }
}