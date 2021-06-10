package com.dignicate.zero1.infra.mock.subject01

import com.dignicate.zero1.domain.subject01.BasicFetchMockDomain
import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.case101.SimpleCompanyInfoRepositoryInterface
import io.reactivex.Single
import kotlin.concurrent.thread

class SimpleCompanyInfoRepositoryMock(private val delayMs: Long) :
    SimpleCompanyInfoRepositoryInterface {
    override fun fetch(id: CompanyInfo.Id): Single<CompanyInfo> =
        Single.create { callback ->
            thread {
                Thread.sleep(delayMs)
                callback.onSuccess(
                    CompanyInfo(
                        nameJP = "ディグニケート合同会社",
                        nameEN = "Dignicate, LLC",
                        address = "東京都新宿区西新宿３−１−５新宿嘉泉ビル８F",
                        foundationDate = BasicFetchMockDomain.YMD(2019, 5, 20),
                        capital = BasicFetchMockDomain.Currency.JPY(90000000000000),
                        numberOfEmployees = 29018
                    )
                )
            }
        }
}