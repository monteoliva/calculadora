package com.br.calculadora;

// imports ANDROID API
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// imports JAVA API
import java.text.DecimalFormat;

/**
 * Classe da calculadora
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 26/10/2017.
 */
public class Calculadora {
    private TextView txtVisor;
    private double valorAtual           = 0;
    private boolean operadorPressionado = false;
    private boolean igualPressionado    = false;
    private String operador             = "";

    /**
     * Metodo executado quando é pressionado as Teclas Numéricas
     *
     * @param view
     */
    public void numero(View view) {
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
    public void virgula(View view) {
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
    public void operador(View view) {
        // calcula
        calcular(false);

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
     * Metodo para calcular os valores conforme o operador
     */
    public void calcular(boolean igual) {
        // formato
        DecimalFormat df = new DecimalFormat("0.##");

        // seta o flag de igual pressionado
        igualPressionado = igual;

        // verifica se o operador foi pressionado
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
            operadorPressionado = false;

            // pega o valor formatado
            String formatado = df.format(valorCalculado);

            // seta o valor na tela
            txtVisor.setText(formatado);
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
        igualPressionado    = false;

        // seta a tela
        txtVisor.setText(atual);
    }

    /**
     * Metodo para limpar os dados
     */
    public void clear() {
        // seta 0 na tela
        txtVisor.setText("0");

        // limpa variaveis
        operadorPressionado = false;
        igualPressionado    = false;
        operador            = "";
        valorAtual          = 0;
    }

    /**
     * Metodo Setter´s
     *
     * @param txtVisor
     */
    public void setTxtVisor(TextView txtVisor) { this.txtVisor = txtVisor; }
}