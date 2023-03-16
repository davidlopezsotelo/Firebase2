package com.example.firebase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

private lateinit var analytics: FirebaseAnalytics

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        analytics = Firebase.analytics

        val analityc = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("mensaje", "Integracion de Firebase completa")
        analityc.logEvent("InitScreen", bundle)

        //setup
        setup()

    }

    private fun setup() {

        title = "Autenticacion"

        val botonIniciar = findViewById<Button>(R.id.button_Iniciar)
        val botonregistrar=findViewById<Button>(R.id.button_Registrar)
        val textEmail = findViewById<EditText>(R.id.editTextEmail)
        val textContraseña = findViewById<EditText>(R.id.editTextContraseña)

        //Funciones de boton iniciar
        botonIniciar.setOnClickListener {
            if (textEmail.text.isNotEmpty() && textContraseña.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        textEmail.text.toString(),
                        textContraseña.text.toString()
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                        } else {
                            showAlert()// mensaje de alerta
                        }
                    }
            }

        }//--------------------------------------------------------

        //funciones de boton registrar
        botonregistrar.setOnClickListener{
            if (textEmail.text.isNotEmpty() && textContraseña.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(// cambiar la funcion para sing, el resto es igual que el otro boton
                        textEmail.text.toString(),
                        textContraseña.text.toString()
                    )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                        } else {
                            showAlert()// mensaje de alerta
                        }
                    }
            }
        }



    }

    //creamos un mensaje de alerta
    private fun showAlert(){
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario.")
        builder.setPositiveButton("aceptar",null)
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }//ff

    private fun showHome(email:String,proveedor:ProviderType){

        val homeIntent =Intent(this,HomeActivity::class.java).apply {

            putExtra("email",email)
            putExtra("provider",proveedor.name)
        }
        startActivity(homeIntent)
    }
}