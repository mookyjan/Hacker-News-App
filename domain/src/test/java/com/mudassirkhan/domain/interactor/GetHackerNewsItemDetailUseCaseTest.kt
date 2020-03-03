package com.mudassirkhan.domain.interactor

import com.mudassirkhan.domain.MissingUseCaseParameterException
import com.mudassirkhan.domain.Schedulers
import com.mudassirkhan.domain.entity.CommentsDomain
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import com.mudassirkhan.domain.utils.TestSchedulers
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner.Silent::class)
class GetHackerNewsItemDetailUseCaseTest{

    private lateinit var getHackerNewsItemDetailUseCase : GetHackerNewsItemDetailUseCase
    @Mock
    private lateinit var hackerNewsGateway : HackerNewsGateway

    private lateinit var schedulers: Schedulers


    @Before
    fun setUp(){
//        MockitoAnnotations.initMocks(this);
        schedulers = TestSchedulers()
        getHackerNewsItemDetailUseCase = GetHackerNewsItemDetailUseCase(schedulers,hackerNewsGateway)
    }

    @Test
    fun `get the comments of top news item`() {

        //Given
        val itemId ="22308076"
        val commentsDomain = CommentsDomain(id = 22339059,
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

        getHackerNewsItemDetailUseCase.setParam(itemId)
        Mockito.`when`(hackerNewsGateway.getHackerNewsDetails(itemId)).thenReturn(Single.just(commentsDomain))

        //when

        val testObserver = getHackerNewsItemDetailUseCase.execute().test()

        //should

        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(commentsDomain)

        Mockito.verify(hackerNewsGateway).getHackerNewsDetails(itemId)

    }


    @Test(expected = MissingUseCaseParameterException::class)
    @Throws(MissingUseCaseParameterException::class)
    fun `give error when the param value is not setted`() {

        //Given
        val itemId ="22308076"
//        getHackerNewsItemDetailUseCase.setParam(itemId)

        Mockito.`when`(hackerNewsGateway.getHackerNewsDetails(itemId)).thenReturn(Single.error(MissingUseCaseParameterException(javaClass)))

        //when

        val testObserver = getHackerNewsItemDetailUseCase.execute().test()
            .assertError(RuntimeException::class.java)

        //should

        testObserver.assertComplete()
            .assertError(MissingUseCaseParameterException::class.java)


//        Mockito.verify(hackerNewsGateway).getHackerNewsDetails(itemId)


//        }
    }
}