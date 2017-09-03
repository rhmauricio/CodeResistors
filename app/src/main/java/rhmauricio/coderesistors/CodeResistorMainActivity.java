package rhmauricio.coderesistors;

import android.annotation.SuppressLint;
import android.icu.text.DecimalFormat;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.*;

public class CodeResistorMainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bcalcular;
    Spinner sbanda1,sbanda2,sbanda3,sbanda4;
    Spinner banda1,banda2,tolerancia,multiplicador;
    TextView tresult;

    int  b1=0; int b2=0 ;double m=0; double tol=0; double r=0;String escala="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_code_resistor_main);
        bcalcular=(Button)findViewById(R.id.bcalcular);
        sbanda1=(Spinner)findViewById(R.id.sbanda1);
        sbanda2=(Spinner)findViewById(R.id.sbanda2);
        sbanda3=(Spinner)findViewById(R.id.sbanda3);
        sbanda4=(Spinner)findViewById(R.id.sbanda4);
        banda1=(Spinner)findViewById(R.id.sbanda1);
        banda2=(Spinner)findViewById(R.id.sbanda2);
        multiplicador=(Spinner)findViewById(R.id.sbanda3);
        tolerancia=(Spinner)findViewById(R.id.sbanda4);
        tresult=(TextView)findViewById(R.id.tresult);

        bcalcular.setOnClickListener(this);

        final ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.banda1,android.R.layout.simple_spinner_item);
        banda1.setAdapter(adapter1);
        final ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.banda2,android.R.layout.simple_spinner_item);
        banda2.setAdapter(adapter2);
        final ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.multiplicador,android.R.layout.simple_spinner_item);
        multiplicador.setAdapter(adapter3);
        final ArrayAdapter<CharSequence> adapter4=ArrayAdapter.createFromResource(this,R.array.tolerancia,android.R.layout.simple_spinner_item);
        tolerancia.setAdapter(adapter4);

    }

    @Override
    public void onClick(View view) {
        try{
        switch (view.getId()) {

            case R.id.bcalcular:

                b1 = (banda1.getSelectedItemPosition()) - 1;
                b2 = (banda2.getSelectedItemPosition() - 1);
                int mul = multiplicador.getSelectedItemPosition();
                switch (mul) {
                    case 0:

                        break;
                    case 1:

                        m = 1;
                        break;
                    case 2:
                        m = 10;
                        break;
                    case 3:
                        m = 100;
                        break;
                    case 4:
                        m = 1;
                        escala = "K";
                        break;
                    case 5:
                        m = 10;
                        escala = "K";
                        break;
                    case 6:
                        m = 100;
                        escala = "K";
                        break;
                    case 7:
                        m = 1;
                        escala = "M";
                        break;
                    case 8:
                        m = 10;
                        escala = "M";
                        break;
                    case 9:
                        m = 100;
                        escala = "M";
                        break;
                    case 10:
                        m = 1;
                        escala = "G";
                        break;
                    case 11:
                        m = 0.1;
                        break;
                    case 12:
                        m = 0.01;
                        break;
                    default:
                        Toast t= Toast.makeText(getApplication().getApplicationContext(),"Parametro desconocido",Toast.LENGTH_SHORT);
                        t.show();
                        break;
                }
                mul = 0;

                tol = tolerance(tolerancia.getSelectedItemPosition());
        }
        String conc = valueOf(b1) + valueOf(b2);
        int conc1 = Integer.valueOf(conc);
        //tresult.setText("primeros dos valores"+ conc1);
        r = conc1 * m;
        DecimalFormat f = new DecimalFormat("0.00");
           if (String.valueOf(b){
               tresult.setText(valueOf(b1) + valueOf(b2) + "*" + m + escala + " con torlerancia de " + tol + "%" + "=" + "\n" + f.format(r) + escala + "ohm");
               b1 = 0;
               b2 = 0;
               tol = 0;
           }else{
               Toast t= Toast.makeText(getApplication().getApplicationContext(),"Espacios en blanco",Toast.LENGTH_SHORT);
               t.show();
               r=0;
           }
    }catch (Exception e){
            Toast t= Toast.makeText(getApplication().getApplicationContext(),"Espacios en blanco",Toast.LENGTH_SHORT);
            t.show();
            r=0;
        }
    }

   public double tolerance(int color){
        double valor=0;
            switch (color){
                case 0:
                    Toast t= Toast.makeText(getApplication().getApplicationContext(),"Espacios en blanco",Toast.LENGTH_SHORT);
                    t.show();
                    break;
                case 1:
                    valor=1;
                break;
                case 2:
                    valor=2;
                break;
                case 3:
                    valor=0.5;
                break;
                case 4:
                    valor=0.25;
                break;
                case 5:
                    valor=0.10;
                break;
                case 6:
                    valor=0.05;
                break;
                case 7:
                    valor=5;
                break;
                case 8:
                    valor=10;
                break;

            }
            return valor;
    }
}
