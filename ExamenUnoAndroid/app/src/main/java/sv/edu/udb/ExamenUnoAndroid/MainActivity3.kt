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

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "DefaultLocale", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nameInput = findViewById<EditText>(R.id.editTextName)
        val grade1Input = findViewById<EditText>(R.id.editValue1)
        val grade2Input = findViewById<EditText>(R.id.editValue2)
        val grade3Input = findViewById<EditText>(R.id.editValue3)
        val grade4Input = findViewById<EditText>(R.id.editValue4)
        val grade5Input = findViewById<EditText>(R.id.editValue5)
        val resultView = findViewById<TextView>(R.id.textViewResult)
        val calculateButton = findViewById<Button>(R.id.buttonCalculate)

        calculateButton.setOnClickListener {
            val name = nameInput.text.toString()
            val grade1 = grade1Input.text.toString().toDoubleOrNull()

            val grade2 = grade2Input.text.toString().toDoubleOrNull()

            val grade3 = grade3Input.text.toString().toDoubleOrNull()

            val grade4 = grade4Input.text.toString().toDoubleOrNull()

            val grade5 = grade5Input.text.toString().toDoubleOrNull()
                if (grade1 != null && grade2 != null && grade3 != null && grade4 != null && grade5 != null && name.isNotEmpty()) {
                    if (grade1 > 0 && grade1 <= 10 && grade2 > 0 && grade2 <= 10 && grade3 > 0 && grade3 <= 10
                        && grade4 > 0 && grade4 <= 10 && grade5 > 0 && grade5 <= 10) {
                val breakdown = calculateDeductions(grade1, grade2, grade3, grade4, grade5)
                        if(breakdown.netGrades >= 6){
                            resultView.text = """
                    Nombre: $name
                    Su promedio de notas es: ${String.format("%.2f", breakdown.netGrades)}
                    Estado: Aprobado
                """.trimIndent()
                        }else{
                            resultView.text = """
                    Nombre: $name
                    Su promedio de notas es: ${String.format("%.2f", breakdown.netGrades)}
                    Estado: Reprobado
                """.trimIndent()
                        }
                } else {
                resultView.text = "Ingrese un nombre y notas válidas."
                }
            }
        }
    }

    private fun calculateDeductions(grade1: Double, grade2 :Double, grade3 :Double,grade4 :Double,grade5 :Double): GradesBreakdown {
        val netGrades = grade1*0.15 + grade2*0.15 + grade3*0.2 + grade4*0.25 +grade5*0.25
        return GradesBreakdown(netGrades)
    }
}
data class GradesBreakdown(
    val netGrades: Double
)

