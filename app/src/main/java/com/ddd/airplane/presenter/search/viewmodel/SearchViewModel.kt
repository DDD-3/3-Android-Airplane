package com.ddd.airplane.presenter.search.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddd.airplane.common.base.BaseViewModel
import com.ddd.airplane.common.utils.tryCatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(application: Application) : BaseViewModel(application) {

    private var searchFor: String? = null

    // 검색결과 여부
    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

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
            }
        }
    }
}
