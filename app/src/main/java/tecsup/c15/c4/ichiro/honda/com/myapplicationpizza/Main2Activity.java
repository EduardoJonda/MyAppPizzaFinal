package tecsup.c15.c4.ichiro.honda.com.myapplicationpizza;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class Main2Activity extends Activity {

    private data v1 = new data("","",0,0);
    private int queso, japon;

    private CheckBox checkBox;
    private CheckBox checkBox2;
    private Spinner spinner01;
    private RadioGroup ragroup;
    private EditText anserw;
    private RadioGroup Rgroup;
    private RadioButton Rbtn, Rbtn2, Rbtn3;

    //Interfaz
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinner01 = (Spinner) findViewById(R.id.sp01);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipos, android.R.layout.simple_spinner_item);
        spinner01.setAdapter(adapter);

        ragroup = (RadioGroup)findViewById(R.id.gruop);
        Rbtn = (RadioButton) findViewById(R.id.mgruesa);
        Rbtn2 = (RadioButton) findViewById(R.id.mdelgada);
        Rbtn3 = (RadioButton) findViewById(R.id.martesanal);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        anserw=(EditText)findViewById(R.id.resultado);

        spinner01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int post, long id) {

                String pizza = parent.getItemAtPosition(post).toString();
                switch (pizza){
                    case "Americana":
                        v1.setPizza("Pizza Americana");
                        v1.setPrecio(48);
                        Total();
                        break;
                    case "Hawaiana":
                        v1.setPizza("Pizza Hawaiana");
                        v1.setPrecio(40);
                        Total();
                        break;
                    case "Super Suprema":
                        v1.setPizza("Pizza Super Suprema");
                        v1.setPrecio(52);
                        Total();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void RadioClicked(View view){
        boolean chec = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.mgruesa:
                v1.setMaza("Maza gruesa");
                if(chec)
                    break;
            case R.id.mdelgada:
                v1.setMaza("Maza delgada");
                if(chec)
                    break;
            case R.id.martesanal:
                v1.setMaza("Maza artesanal");
                if(chec)
                    break;
        }
    }

    public void complementos(View view){

        CheckBox checkBox = (CheckBox)view;
        switch (view.getId()){
            case R.id.checkBox:
                if (checkBox.isChecked())
                    queso = 10;
                Total();
                break;
            case R.id.checkBox2:
                if (checkBox2.isChecked())
                    japon = 20;
                Total();
                break;

        }

    }

    public void Total(){
        v1.setPrecio_extra(queso + japon);
        total = v1.getPrecio()+v1.getPrecio_extra();
    }

    public void showDialog(View view){

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Información");

        Button button_cancel = (Button)dialog.findViewById(R.id.customDialogCancel);
        Button button_ok = (Button)dialog.findViewById(R.id.customDialogOk);
        TextView txt_informacion = (TextView)dialog.findViewById(R.id.info);

        txt_informacion.setText("Su pedido de "+v1.getPizza()+" con "+v1.getMaza()+" en total serian S/."+total);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                pedido_notificacion(view);

            }
        });
        dialog.show();
    }

    public void pedido_notificacion(View view){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingIntent = PendingIntent.getActivity(Main2Activity.this, 100, intent, PendingIntent.FLAG_ONE_SHOT);
                Notification notification = new NotificationCompat.Builder(Main2Activity.this)
                        .setContentTitle("Pizza S.A.C")
                        .setContentText("Su pizza de"+v1.getPizza()+" llegara en minimo 10 minutos")
                        .setSmallIcon(R.mipmap.pizza)
                        .setColor(ContextCompat.getColor(Main2Activity.this, R.color.colorPrimary))
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();

                NotificationManager notificationManager = (NotificationManager) Main2Activity.this.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(100, notification);

            }
        }, 5000);
    }


}
