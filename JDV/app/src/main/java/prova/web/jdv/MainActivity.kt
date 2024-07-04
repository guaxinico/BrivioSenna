package prova.web.jdv


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import prova.web.jdv.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var jogadorAtual = "X"

    val tabuleiro = arrayOf(
        arrayOf("A", "B", "C"),
        arrayOf("D", "E", "F"),
        arrayOf("G", "H", "I")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)
    }
        fun buttonClick(view: View) {

            val buttonSelecionado = view as Button

            buttonSelecionado.text = jogadorAtual

            when (buttonSelecionado.id) {
                binding.button1.id -> tabuleiro[0][0] = jogadorAtual
                binding.button2.id -> tabuleiro[0][1] = jogadorAtual
                binding.button3.id -> tabuleiro[0][2] = jogadorAtual
                binding.button4.id -> tabuleiro[1][0] = jogadorAtual
                binding.button5.id -> tabuleiro[1][1] = jogadorAtual
                binding.button6.id -> tabuleiro[1][2] = jogadorAtual
                binding.button7.id -> tabuleiro[2][0] = jogadorAtual
                binding.button8.id -> tabuleiro[2][1] = jogadorAtual
                binding.button9.id -> tabuleiro[2][2] = jogadorAtual
            }

            var vencedor = verificarVencedor(tabuleiro)

            if (!vencedor.isNullOrBlank()) {
                Toast.makeText(this, "Vencedor: " + vencedor, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            if (jogadorAtual.equals("X")) {
                buttonSelecionado.setBackgroundColor(Color.BLUE)
                jogadorAtual = "O"
            } else {
                buttonSelecionado.setBackgroundColor(Color.RED)
                jogadorAtual = "X"
            }
            buttonSelecionado.isEnabled = false
        }

        fun verificarVencedor(tabuleiro: Array<Array<String>>): String? {
            for (i in 0 until 3) {
                if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) {
                    return tabuleiro[i][0]
                }
                if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) {
                    return tabuleiro[0][i]
                }
            }
            if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
                return tabuleiro[0][0]
            }
            if (tabuleiro[0][0] == tabuleiro[1][0] && tabuleiro[1][0] == tabuleiro[2][0]) {
                return tabuleiro[0][2]
            }
            var empate = 0
            for (linha in tabuleiro){
                for (valor in linha){
                    if (valor.equals("X")||valor.equals("O")){
                        empate++
                    }

                }

            }
            return null
        }
}
