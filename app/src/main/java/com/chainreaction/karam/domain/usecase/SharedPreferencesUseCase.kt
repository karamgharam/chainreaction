package com.chainreaction.karam.domain.usecase

import com.chainreaction.karam.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesUseCase @Inject constructor(
    private val repository: SharedPreferencesRepository
) {
    fun <T> getPref(key: String, objectClass: Class<T>): T? {
        return repository.getPref(key, objectClass)
    }

    fun <T> savePref(value: T, key: String) {
        return repository.savePref(value, key)
    }
}
