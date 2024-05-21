package data.service

import data.service.model.BowlResponseModelWrapper
import domain.util.DomainResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class BowlApiImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
) : BowlApi {
    private val token = "3b99bf63-96a5-440c-b9d2-b5bb8d529acd"

    override suspend fun fetchFoodItems(): DomainResult<BowlResponseModelWrapper> {
        return try {
            DomainResult.Success(
                httpClient.get("$baseUrl${Endpoints.FOOD_LIST}") {
                    parameter("alt", "media")
                    parameter("token", token)
                }.body(),
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            DomainResult.Error(e.response.status.description)
        } catch (e: ClientRequestException) {
            // 4xx - responses
            DomainResult.Error(e.response.status.description)
        } catch (e: ServerResponseException) {
            // 5xx - responses
            DomainResult.Error(e.response.status.description)
        } catch (e: Exception) {
            DomainResult.Error(e.message ?: "Something went wrong")
        }
    }
}
