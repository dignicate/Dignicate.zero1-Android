package com.dignicate.zero1.infra.module

import com.dignicate.zero1.domain.subject01.case101.SimpleCompanyInfoRepositoryInterface
import com.dignicate.zero1.infra.mock.subject01.SimpleCompanyInfoRepositoryMock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesSimpleCompanyInfoRepository(): SimpleCompanyInfoRepositoryInterface {
        return SimpleCompanyInfoRepositoryMock(4)
    }

}