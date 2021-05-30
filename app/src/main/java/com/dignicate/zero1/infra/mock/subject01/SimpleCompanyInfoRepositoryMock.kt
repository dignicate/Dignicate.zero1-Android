package com.dignicate.zero1.infra.mock.subject01

import com.dignicate.zero1.domain.subject01.CompanyInfo
import com.dignicate.zero1.domain.subject01.SimpleCompanyInfoRepositoryInterface
import io.reactivex.Single
import kotlin.concurrent.thread

class SimpleCompanyInfoRepositoryMock : SimpleCompanyInfoRepositoryInterface {
    override fun fetch(id: CompanyInfo.Id): Single<CompanyInfo> =
        Single.create { callback ->
            thread {
                Thread.sleep(2000L)
                callback.onSuccess(
                    CompanyInfo(
                        nameJP = "ディグニケート合同会社",
                        nameEN = "Dignicate, LLC"
                    )
                )
            }
        }
}