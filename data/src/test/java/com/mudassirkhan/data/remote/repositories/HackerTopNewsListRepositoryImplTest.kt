package com.mudassirkhan.data.remote.repositories

import com.mudassirkhan.data.remote.entities.CommentsData
import com.mudassirkhan.data.remote.entities.NewsItem
import com.mudassirkhan.domain.entity.CommentsDomain
import com.mudassirkhan.domain.gateway.HackerNewsGateway
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class HackerTopNewsListRepositoryImplTest {

    @Mock
    private lateinit var hackerTopNewsListRepository: HackerTopNewsListRepository

    private lateinit var hackerNewsGateway: HackerNewsGateway

    @Before
    fun setUp() {

        hackerNewsGateway = HackerTopNewsListRepositoryImpl(hackerTopNewsListRepository)

    }


    @Test
    @Throws(Exception::class)
    fun `get news list ids`() {

        val response = listOf<String>()

        Mockito.`when`(hackerTopNewsListRepository.getTopNewsList())
            .thenReturn(Single.just(response))

        val testObserver = hackerNewsGateway.getHackerNewsList().test()


        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(response)


    }

    @Test
    @Throws(Exception::class)
    fun `get news list ids error`() {

        val response = RuntimeException()

        Mockito.`when`(hackerTopNewsListRepository.getTopNewsList())
            .thenReturn(Single.error(RuntimeException()))


        val testObserver = hackerNewsGateway.getHackerNewsList().test()
            .assertError(RuntimeException::class.java)


        testObserver.assertNotComplete()
            .assertError(RuntimeException::class.java)


    }


    @Test
    @Throws(Exception::class)
    fun `Get HackerNews News list from API`() {

        //Given
        val itemId = "12334445"
        val domainItems = com.mudassirkhan.domain.entity.NewsItem(
            by = "dnetesn",
            descendants = 3,
            id = 22335534,
            kids = listOf<Long>(22339654),
            score = 26,
            time = 1581777515,
            title = "Upcoming missions to look for 'biosignatures' in exoplanet atmospheres",
            type = "story",
            url = "https://phys.org/news/2020-02-earth-cousins-upcoming-missions-biosignatures.html"
        )
        val repositoryItems = NewsItem(
            by = "dnetesn",
            descendants = 3,
            id = 22335534,
            kids = listOf<Long>(22339654),
            score = 26,
            time = 1581777515,
            title = "Upcoming missions to look for 'biosignatures' in exoplanet atmospheres",
            type = "story",
            url = "https://phys.org/news/2020-02-earth-cousins-upcoming-missions-biosignatures.html"
        )

        Mockito.`when`(hackerTopNewsListRepository.getTopNews(itemId))
            .thenReturn(Single.just(repositoryItems))

        //When
        val testObserver = hackerNewsGateway.getHackerNewsItem(itemId).test()

        //should

        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(domainItems)
    }


    @Test
    @Throws(Exception::class)
    fun `Get HackerNews News Detail for comments`() {

        //Given
        val itemId = "22339059"
        val domainItems = CommentsDomain(
            id = 22339059,
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
            children = null,
            options =null
        )
        val repositoryItems = CommentsData(
            id = 22339059,
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
            children = null,
            options = null
        )

        Mockito.`when`(hackerTopNewsListRepository.getNewsDetail(itemId))
            .thenReturn(Single.just(repositoryItems))

        //When
        val testObserver = hackerNewsGateway.getHackerNewsDetails(itemId).test()

        //should

        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(domainItems)
    }
}