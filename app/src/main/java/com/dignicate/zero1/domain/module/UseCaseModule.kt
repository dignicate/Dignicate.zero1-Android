package com.dignicate.zero1.domain.module

import com.dignicate.zero1.domain.subject01.case101.hiltdi.BasicFetchApiUse
import com.dignicate.zero1.domain.subject01.case101.hiltdi.BasicFetchApiUseCaseInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    fun providesBasicFetchApiUseCase(): BasicFetchApiUseCaseInterface {
        return BasicFetchApiUse()
    }
}