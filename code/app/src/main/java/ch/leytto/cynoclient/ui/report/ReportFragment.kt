package ch.leytto.cynoclient.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.ReportListAdapter
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodels.ReportViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

class ReportFragment : Fragment() {

    private val reportViewModel: ReportViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).reportRepository)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_report, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.report_recyclerview)
        val adapter = ReportListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        reportViewModel.AllReports.observe(viewLifecycleOwner) { reports ->
            reports.let { adapter.submitList(it) }
        }
        return root
    }
}