package com.mudassirkhan.hackernewsapp.ui.news_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mudassirkhan.data.remote.entities.NewsItem
import com.mudassirkhan.domain.entity.CommentsDomain
import com.mudassirkhan.domain.interactor.GetHackerNewsItemDetailUseCase
import com.mudassirkhan.hackernewsapp.mapper.mapDomainToPresentationNewsItemDetail
import com.mudassirkhan.hackernewsapp.utils.IResourceProvider
import io.reactivex.Single
import org.hamcrest.core.Is
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class NewsDetailViewModelTest{

    @Mock
    lateinit var hackerNewsItemDetailUseCase: GetHackerNewsItemDetailUseCase

    @Mock
    lateinit var iResourceProvider: IResourceProvider

    private lateinit var newsDetailViewModel: NewsDetailViewModel

    //to update the value of mutableLive data instantly
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){

        newsDetailViewModel = NewsDetailViewModel(hackerNewsItemDetailUseCase,iResourceProvider)
    }

    @Test
    @Throws(Exception::class)
    fun `when get news detail so it should update comment list`(){

        val itemId ="22339059"
        val repositoryItems = NewsItem("1",3434,345)
        val domainItems = CommentsDomain(id = 22339059,
            createdAt = "2020-02-16T00:38:51.000Z",
            createdAtI = 1581813531,
            type = "story",
            author = "apsec112",
            title = "FDA clears ‘world’s first’ portable, low-cost MRI",
            url = "https://www.healthimaging.com/topics/healthcare-economics/fda-clear-worlds-first-portable-mri",
            text = null,
            points = 242,
            parentId = null,
            storyId = null,
            children = emptyList(),
            options = emptyList())

        Mockito.`when`(hackerNewsItemDetailUseCase.execute()).thenReturn(Single.just(domainItems))

        // When

        newsDetailViewModel.getNewsDetail(itemId)

        // Should
        assertThat(newsDetailViewModel.newsDetailItem.value,Is.`is`(mapDomainToPresentationNewsItemDetail(domainItems)))

    }
}