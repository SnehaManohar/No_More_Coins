package com.example.ss4ne.bus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;



public class Main4Activity extends AppCompatActivity {

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    private TextView value;
    String scanContent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the main content layout of the Activity
        setContentView(R.layout.activity_main4);
        value=(TextView)findViewById(R.id.textView1);

    }
    String contents;
    //product qr code mode
    public void scanQR(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();

        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(Main4Activity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();

        }
    }

    //alert dialog for downloadDialog
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult!=null)
        {
            scanContent = scanningResult.getContents();
            value.setText(scanContent);

        }
        //if (requestCode == 0) {
            //if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                //String contents = intent.getStringExtra("SCAN_RESULT");
                //Toast toast = Toast.makeText(this, contents, Toast.LENGTH_LONG);
              //  toast.show();
    }


    public void update(View view)
    {
        int row=getIntent().getIntExtra("ROW",0);
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getContact(row);
        String bala=c.getString(3);
        int balance=Integer.parseInt(bala);
        int cost=Integer.parseInt(scanContent);
        int ubal=balance-cost;
        String sbal=Integer.toString(ubal);
        if(db.updatesub(row,sbal))
            Toast.makeText(this, "Update successful ", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed ", Toast.LENGTH_LONG).show();

        db.close();
    }
    public void search(View view)
    {
        int row=getIntent().getIntExtra("ROW",0);
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getContact(row);
        TextView bals=(TextView)findViewById(R.id.textView8);
        bals.setText(c.getString(3));
        db.close();

    }
}