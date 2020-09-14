package com.example.android.sub1.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android.sub1.ui.favorite.movie.FavoriteMovie
import com.example.android.sub1.ui.favorite.tvShow.FavoriteTvShow

class PagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = listOf(
        FavoriteMovie(),
        FavoriteTvShow()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Movie"
            else -> "Tv Show"
        }
    }
}