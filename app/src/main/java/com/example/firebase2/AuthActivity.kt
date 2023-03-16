package com.example.firebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

private lateinit var analytics: FirebaseAnalytics

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
       analytics = Firebase.analytics

       val analityc=FirebaseAnalytics.getInstance(this )
        val bundle=Bundle()
        bundle.putString("mensaje","Integracion de Firebase completa")
        analityc.logEvent("InitScreen",bundle)

    }



}