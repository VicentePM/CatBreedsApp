package com.example.catbreedsapp.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catbreedsapp.R
import com.example.catbreedsapp.data.models.votesList.Votes
import com.example.catbreedsapp.databinding.HolderVotesBinding

class votesAdapter(val listener: OnItemCLickListener): RecyclerView.Adapter<votesAdapter.Holder>() {

    interface OnItemCLickListener {
        fun onItemClick(vote: Votes)
    }

    private val voteList = ArrayList<Votes>()

    inner class Holder(val binding: HolderVotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderVotesBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return voteList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val vote: Votes = voteList[position]

        Glide.with(holder.itemView).load(vote.image?.url).into(holder.binding.imageView2)
        holder.binding.tvCreatedAt.text = vote.createdAt

        if(vote.value == 1){
            holder.binding.imageView3.setImageResource(R.drawable.baseline_thumb_up_24)
        } else {
            holder.binding.imageView3.setImageResource(R.drawable.baseline_thumb_down_24)
        }

        holder.binding.imageView4.setOnClickListener {
            listener.onItemClick(vote)
        }
    }

    fun update(list: List<Votes>) {
        voteList.clear()
        voteList.addAll(list)
        notifyDataSetChanged()
    }

}