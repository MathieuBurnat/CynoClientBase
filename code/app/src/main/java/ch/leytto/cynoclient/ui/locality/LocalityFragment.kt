package ch.leytto.cynoclient.ui.locality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.LocalityListAdapter
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodels.LocalityViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

class LocalityFragment : Fragment() {

    private val localityViewModel: LocalityViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).localityRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_locality, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.locality_recyclerview)
        val adapter = LocalityListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        localityViewModel.AllLocalities.observe(viewLifecycleOwner) { localities ->
            localities.let { adapter.submitList(it) }
        }
        return root
    }
}