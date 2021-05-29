package com.dignicate.zero1.domain.subject01

data class CompanyInfo(
    val nameJP: String,
    val nameEN: String
) {
    class Id(val value: Int)
}

/**
let nameJP: String
let nameEN: String
let address: String
let foundationDate: String
let capital: Int
let numberOfEmployees: Int

struct ID {
let value: Int
}
 */