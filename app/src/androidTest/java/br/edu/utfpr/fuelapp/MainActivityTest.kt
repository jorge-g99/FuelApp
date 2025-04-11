package br.edu.utfpr.fuelapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun deveCalcularGastoPorKm_eExibirResultadoNaTela() {
        // Simulando inputs manuais já que não vamos abrir ListGas nessa simulação
        onView(withId(R.id.fuel_value1)).perform(typeText("12.0"), closeSoftKeyboard())
        onView(withId(R.id.fuel_value2)).perform(typeText("10.0"), closeSoftKeyboard())
        onView(withId(R.id.input_litro_1)).perform(typeText("24.0"), closeSoftKeyboard())
        onView(withId(R.id.input_litro_2)).perform(typeText("20.0"), closeSoftKeyboard())

        // Verifica se o resultado esperado aparece (parcialmente)
        onView(withId(R.id.result)).check(matches(withText(containsString("Melhor resultado"))))
    }
}
