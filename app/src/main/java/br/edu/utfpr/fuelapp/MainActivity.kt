package br.edu.utfpr.fuelapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private lateinit var btSearch1 : Button
    private lateinit var btSearch2 : Button

    private lateinit var tiFuel1 : TextInputLayout
    private lateinit var etFuel1 : TextInputEditText
    private lateinit var tiFuel2 : TextInputLayout
    private lateinit var etFuel2 : TextInputEditText

    private lateinit var etValue1 : TextInputEditText
    private lateinit var etValue2 : TextInputEditText
    private lateinit var tvResult : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSearch1 = findViewById(R.id.btSearch1)
        btSearch2 = findViewById(R.id.btSearch2)
        tiFuel1 = findViewById(R.id.fuel_edit1)
        etFuel1 = findViewById(R.id.fuel_value1)
        tiFuel2 = findViewById(R.id.fuel_edit2)
        etFuel2 = findViewById(R.id.fuel_value2)
        etValue1 = findViewById(R.id.input_litro_1)
        etValue2 = findViewById(R.id.input_litro_2)
        tvResult = findViewById(R.id.result)

        btSearch1.setOnClickListener {view ->
            btSearchOnClick(view)
        }

        btSearch2.setOnClickListener {view ->
            btSearchOnClick(view)
        }

        etValue1.addTextChangedListener(SimpleTextWatcher {
            handleTextInput()
        })

        etValue2.addTextChangedListener(SimpleTextWatcher {
            handleTextInput()
        })

    }

    private fun btSearchOnClick(view: View) {
        val intent = Intent(this, ListGas::class.java)
        intent.putExtra("buttonId", view.id)
        getResult.launch( intent )
    }

    private val getResult = registerForActivityResult( ActivityResultContracts.StartActivityForResult() ) {

        if ( it.resultCode == RESULT_OK )  {
            if ( it.data != null ) {
                val retorno = it.data?.getDoubleExtra("fuelReturn", 0.0)
                val name = it.data?.getStringExtra("fuelName")
                val buttonId = it.data?.getIntExtra("buttonId", 0)
                if (name != null){
                    when (buttonId) {
                        R.id.btSearch1 -> {
                            tiFuel1.hint = name
                            etFuel1.setText( retorno.toString() )
                        }
                        R.id.btSearch2 -> {
                            tiFuel2.hint = name
                            etFuel2.setText( retorno.toString() )
                        }
                    }
                }
            }
        }
    }

    private fun handleTextInput() {
        try {
            val label1 = tiFuel1.hint?.toString().orEmpty()
            val label2 = tiFuel2.hint?.toString().orEmpty()
            val fuel1 = etFuel1.text?.toString()?.toDoubleOrNull()
            val fuel2 = etFuel2.text?.toString()?.toDoubleOrNull()
            val value1 = etValue1.text?.toString()?.toDoubleOrNull()
            val value2 = etValue2.text?.toString()?.toDoubleOrNull()

            if (fuel1 != null && fuel1 > 0 &&
                fuel2 != null && fuel2 > 0 &&
                value1 != null && value1 > 0 &&
                value2 != null && value2 > 0) {

                val result = FuelCalculator.calculate(label1, value1, fuel1, label2, value2, fuel2)
                tvResult.text = result
            } else {
                tvResult.text = "Preencha todos os campos acima!"
            }
        } catch (e: NumberFormatException) {
            tvResult.text = "Insira valores numéricos válidos"
        }
    }
}

class SimpleTextWatcher(private val afterTextChanged: (Editable?) -> Unit) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        afterTextChanged.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Não é necessário implementar
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Não é necessário implementar
    }
}