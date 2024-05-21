package di

import data.service.BowlApi
import data.service.BowlApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/tomatoo-a85d6.appspot.com/o/"

val networkModule =
    module {
        single<BowlApi> {
            BowlApiImpl(
                get(),
                BASE_URL,
            )
        }

        single { createJson() }

        single {
            createHttpClient(
                get(),
                get(),
                enableNetworkLogs = true,
            )
        }
    }

fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean,
) = HttpClient(httpClientEngine) {
    install(HttpTimeout) {
        val timeout = 30 * 1000L
        connectTimeoutMillis = timeout
        requestTimeoutMillis = timeout
        socketTimeoutMillis = timeout
    }
    install(ContentNegotiation) {
        json(json)
    }
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
}

fun createJson() =
    Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }
