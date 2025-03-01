package sv.edu.udb.ExamenUnoAndroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private lateinit var display2: TextView
    private var currentInput = ""
    private var lastOperator: String? = null
    private var previousInput = ""
    private var FinalInput = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display = findViewById(R.id.display)
        display2 = findViewById(R.id.display2)

        val button1 = findViewById<Button>(R.id.button_1)
        val button2 = findViewById<Button>(R.id.button_2)
        val button3 = findViewById<Button>(R.id.button_3)
        val button4 = findViewById<Button>(R.id.button_4)
        val button5 = findViewById<Button>(R.id.button_5)
        val button6 = findViewById<Button>(R.id.button_6)
        val button7 = findViewById<Button>(R.id.button_7)
        val button8 = findViewById<Button>(R.id.button_8)
        val button9 = findViewById<Button>(R.id.button_9)
        val button0 = findViewById<Button>(R.id.button_0)
        val buttonClear = findViewById<Button>(R.id.button_clear)
        val buttonPlus = findViewById<Button>(R.id.button_plus)
        val buttonMinus = findViewById<Button>(R.id.button_minus)
        val buttonMultiply = findViewById<Button>(R.id.button_multiply)
        val buttonDivide = findViewById<Button>(R.id.button_divide)
        val buttonSqrt = findViewById<Button>(R.id.button_sqrt)
        val buttonPow = findViewById<Button>(R.id.button_pow)
        val buttonEquals = findViewById<Button>(R.id.button_equals)

        button1.setOnClickListener { appendToCurrentInput("1") }
        button2.setOnClickListener { appendToCurrentInput("2") }
        button3.setOnClickListener { appendToCurrentInput("3") }
        button4.setOnClickListener { appendToCurrentInput("4") }
        button5.setOnClickListener { appendToCurrentInput("5") }
        button6.setOnClickListener { appendToCurrentInput("6") }
        button7.setOnClickListener { appendToCurrentInput("7") }
        button8.setOnClickListener { appendToCurrentInput("8") }
        button9.setOnClickListener { appendToCurrentInput("9") }
        button0.setOnClickListener { appendToCurrentInput("0") }

        buttonClear.setOnClickListener {
            currentInput = ""
            previousInput = ""
            FinalInput = ""
            lastOperator = null
            updateDisplay()
            updateDisplay2()
        }

        buttonPlus.setOnClickListener { setOperator("+") }
        buttonMinus.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonDivide.setOnClickListener { setOperator("/") }
        buttonSqrt.setOnClickListener { setOperator("√") }
        buttonPow.setOnClickListener { setOperator("^") }
        buttonEquals.setOnClickListener { calculateResult() }

    }

    private fun appendToCurrentInput(number: String) {
        currentInput += number
        updateDisplay()
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            previousInput = currentInput
            currentInput = ""
            lastOperator = operator
        }
    }

    private fun updateDisplay() {
        display.text = currentInput
    }
    private fun updateDisplay2() {
       display2.text = FinalInput
    }


    private fun calculateResult() {
        if (previousInput.isNotEmpty() && currentInput.isNotEmpty() && lastOperator != null) {
            val num1 = previousInput.toDouble()
            val num2 = currentInput.toDouble()
            val result = when (lastOperator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> num1 / num2
                "√" -> Math.sqrt(num1)
                "^" -> Math.pow(num1,2.0)
                else -> 0.0
            }
            currentInput = result.toString()
            FinalInput = result.toString()
            previousInput = ""
            lastOperator = null
            updateDisplay2()
        }else if(previousInput.isNotEmpty() && lastOperator != null){
            val num1 = previousInput.toDouble()
            val result = when (lastOperator) {
                "√" -> Math.sqrt(num1)
                "^" -> Math.pow(num1,2.0)
                else -> 0.0
            }
            currentInput = result.toString()
            FinalInput = result.toString()
            previousInput = ""
            lastOperator = null
            updateDisplay2()
        }
    }

}