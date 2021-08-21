package com.dignicate.zero1.domain.module

import com.dignicate.zero1.domain.subject01.case101.hiltdi.BasicFetchApiUse
import com.dignicate.zero1.domain.subject01.case101.hiltdi.BasicFetchApiUseCaseInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindBasicFetchApiUseCase(basicFetchApiUseCase: BasicFetchApiUse): BasicFetchApiUseCaseInterface
}