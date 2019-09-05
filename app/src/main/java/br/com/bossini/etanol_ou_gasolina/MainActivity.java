package br.com.bossini.etanol_ou_gasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView gasolinaTextView;
    private TextView etanolTextView;
    private TextView resultadoTextView;
    private SeekBar gasolinaSeekBar;
    private SeekBar etanolSeekBar;

    private ImageView resultadoImageView;

    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gasolinaTextView = findViewById(R.id.gasolinaTextView);
        etanolTextView = findViewById(R.id.etanolTextView);
        resultadoTextView = findViewById(R.id.resultadoTextView);

        gasolinaSeekBar = findViewById(R.id.gasolinaSeekBar);
        etanolSeekBar = findViewById(R.id.etanolSeekBar);

        gasolinaSeekBar.setOnSeekBarChangeListener(onGasolinaSeekBarChangeListener);
        etanolSeekBar.setOnSeekBarChangeListener(onEtanolSeekBarChangeListener);

        resultadoImageView = findViewById(R.id.resultadoImageView);

    }

    private void AnalisaMelhorEscoha(double PrecoGasolina, double PrecoEtanol){
        if(PrecoGasolina == 0 || PrecoEtanol == 0){
            resultadoTextView.setText(R.string.texto_resultado_padrao);
            resultadoImageView.setImageResource(R.drawable.image);

        }
        else{
            double percentual = PrecoEtanol / PrecoGasolina;

            if(percentual <= 0.7){
                resultadoTextView.setText(R.string.texto_resultado_etanol);
                resultadoImageView.setImageResource(R.drawable.etanol);
                resultadoTextView.setTextColor(Color.parseColor("#4CAF50"));
                resultadoTextView.setTextSize(24);

            }
            else{
                resultadoTextView.setText(R.string.texto_resultado_gasolina);
                resultadoImageView.setImageResource(R.drawable.gasolina);
                resultadoTextView.setTextColor(Color.parseColor("#E53935"));
                resultadoTextView.setTextSize(24);
            }
        }
    }

    private SeekBar.OnSeekBarChangeListener onGasolinaSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            double preco = progress / 100d;

            gasolinaTextView.setText(getResources().getString(R.string.texto_gasolina) + " " + currencyFormat.format(preco));
            
            double precoEtanol = Double.parseDouble((String.valueOf(etanolSeekBar.getProgress()))) / 100D;

            AnalisaMelhorEscoha(preco, precoEtanol);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    private SeekBar.OnSeekBarChangeListener onEtanolSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            double preco = progress / 100d;

            etanolTextView.setText(getResources().getString(R.string.texto_etanol) + " " + currencyFormat.format(preco));

            double precoGasolina = Double.parseDouble((String.valueOf(gasolinaSeekBar.getProgress()))) / 100D;

            AnalisaMelhorEscoha(precoGasolina, preco);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}
