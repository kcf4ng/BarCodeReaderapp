package com.example.barcodereaderapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {
    private String strCode;
    final Activity activity = this;

    private View.OnClickListener scan_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            IntentIntegrator integrator = new IntentIntegrator(activity);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setPrompt("請掃描條碼");
            integrator.setCameraId(0); //使用特定的鏡頭
            integrator.setBeepEnabled(false);
            integrator.setBarcodeImageEnabled(false);
            integrator.initiateScan();
        }
    };

    private View.OnClickListener btnGen_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            strCode=txtCode.getText().toString();
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = null;
            BitMatrix bitMatrix2 =  null;

            try {
                bitMatrix = multiFormatWriter.encode(strCode, BarcodeFormat.UPC_A,200,200);
                bitMatrix2 = multiFormatWriter.encode(strCode, BarcodeFormat.QR_CODE,200,200);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            Bitmap bitmap1 =  barcodeEncoder.createBitmap(bitMatrix2);
            img.setImageBitmap(bitmap);
            img2.setImageBitmap(bitmap1);
        }
    };


    //接收掃描到的條碼，回到主頁用Toast秀出條碼的內容_START
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!= null)
        {
            if (result.getContents()==null)
            {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,result.getContents(),Toast.LENGTH_SHORT).show();
                lblCode.setText(result.getContents());
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    //接收掃描到的條碼，回到主頁用Toast秀出條碼的內容_END


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitialComponent();
    }

    private void InitialComponent() {
        scan_btn = (Button)findViewById(R.id.scan_btn);
        scan_btn.setOnClickListener(scan_click);
        btnGen = findViewById(R.id.btnGen);
        btnGen.setOnClickListener(btnGen_click);
        img=findViewById(R.id.img);
        img2 = findViewById(R.id.img2);
        lblCode = findViewById(R.id.lblCode);
        txtCode = findViewById(R.id.txtCode);
    }


    private Button scan_btn,btnGen;
    private ImageView img,img2;
    private TextView lblCode;
    EditText txtCode;
}
