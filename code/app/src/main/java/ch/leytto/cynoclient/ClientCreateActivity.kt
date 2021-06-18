package ch.leytto.cynoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import ch.leytto.cynoclient.db.CynoClientRoomDatabase
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.viewmodels.ClientViewModel
import ch.leytto.cynoclient.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ClientCreateActivity : AppCompatActivity() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { CynoClientRoomDatabase.getDatabase(this,applicationScope) }
    val viewModel: ClientViewModel by viewModels {
        ViewModelFactory((application as CynoClientApplication).clientRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_client_create)
        var sex: Boolean
        sex = true
        // Get UI elements
        var et_firstName = findViewById<EditText>(R.id.client_detail_firstname_plain_text)
        var et_lastName = findViewById<EditText>(R.id.client_detail_lastname_plain_text)
        var et_email = findViewById<EditText>(R.id.client_detail_mail_plain_text)
        var et_street = findViewById<EditText>(R.id.client_detail_street_pain_text)
        var et_locality = findViewById<EditText>(R.id.client_detail_locality_pain_text)
        var et_phone = findViewById<EditText>(R.id.client_detail_phone_plain_text)
        var rb_sexe = findViewById<RadioGroup>(R.id.radioGroupSex)
        var btn_submit = findViewById<Button>(R.id.client_detail_save_button)
        // Set listener whenever the fields is changed
        //------------
        et_firstName.addTextChangedListener(){
            viewModel.setFirstname(it.toString())
        }
        et_lastName.addTextChangedListener(){
            viewModel.setLastname(it.toString())
        }
        et_email.addTextChangedListener(){
            viewModel.setEmail(it.toString())
        }
        et_street.addTextChangedListener(){
            viewModel.setStreet(it.toString())
        }
        et_locality.addTextChangedListener(){
            viewModel.setLocality(it.toString())
        }
        et_phone.addTextChangedListener(){
            viewModel.setPhone(it.toString())
        }
        rb_sexe.setOnCheckedChangeListener{group: RadioGroup?, checkedId: Int ->
            if (checkedId === R.id.client_detail_radio_female) {
                sex = false
            }else if (checkedId === R.id.client_detail_radio_male){
                sex = true
            }else{
                sex = false
            }

        }
        //------------
        btn_submit.setOnClickListener {
            val firstname = et_firstName.text.toString()
            val lastname = et_lastName.text.toString()
            val email = et_email.text.toString()
            var street = et_street.text.toString()
            var locality = et_locality.text.toString()
            var phone = et_phone.text.toString()

            viewModel.insert(Client(0,firstname,lastname,sex,email,phone,street,locality.toInt()))
            Toast.makeText(this@ClientCreateActivity, "L'utilisateur "+et_firstName.text +" "+ et_lastName.text+" a bien été créé.",Toast.LENGTH_LONG).show()
        }
        lifecycleScope.launch {
            viewModel.isSubmitEnabled.collect { value ->
                btn_submit.isEnabled = value
            }
        }

    }

}