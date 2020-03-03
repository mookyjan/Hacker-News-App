package com.mudassirkhan.domain.interactor

import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import com.mudassirkhan.domain.utils.TestSchedulers
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class GetHackerNewsListUseCaseTest {

    private lateinit var useCase: GetHackerNewsListUseCase
    private val mockGateway: HackerNewsGateway = mock()
    private lateinit var schedulers: Schedulers
    private val newsList = listOf("")


    @Before
    fun setUp() {

//        RxJavaPlugins.reset()
        schedulers = TestSchedulers()
        useCase = GetHackerNewsListUseCase(schedulers, mockGateway)
    }

    @Test
    fun `get the ids list of top news list`() {

        //Given
        val mockNewsList = listOf("22308076", "22308076")
        Mockito.`when`(mockGateway.getHackerNewsList()).thenReturn(Single.just(mockNewsList))

        //when

        val testObserver = useCase.execute().test()

        //should

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(mockNewsList)

        Mockito.verify(mockGateway).getHackerNewsList()

    }
}