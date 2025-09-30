package ir.arash.altafi.sample.di

import android.content.Context
import androidx.room.Room
import ir.arash.altafi.sample.data.db.AppDatabase
import ir.arash.altafi.sample.data.db.TestDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTestDetailDao(database: AppDatabase): TestDetailDao {
        return database.testDetailDao()
    }
}