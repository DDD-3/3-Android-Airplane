package com.ddd.airplane.presenter.search.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.data.response.SearchData
import com.ddd.airplane.data.response.chat.ProgramData
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import kotlinx.coroutines.*
import timber.log.Timber

class SearchViewModel(application: Application) : BaseViewModel(application) {

    private var searchFor: String? = null

    // list
    var searchPagedList =
        LivePagedListBuilder(getSearchDataSourceFactory(), getPageListConfig()).build()
    var manyPagedList =
        LivePagedListBuilder(getManyDataSourceFactory(), getPageListConfig()).build()

    // 검색결과 여부
    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _isSearchAdapter = MutableLiveData<Boolean>()
    val isSearchAdapter: LiveData<Boolean> = _isSearchAdapter

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        tryCatch {
            val searchText = s.toString().trim()

            // 빈값 처리, 같은 값 처리
            if (searchText.isNullOrEmpty()) {
                _isEmpty.value = true
                return
            }
            _isEmpty.value = false

            if (searchText == searchFor) {
                return
            }
            searchFor = searchText

            CoroutineScope(Dispatchers.Main).launch {
                delay(300)  // debounce timeOut
                if (searchText != searchFor) {
                    return@launch
                }
                Timber.d(">> $searchFor")
                initSearch()
            }
        }
    }

    /**
     * 검색 초기화
     */
    private fun initSearch() {
        searchPagedList =
            LivePagedListBuilder(getSearchDataSourceFactory(), getPageListConfig()).build()
        _isSearchAdapter.value = false
    }

    /**
     * 검색 Request
     */
    private fun getSearchList(
        position: Int = 1,
        listener: ((List<ProgramData>, Int) -> Unit)? = null
    ) {

        if (searchFor.isNullOrEmpty()) {
            return
        }

        viewModelScope.launch(ioDispatchers) {

            val response = RetrofitManager
                .general
                .getSearch(
                    query = searchFor!!,
                    pageNum = position
                )

            if (response.isSuccessful) {
                val pageNum = response.body()?.pageInfo?.pageNum ?: 1
                val list = response.body()?.items?.toMutableList() ?: mutableListOf()
                listener?.invoke(list, pageNum)
            }
        }
    }

    /**
     * 많이 참여한 방송 Request
     */
    private fun getManyList(
        position: Int = 1,
        listener: ((List<ProgramData>, Int) -> Unit)? = null
    ) {
        viewModelScope.launch(ioDispatchers) {

//            val response = RetrofitManager
//                .general
//                .getSearch(
//                    query = searchFor!!,
//                    pageNum = position
//                )
//
//            if (response.isSuccessful) {
//                val pageNum = response.body()?.pageInfo?.pageNum ?: 1
//                val list = response.body()?.items?.toMutableList() ?: mutableListOf()
//                listener?.invoke(list, pageNum)
//            }
        }
    }

    /**
     * Paged Config
     */
    private fun getPageListConfig() = PagedList.Config.Builder()
        .setPrefetchDistance(5)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .setEnablePlaceholders(false)
        .build()

    /**
     * 검색 DataSourceFactory
     */
    private fun getSearchDataSourceFactory() = object : DataSource.Factory<Int, ProgramData>() {

        override fun create(): DataSource<Int, ProgramData> {

            return object : PageKeyedDataSource<Int, ProgramData>() {

                override fun loadInitial(
                    params: LoadInitialParams<Int>,
                    callback: LoadInitialCallback<Int, ProgramData>
                ) {
                    Timber.d("loadInitial : $params")
                    getSearchList(1) { list, pageNum ->
                        callback.onResult(list, null, pageNum)
                    }
                }

                override fun loadBefore(
                    params: LoadParams<Int>,
                    callback: LoadCallback<Int, ProgramData>
                ) {
                    Timber.d("loadBefore : $params")
                    getSearchList(params.key) { list, pageNum ->
                        callback.onResult(list, pageNum.plus(1))
                    }
                }

                override fun loadAfter(
                    params: LoadParams<Int>,
                    callback: LoadCallback<Int, ProgramData>
                ) {

                }
            }
        }
    }

    /**
     * 많이 참여한 방송 DataSourceFactory
     */
    private fun getManyDataSourceFactory() = object : DataSource.Factory<Int, ProgramData>() {

        override fun create(): DataSource<Int, ProgramData> {

            return object : PageKeyedDataSource<Int, ProgramData>() {

                override fun loadInitial(
                    params: LoadInitialParams<Int>,
                    callback: LoadInitialCallback<Int, ProgramData>
                ) {
                    Timber.d("loadInitial : $params")
                    getSearchList(1) { list, pageNum ->
                        callback.onResult(list, null, pageNum)
                    }
                }

                override fun loadBefore(
                    params: LoadParams<Int>,
                    callback: LoadCallback<Int, ProgramData>
                ) {
                    Timber.d("loadBefore : $params")
                    getSearchList(params.key) { list, pageNum ->
                        callback.onResult(list, pageNum.plus(1))
                    }
                }

                override fun loadAfter(
                    params: LoadParams<Int>,
                    callback: LoadCallback<Int, ProgramData>
                ) {

                }
            }
        }
    }


    /**
     * Diff Callback
     */
    val diffCallback: DiffUtil.ItemCallback<ProgramData> =
        object : DiffUtil.ItemCallback<ProgramData>() {
            override fun areItemsTheSame(
                oldItem: ProgramData,
                newItem: ProgramData
            ): Boolean {
                return oldItem.roomId == newItem.roomId
            }

            override fun areContentsTheSame(
                oldItem: ProgramData,
                newItem: ProgramData
            ): Boolean {
                return oldItem.roomId == newItem.roomId
            }
        }
}