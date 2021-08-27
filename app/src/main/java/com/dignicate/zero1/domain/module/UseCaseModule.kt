package com.dignicate.zero1.domain.module

import com.dignicate.zero1.domain.subject01.case101.hiltdi.BasicFetchApiUseCase
import com.dignicate.zero1.domain.subject01.case101.hiltdi.BasicFetchApiUseCaseInterface
import com.dignicate.zero1.domain.subject01.case102.hiltdi.FetchWithDataStateUseCase
import com.dignicate.zero1.domain.subject01.case102.hiltdi.FetchWithDataStateUseCaseInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindBasicFetchApiUseCase(basicFetchApiUseCase: BasicFetchApiUseCase): BasicFetchApiUseCaseInterface

    @Singleton
    @Binds
    abstract fun bindFetchWithDataStateUseCase(fetchWithDataStateUseCase: FetchWithDataStateUseCase): FetchWithDataStateUseCaseInterface
}