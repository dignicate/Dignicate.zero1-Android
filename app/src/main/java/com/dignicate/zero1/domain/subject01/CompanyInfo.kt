package com.dignicate.zero1.domain.subject01

data class CompanyInfo(
    val nameJP: String,
    val nameEN: String,
    val address: String,
    val foundationDate: String,
    val capital: Int,
    val numberOfEmployees: Int
) {
    class Id(val value: Int)
}
