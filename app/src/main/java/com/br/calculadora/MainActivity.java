package com.br.calculadora;

// imports ANDROID API
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Tela Principal do Sisterma
 *
 * @author Professor Claudio Monteoliva
 * @since 1.0 on 26/10/2017
 */
public class MainActivity extends AppCompatActivity {
    private Calculadora calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // seta o layout padrao
        setContentView(R.layout.activity_main);

        // pega o TextView
        TextView txtVisor = (TextView) findViewById(R.id.txtVisor);

        // instancia a calsse da Calculadora
        calculadora = new Calculadora();
        calculadora.setTxtVisor(txtVisor);

        
    }

    /**
     * Metodo executado quando é pressionado as Teclas Numéricas
     *
     * @param view
     */
    public void numClick(View view) { calculadora.numero(view); }

    /**
     * Método que é excutado quando é pressionado a Tecla Vírgula (,)
     *
     * @param view
     */
    public void virgulaClick(View view) { calculadora.virgula(view); }

    /**
     * Método executado quando é pressionado as Teclas de Operadores
     *
     * @param view
     */
    public void operadorClick(View view) { calculadora.operador(view); }

    /**
     * Metodo que calcula o valor ao pressionado a Tecla Igual (=)
     *
     * @param view
     */
    public void calculaClick(View view) { calculadora.calcular(true);  }

    /**
     * Metodo para limpar os dados quando é pressionado a Tecla C
     *
     * @param view
     */
    public void clearClick(View view) { calculadora.clear(); }
}