package com.chainreaction.karam.domain.repository

import com.chainreaction.karam.domain.data.resp.MyDataResp
import io.reactivex.rxjava3.core.Single

interface GetDataRepository {
    fun getData(): Single<MyDataResp>


}
