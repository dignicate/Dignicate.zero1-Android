package com.dignicate.zero1.domain.subject01

data class CompanyInfo(
    val id: CompanyInfo.Id,
    val nameJP: String,
    val nameEN: String,
    val address: String,
    val foundationDate: BasicFetchMockDomain.YMD,
    val capital: BasicFetchMockDomain.Currency,
    val numberOfEmployees: Int
) {
    class Id(val value: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Id

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value
        }
    }
}
