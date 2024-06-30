package br.edu.utfpr.fuelapp

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListGas : AppCompatActivity() {

    private lateinit var lvFuel : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_gas)

        lvFuel = findViewById(R.id.lvFuel)

        lvFuel.setOnItemClickListener { parent, _, position, _ ->
            val codSelecionado = position + 1
            val fuelName = parent.getItemAtPosition(position).toString()
            intent.putExtra( "fuelReturn", onFuelSelected(codSelecionado) )
            intent.putExtra("fuelName", fuelName)
            setResult( RESULT_OK, intent )
            finish()
        }
    }

    private fun onFuelSelected(fuel: Number): Double {
        return when (fuel) {
            1 -> 12.0
            2 -> 10.0
            3 -> 11.0
            4 -> 9.0
            else -> 0.0
        }
    }
}