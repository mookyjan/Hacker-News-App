package com.mudassirkhan.hackernewsapp.ui.newsList

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.domain.interactor.GetHackerNewsItemUseCase
import com.mudassirkhan.domain.interactor.GetHackerNewsListUseCase
import com.mudassirkhan.hackernewsapp.mapper.mapDomainToPresentationNewsItem
import com.mudassirkhan.hackernewsapp.utils.IResourceProvider
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.hamcrest.core.Is
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import org.mockito.junit.MockitoJUnitRunner


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class NewsListViewModelTest{

    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var application: Application
    @Mock
    private lateinit var getHackerNewsListUseCase: GetHackerNewsListUseCase

    @Mock
    private lateinit var getHackerNewsItemUseCase : GetHackerNewsItemUseCase

    private lateinit var newsListViewModel: NewsListViewModel
    @Mock
    private lateinit var iResourceProvider: IResourceProvider

    //to update the value of mutableLive data instantly
    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun setup() {
//        MockitoAnnotations.initMocks(this);

        newsListViewModel = NewsListViewModel( getHackerNewsListUseCase,getHackerNewsItemUseCase,iResourceProvider)
    }

    @Test
    @Throws(Exception::class)
    fun `Given news list Ids, When call news ids list, Should update newsList`() {

        // Given


        val items = listOf<String>("22333994","22334236")

        Mockito.`when`(getHackerNewsListUseCase.execute()).thenReturn(Single.just(items))

        // When

        newsListViewModel.getNewsList()

        // Should
        assertThat(newsListViewModel.newsList, Is.`is`(items))
        //to check the size of the list
        val expectedValue = 2// 2 ids

        assertNotNull(newsListViewModel.newsList)

        assertEquals(expectedValue, newsListViewModel.newsList.size)
        //empty field will be empty
        assertEquals(newsListViewModel.empty.get(),true)
//
//        Mockito.verify(getHackerNewsItemUseCase, Mockito.times(expectedValue)).getNewsItem("");
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load news Ids list with error, Should update error`() {

        // Given

        val error = RuntimeException("Error emission")
        Mockito.`when`(getHackerNewsListUseCase.execute()).thenReturn(Single.error(error))

        // When

        newsListViewModel.getNewsList()

        // Should

        assertThat(newsListViewModel.error.value, Is.`is`(error.message))
    }

    @Test
    @Throws(Exception::class)
    fun `Given unknown error emission, When load news ids list with unknown error, Should update error`() {

        // Given

        val error = "Unknown error"
//        Mockito.`when`(application.getString(ArgumentMatchers.anyInt())).thenReturn(error)
        Mockito.`when`(getHackerNewsListUseCase.execute()).thenReturn(Single.error(RuntimeException()))

        // When

        newsListViewModel.getNewsList()

        // Should

        assertThat(newsListViewModel.error.value, Is.`is`(error))
    }


    @Test
    @Throws(Exception::class)
    fun ` When call news item, Should update newsItem `() {

        // Given
       val itemId ="22333994"
        val domainItems = com.mudassirkhan.domain.entity.NewsItem( by = "dnetesn",
            descendants = 3,
            id = 22335534,
            kids = listOf<Long>(22339654),
            score = 26,
            time = 1581777515,
            title = "Upcoming missions to look for 'biosignatures' in exoplanet atmospheres",
            type = "story",
            url = "https://phys.org/news/2020-02-earth-cousins-upcoming-missions-biosignatures.html")

        getHackerNewsItemUseCase.setParam(itemId)
        Mockito.`when`(getHackerNewsItemUseCase.execute()).thenReturn(Single.just(domainItems))

        // When

        newsListViewModel.getNewsItem(itemId)

        // Should
        assertThat(newsListViewModel.presentationNewsItem, Is.`is`(mapDomainToPresentationNewsItem(domainItems)))
    }



    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load news item with error, Should update error`() {

        // Given

        val error = RuntimeException("Error emission")
        Mockito.`when`(getHackerNewsListUseCase.execute()).thenReturn(Single.error(error))

        // When

        newsListViewModel.getNewsList()

        // Should

        assertThat(newsListViewModel.error.value, Is.`is`(error.message))
    }

    @Test
    @Throws(Exception::class)
    fun `Given unknown error emission, When load news item  with unknown error, Should update error`() {

        // Given

        val error = "Unknown error"
        Mockito.`when`(getHackerNewsListUseCase.execute()).thenReturn(Single.error(RuntimeException()))

        // When

        newsListViewModel.getNewsList()

        // Should

        assertThat(newsListViewModel.error.value, Is.`is`(error))
    }
}