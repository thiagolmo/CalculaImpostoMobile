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

        double INSS = calculoINSS(salario);
        double IR = calculoImpostoRenda(salario,INSS);
        double calculoSalarioLiquido = calculoSalarioLiquido(salario,IR,INSS);

        if (validaSalarioMinino(salario) == true) {
            String sResultadoValorINSS = String.valueOf(String.format("%.2f", INSS));
            String sResultadoValorIR = String.valueOf(String.format("%.2f", IR));
            String sResultado = String.valueOf(String.format("%.2f", calculoSalarioLiquido));

            TextView tvResultadoSalario = (TextView) findViewById(R.id.txValorSalario);
            TextView tvResultadoValorINSS = (TextView) findViewById(R.id.txtINSS);
            TextView tvResultadoValorIR = (TextView) findViewById(R.id.txIR);
            TextView tvResultado = (TextView) findViewById(R.id.txResultado);

            tvResultadoSalario.setText("R$ " + sSalario);
            tvResultadoValorINSS.setText("R$ " + sResultadoValorINSS);
            tvResultadoValorIR.setText("R$ " + sResultadoValorIR);
            tvResultado.setText("TOTAL: R$ " + sResultado);
        }
    }
    /*PRIMEIRO PASSO -  CALCULO DO INSS*/
    public double calculoINSS(double salario){

        double salarioINSS;
        double valorINSS = 0;
        
        //FAIXA A
        if(salario<=1556.94){
            valorINSS = salario * 0.08;
            //FAIXA B
        }else if(salario<=2594.92){
            valorINSS = salario * 0.09;
            //FAIXA C
        }else if(salario>2594.93){
            //TETO
            if(salario>=5189.82){
                salarioINSS=5189.82;}
            else {
                salarioINSS=salario;
            }
            valorINSS = salarioINSS * 0.11;
            
        }
        return valorINSS;
        
    }

    /*SEGUNDO PASSO - CALCULO IMPOSTO DE RENDA*/
    public double calculoImpostoRenda(double salario, double valorINSS){
        
        double valorSalarioINSS = salario - valorINSS;
        double valorIR = 0;

        //FAIXA A
        if (valorSalarioINSS<=1903.98){
            valorIR = 0;
            //FAIXA B
        }else if (valorSalarioINSS<=2826.65){
            valorIR = (valorSalarioINSS * 0.075)-142.80;
            //FAIXA C
        }else if (valorSalarioINSS<=3751.05){
            valorIR = (valorSalarioINSS * 0.15)-354.80;
            //FAIXA D
        }else if(valorSalarioINSS<=4664.68){
            valorIR = (valorSalarioINSS * 0.225)-636.13;
            //FAIXA E
        }else if (valorSalarioINSS>4664.68){
            valorIR = (valorSalarioINSS * 0.275)-869.36;
        }
        
        return valorIR;

    }

    /*TERCEIRO PASSO - CALCULO SALARIO LIQUIDO*/
    public double calculoSalarioLiquido(double salario, double valorIR, double valorINSS){

        double salarioLiquido = (salario - valorINSS) - valorIR;
        return salarioLiquido;

    }

    /*VALIDAÇÃO SALÁRIO MININO*/
    public boolean validaSalarioMinino(double salario){
        if (salario < 880.00){
            Toast.makeText(getApplicationContext(),"Salário inferior ao salário Mínimo (R$ 880,00).",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }

    }

}
