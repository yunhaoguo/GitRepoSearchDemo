package com.example.gitreposearchdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitreposearchdemo.api.GithubService
import com.example.gitreposearchdemo.api.RepoCallback
import com.example.gitreposearchdemo.api.RepoSearchResponse
import com.example.gitreposearchdemo.api.search
import com.example.gitreposearchdemo.model.Repo
import com.example.gitreposearchdemo.ui.ReposAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var repos: MutableList<Repo> = mutableListOf()

    private val adapter = ReposAdapter(repos)

    private val service by lazy {
        GithubService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rv.addItemDecoration(decoration)
        btn_search.setOnClickListener {
            search(
                service,
                et_input.text.toString(),
                1,
                50,
                object : RepoCallback {
                    override fun onSuccess(reposNet: List<Repo>) {
                        repos.clear()
                        repos.addAll(reposNet)
                        adapter.notifyDataSetChanged()
                    }
                    override fun onFailure(e: String?) {

                    }
            })
        }

    }
}
