package br.edu.utfpr.fuelapp

import java.math.BigDecimal
import java.math.RoundingMode

data class FuelResult(
    val label: String,
    val value: Double,
    val km: Double,
    val costPerKm: BigDecimal
)

object FuelCalculator {
    fun calculate(fuel1Label: String, fuel1Value: Double, fuel1Km: Double,
                  fuel2Label: String, fuel2Value: Double, fuel2Km: Double): String {

        val spent1 = BigDecimal(fuel1Value / fuel1Km).setScale(2, RoundingMode.HALF_UP)
        val spent2 = BigDecimal(fuel2Value / fuel2Km).setScale(2, RoundingMode.HALF_UP)
        val betterSpent = if (spent1 < spent2) spent1 else spent2

        return """
            Gasto por $fuel1Label: R$$fuel1Value / $fuel1Km Km = $spent1

            Gasto por $fuel2Label: R$$fuel2Value / $fuel2Km Km = $spent2

            Melhor resultado: R$$betterSpent/Km
        """.trimIndent()
    }
}
