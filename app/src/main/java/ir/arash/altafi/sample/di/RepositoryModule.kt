package ir.arash.altafi.sample.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import ir.arash.altafi.sample.data.api.CelebrityService
import ir.arash.altafi.sample.data.api.PagingService
import ir.arash.altafi.sample.data.api.UserService
import ir.arash.altafi.sample.data.dataSource.TestDataSource
import ir.arash.altafi.sample.data.db.TestDetailDao
import ir.arash.altafi.sample.data.repository.CelebrityRepository
import ir.arash.altafi.sample.data.repository.UserRepository
import ir.arash.altafi.sample.data.repository.DataStoreRepository
import ir.arash.altafi.sample.data.repository.PagingRepository
import ir.arash.altafi.sample.data.repository.TestRepository
import ir.arash.altafi.sample.utils.EncryptionUtils
import ir.arash.altafi.sample.utils.JsonUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.arash.altafi.sample.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        dataStore: DataStore<Preferences>,
        encryptionUtils: EncryptionUtils,
        jsonUtils: JsonUtils
    ) = DataStoreRepository(dataStore, encryptionUtils, jsonUtils)

    @Singleton
    @Provides
    fun provideUserRepository(
        userService: UserService,
    ) = UserRepository(userService)

    @Singleton
    @Provides
    fun providePagingRepository(
        userService: PagingService,
    ) = PagingRepository(userService)

    @Singleton
    @Provides
    fun provideTestRepository(
        testDataSource: TestDataSource,
        userDao: TestDetailDao,
    ) = TestRepository(testDataSource, userDao)

    @Singleton
    @Provides
    fun provideCelebrityRepository(
        celebrityService: CelebrityService,
    ) = CelebrityRepository(celebrityService)

    @Provides
    @Singleton
    fun provideServerUrl(): String {
        return BuildConfig.BASE_URL
    }
}