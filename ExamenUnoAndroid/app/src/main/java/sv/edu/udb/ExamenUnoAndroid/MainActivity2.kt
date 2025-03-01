package sv.edu.udb.ExamenUnoAndroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameInput = findViewById<EditText>(R.id.editTextName)
        val salaryInput = findViewById<EditText>(R.id.editTextSalary)
        val resultView = findViewById<TextView>(R.id.textViewResult)
        val calculateButton = findViewById<Button>(R.id.buttonCalculate)

        calculateButton.setOnClickListener {
            val name = nameInput.text.toString()
            val salary = salaryInput.text.toString().toDoubleOrNull()

            if (salary != null && name.isNotEmpty()) {
                val breakdown = calculateDeductions(salary)
                resultView.text = """
                    Nombre: $name
                    Salario Base: $salary
                    Descuento de ISSS: $${String.format("%.2f", breakdown.isss)}
                    Descuento de AFP: $${String.format("%.2f",breakdown.afp)}
                    Valor de Renta: $${String.format("%.2f",breakdown.isr)}
                    Salario Neto: $${String.format("%.2f",breakdown.netSalary)}
                """.trimIndent()
            } else {
                resultView.text = "Ingrese un nombre y un salario válido."
            }
        }
    }

    private fun calculateDeductions(salary: Double): SalaryBreakdown {
        val isss = if (salary * 0.03 > 30.00) 30.00 else salary * 0.03
        val afp = salary * 0.0725
        val taxableSalary = salary - isss - afp
        val isr = when {
            taxableSalary <= 472.00 -> 0.0
            taxableSalary <= 895.24 -> 17.67 + (taxableSalary - 472.00) * 0.10
            taxableSalary <= 2038.10 -> 60.00 + (taxableSalary - 895.24) * 0.20
            else -> 288.57 + (taxableSalary - 2038.10) * 0.30
        }
        val netSalary = salary - isss - afp - isr
        return SalaryBreakdown(isss, afp, isr, netSalary)
    }
}

data class SalaryBreakdown(
    val isss: Double,
    val afp: Double,
    val isr: Double,
    val netSalary: Double
)
