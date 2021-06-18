package ch.leytto.cynoclient.ui.dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.leytto.cynoclient.DogListAdapter
import ch.leytto.cynoclient.CynoClientApplication
import ch.leytto.cynoclient.R
import ch.leytto.cynoclient.viewmodels.DogViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory

class DogFragment : Fragment() {

    private val dogViewModel: DogViewModel by viewModels {
        ViewModelFactory((requireActivity().application as CynoClientApplication).dogRepository)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dog, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.dog_recyclerview)
        val adapter = DogListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        dogViewModel.allDogs.observe(viewLifecycleOwner) { dogs ->
            dogs.let { adapter.submitList(it) }
        }

        return root
    }
}