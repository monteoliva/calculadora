package com.br.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView txtVisor;
    private double valorAtual           = 0;
    private boolean operadorPressionado = false;
    private boolean igualPressionado    = false;
    private String operador             = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // seta o layout padrao
        setContentView(R.layout.activity_main);

        // pega o TextView
        txtVisor = (TextView) findViewById(R.id.txtVisor);
    }

    /**
     * Metodo executado quando é pressionado as Teclas Numéricas
     *
     * @param view
     */
    public void numClick(View view) {
        // pega o botao pressionado
        Button btn = (Button) view;

        // botao pressionado
        String btnPress = btn.getText().toString();

        // seta o valor no campo
        setTextValor(btnPress);
    }

    /**
     * Método que é excutado quando é pressionado a Tecla Vírgula (,)
     *
     * @param view
     */
    public void virgulaClick(View view) {
        // pega o valor atual
        String atual = txtVisor.getText().toString();

        // verifica se no campo atual já tem uma virgula
        if (!atual.contains(",")) {
            // pega o botao pressionado
            Button btn = (Button) view;

            // seta o valor no campo
            setTextValor(btn.getText().toString());
        }
    }

    /**
     * Método executado quando é pressionado as Teclas de Operadores
     *
     * @param view
     */
    public void operadorClick(View view) {
        // calcula
        calcular();

        // pega o botao pressionado
        Button btn = (Button) view;

        // pega o valor digitado
        String valorDigitado = txtVisor.getText().toString().replaceAll(",",".");

        // pega o valor atual
        valorAtual = Double.parseDouble(valorDigitado);

        // pega o operador
        operador = btn.getText().toString();

        // seta como operador pressionado
        operadorPressionado = true;
    }

    /**
     * Metodo que calcula o valor ao pressionado a Tecla Igual (=)
     *
     * @param view
     */
    public void calculaClick(View view) { calcular(); }

    /**
     * Metodo para calcular os valores conforme o operador
     */
    private void calcular() {
        // formato
        DecimalFormat df = new DecimalFormat("0.##");

        if (!operador.isEmpty()) {
            // pega o ultimo valor digitado
            String ultimoDigitado = txtVisor.getText().toString().replaceAll(",",".");

            // pega o ultimo valor
            double ultimoValor = Double.parseDouble(ultimoDigitado);

            // inicia o valor calculado
            double valorCalculado = 0;

            // verifica o operador
            if      (operador.equals("+")) { valorCalculado = valorAtual + ultimoValor; }
            else if (operador.equals("-")) { valorCalculado = valorAtual - ultimoValor; }
            else if (operador.equals("X")) { valorCalculado = valorAtual * ultimoValor; }
            else if (operador.equals("/")) { valorCalculado = valorAtual / ultimoValor; }

            // limpa o operador
            operador            = "";
            operadorPressionado = true;

            // pega o valor formatado
            String formatado = df.format(valorCalculado);

            // seta o valor na tela
            setTextValor(formatado);
        }
    }

    /**
     * Metodo que coloca o valor digitado na tela
     *
     * @param valor
     */
    private void setTextValor(String valor) {
        // pega o valor atual no TextView
        String atual = txtVisor.getText().toString();

        // verifica
        if (atual.equals("0") && !valor.equals(",")) { atual = ""; }

        // pega o valor atual e coloca na tela
        atual = (!operadorPressionado && !igualPressionado) ? atual + valor : valor;

        // seta operador
        operadorPressionado = false;

        // seta a tela
        txtVisor.setText(atual);
    }

    /**
     * Metodo para limpar os dados quando é pressionado a Tecla C
     *
     * @param view
     */
    public void clearClick(View view) {
        // seta 0 na tela
        txtVisor.setText("0");

        // limpa variaveis
        operadorPressionado = false;
        igualPressionado    = false;
        operador            = "";
        valorAtual          = 0;
    }
}