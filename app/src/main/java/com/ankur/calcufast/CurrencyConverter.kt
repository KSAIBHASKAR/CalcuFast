package com.ankur.calcufast

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Header
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ankur.calcufast.util.ConnectionManager
import org.json.JSONException

class CurrencyConverter : AppCompatActivity() {
    lateinit var spinner1:Spinner
    lateinit var spinner2:Spinner
    lateinit var txtAboutButton:TextView
    lateinit var txtCalcButton:TextView
    lateinit var txtConvertButton: TextView
    lateinit var etEnterAmount:EditText
    lateinit var txtService2:TextView


    var converted_amount=""
    lateinit var txtConversionResult:TextView
    var finalResult=0.0
    var url=""
    var toDisable=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_converter)

        spinner1=findViewById(R.id.spnSpinner1)//----------------spinner code started
        spinner2=findViewById(R.id.spnSpinner2)
        val cur = resources.getStringArray(R.array.currency)
        val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, cur)
        spinner1.adapter = adapter
        spinner2.adapter= adapter
        var spinWord1=""
        var spinWord2=""

        spinner1.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                spinWord1=cur[p2]
                spinWord1=spinWord1.substring(spinWord1.length-3,spinWord1.length)
                fromApi(spinWord1)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        spinner2.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinWord2=cur[p2]
                spinWord2=spinWord2.substring(spinWord2.length-3,spinWord2.length)
                toApi(spinWord2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }//----------------spinner code ended

        txtAboutButton=findViewById(R.id.txtAboutButton)
        txtCalcButton=findViewById(R.id.txtCalcButton)
        txtConversionResult=findViewById(R.id.txtConversionResult)
        txtConvertButton=findViewById(R.id.txtConvertButton)
        etEnterAmount=findViewById(R.id.etEnterAmount)
        txtService2=findViewById(R.id.txtService2)

        txtAboutButton.setOnClickListener {
            val intent=Intent(this@CurrencyConverter,About::class.java)
            startActivity(intent)
            this.finish()
        }//------------button in toolbar when pressed opens about page
        /*txtCalcButton.setOnClickListener {
            val intent=Intent(this@MainActivity,::class.java)
        }*/
        txtCalcButton.setOnClickListener {
            val intent=Intent(this@CurrencyConverter,MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }//------------button in toolbar when pressed opens calculator page

        val queue=Volley.newRequestQueue(this@CurrencyConverter)
        //val url="https://api.getgeoapi.com/v2/currency/convert?amount=100&format=json&from=USD&to=INR"

        if(ConnectionManager().checkConnectivity(this@CurrencyConverter)) {
            //below is the convert button functioning
            txtConvertButton.setOnClickListener {

                hideSoftKeyboard(it)//------hiding keyboard function

                var xxx=url.substring(url.length-3,url.length)
                var amount=etEnterAmount.getText().toString()
                if(amount.equals("")||amount.equals(".")||amount.equals(","))
                {
                    Toast.makeText(this@CurrencyConverter,"Enter Amount",Toast.LENGTH_SHORT).show()
                    amount="0"
                }//checks if amount extracted is not 0 , .
                var amount2=amount.toDouble()

                val occuranceOfM=url.lastIndexOf("m")
                var yyy=url.substring(occuranceOfM+2,occuranceOfM+5)

                if(xxx.equals(yyy))
                {
                    Toast.makeText(this@CurrencyConverter,"Select different Countries / Select From before To",Toast.LENGTH_LONG).show()
                }//--------if spinner1 extracted word is equal to spinner2 word
                else {

                    val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
                        //println("response is $it")
                        //try block to handle api exception
                        try {
                            val data = it.getJSONObject("rates")
                            val data2 = data.getJSONObject(xxx)
                            converted_amount = data2.getString("rate")
                            var converted_amount2 = converted_amount.toDouble()
                            finalResult = converted_amount2 * amount2
                            txtConversionResult.text = finalResult.toString()

                            val dateData=it.getString("updated_date")
                            txtService2.text=dateData.toString()

                        } catch (e: JSONException) {
                            Toast.makeText(this@CurrencyConverter, "Some Unexpected Error Occurred1!", Toast.LENGTH_SHORT).show()
                        }// catch block of jsonexception


                    }, Response.ErrorListener {
                        if (finalResult != 0.0) {
                            Toast.makeText(this@CurrencyConverter, "Some Unexpected Error Occurred2!", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        override fun getHeaders(): MutableMap<String, String> {
                            val headers = HashMap<String, String>()
                            headers["Content-type"] = "application/Json"
                            headers["x-api-key"] = "e73cd0a65f8314b00b633cd8d1d9809fa4120ff7"
                            //headers["token"] = "5419298965184cfd99bfe3b1348f61af"

                            return headers
                        }// sending tokens and stuffs

                    }

                    queue.add(jsonObjectRequest)
                }
            }
        }
        else
        {
            val dialog=AlertDialog.Builder(this@CurrencyConverter)
            dialog.setTitle("Error")
            dialog.setMessage("Internet Connection is not found")
            dialog.setPositiveButton("Open Settings"){text,listener->
                val settingsIntent=Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
            }// if internet connection not found then dialog pops, positive button
            dialog.setNegativeButton("Go back to Calculator"){text,listener->
                intent=Intent(this@CurrencyConverter,MainActivity::class.java)
                onBackPressed()
            }// negative button
            dialog.create()
            dialog.show()
        }
    }

    fun fromApi(fromCurrency:String)
    {
        url="https://api.getgeoapi.com/v2/currency/convert?format=json&from="+fromCurrency
    }// gets the spinner one selected text and adds in url
    fun toApi(toCurrency:String)
    {
        url=url+"&to="+toCurrency

    }// gets the spinner two selected text and adds in url
    override fun onBackPressed() {
        val intent=Intent(this@CurrencyConverter,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }// changing back press function to open calculator when on currency or about screen and finishing closed activity

    fun hideSoftKeyboard(view:View)
    {
        val imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }// function to hide soft keyboard

}