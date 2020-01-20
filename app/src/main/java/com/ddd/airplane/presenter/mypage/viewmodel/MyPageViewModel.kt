package com.ddd.airplane.presenter.mypage.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import androidx.recyclerview.widget.DiffUtil
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.interfaces.OnResponseListener
import com.ddd.airplane.common.manager.MemberManager
import com.ddd.airplane.data.response.ErrorData
import com.ddd.airplane.data.response.SubscribeData
import com.ddd.airplane.data.response.chat.ChatRoomData
import com.ddd.airplane.repository.network.retrofit.RetrofitManager
import com.ddd.airplane.repository.network.retrofit.request
import timber.log.Timber
import java.util.concurrent.Executors

class MyPageViewModel(application: Application) : BaseViewModel(application) {

    // 닉네임
    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> = _nickName

    // 구독 리스트
    private val subscribeList = ArrayList<ChatRoomData>()
    private val _isSubscribe = MutableLiveData<Boolean>()
    val isSubscribe: LiveData<Boolean> = _isSubscribe

    init {
        setProfile()
    }

    /**
     * 프로필 정보 세팅
     */
    private fun setProfile() {
        MemberManager.getAccount {
            it?.run {
                _nickName.postValue(nickName)
            }
        }
    }

    /**
     * 구독방송 리스트 조회
     */
    private fun getSubscribe(
        position: Int = 1,
        listener: ((List<ChatRoomData>, Int) -> Unit)? = null
    ) {

        Timber.d("position : $position")
        if (position < 1) {
            return
        }

        RetrofitManager
            .subscribe
            .getSubscribe(position)
            .request(this@MyPageViewModel, object : OnResponseListener<SubscribeData> {

                override fun onSuccess(response: SubscribeData) {
                    val pageNum = response.pageInfo?.pageNum ?: 1
                    val list = response.items?.toMutableList() ?: mutableListOf()

                    subscribeList.addAll(list)
                    // 구독 리스트 여부
                    _isSubscribe.value = subscribeList.size > 0

                    listener?.invoke(list, pageNum)
                }

                override fun onError(error: ErrorData) {

                }
            })
    }

    /**
     * Paged Config
     */
    private val pageListConfig = PagedList.Config.Builder()
        .setPrefetchDistance(5)
        .setInitialLoadSizeHint(20)
        .setPageSize(20)
        .setEnablePlaceholders(false)
        .build()

    /**
     * Data Factory
     */
    private val dataSourceFactory = object : DataSource.Factory<Int, ChatRoomData>() {
        override fun create(): DataSource<Int, ChatRoomData> {

            return object : PageKeyedDataSource<Int, ChatRoomData>() {

                override fun loadInitial(
                    params: LoadInitialParams<Int>,
                    callback: LoadInitialCallback<Int, ChatRoomData>
                ) {
                    Timber.d("loadInitial : $params")
                    getSubscribe(1) { list, pageNum ->
                        callback.onResult(list, null, pageNum)
                    }
                }

                override fun loadBefore(
                    params: LoadParams<Int>,
                    callback: LoadCallback<Int, ChatRoomData>
                ) {
                    Timber.d("loadBefore : $params")
                    getSubscribe(params.key) { list, pageNum ->
                        callback.onResult(list, pageNum.plus(1))
                    }
                }

                override fun loadAfter(
                    params: LoadParams<Int>,
                    callback: LoadCallback<Int, ChatRoomData>
                ) {
//                    Timber.d("loadAfter : $params")
//                    getSubscribe(params.key) { list, pageNum ->
//                        callback.onResult(list, pageNum.minus(1))
//                    }
                }
            }

        }
    }

    /**
     * Live Paged List
     */
    val pagedList = LivePagedListBuilder(dataSourceFactory, pageListConfig)
        .setBoundaryCallback(object : PagedList.BoundaryCallback<ChatRoomData>() {

            override fun onItemAtFrontLoaded(itemAtFront: ChatRoomData) {
                super.onItemAtFrontLoaded(itemAtFront)
                Timber.d("onItemAtFrontLoaded")
            }

            override fun onItemAtEndLoaded(itemAtEnd: ChatRoomData) {
                super.onItemAtEndLoaded(itemAtEnd)
                Timber.d("onItemAtEndLoaded")
            }

            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                Timber.d("onZeroItemsLoaded")
            }
        })
        .build()

    /**
     * Diff Callback
     */
    val diffCallback: DiffUtil.ItemCallback<ChatRoomData> =
        object : DiffUtil.ItemCallback<ChatRoomData>() {
            override fun areItemsTheSame(
                oldItem: ChatRoomData,
                newItem: ChatRoomData
            ): Boolean {
                return oldItem.roomId == newItem.roomId
            }

            override fun areContentsTheSame(
                oldItem: ChatRoomData,
                newItem: ChatRoomData
            ): Boolean {
                return oldItem.roomId == newItem.roomId
            }
        }
}
