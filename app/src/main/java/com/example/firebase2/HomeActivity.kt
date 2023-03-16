package com.example.firebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

//creamos enum class

enum class ProviderType{
    BASIC
}


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setup
        val bundle=intent.extras
        val email=bundle?.getString("email")
        val provider:String?=bundle?.getString("provider")

        setup(email?:"",provider?:"")
    }

    private fun setup(email:String,provider:String){

        val textEmail = findViewById<TextView>(R.id.editTextTextEmailAddress)
        val providerTextView=findViewById<TextView>(R.id.providerTextView)
        val BotonSalir=findViewById<Button>(R.id.button_cerrar)

        title="Inicio"
        textEmail.text= email
        providerTextView.text=provider

        BotonSalir.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }




    }
}