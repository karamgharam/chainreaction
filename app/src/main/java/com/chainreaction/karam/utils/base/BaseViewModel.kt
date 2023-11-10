package com.chainreaction.karam.utils.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chainreaction.karam.utils.Event

open class BaseViewModel : ViewModel() {

    //u can must add any thing u want

    private val showProgressDialogLiveData: MutableLiveData<Event<Boolean>> =
        MutableLiveData<Event<Boolean>>()

    fun getShowProgressDialogLiveData(): LiveData<Event<Boolean>> {
        return showProgressDialogLiveData
    }

    protected fun showLoadingDialog(show: Boolean) {
        showProgressDialogLiveData.value = Event(show)
    }
}