package com.chainreaction.karam.data.repository

import com.chainreaction.karam.data.source.local.SharedPreferenceManager
import com.chainreaction.karam.domain.repository.SharedPreferencesRepository


class SharedPreferencesRepositoryImp(
    private val sharedPreferenceManager: SharedPreferenceManager
) : SharedPreferencesRepository {

    override fun <T> savePref(value: T, key: String) {
        sharedPreferenceManager.savePref(value, key)
    }

    override fun <T : Any?> getPref(key: String, objectClass: Class<T>): T? {
        return sharedPreferenceManager.getPref(key, objectClass)
    }


}
