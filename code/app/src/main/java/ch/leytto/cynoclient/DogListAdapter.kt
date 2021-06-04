package ch.leytto.cynoclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.db.entities.Dog

class DogListAdapter : ListAdapter<Dog, DogListAdapter.DogViewHolder>(DogsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameItemView: TextView = itemView.findViewById(R.id.name)

        fun bind(dog: Dog?) {
            nameItemView.text = dog?.noun //+ " " + dog?.lastname
        }

        companion object {
            fun create(parent: ViewGroup): DogViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.dog_adapter, parent, false)
                return DogViewHolder(view)
            }
        }
    }

    class DogsComparator : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.id == newItem.id
        }
    }
}