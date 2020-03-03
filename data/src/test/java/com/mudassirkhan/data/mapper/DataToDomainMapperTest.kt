package com.mudassirkhan.data.mapper

import com.mudassirkhan.data.mapper.DataToDomainMapper.mapDataToDomainComments
import com.mudassirkhan.data.mapper.DataToDomainMapper.mapDataToDomainNewsItem
import com.mudassirkhan.data.remote.entities.CommentsData
import com.mudassirkhan.domain.entity.CommentsDomain
import com.mudassirkhan.domain.entity.NewsItem
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.mudassirkhan.data.remote.entities.NewsItem as DataNewsItem

class DataToDomainMapperTest {

    companion object {

        lateinit var newsItem: NewsItem
        lateinit var dataNewsItem : DataNewsItem
        lateinit var lisOfNewsItem : List<NewsItem>
        lateinit var listOfDataNewsItem : List<DataNewsItem>
        lateinit var commentData : CommentsData
        lateinit var domainData : CommentsDomain
        lateinit var listOfCommentData : List<CommentsData>
        lateinit var listOfDomainData : List<CommentsDomain>
    }

    @Before
    fun setup() {
         dataNewsItem  = DataNewsItem("2",1,34, listOf<Long>(1,43),
            12,43443433,"title","comment")

        newsItem =  mapDataToDomainNewsItem(dataNewsItem)

        listOfDataNewsItem = listOf<DataNewsItem>(dataNewsItem)
        lisOfNewsItem = listOf(newsItem)

        commentData = CommentsData(1234,"344334",type = "comment",author = "john", title = "title")

        domainData =  mapDataToDomainComments(commentData)
        listOfCommentData = listOf(commentData)
        listOfDomainData = listOf(domainData)
    }

    @Test
    fun `mapped data are valid`(){
        Assert.assertEquals(newsItem, mapDataToDomainNewsItem(dataNewsItem))
    }

    @Test
    fun `fun mapped data for list is valid`(){
        Assert.assertEquals(lisOfNewsItem, mapDataToDomainNewsItem(listOfDataNewsItem))
    }

    @Test
    fun `mapped comment Data to Domain Comment are valid`(){
        Assert.assertEquals(domainData,mapDataToDomainComments(commentData))
    }

    @Test
    fun `mapped data comment to domain comment list`(){
        Assert.assertEquals(listOfDomainData,mapDataToDomainComments(listOfCommentData))
    }
}