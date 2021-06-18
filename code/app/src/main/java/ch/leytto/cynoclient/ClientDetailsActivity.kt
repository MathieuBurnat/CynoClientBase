package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ch.leytto.cynoclient.ClientListAdapter
import ch.leytto.cynoclient.viewmodels.ClientViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientDetailsActivity : AppCompatActivity() {
    private val clientViewModel: ClientViewModel by viewModels {
        ViewModelFactory((this.application as CynoClientApplication).clientRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_client_details)


        val ref = this
        lifecycleScope.launch(Dispatchers.IO) {
            val clientData = clientViewModel.getById(intent.getStringExtra("client_id")!!)

            ref.runOnUiThread {
                title = "Détails de ${clientData.client.firstname} ${clientData.client.lastname}"

                // Firstname
                findViewById<EditText>(R.id.client_detail_firstname_plain_text).setText(clientData.client.firstname)

                // Lastname
                findViewById<EditText>(R.id.client_detail_lastname_plain_text).setText(clientData.client.lastname)

                // Email
                findViewById<EditText>(R.id.client_detail_mail_plain_text).setText(clientData.client.email ?: "Non précisé")

                // Phone
                findViewById<EditText>(R.id.client_detail_phone_plain_text).setText(clientData.client.phone)

                // Street
                findViewById<EditText>(R.id.client_detail_street_pain_text).setText(clientData.client.street ?: "Non précisée")

                // Locality
                findViewById<EditText>(R.id.client_detail_locality_pain_text).setText(clientData.locality?.noun ?: "Non précisée")

                // Gender
                if (clientData.client.female)
                    findViewById<EditText>(R.id.client_detail_radio_female).setText("Femme")
                else
                    findViewById<EditText>(R.id.client_detail_radio_male).setText("Homme")
            }
        }
    }
}