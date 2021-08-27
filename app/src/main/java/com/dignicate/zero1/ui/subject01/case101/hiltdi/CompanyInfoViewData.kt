package com.dignicate.zero1.ui.subject01.case101.hiltdi

import io.reactivex.Observable

data class CompanyInfoViewData(
    val companyNameJP: Observable<String>,
    val companyNameEN: Observable<String>,
    val address: Observable<String>,
    val foundationDate: Observable<String>,
    val capital: Observable<String>,
    val numberOfEmployees: Observable<String>,
    val visibilityOfProgress: Observable<Int>? = null
)
