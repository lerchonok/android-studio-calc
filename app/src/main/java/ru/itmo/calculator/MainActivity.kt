package ru.itmo.calculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    lateinit var math_operation: TextView
    lateinit var result_text: TextView
    lateinit var btn_0: TextView
    lateinit var btn_1: TextView
    lateinit var btn_2: TextView
    lateinit var btn_3: TextView
    lateinit var btn_4: TextView
    lateinit var btn_5: TextView
    lateinit var btn_6: TextView
    lateinit var btn_7: TextView
    lateinit var btn_8: TextView
    lateinit var btn_9: TextView
    lateinit var ac_btn: TextView
    lateinit var back_btn: TextView
    lateinit var par1_btn: TextView
    lateinit var par2_btn: TextView
    lateinit var div_btn: TextView
    lateinit var mult_btn: TextView
    lateinit var plus_btn: TextView
    lateinit var minus_btn: TextView
    lateinit var equal_btn: TextView
    lateinit var dot_btn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        math_operation = findViewById(R.id.math_operation)
        result_text = findViewById(R.id.result_text)
        btn_0 = findViewById(R.id.btn_0)
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)
        btn_4 = findViewById(R.id.btn_4)
        btn_5 = findViewById(R.id.btn_5)
        btn_6 = findViewById(R.id.btn_6)
        btn_7 = findViewById(R.id.btn_7)
        btn_8 = findViewById(R.id.btn_8)
        btn_9 = findViewById(R.id.btn_9)
        ac_btn = findViewById(R.id.ac_btn)
        back_btn = findViewById(R.id.back_btn)
        par1_btn = findViewById(R.id.par1_btn)
        par2_btn = findViewById(R.id.par2_btn)
        div_btn = findViewById(R.id.div_btn)
        mult_btn = findViewById(R.id.mult_btn)
        plus_btn = findViewById(R.id.plus_btn)
        minus_btn = findViewById(R.id.minus_btn)
        equal_btn = findViewById(R.id.equal_btn)
        dot_btn = findViewById(R.id.dot_btn)
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

            if (!currentText.endsWith(".") && !currentText.contains(".")) {
                math_operation.append(".")
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