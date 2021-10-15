package com.ankur.calcufast

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class About : AppCompatActivity() {
    lateinit var txtCurrButton:TextView
    lateinit var txtCalcButton:TextView
    lateinit var txtContact:TextView
    lateinit var txtSupport:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        txtCurrButton=findViewById(R.id.txtCurrButton)
        txtCalcButton=findViewById(R.id.txtCalcButton)
        txtContact=findViewById(R.id.txtContact)
        txtSupport=findViewById(R.id.txtSupport)

        txtCalcButton.setOnClickListener {
            val intent=Intent(this@About, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        txtCurrButton.setOnClickListener {
            val intent= Intent(this@About, CurrencyConverter::class.java)
            startActivity(intent)
            this.finish()
        }
        txtContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val recipients = arrayOf("userbotgl65@gmail.com")
            intent.putExtra(Intent.EXTRA_EMAIL, recipients)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject text here...")
            intent.putExtra(Intent.EXTRA_TEXT, "Body of the content here...")
            intent.putExtra(Intent.EXTRA_CC, "mailcc@gmail.com")
            intent.type = "text/html"
            intent.setPackage("com.google.android.gm")
            startActivity(Intent.createChooser(intent, "Send mail"))
        }
        txtSupport.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://paytm.me/uVp-9aj"))
            startActivity(browserIntent)
        }

    }

    override fun onBackPressed() {
        val intent=Intent(this@About, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}