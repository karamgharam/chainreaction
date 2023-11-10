package com.chainreaction.karam.domain.usecase.base

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class SingleUseCase<V : Any, T : Any> : UseCase() {

    internal abstract fun buildUseCaseSingle(requestBody: V? = null): Single<T>

    fun execute(
        requestBody: V? = null,
        onSuccess: ((t: T) -> Unit),
        onError: ((error: String) -> Unit),
        onFinished: () -> Unit = {}
    ) {
        disposeLast()
        lastDisposable = buildUseCaseSingle(requestBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess) { throwable ->
                //u can send code or message or any thing u want
                onError(throwable.toString())
            }

        lastDisposable?.let {
            compositeDisposable.add(it)
        }
    }
}
