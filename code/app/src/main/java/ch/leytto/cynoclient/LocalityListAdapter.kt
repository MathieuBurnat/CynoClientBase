package ch.leytto.cynoclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.db.entities.Locality
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class LocalityListAdapter : ListAdapter<Locality, LocalityListAdapter.LocalityViewHolder>(LocalitiesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalityViewHolder {
        return LocalityViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LocalityViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    class LocalityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




        private val nameItemView: TextView = itemView.findViewById(R.id.name)

        fun bind(locality: Locality?) {
            nameItemView.text = locality?.noun
        }

        companion object {
            fun create(parent: ViewGroup): LocalityViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.locality_adapter, parent, false)
                return LocalityViewHolder(view)
            }
        }
    }

    class LocalitiesComparator : DiffUtil.ItemCallback<Locality>() {
        override fun areItemsTheSame(oldItem: Locality, newItem: Locality): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Locality, newItem: Locality): Boolean {
            return oldItem.id == newItem.id
        }
    }
}