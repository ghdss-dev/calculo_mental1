package cbersoftware.dev.calculo_mental.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cbersoftware.dev.calculo_mental.R;
import cbersoftware.dev.calculo_mental.apoio.Util_calculo_mental;

public class Calculo_Mental extends AppCompatActivity {

    private TextView tvOperacao, tvPontos;
    private EditText etResposta;
    private Spinner spinnerDificuldade;
    private Button btnConfirmar, btnContinuar;
    private int pontos = 0;
    private int acertos = 0;
    private Util_calculo_mental calculoMental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_mental);

        tvOperacao = findViewById(R.id.tvOperacao);
        tvPontos = findViewById(R.id.tvPontos);
        etResposta = findViewById(R.id.etResposta);
        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnContinuar = findViewById(R.id.btnContinuar);
        spinnerDificuldade = findViewById(R.id.spinnerDificuldade);

        atualizarOperacao();

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etResposta.getText().toString().isEmpty()) {
                    int respostaUsuario = Integer.parseInt(etResposta.getText().toString());
                    boolean certo = false;

                    // Usa a operação gerada aleatoriamente
                    switch (calculoMental.getOperacao()) {
                        case 0:
                            certo = calculoMental.somar(respostaUsuario);
                            break;
                        case 1:
                            certo = calculoMental.diminuir(respostaUsuario);
                            break;
                        case 2:
                            certo = calculoMental.multiplicar(respostaUsuario);
                            break;
                        default:
                            System.out.println("Operação desconhecida");
                    }

                    if (certo) {
                        acertos += 1;
                        pontos += 1;
                        Toast.makeText(Calculo_Mental.this, "Resposta correta", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Calculo_Mental.this, "Resposta errada. Tente novamente", Toast.LENGTH_SHORT).show();
                    }

                    atualizarTela();
                    perguntarContinuar();
                } else {
                    etResposta.setError("Por favor, insira uma resposta.");
                }
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarOperacao();  // Gera uma nova operação
            }
        });
    }

    private void atualizarOperacao() {
        int dificuldade = spinnerDificuldade.getSelectedItemPosition() + 1;  // Define a dificuldade selecionada
        calculoMental = new Util_calculo_mental(dificuldade);  // Cria um novo cálculo mental
        atualizarTela();
    }

    private void atualizarTela() {
        tvOperacao.setText(calculoMental.toString());  // Exibe a operação atual na tela
        tvPontos.setText("Pontos: " + pontos);        // Atualiza a pontuação
        etResposta.setText("");                       // Limpa o campo de resposta
    }

    private void perguntarContinuar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Calculo_Mental.this);
        builder.setMessage("Deseja continuar jogando?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        atualizarOperacao();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Calculo_Mental.this, "Você fez " + acertos + " acerto(s). Até a próxima!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }
}
