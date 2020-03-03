package com.mudassirkhan.hackernewsapp.ui.news_detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mudassirkhan.hackernewsapp.R
import com.mudassirkhan.hackernewsapp.ui.news_detail.article.ArticleFragment
import com.mudassirkhan.hackernewsapp.ui.news_detail.comments.CommentsFragment


private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentStatePagerAdapter(fm) {

    private  var  childFragments : Array<Fragment> = arrayOf<Fragment>(
        CommentsFragment.newInstance(),
        ArticleFragment.newInstance()
    )


    override fun getItem(position: Int): Fragment {

        return childFragments.get(position )
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return childFragments.size
    }
}