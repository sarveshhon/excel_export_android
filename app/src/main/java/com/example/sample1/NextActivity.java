package com.example.sample1;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sample1.databinding.ActivityNextBinding;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class NextActivity extends AppCompatActivity {

    HSSFWorkbook hssfWorkbook;
    HSSFSheet hssfSheet;

    Context context;
    TextInputEditText etAge;
    Button btnSubmit;

    ActivityNextBinding mbinding;

    String[] details = {"Age","Gender","Smoking","Yellow Fingers","Anxiety","Peer Pressure","Chornic Disease","Fatigue","Allergy","Wheezing","Alcohol Consuming","Coughing","Shortness of Breth","Swallowing Difficulty","Chest Pain"};
    String[] choice = {"NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding = ActivityNextBinding.inflate(getLayoutInflater());
        setContentView(mbinding.getRoot());

        context = NextActivity.this;
        hssfWorkbook = new HSSFWorkbook();
        hssfSheet = hssfWorkbook.createSheet("Report");

        etAge = findViewById(R.id.etAge);
        btnSubmit = findViewById(R.id.btnSubmit);

        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        btnSubmit.setOnClickListener(v -> {
            RadioButton rbGender = findViewById(mbinding.rbgGender.getCheckedRadioButtonId());
            RadioButton rbSmoking = findViewById(mbinding.rbgSmoking.getCheckedRadioButtonId());
            RadioButton rbYellowFingers = findViewById(mbinding.rbgYellowFingers.getCheckedRadioButtonId());
            RadioButton rbAnxiety = findViewById(mbinding.rbgAnxiety.getCheckedRadioButtonId());
            RadioButton rbPeerPressure = findViewById(mbinding.rbgPeerPressure.getCheckedRadioButtonId());
            RadioButton rbChornicDisease = findViewById(mbinding.rbgChornicDisease.getCheckedRadioButtonId());
            RadioButton rbFatigue = findViewById(mbinding.rbgFatigue.getCheckedRadioButtonId());
            RadioButton rbAllergy = findViewById(mbinding.rbgAllergy.getCheckedRadioButtonId());
            RadioButton rbWheezing = findViewById(mbinding.rbgWheezing.getCheckedRadioButtonId());
            RadioButton rbAlcoholConsuming = findViewById(mbinding.rbgAlcoholConsuming.getCheckedRadioButtonId());
            RadioButton rbCoughing = findViewById(mbinding.rbgCoughing.getCheckedRadioButtonId());
            RadioButton rbShortnessOfBreth = findViewById(mbinding.rbgShortnessOfBreth.getCheckedRadioButtonId());
            RadioButton rbSwallowingDifficulty = findViewById(mbinding.rbgSwallowingDifficulty.getCheckedRadioButtonId());
            RadioButton rbChestPain = findViewById(mbinding.rbgChestPain.getCheckedRadioButtonId());

            if(etAge.getText().toString().isEmpty() || Integer.parseInt(etAge.getText().toString()) <=0) {
                etAge.setError("Enter Valid Age");
            }else if(rbGender != null && rbSmoking != null && rbYellowFingers != null && rbAnxiety != null && rbPeerPressure != null && rbChornicDisease != null &&
                    rbFatigue != null && rbAllergy != null && rbWheezing != null && rbAlcoholConsuming != null && rbCoughing != null && rbShortnessOfBreth != null &&
                    rbSwallowingDifficulty != null && rbChestPain != null) {

                choice[0] = mbinding.etAge.getText().toString();
                choice[1] = rbGender.getText().toString();
                choice[2] = rbSmoking.getText().toString();
                choice[3] = rbYellowFingers.getText().toString();
                choice[4] = rbAnxiety.getText().toString();
                choice[5] = rbPeerPressure.getText().toString();
                choice[6] = rbChornicDisease.getText().toString();
                choice[7] = rbFatigue.getText().toString();
                choice[8] = rbAllergy.getText().toString();
                choice[9] = rbWheezing.getText().toString();
                choice[10] = rbAlcoholConsuming.getText().toString();
                choice[11] = rbCoughing.getText().toString();
                choice[12] = rbShortnessOfBreth.getText().toString();
                choice[13] = rbSwallowingDifficulty.getText().toString();
                choice[14] = rbChestPain.getText().toString();
                generateExcelFile();
            }else {
                Toast.makeText(context, "Please Fill Everything", Toast.LENGTH_SHORT).show();
            }


        });

    }

    void createExcelFile() {

        HSSFRow row1 = hssfSheet.createRow(1);
        HSSFCell r1column0 = row1.createCell(0);
        HSSFCell r1column1 = row1.createCell(1);
        r1column0.setCellValue("Person Name");
        r1column1.setCellValue((getIntent().getStringExtra("Name")!=null) ?getIntent().getStringExtra("Name") : "NA");

        HSSFRow row2 = hssfSheet.createRow(2);
        HSSFCell r2column0 = row2.createCell(0);
        HSSFCell r2column1 = row2.createCell(1);
        r2column0.setCellValue("Mobile Number");
        r2column1.setCellValue((getIntent().getStringExtra("Mobile")!=null) ?getIntent().getStringExtra("Mobile") : "NA");

        HSSFRow row4 = hssfSheet.createRow(4);
        HSSFCell r4column0 = row4.createCell(0);
        HSSFCell r4column1 = row4.createCell(1);
        r4column0.setCellValue("Details");
        r4column1.setCellValue("Choice");

        for (int i = 0; i < details.length; i++) {
            HSSFRow row = hssfSheet.createRow(i+5);
            HSSFCell column0 = row.createCell(0);
            HSSFCell column1 = row.createCell(1);
            column0.setCellValue(details[i]);
            column1.setCellValue(choice[i]);
        }

    }

    void generateExcelFile() {
        try {
            createExcelFile();
            final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "Report "+ sdf1.format(timestamp) +".xls";
            File f = new File(mPath);
            if (!f.exists()){
                f.createNewFile();
            }

            FileOutputStream fileOutputStream= new FileOutputStream(f);
            hssfWorkbook.write(fileOutputStream);
            f.createNewFile();

            if (fileOutputStream!=null){
                fileOutputStream.flush();
                System.out.println(f);
                fileOutputStream.close();
                Toast.makeText(context, "Report Generated Successfully for "+getIntent().getStringExtra("Name"), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}