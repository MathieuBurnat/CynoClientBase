package ch.leytto.cynoclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.db.entities.Dog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

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
        /*private val dogName: TextView = itemView.findViewById(R.id.dogName)
        private val dogDate: TextView = itemView.findViewById(R.id.dogDate)
        //private val nameItemView: TextView = itemView.findViewById(R.id.name) //radio button
        //private val nameItemView: TextView = itemView.findViewById(R.id.name) //radio button
        //private val nameItemView: TextView = itemView.findViewById(R.id.name) //radio button
        private val dogColor: TextView = itemView.findViewById(R.id.dogColor)*/

        fun bind(dog: Dog?) {
            nameItemView.text = dog?.noun //+ " " + dog?.lastnamey
            /*dogName.text = dog?.noun
            dogDate.text = dog?.birthdate
            dogColor.text = dog?.color*/
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