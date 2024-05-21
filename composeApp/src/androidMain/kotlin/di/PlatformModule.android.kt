package di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.AppDatabase
import data.database.dao.BowlDao
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import presentation.detail.DetailViewModel
import presentation.favorite.FavoriteViewModel
import presentation.home.HomeViewModel

actual fun platformModule() =
    module {
        single { Android.create() }
        single<AppDatabase> { createRoomDatabase(get()) }
        single<BowlDao> {
            get<AppDatabase>().getBowlDao()
        }
        viewModel {
            HomeViewModel(get())
        }
        viewModel {
            DetailViewModel(get(), get())
        }
        viewModel {
            FavoriteViewModel(get())
        }
    }

fun createRoomDatabase(ctx: Context): AppDatabase {
    val dbFile = ctx.getDatabasePath("bowl.db")
    return Room.databaseBuilder<AppDatabase>(ctx, dbFile.absolutePath)
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
