package com.example.gitreposearchdemo.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitreposearchdemo.api.GithubService
import com.example.gitreposearchdemo.api.RepoCallback
import com.example.gitreposearchdemo.api.search


class SearchRepoViewModel : ViewModel() {


    private val service by lazy {
        val s = GithubService.create()
        s
    }

    val repos = MutableLiveData<List<Repo>>()

    fun search(keyword: String) {
        search(
            service,
            keyword,
            1,
            50,
            object : RepoCallback {
                override fun onSuccess(reposNet: List<Repo>) {
                    repos.postValue(reposNet)
                }
            })
    }
}