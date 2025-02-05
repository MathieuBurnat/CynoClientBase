package ch.leytto.cynoclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.db.entities.Client

class ClientListAdapter : ListAdapter<Client, ClientListAdapter.ClientViewHolder>(ClientsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameItemView: TextView = itemView.findViewById(R.id.name)
        private val buttonDetail: ImageButton = itemView.findViewById(R.id.button_client_details)
        //private val nameDetailItemView: EditText = itemView.findViewById(R.id.client_detail_name_plain_text)

        fun bind(client: Client?) {
            nameItemView.text = client?.firstname + " " + client?.lastname
            buttonDetail.tag = client?.id
            //nameDetailItemView.setText(client?.firstname + " " + client?.lastname, TextView.BufferType.EDITABLE)
        }

        companion object {
            fun create(parent: ViewGroup): ClientViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.client_adapter, parent, false)
                return ClientViewHolder(view)
            }
        }
    }

    class ClientsComparator : DiffUtil.ItemCallback<Client>() {
        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem.id == newItem.id
        }
    }
}