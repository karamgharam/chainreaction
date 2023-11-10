package com.chainreaction.karam.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chainreaction.karam.domain.data.resp.MyDataResp
import com.chainreaction.karam.domain.usecase.GetDataUseCase
import com.chainreaction.karam.domain.usecase.SharedPreferencesUseCase
import com.chainreaction.karam.utils.Event
import com.chainreaction.karam.utils.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val sharedPreferencesUseCase: SharedPreferencesUseCase

) : BaseViewModel() {

    fun loadData() {
        showLoadingDialog(true)
        //u can add request if needed (for POST)
        getDataUseCase.execute(
            onSuccess = {
                showLoadingDialog(false)
                //save my data
                sharedPreferencesUseCase.savePref(it, "myData")
            }, onError = {
                showLoadingDialog(false)
                //handle error here
            })
    }

    fun share() {
        val myData: MyDataResp? = sharedPreferencesUseCase.getPref("myData", MyDataResp::class.java)
        //share the fact form the saved data
        if (myData != null)
            shareLiveData.value = Event(myData.fact)

        //else your message

    }

    private val shareLiveData: MutableLiveData<Event<String>> =
        MutableLiveData<Event<String>>()

    fun getShareLiveData(): LiveData<Event<String>> {
        return shareLiveData
    }

    override fun onCleared() {
        super.onCleared()
        getDataUseCase.dispose()
    }
}