package com.everis.bootcamp.threading

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class AstrosRepository {

    //011 - Criar função para carregar os astronautas
    fun loadData(): AstrosResult?{
        val client = OkHttpClient()
        val request = Request.Builder().url("http://api.open-notify.org/astros.json").build()
        val response = client.newCall(request).execute()

        return parseJsonToResult(response.body?.string())
    }

    //012 - Criar função para converter o json
    fun parseJsonToResult(body: String?) = Gson().fromJson(body, AstrosResult::class.java)
}