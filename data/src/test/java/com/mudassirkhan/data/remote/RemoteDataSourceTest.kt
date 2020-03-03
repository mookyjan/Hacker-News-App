package com.mudassirkhan.data.remote

import com.mudassirkhan.data.NetworkModule
import com.mudassirkhan.data.remote.api.HackerNewsApiService
import com.mudassirkhan.data.remote.entities.CommentsData
import com.mudassirkhan.data.remote.entities.NewsItem
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONArray
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.File


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var networkModule: NetworkModule
    @Mock
    private lateinit var hackerNewsApiService: HackerNewsApiService

    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var okHttpClient: OkHttpClient

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        MockitoAnnotations.initMocks(this);
        mockWebServer.start()

        networkModule = NetworkModule()
        okHttpClient = networkModule.provideOkHttpClient()
        hackerNewsApiService =
            networkModule.provideHackerAPI(okHttpClient, mockWebServer.url("/").toString())
        remoteDataSource = RemoteDataSource(hackerNewsApiService)
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    @Test
    @Throws(Exception::class)
    fun `When get all news Item ids, Should get those ids `() {

        // Given

        val items = listOf<String>("22339059", "22339596", "22335738")
        val json: String = getJson("json/news_item_ids_list.json")
//        val items = getJsonData(json)
        val response = MockResponse().setBody(json)
        mockWebServer.enqueue(response)

        // When

        val testObserver = remoteDataSource.getHackerTopNewsList()
            .test()

        // Then

        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(items)
    }


    @Test
    @Throws(Exception::class)
    fun `When get  news Item , Should get item `() {

        // Given
        val itemId = "22339059"
        val items = NewsItem(
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
        val json: String = getJson("json/new_item_detail.json")
        val response = MockResponse().setBody(json)
        mockWebServer.enqueue(response)

        // When

        val testObserver = remoteDataSource.getHackerTopNews(itemId)
            .test()

        // Then

        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(items)
    }


    @Test
    @Throws(Exception::class)
    fun `When get comments for news Item, Should get those comments `() {

        // Given
        val itemId = "22339059"
        val items = CommentsData(
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
        val json: String = getJson("json/news_item_comments.json")
//        val items = getJsonData(json)
        val response = MockResponse().setBody(json)
        mockWebServer.enqueue(response)

        // When

        val testObserver = remoteDataSource.getNewsDetail(itemId)
            .test()

        // Then

        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(items)
    }


    private fun getJson(path: String): String {
//        C:\Projects\Examples\Hacker News App\data\src\test\resources\json\news_item_ids_list.json
        val file = File("src/test/resources/$path")
        return String(file.readBytes())
    }

}