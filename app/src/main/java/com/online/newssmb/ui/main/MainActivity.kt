package com.online.newssmb.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.online.newssmb.R
import com.online.newssmb.data.remote.ApiClient
import com.online.newssmb.data.response.ArticlesItem
import com.online.newssmb.data.response.NewsResponse
import com.online.newssmb.ui.detail.DetailActivity
import com.online.newssmb.ui.detail.DetailActivity.Companion.EXTRA_NEWS
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.partial_shimmer.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter {
            //onClick
            moveActivity(it)
        }
        rvNews.layoutManager = LinearLayoutManager(this)

        rvNews.adapter = adapter

    }

    private fun moveActivity(news: ArticlesItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_NEWS, news)
        startActivity(intent)
    }

    private fun getNews() {

        loading(true)
        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                loading(false)
                if (response.isSuccessful) {
                    val articles: List<ArticlesItem> =
                        response.body()?.articles as List<ArticlesItem>
                    adapter.setNews(articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {


            }
        })
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            sfLoading.visibility = View.VISIBLE
            sfLoading.startShimmer()
        } else {
            sfLoading.visibility = View.GONE
            sfLoading.stopShimmer()
        }
    }

}
