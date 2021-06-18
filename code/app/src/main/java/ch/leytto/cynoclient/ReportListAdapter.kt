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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ReportListAdapter : ListAdapter<Consultation, ReportListAdapter.ReportViewHolder>(ReportsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




        private val nameItemView: TextView = itemView.findViewById(R.id.name)

        fun bind(report: Consultation?) {
            nameItemView.text = report?.situation
        }

        companion object {
            fun create(parent: ViewGroup): ReportViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.report_adapter, parent, false)
                return ReportViewHolder(view)
            }
        }
    }

    class ReportsComparator : DiffUtil.ItemCallback<Consultation>() {
        override fun areItemsTheSame(oldItem: Consultation, newItem: Consultation): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Consultation, newItem: Consultation): Boolean {
            return oldItem.id == newItem.id
        }
    }
}