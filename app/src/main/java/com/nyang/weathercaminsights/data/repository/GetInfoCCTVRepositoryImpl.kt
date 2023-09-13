package com.nyang.weathercaminsights.data.repository

import android.util.Log
import com.nyang.weathercaminsights.data.service.GetInfoCCTVManager
import com.nyang.weathercaminsights.domain.model.RequestCCTV
import com.nyang.weathercaminsights.domain.model.ResponseCCTV
import com.nyang.weathercaminsights.domain.repository.GetInfoCCTVRepository

class GetInfoCCTVRepositoryImpl : GetInfoCCTVRepository {
    override suspend fun getInfoCCTV(requestCCTV: RequestCCTV): ResponseCCTV {

        val service = GetInfoCCTVManager.getService()
        var result = ResponseCCTV()

        val data = service.getInfoCCTV(
            apiKey = requestCCTV.apiKey,
            type = requestCCTV.type,
            cctvType = requestCCTV.cctvType,
            minX = requestCCTV.minX,
            maxX = requestCCTV.maxX,
            minY = requestCCTV.minY,
            maxY = requestCCTV.maxY,
            getType = requestCCTV.getType
        )

        when (data.isSuccessful) {
            true -> {
                result = data.body() ?: ResponseCCTV()
                Log.d("Ssssss", data.body().toString())

            }

            else -> {
                Log.d("Failure", "???")
            }
        }
        /*.enqueue(object : Callback<ResponseCCTV> {
            override fun onResponse(
                call: Call<ResponseCCTV>,
                response: Response<ResponseCCTV>
            ) {
                Log.d("Success", response.body().toString())

                response.body()?.let {
                    result = it
                }
            }

            override fun onFailure(
                call: Call<ResponseCCTV>,
                t: Throwable
            ) {
                Log.d("Failure", t.localizedMessage ?: "bbb")
            }

        })*/

        return result
    }
}