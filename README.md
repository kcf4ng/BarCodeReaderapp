# BarCodeReaderapp

上方按鈕功能:掃描Barcode/QRcode，用Toast顯示Cose內容

程式碼中的 integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);

將ALL_CODE_TYPES 換成 QR_CODE_TYPES 則會變成只能掃 QRcode


下方輸入輸入數字(例如:712345678911)，並按下下方按鈕，則會生成 UPC_A 的條碼


程式碼中  bitMatrix = multiFormatWriter.encode(strCode, BarcodeFormat.UPC_A,200,200);

將 UPC_A 改成 QR_CODE 則會變成生成QRcode


參考網址:

[教學] QRCode Scanner(Android Version)
http://dangerlover9403.pixnet.net/blog/post/200742339-%5B%E6%95%99%E5%AD%B8%5D-qrcode-scanner(android-version)

[教學] Generate barcode in android app using Zxing.
https://medium.com/@aanandshekharroy/generate-barcode-in-android-app-using-zxing-64c076a5d83a

ZXING文件
https://zxing.github.io/zxing/apidocs/com/google/zxing/integration/android/IntentIntegrator.html

![Alt text](https://i.imgur.com/HQcs4LS.jpg)
