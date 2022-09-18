package com.online.newssmb.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.online.newssmb.R
import com.online.newssmb.data.response.ArticlesItem
import com.online.newssmb.ui.main.hlmprofil
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val EXTRA_NEWS = "extraNews"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val btnClick: Button = findViewById(R.id.btnToProfil)
        btnClick.setOnClickListener(this)

        val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)

        news?.let {
            initView(it)
        }

    }

    private fun initView(news: ArticlesItem) {
        tvTitle.text = news.title
        tvAuthor.text = news.author
        tvSourceName.text = news.source?.name

        Glide.with(this@DetailActivity)
            .load(news.urlToImage)
            .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
            .into(ivNews)

        btnToNews.setOnClickListener {
            openWebPage(news.url)
        }
    }

    private fun openWebPage(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                R.id.btnToProfil -> {
                    val pindahIntent = Intent(this, hlmprofil::class.java)
                    startActivity(pindahIntent)
                }
            }
        }

    }
}
