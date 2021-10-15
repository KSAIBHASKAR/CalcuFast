package com.ankur.calcufast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btnClear: TextView
    lateinit var btnBackSpace: TextView
    lateinit var btnPower: TextView
    lateinit var btnDivision: TextView
    lateinit var btnMultiplication: TextView
    lateinit var btnSubstraction: TextView
    lateinit var btnAddition: TextView
    lateinit var btnEquals: Button
    lateinit var btnZeroZero:TextView
    lateinit var btnDecimal: TextView
    lateinit var btnZero: TextView
    lateinit var btnOne: TextView
    lateinit var btnTwo:TextView
    lateinit var btnThree:TextView
    lateinit var btnFour:TextView
    lateinit var btnFive:TextView
    lateinit var btnSix:TextView
    lateinit var btnSeven:TextView
    lateinit var btnEight:TextView
    lateinit var btnNine:TextView
    //lateinit var btnChoose1:TextView
    //lateinit var btnChoose2:TextView
    lateinit var txtAboutButton:TextView
    lateinit var txtCalcButton:TextView
    lateinit var txtCurrButton:TextView

    lateinit var txtEditScreen: TextView
    var res:Int=0
    var decCounter:Int=0

    var expression:String=""
    var result1:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClear=findViewById(R.id.btnClear)
        btnBackSpace=findViewById(R.id.btnBackSpace)
        btnPower=findViewById(R.id.btnPower)
        btnDivision=findViewById(R.id.btnDivision)
        btnMultiplication=findViewById(R.id.btnMultiplication)
        btnSubstraction=findViewById(R.id.btnSubstraction)
        btnAddition=findViewById(R.id.btnAddition)
        btnEquals=findViewById(R.id.btnEquals)
        btnZeroZero=findViewById(R.id.btnZeroZero)
        btnDecimal=findViewById(R.id.btnDecimal)
        btnZero=findViewById(R.id.btnZero)
        btnOne=findViewById(R.id.btnOne)
        btnTwo=findViewById(R.id.btnTwo)
        btnThree=findViewById(R.id.btnThree)
        btnFour=findViewById(R.id.btnFour)
        btnFive=findViewById(R.id.btnFive)
        btnSix=findViewById(R.id.btnSix)
        btnSeven=findViewById(R.id.btnSeven)
        btnEight=findViewById(R.id.btnEight)
        btnNine=findViewById(R.id.btnNine)
        //btnChoose1=findViewById(R.id.btnChoose1)
        //btnChoose2=findViewById(R.id.btnChoose2)
        txtEditScreen=findViewById(R.id.txtEditScreen)

        txtCalcButton=findViewById(R.id.txtCalcButton)
        txtCurrButton=findViewById(R.id.txtCurrButton)
        txtAboutButton=findViewById(R.id.txtAboutButton)

        btnZero.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "0"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnOne.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "1"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnTwo.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "2"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnThree.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "3"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnFour.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "4"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnFive.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "5"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnSix.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "6"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnSeven.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "7"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnEight.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "8"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnNine.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "9"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnClear.setOnClickListener {
                expression = ""
                txtEditScreen.setText(expression)
        }
        btnBackSpace.setOnClickListener {
                var expressionLength = expression.length
                if (expression == "") {

                } else {
                    var subExpression = expression.substring(0, expressionLength - 1)
                    expression = subExpression
                    txtEditScreen.setText(expression)
                }
        }
        btnPower.setOnClickListener {
            if(expression.length<30) {
                if (expression != "") {
                    decCounter = 0
                    expression = expression + "^"
                    txtEditScreen.setText(expression)
                }
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnDivision.setOnClickListener {
            if(expression.length<30) {
                if (expression != "") {
                    decCounter = 0
                    expression = expression + "/"
                    txtEditScreen.setText(expression)
                }
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnMultiplication.setOnClickListener {
            if(expression.length<30) {
                if (expression != "") {
                    decCounter = 0
                    expression = expression + "*"
                    txtEditScreen.setText(expression)
                }
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnAddition.setOnClickListener {
            if(expression.length<30) {
                if (expression != "") {
                    decCounter = 0
                    expression = expression + "+"
                    txtEditScreen.setText(expression)
                }
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnSubstraction.setOnClickListener {
            if(expression.length<30) {
                if (expression != "") {
                    decCounter = 0
                    expression = expression + "-"
                    txtEditScreen.setText(expression)
                }
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnZeroZero.setOnClickListener {
            if(expression.length<30) {
                expression = expression + "00"
                txtEditScreen.setText(expression)
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        btnDecimal.setOnClickListener {
            if(expression.length<30) {
                if (decCounter == 0) {
                    if (expression != "") {
                        decCounter = 1
                        expression = expression + "."
                        txtEditScreen.setText(expression)
                    }
                }
            }
            else
            {
                Toast.makeText(this@MainActivity,"Reached the maximum number of digits",Toast.LENGTH_SHORT).show()
            }
        }
        /*btnChoose1.setOnClickListener {
        }
        btnChoose2.setOnClickListener {
        }*/
        btnEquals.setOnClickListener {
            decCounter=0
            if(expression!="")
            {
                try {
                    var txt = ExpressionBuilder(expression).build()
                    result1 = txt.evaluate()
                    txtEditScreen.text = result1.toString()
                    expression=result1.toString()
                }// using dependency to calculate
                catch (e: Exception)
                {
                    txtEditScreen.text="Error"
                }
            }
        }

        txtAboutButton.setOnClickListener {
            val intent=Intent(this@MainActivity,About::class.java)
            startActivity(intent)
            this.finish()
        }// when about button in toolbar is clicked about page opens
        /*txtCalcButton.setOnClickListener {
            val intent=Intent(this@MainActivity,::class.java)
        }*/
        txtCurrButton.setOnClickListener {
            val intent=Intent(this@MainActivity,CurrencyConverter::class.java)
            startActivity(intent)
            this.finish()
        }//when currency button in toolbar is clicked currency page opens

    }


}