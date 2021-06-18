package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ch.leytto.cynoclient.viewmodels.ClientViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientDeleteActivity : AppCompatActivity() {
    private val clientViewModel: ClientViewModel by viewModels {
        ViewModelFactory((this.application as CynoClientApplication).clientRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_customer)

        val ref = this
        lifecycleScope.launch(Dispatchers.IO) {
            val clientData = clientViewModel.getById(intent.getStringExtra("client_id")!!)
        }
    }
}