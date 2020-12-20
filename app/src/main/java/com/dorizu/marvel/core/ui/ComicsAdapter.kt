package com.dorizu.marvel.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dorizu.marvel.R
import com.dorizu.marvel.core.domain.model.Comic
import kotlinx.android.synthetic.main.item_comic.view.*

class ComicsAdapter: RecyclerView.Adapter<ComicsAdapter.ListViewHolder>() {

    private var listData = ArrayList<Comic>()
    var onItemClick: ((Comic) -> Unit)? = null

    fun setData(newList: List<Comic>?){
        if (newList == null) return
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false))


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Comic){
            with(itemView){
                Glide.with(itemView.context)
                    .load(data.thumbnail)
                    .into(img_thumnail)
                tv_judul.text = data.title
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}