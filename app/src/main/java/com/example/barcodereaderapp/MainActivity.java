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
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button scan_btn;
    private String strCode;
    private Button btnGen;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //按下按鈕，掃描條碼_START
        scan_btn = (Button)findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("請掃描條碼");
                integrator.setCameraId(0); //使用特定的鏡頭
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
        //按下按鈕，掃描條碼_END



        //按下按鈕，生成Barcode_START
        btnGen = findViewById(R.id.btnGen);
        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtCode = findViewById(R.id.txtCode);
                strCode=txtCode.getText().toString();

                img=findViewById(R.id.img);

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                BitMatrix bitMatrix = null;
                try {
                    bitMatrix = multiFormatWriter.encode(strCode, BarcodeFormat.UPC_A,200,200);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                img.setImageBitmap(bitmap);
            }
        });
        //按下按鈕，生成Barcode_END









    }//OnCreate 結束



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

            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    //接收掃描到的條碼，回到主頁用Toast秀出條碼的內容_END



}
