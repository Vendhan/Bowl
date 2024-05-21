package di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.AppDatabase
import data.database.dao.BowlDao
import data.database.instantiateImpl
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory
import presentation.detail.DetailViewModel
import presentation.favorite.FavoriteViewModel
import presentation.home.HomeViewModel

actual fun platformModule() =
    module {
        single { Darwin.create() }
        single<AppDatabase> { createRoomDatabase() }
        single<BowlDao> {
            get<AppDatabase>().getBowlDao()
        }
        singleOf(::HomeViewModel)
        factory<DetailViewModel> {
            DetailViewModel(get(), get())
        }
        singleOf(::FavoriteViewModel)
    }

fun createRoomDatabase(): AppDatabase {
    val dbFilePath = NSHomeDirectory() + "/bowl.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
        factory = { AppDatabase::class.instantiateImpl() },
    )
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
