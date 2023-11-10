package com.chainreaction.karam.data.repository

import com.chainreaction.karam.data.source.remote.Api
import com.chainreaction.karam.domain.data.resp.MyDataResp
import com.chainreaction.karam.domain.repository.GetDataRepository
import io.reactivex.rxjava3.core.Single


class GetDataRepositoryImp(
    private val retrofitService: Api
) : GetDataRepository {

    override fun getData(): Single<MyDataResp> {
        return retrofitService.getData()
    }
}
