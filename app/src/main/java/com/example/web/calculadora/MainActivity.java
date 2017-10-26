package com.example.web.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText visor;

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    private Button btnA;
    private Button btnM;
    private Button btnI;
    private Button btnD;
    private Button btnS;
    private Button btnC;
    private Button btnV;

    private List<String> expressao = new ArrayList<String>();

    private String valorAtual = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visor = (EditText) findViewById(R.id.visor);
        visor.setMovementMethod(new ScrollingMovementMethod());

        btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 0 );
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 1 );
            }
        });

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 2 );
            }
        });

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 3 );
            }
        });

        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 4 );
            }
        });

        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 5 );
            }
        });

        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 6 );
            }
        });

        btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 7 );
            }
        });

        btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 8 );
            }
        });

        btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                digitaValor( 9 );
            }
        });

        btnA = (Button) findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                operacao("+");
            }
        });

        btnM = (Button) findViewById(R.id.btnM);
        btnM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                operacao("x");
            }
        });

        btnS = (Button) findViewById(R.id.btnS);
        btnS.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                operacao("-");
            }
        });

        btnD = (Button) findViewById(R.id.btnD);
        btnD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                operacao("/");
            }
        });

        btnI = (Button) findViewById(R.id.btnI);
        btnI.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               igual();
           }
        });

        btnC = (Button) findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                clear();
            }
        });

        btnV = (Button) findViewById(R.id.btnV);
        btnV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ponto();
            }
        });

    }

    public void digitaValor(int valor){

        if( !visor.getEditableText().toString().contains("=")){
            if (visor.getEditableText().toString().equals("0")) {
                visor.setText(Integer.toString(valor));
            } else {
                visor.setText(visor.getEditableText().toString() + Integer.toString(valor));
            }

            valorAtual += Integer.toString(valor);
        }

    }

    public void ponto(){

        if( !visor.getEditableText().toString().contains("=")){
            if (visor.getEditableText().toString().equals("0")) {
                visor.setText("0.");
                valorAtual += "0.";
            } else {
                if( valorAtual.equals("") ){ valorAtual = "0"; }
                if( !valorAtual.contains(".") ){
                    visor.setText(visor.getEditableText().toString() + ".");
                    valorAtual += ".";
                }
            }

        }

    }

    public void operacao(String op) {

        if (!valorAtual.equals("") ) {
            if(!valorAtual.equals("igual")){
                expressao.add(valorAtual);
                visor.setText(visor.getEditableText().toString() + op);
            }else{
                visor.setText(expressao.get(0) + op);
            }
            expressao.add(op);
            valorAtual = "";
        }

    }

    public void clear(){

        visor.setText( "0" );
        valorAtual = "";
        expressao.clear();

    }

    public void igual(){

        if( !visor.getEditableText().toString().contains("=") && !valorAtual.equals("") ) {
            expressao.add(valorAtual);
            valorAtual = "igual";

            for (int i = 0; i < expressao.size(); i++) {
                if (expressao.get(i).equals("x")) {
                    expressao.set(i - 1, String.valueOf(Double.parseDouble(expressao.get(i - 1)) * Double.parseDouble(expressao.get(i + 1))));
                    expressao.remove(i);
                    expressao.remove(i);
                    i = 0;
                }else if (expressao.get(i).equals("/")) {
                    expressao.set(i - 1, String.valueOf(Double.parseDouble(expressao.get(i - 1)) / Double.parseDouble(expressao.get(i + 1))));
                    expressao.remove(i);
                    expressao.remove(i);
                    i = 0;
                }
            }

            for (int i = 0; i < expressao.size(); i++) {
                if (expressao.get(i).equals("+")) {
                    expressao.set(i - 1, String.valueOf(Double.parseDouble(expressao.get(i - 1)) + Double.parseDouble(expressao.get(i + 1))));
                    expressao.remove(i);
                    expressao.remove(i);
                    i = 0;
                }else if(expressao.get(i).equals("-")){
                    expressao.set(i - 1, String.valueOf(Double.parseDouble(expressao.get(i - 1)) - Double.parseDouble(expressao.get(i + 1))));
                    expressao.remove(i);
                    expressao.remove(i);
                    i = 0;
                }
            }
            visor.setText(visor.getEditableText().toString() + " = " + expressao.get(0));
        }

    }


}
