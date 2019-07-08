package com.example.gitreposearchdemo.api

import com.example.gitreposearchdemo.model.Repo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


fun search(service: GithubService,
           query: String,
           page: Int,
           itemPerPage: Int,
           callback: RepoCallback) {
    service.searchRepos(query, page, itemPerPage)
        .enqueue(object : Callback<RepoSearchResponse> {
            override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {
                callback.onFailure(t.message)
            }

            override fun onResponse(call: Call<RepoSearchResponse>, response: Response<RepoSearchResponse>) {
                if (response.isSuccessful) {
                    val repoNet = response.body()?.items ?: emptyList()
                    callback.onSuccess(repoNet)
                } else {
                    callback.onFailure("Response not success")
                }

            }
        })
}

interface RepoCallback {
    fun onSuccess(reposNet: List<Repo>)
    fun onFailure(e: String?) {
        println(e)
    }
}


interface GithubService {

    @GET("search/repositories?sort=stars")
    fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemPerPage: Int
    ): Call<RepoSearchResponse>

    companion object {
        private const val BASE_URL = "https://api.github.com"

        fun create(): GithubService {
            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService::class.java)
        }
    }
}