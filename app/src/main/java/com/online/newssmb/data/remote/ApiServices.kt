package com.online.newssmb.data.remote

import com.online.newssmb.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET ("top-headlines?country=id&apiKey=bd826e94856e49cd9136c7f5a73c3e74")
    fun getNews(): Call <NewsResponse>

}
