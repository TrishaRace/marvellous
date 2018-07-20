package com.example.carrerap.marvellous

import com.example.carrerap.marvellous.model.ApiResponse
import com.google.gson.Gson
import java.net.URL


fun readMarvelApi(): ApiResponse? {
    val COMPLETE_URL = "http://gateway.marvel.com/v1/public/characters?ts=1&apikey=bb002cae0025452e0e19ba71896a9b11&hash=75772836ecf8d7ad6adabe834692d8fb"
    var apiResponse: ApiResponse? = null
    doAsync {
        val result = URL(COMPLETE_URL).readText()
        apiResponse = Gson().fromJson(result, ApiResponse::class.java)
    }
    return apiResponse
}


fun doAsync(f: () -> Unit) {
    Thread({ f() }).start()
}

