package ru.itmo.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    val math_operation: TextView = findViewById(R.id.math_operation)
    val result_text: TextView = findViewById(R.id.result_text)
    val btn_0: TextView = findViewById(R.id.btn_0)
    val btn_1: TextView = findViewById(R.id.btn_1)
    val btn_2: TextView = findViewById(R.id.btn_2)
    val btn_3: TextView = findViewById(R.id.btn_3)
    val btn_4: TextView = findViewById(R.id.btn_4)
    val btn_5: TextView = findViewById(R.id.btn_5)
    val btn_6: TextView = findViewById(R.id.btn_6)
    val btn_7: TextView = findViewById(R.id.btn_7)
    val btn_8: TextView = findViewById(R.id.btn_8)
    val btn_9: TextView = findViewById(R.id.btn_9)
    val ac_btn: TextView = findViewById(R.id.ac_btn)
    val back_btn: TextView = findViewById(R.id.back_btn)
    val par1_btn: TextView = findViewById(R.id.par1_btn)
    val par2_btn: TextView = findViewById(R.id.par2_btn)
    val div_btn: TextView = findViewById(R.id.div_btn)
    val mult_btn: TextView = findViewById(R.id.mult_btn)
    val plus_btn: TextView = findViewById(R.id.plus_btn)
    val minus_btn: TextView = findViewById(R.id.minus_btn)
    val equal_btn: TextView = findViewById(R.id.equal_btn)
    val dot_btn: TextView = findViewById(R.id.dot_btn)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_0.setOnClickListener { setTextFields("0") }
        btn_1.setOnClickListener { setTextFields("1") }
        btn_2.setOnClickListener { setTextFields("2") }
        btn_3.setOnClickListener { setTextFields("3") }
        btn_4.setOnClickListener { setTextFields("4") }
        btn_5.setOnClickListener { setTextFields("5") }
        btn_6.setOnClickListener { setTextFields("6") }
        btn_7.setOnClickListener { setTextFields("7") }
        btn_8.setOnClickListener { setTextFields("8") }
        btn_9.setOnClickListener { setTextFields("9") }

        minus_btn.setOnClickListener { setTextFields("-") }
        plus_btn.setOnClickListener { setTextFields("+") }
        mult_btn.setOnClickListener { setTextFields("*") }
        div_btn.setOnClickListener { setTextFields("/") }
        par1_btn.setOnClickListener { setTextFields("(") }
        par2_btn.setOnClickListener { setTextFields(")") }

        dot_btn.setOnClickListener {
            val currentText = math_operation.text.toString()

            if (!currentText.endsWith(",") && !currentText.contains(",")) {
                math_operation.append(",")
            }
        }

        ac_btn.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
        }

        back_btn.setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty())
                math_operation.text = str.substring(0, str.length - 1)
            result_text.text = ""
        }

        equal_btn.setOnClickListener {
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble())
                    result_text.text = longRes.toString()
                else
                    result_text.text = result.toString()
            } catch(e:Exception) {
                Log.d("Error", "message:")
            }
        }
    }

    fun setTextFields(str: String) {
        if(result_text.text != "") {
            math_operation.text = result_text.text
            result_text.text = ""
        }
        math_operation.append(str)
    }
}