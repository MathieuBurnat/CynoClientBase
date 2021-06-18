package ch.leytto.cynoclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.db.entities.Consultation
import ch.leytto.cynoclient.db.entities.Service
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ServiceListAdapter : ListAdapter<Service, ServiceListAdapter.ServiceViewHolder>(ServicesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        return ServiceViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




        private val nameItemView: TextView = itemView.findViewById(R.id.name)

        fun bind(service: Service?) {
            nameItemView.text = service?.moment
        }

        companion object {
            fun create(parent: ViewGroup): ServiceViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.service_adapter, parent, false)
                return ServiceViewHolder(view)
            }
        }
    }

    class ServicesComparator : DiffUtil.ItemCallback<Service>() {
        override fun areItemsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Service, newItem: Service): Boolean {
            return oldItem.id == newItem.id
        }
    }
}