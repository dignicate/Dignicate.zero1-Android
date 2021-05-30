package com.dignicate.zero1.domain.subject01

/**
 * Easy mock implementation to focus on the sample purpose.
 */
object BasicFetchMockDomain {
    data class YMD(
        val year: Int,
        val month: Int,
        val day: Int
    ) {
        val localizedExpression: String
            get() = "令和${year - 2018}年${year}月${day}日"
    }

    sealed class Currency {
        class JPY(val amount: Long) : Currency()
        class USD(val amount: Long) : Currency()

        val localizedExpression: String
            get() {
                return when (this) {
                    is JPY -> "${amount / 1000000000000}兆円"
                    is USD -> "${amount / 1000000000000}兆ドル"
                }
            }
    }
}
