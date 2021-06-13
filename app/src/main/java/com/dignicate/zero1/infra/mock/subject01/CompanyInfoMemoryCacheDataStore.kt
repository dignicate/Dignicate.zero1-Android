package com.dignicate.zero1.infra.mock.subject01

import com.dignicate.zero1.domain.subject01.CompanyInfo

object CompanyInfoMemoryCacheDataStore {

    private val memoryCache: MutableMap<CompanyInfo.Id, CompanyInfo> = HashMap()

    fun hasMemoryCache(id: CompanyInfo.Id): Boolean =
        memoryCache.containsKey(id)

    fun fetch(id: CompanyInfo.Id): CompanyInfo? =
        memoryCache[id]

    fun save(companyInfo: CompanyInfo) {
        memoryCache[companyInfo.id] = companyInfo
    }

    fun clear() {
        memoryCache.clear()
    }
}