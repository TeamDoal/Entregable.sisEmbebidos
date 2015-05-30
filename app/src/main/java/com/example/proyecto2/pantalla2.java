package com.example.proyecto2;
import android.content.DialogInterface;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.Menu;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.os.Handler;

public class pantalla2 extends Activity {
    private int i=0;
    private Handler progressBarHandler = new Handler();
    String[] info; // Para recibir la información.
    TextView Quien, Ubicacion;
    //simula un archivo
    private long fileSize = 0;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);
        iv = (ImageView) findViewById (R.id.imageView);

        Quien = (TextView) findViewById(R.id.textViewQuien);
        Ubicacion = (TextView) findViewById(R.id.textViewUbicacion);

        //tv.setText(getIntent().getExtras().getString("Textbox"));
        //tvUbicacion.setText(getIntent().getExtras().getString("TextboxU"));

        //Obtengo la información de la Actividad anterior y se la asigno a la variable info.
        Intent men = getIntent();
        info = men.getStringArrayExtra(Proyecto2Activity.ACT_INFO);

        //Muestro la información en la interfaz.
        Quien.setText(info[0]);
        Ubicacion.setText(info[1]);

        Button b = (Button) findViewById(R.id.buttonCamara);
        b.setOnClickListener(new OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        Button c =(Button) findViewById(R.id.buttonGuardar);
        c.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(v.getContext());
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMessage("Guardando...");
                pd.setIndeterminate(false);
                pd.setCancelable(true);
                pd.show();

                i = 0;
                fileSize = 0;
                new Thread(new Runnable() {
                    public void run() {
                        while (i < 100) {
                            i = doSomeTask();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            progressBarHandler.post(new Runnable() {

                                public void run() {
                                    pd.setProgress(i);
                                }
                            });
                            if (i >= 100) {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                pd.dismiss();
                            }
                        }
                    }
                }).start();
            }
        });

        Button d =(Button) findViewById(R.id.buttonSalir);
        d.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(pantalla2.this);
                builder.setTitle("Salir");
                builder.setMessage("Seguro que desea salir?");
                //no deja hacer nada hasta cerrar la alerta si esta en modo false
                builder.setCancelable(false);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent j = new Intent(pantalla2.this, Pantalla0.class);
                        startActivity(j);

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                        Intent i = new Intent(pantalla2.this, Proyecto2Activity.class);
                        startActivity(i);

                    }
                });
                //se crea una alerta
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int doSomeTask() {
        while(fileSize <=1000000){
            fileSize++;
            if(fileSize == 250000)
            {
                return 25;
            } else if(fileSize == 500000){
                return 50;
            } else if (fileSize == 750000){
                return 75;
            }
        }
        return 100;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bm = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.proyecto2, menu);
        return true;
    }

}
