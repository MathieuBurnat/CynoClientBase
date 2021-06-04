package ch.leytto.cynoclient.ui.dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ch.leytto.cynoclient.R

class DogFragment : Fragment() {

    private lateinit var reportViewModel: DogViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        reportViewModel =
                ViewModelProvider(this).get(DogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dog, container, false)
        val textView: TextView = root.findViewById(R.id.text_dog)
        reportViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}