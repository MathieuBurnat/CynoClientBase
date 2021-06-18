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

class ClientInfoActivity : AppCompatActivity() {
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
                findViewById<TextView>(R.id.client_detail_firstname_plain_text).text = clientData.client.firstname

                // Lastname
                findViewById<TextView>(R.id.client_detail_lastname_plain_text).text = clientData.client.lastname

                // Email
                findViewById<TextView>(R.id.client_detail_mail_plain_text).text = clientData.client.email ?: "Non précisé"

                // Phone
                findViewById<TextView>(R.id.client_detail_phone_plain_text).text = clientData.client.phone

                // Street
                findViewById<TextView>(R.id.client_detail_street_pain_text).text = clientData.client.street ?: "Non précisée"

                // Locality
                findViewById<TextView>(R.id.client_detail_locality_pain_text).text = clientData.locality?.noun ?: "Non précisée"

                // Gender
                if (clientData.client.female)
                    findViewById<RadioGroup>(R.id.radioGroupSex).check(R.id.client_detail_radio_female)
                else
                    findViewById<RadioGroup>(R.id.radioGroupSex).check(R.id.client_detail_radio_male)
            }
        }
    }
}