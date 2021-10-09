package com.example.marvelhub.data.pagingremoteMedatiors

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelhub.data.repository.RemoteDataSource
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.domain.model.EventData
import com.example.marvelhub.utils.Constants
import com.example.marvelhub.utils.Mappers
import retrofit2.HttpException
import java.io.IOException

class StoriesPagingSource(private val remoteDataSource: RemoteDataSource, private val id:Int):
    PagingSource<Int, EventData>() {

    override fun getRefreshKey(state: PagingState<Int, EventData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(20)
    }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventData> {
        val position = params.key ?: Constants.BASE_OFFSET
        return try {
            val response = remoteDataSource.getCharacterStories(id,position+1)
            val repos = response!!.data.storiesDtos.map {
                Mappers.fromStoriesDtoToEventData(it)
            }
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                position + 20
            }
            LoadResult.Page(
                data = repos,
                prevKey = null ,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}