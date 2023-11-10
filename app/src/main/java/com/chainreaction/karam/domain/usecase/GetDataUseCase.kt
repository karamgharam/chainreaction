package com.chainreaction.karam.domain.usecase

import com.chainreaction.karam.domain.data.resp.MyDataResp
import com.chainreaction.karam.domain.repository.GetDataRepository
import com.chainreaction.karam.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GetDataUseCase @Inject constructor(
    private val repository: GetDataRepository
) : SingleUseCase<Any, MyDataResp>() {

    override fun buildUseCaseSingle(requestBody: Any?): Single<MyDataResp> {
        return repository.getData()
    }
}
