package com.mudassirkhan.data.remote.repositories

import com.mudassirkhan.data.local.HackerNewsLocalDataSource
import com.mudassirkhan.data.remote.RemoteDataSource
import com.mudassirkhan.data.remote.entities.CommentsData
import com.mudassirkhan.data.remote.entities.NewsItem
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class HackerTopNewsListRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var hackerLocalDataSource: HackerNewsLocalDataSource

    private lateinit var hackerTopNewsListRepository: HackerTopNewsListRepository

    @Before
    fun setUp() {
        hackerTopNewsListRepository =
            HackerTopNewsListRepository(remoteDataSource, hackerLocalDataSource)
    }


    @Test
    @Throws(Exception::class)
    fun `get list of news ids`() {

        //Given
        val listOfIds = listOf<String>()

        `when`(remoteDataSource.getHackerTopNewsList()).thenReturn(Single.just(listOfIds))

        //when
        val testObservable = hackerTopNewsListRepository.getTopNewsList().test().await()

        //should
        testObservable.assertComplete()
            .assertNoErrors()
            .assertValue(listOfIds)

    }


    @Test
    @Throws(Exception::class)
    fun `Get news item details`() {
        // Given

        val itemId = "22335534"

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

        Mockito.`when`(remoteDataSource.getHackerTopNews(itemId))
            .thenReturn(Single.just(repositoryItems))

        // When

        val testObserver = hackerTopNewsListRepository.getTopNews(itemId)
            .test()
            .await()

        // Should

        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(repositoryItems)

    }

    @Test
    @Throws(Exception::class)
    fun `get news details comments`() {

        //Given
        val itemId = "22339059"
        val responseOfComment = CommentsData(
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
            children = emptyList(),
            options = emptyList()
        )

        `when`(remoteDataSource.getNewsDetail(itemId)).thenReturn(Single.just(responseOfComment))

        //when
        val testObserver = hackerTopNewsListRepository.getNewsDetail(itemId)
            .test().await()

        //should
        testObserver.assertComplete()
            .assertNoErrors()
            .assertValue(responseOfComment)
    }
}