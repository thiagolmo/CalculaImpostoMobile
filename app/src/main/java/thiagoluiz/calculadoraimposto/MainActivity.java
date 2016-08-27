package thiagoluiz.calculadoraimposto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickCalcular(View view) {

        EditText edSalario = (EditText) findViewById(R.id.edSalario);
        String sSalario = edSalario.getText().toString();
        double salario = Double.parseDouble(sSalario);

        //DECLARAÇÃO DE VARIAVEIS
        double salario_INSS = 0;
        double valor_IR = 0;
        double valor_INSS = 0;
        double salario_liquido = 0;
        double valor_sal_INSS = 0;

        /*PRIMEIRO PASSO CALCULO DO INSS*/


        //FAIXA A
        if(salario<=1556.94){
            valor_INSS = salario * 0.08;
            salario_INSS = salario - valor_INSS;
        //FAIXA B
        }else if(salario<=2594.92){
            valor_INSS = salario * 0.09;
            salario_INSS = salario - valor_INSS;
        //FAIXA C
        }else if(salario>2594.93){
            //TETO
            if(salario>=5189.82){
                salario_INSS=5189.82;}
            else {
                salario_INSS=salario;
            }
            valor_INSS = salario_INSS * 0.11;
            valor_sal_INSS = salario - valor_INSS;
        }
        
        /*SEGUNDO PASSO CALCULO IMPOSTO DE RENDA*/


        //FAIXA A
        if (valor_sal_INSS<=1903.98){
            valor_IR = 0;
        //FAIXA B
        }else if (valor_sal_INSS<=2826.65){
            valor_IR = (valor_sal_INSS * 0.075)-142.80;
        //FAIXA C
        }else if (valor_sal_INSS<=3751.05){
            valor_IR = (valor_sal_INSS * 0.15)-354.80;
        //FAIXA D
        }else if(valor_sal_INSS<=4664.68){
            valor_IR = (valor_sal_INSS * 0.225)-636.13;
        //FAIXA E
        }else if (valor_sal_INSS>4664.68){
            valor_IR = (valor_sal_INSS * 0.275)-869.36;
        }

        salario_liquido = (salario - valor_INSS) - valor_IR;



        String sResultadoValorINSS = String.valueOf(valor_INSS);
        String sResultadoValorIR= String.valueOf(valor_IR);
        String sResultado = String.valueOf(salario_liquido);

        TextView tvResultadoSalario = (TextView) findViewById(R.id.txValorSalario);
        TextView tvResultadoValorINSS = (TextView) findViewById(R.id.txtINSS);
        TextView tvResultadoValorIR = (TextView) findViewById(R.id.txIR);
        TextView tvResultado = (TextView) findViewById(R.id.txResultado);

        tvResultadoSalario.setText("R$ "+sSalario);
        tvResultadoValorINSS.setText("R$ "+sResultadoValorINSS);
        tvResultadoValorIR.setText("R$ "+sResultadoValorIR);
        tvResultado.setText("TOTAL: R$ "+sResultado);


    }

}
