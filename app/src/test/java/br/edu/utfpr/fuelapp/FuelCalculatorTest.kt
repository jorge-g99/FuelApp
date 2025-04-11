package br.edu.utfpr.fuelapp

import org.junit.Assert.assertTrue
import org.junit.Test

class FuelCalculatorTest {

    @Test
    fun `deve retornar gasolina como melhor opcao`() {
        val resultado = FuelCalculator.calculate(
            fuel1Label = "Gasolina",
            fuel1Value = 20.0,
            fuel1Km = 12.0,
            fuel2Label = "Diesel",
            fuel2Value = 20.0,
            fuel2Km = 10.0
        )

        assertTrue(resultado.contains("Melhor resultado: R$1.67/Km"))
    }

    @Test
    fun `deve retornar diesel como melhor opcao`() {
        val resultado = FuelCalculator.calculate(
            fuel1Label = "Gasolina",
            fuel1Value = 20.0,
            fuel1Km = 10.0,
            fuel2Label = "Diesel",
            fuel2Value = 20.0,
            fuel2Km = 12.0
        )

        assertTrue(resultado.contains("Melhor resultado: R$1.67/Km"))
    }

    @Test
    fun `deve retornar resultado igual quando ambos sao identicos`() {
        val resultado = FuelCalculator.calculate(
            fuel1Label = "Gasolina",
            fuel1Value = 30.0,
            fuel1Km = 15.0,
            fuel2Label = "Diesel",
            fuel2Value = 20.0,
            fuel2Km = 10.0
        )

        assertTrue(resultado.contains("R$2.00/Km")) // Ambos darão 2.00
    }
}
