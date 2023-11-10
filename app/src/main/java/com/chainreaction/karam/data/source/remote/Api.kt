package com.chainreaction.karam.data.source.remote

import com.chainreaction.karam.domain.data.resp.MyDataResp
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

// retrofit api interface
interface Api {
    @GET("/fact")
    fun getData(
    ): Single<MyDataResp>

}