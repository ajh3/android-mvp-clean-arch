package com.aaronhalbert.androidmvpdemo.ui.browse

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aaronhalbert.androidmvpdemo.R
import com.aaronhalbert.androidmvpdemo.ui.detail.DetailActivity
import com.aaronhalbert.androidmvpdemo.ui.model.CoworkerViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import javax.inject.Inject

const val FIRST_NAME = "firstName"
const val LAST_NAME = "lastName"
const val TITLE = "title"
const val AVATAR = "avatar"
const val BIO = "bio"
const val ID = "id"

class BrowseAdapter @Inject constructor() :
    RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var coworkers: List<CoworkerViewModel> = arrayListOf()
    private val onClickListener = View.OnClickListener { v -> startDetailActivity(v) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_coworker, parent, false)

        val viewHolder = ViewHolder(itemView)
        viewHolder.itemView.setOnClickListener(onClickListener)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return coworkers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindData(holder, coworkers[position])

        /* tag the VH with its CoworkerViewModel to easily access data from the OnClickListener */
        holder.itemView.tag = coworkers[position]
    }

    private fun bindData(
        holder: ViewHolder,
        coworker: CoworkerViewModel
    ) {
        Glide.with(holder.itemView.context)
            .load(coworker.avatar)
            .apply(
                RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_person_black_24dp)
            )
            .into(holder.avatar)

        holder.name.text = holder.itemView.context.getString(
            R.string.coworker_full_name,
            coworker.firstName,
            coworker.lastName
        )
        holder.title.text = coworker.title
        holder.id.text = holder.itemView.context.getString(
            R.string.coworker_id,
            coworker.id
        )
    }

    private fun startDetailActivity(v: View) {
        val intent = Intent(v.context, DetailActivity::class.java)
        val tag = v.tag as CoworkerViewModel

        intent.putExtra(FIRST_NAME, tag.firstName)
        intent.putExtra(LAST_NAME, tag.lastName)
        intent.putExtra(TITLE, tag.title)
        intent.putExtra(AVATAR, tag.avatar)
        intent.putExtra(BIO, tag.bio)
        intent.putExtra(ID, tag.id)

        v.context.startActivity(intent)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatar: ImageView = view.findViewById(R.id.item_avatar)
        var name: TextView = view.findViewById(R.id.item_name)
        var title: TextView = view.findViewById(R.id.item_title)
        var id: TextView = view.findViewById(R.id.item_id)
    }
}
