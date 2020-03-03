package com.mudassirkhan.domain.interactor

import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.entity.NewsItem
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import com.mudassirkhan.domain.utils.TestSchedulers
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class GetHackerNewsItemUseCaseTest{


    private lateinit var getHackerNewsItemUseCase : GetHackerNewsItemUseCase
    @Mock
    private lateinit var hackerNewsGateway : HackerNewsGateway

    private lateinit var schedulers: Schedulers


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this);
        schedulers = TestSchedulers()
        getHackerNewsItemUseCase = GetHackerNewsItemUseCase(schedulers,hackerNewsGateway)
    }

    @Test
    fun `get the news item of top news list`() {

        //Given
        val itemId ="22335534"
        val mockNewsList = NewsItem( by = "dnetesn",
            descendants = 3,
            id = 22335534,
            kids = listOf<Long>(22339654),
            score = 26,
            time = 1581777515,
            title = "Upcoming missions to look for 'biosignatures' in exoplanet atmospheres",
            type = "story",
            url = "https://phys.org/news/2020-02-earth-cousins-upcoming-missions-biosignatures.html")

        getHackerNewsItemUseCase.setParam(itemId)

        Mockito.`when`(hackerNewsGateway.getHackerNewsItem(itemId)).thenReturn(Single.just(mockNewsList))

        //when

        val testObserver = getHackerNewsItemUseCase.execute().test()

        //should

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(mockNewsList)

        Mockito.verify(hackerNewsGateway).getHackerNewsItem(itemId)

    }
}