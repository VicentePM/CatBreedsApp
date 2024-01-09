package com.example.catbreedsapp.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.catbreedsapp.data.models.breedList.Breed
import com.example.catbreedsapp.databinding.HolderBreedBinding

class breedsAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<breedsAdapter.Holder>(), Filterable {

    private val breedList = ArrayList<Breed>()
    private var breedListCopy = ArrayList<Breed>()

    interface OnItemClickListener {
        fun onItemClick(breed: Breed)
    }

    inner class Holder(val binding: HolderBreedBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderBreedBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return breedListCopy.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val breed: Breed = breedListCopy[position]
        holder.binding.tvName.text = breed.name
        holder.binding.tvOrigin.text = breed.origin

        holder.itemView.setOnClickListener{
            listener.onItemClick(breed)
        }
    }

    fun update(list: List<Breed>){
        breedList.clear()
        breedList.addAll(list)
        breedListCopy.clear()
        breedListCopy.addAll(list)
        notifyDataSetChanged()
    }

    fun shortList(short: Boolean){
        if(short){
            breedList.sortBy { it.name }
            breedListCopy.sortBy { it.name }
        } else {
            breedList.sortByDescending { it.name }
            breedListCopy.sortByDescending { it.name }
        }
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charFilter = p0.toString()
                if(charFilter.isEmpty()){
                    breedListCopy = breedList
                } else {
                    breedListCopy = breedList.filter {
                        it.name?.lowercase()!!.contains(charFilter.lowercase())
                    } as ArrayList<Breed>
                }

                val filterResults = FilterResults()
                filterResults.values = breedListCopy
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                breedListCopy = p1?.values as ArrayList<Breed>
                notifyDataSetChanged()
            }

        }
    }
}