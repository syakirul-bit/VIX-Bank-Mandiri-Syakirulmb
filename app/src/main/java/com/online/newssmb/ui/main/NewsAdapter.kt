package com.online.newssmb.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.online.newssmb.R
import com.online.newssmb.data.response.ArticlesItem
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val listener: (ArticlesItem)-> Unit) :
    RecyclerView.Adapter< NewsAdapter.ViewHolder>() {

    private var news = listOf<ArticlesItem>()

    fun setNews(news: List<ArticlesItem>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(news: ArticlesItem){

            itemView.tvTitle.text = news.title

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
                .into(itemView.ivNews)


            itemView.setOnClickListener {
                listener(news)
            }
        }
    }
}
