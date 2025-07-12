package com.khaled.thmanyah.hilt

import com.khaled.thmanyah.data.repo.home.HomeRepository
import com.khaled.thmanyah.data.repo.home.HomeRepositoryImpl
import com.khaled.thmanyah.data.repo.search.SearchRepository
import com.khaled.thmanyah.data.repo.search.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(
        impl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    abstract fun bindSearchRepository(
        impl: SearchRepositoryImpl
    ): SearchRepository
}