package com.example.nathali.grupo6tarea;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar1;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button barra = (Button)findViewById(R.id.btBarra);
        bar1 = (ProgressBar)findViewById(R.id.progressbar1);
        barra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bar1.setVisibility(View.VISIBLE);

                ActualizarProgreso tarea= new ActualizarProgreso();
                tarea.execute();
            }
        });

    }


    public class ActualizarProgreso extends AsyncTask<Void,Integer,Boolean> {

        protected void onPostExecute() {
            super.onPreExecute();

            bar1.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this,"Tarea Finalizada",Toast.LENGTH_LONG).show();

        }

        protected Boolean doInBackground(Void... arg0){
            while(progress<=100 && !isCancelled()){
                progress++;
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                publishProgress(progress);
            }
            return true;
        }

        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            bar1.setProgress(values[0]);
        }

        protected void onPostExecute(Boolean result){
            super.onPostExecute(result);
            Toast.makeText(MainActivity.this,"Tarea Finalizada",Toast.LENGTH_LONG).show();
        }

        protected void onCancelled(){
            Toast.makeText(MainActivity.this,"Tarea Cancelada",Toast.LENGTH_LONG).show();
        }
    }
}

