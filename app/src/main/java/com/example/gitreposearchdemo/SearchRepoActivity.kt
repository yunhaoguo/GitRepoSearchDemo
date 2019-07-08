package com.example.gitreposearchdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitreposearchdemo.model.Repo
import com.example.gitreposearchdemo.model.SearchRepoViewModel
import com.example.gitreposearchdemo.ui.ReposAdapter
import kotlinx.android.synthetic.main.activity_main.*

class SearchRepoActivity : AppCompatActivity() {

    private var repos: MutableList<Repo> = mutableListOf()

    private val adapter = ReposAdapter(repos)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rv.addItemDecoration(decoration)

        val viewModel = ViewModelProviders.of(this).get(SearchRepoViewModel::class.java)
        btn_search.setOnClickListener {
            viewModel.search(et_input.text.toString())
        }

        viewModel.repos.observe(this, Observer<List<Repo>> {
            repos.clear()
            repos.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }
}
