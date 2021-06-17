package ch.leytto.cynoclient.ui.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.ServiceListAdapter
import ch.leytto.cynoclient.ui.service.ServiceViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

class ServiceFragment : Fragment() {

    private val serviceViewModel: ServiceViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).serviceRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_service, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.service_recyclerview)
        val adapter = ServiceListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        serviceViewModel.AllServices.observe(viewLifecycleOwner) { services ->
            services.let { adapter.submitList(it) }
        }
        return root
    }
}