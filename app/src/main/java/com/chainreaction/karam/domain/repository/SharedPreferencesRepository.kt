package com.chainreaction.karam.domain.repository


interface SharedPreferencesRepository {
    fun <T> savePref(value: T, key: String)
    fun <T> getPref(key: String, objectClass: Class<T>): T?

}


