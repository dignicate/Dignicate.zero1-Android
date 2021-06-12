package com.dignicate.zero1.infra.mock.subject01

import android.content.SharedPreferences
import com.dignicate.zero1.domain.subject01.BasicFetchMockDomain
import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case103.CompanyInfoFetchAndSaveRepositoryInterface
import com.dignicate.zero1.domain.subject01.case103.FetchAndSaveDataUseCase
import com.dignicate.zero1.rx.RxExtensions
import io.reactivex.Single
import java.util.*
import kotlin.concurrent.thread

class CompanyInfoFetchAndSaveRepositoryMock(private val delayMs: Long,
                                            private val sharedPreferences: SharedPreferences) : CompanyInfoFetchAndSaveRepositoryInterface {

    private object UserDefaultKey {
        val companyInfoLastUpdate = "companyInfoLastUpdate"
    }

    private val memoryCache = CompanyInfoMemoryCacheDataStore

    override fun fetch(id: CompanyInfo.Id): Single<FetchAndSaveDataUseCase.DataState> =
        if (memoryCache.hasMemoryCache(id)) {
            Single.just(FetchAndSaveDataUseCase.DataState.Local(memoryCache.fetch(id)!!))
        } else {
            Single.create { callback ->
                thread {
                    Thread.sleep(delayMs)
                    callback.onSuccess(
                        FetchAndSaveDataUseCase.DataState.Remote(
                            CompanyInfo(
                                id = CompanyInfo.Id(value = 1234),
                                nameJP = "ディグニケート合同会社",
                                nameEN = "Dignicate, LLC",
                                address = "東京都新宿区西新宿３−１−５新宿嘉泉ビル８F",
                                foundationDate = BasicFetchMockDomain.YMD(2019, 5, 20),
                                capital = BasicFetchMockDomain.Currency.JPY(90000000000000),
                                numberOfEmployees = 29018
                            )
                        )
                    )
                }
            }
        }

    override fun fetchLastUpdated(): Single<RxExtensions.Optional<Date>> =
        Single.create { callback ->
            val milliSec = sharedPreferences.getString(UserDefaultKey.companyInfoLastUpdate, null)?.toLongOrNull()
            if (milliSec != null) {
                callback.onSuccess(RxExtensions.Optional.Some(Date(milliSec)))
            } else {
                callback.onSuccess(RxExtensions.Optional.None())
            }
        }

    override fun saveToLocal(companyInfo: CompanyInfo): Single<Unit> =
        Single.create { callback ->
            val milliSec = Date().time
            sharedPreferences
                .edit()
                .putLong(UserDefaultKey.companyInfoLastUpdate, milliSec)
                .apply()
            callback.onSuccess(Unit)
        }

    override fun clearLocalData(): Single<Unit> =
        Single.create { callback ->
            thread {
                Thread.sleep((delayMs * 2.5).toLong())
                sharedPreferences
                    .edit()
                    .remove(UserDefaultKey.companyInfoLastUpdate)
                    .apply()
                memoryCache.clear()
                callback.onSuccess(Unit)
            }
        }
}