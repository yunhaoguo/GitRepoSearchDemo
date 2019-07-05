package com.example.gitreposearchdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitreposearchdemo.R
import com.example.gitreposearchdemo.model.Repo

class ReposAdapter(val repos: List<Repo>) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = repos[position].name
        holder.tvStar.text = repos[position].stars.toString()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tv_repo_name)
        val tvStar = itemView.findViewById<TextView>(R.id.tv_repo_star)
    }
}