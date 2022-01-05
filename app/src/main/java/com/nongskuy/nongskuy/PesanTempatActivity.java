package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PesanTempatActivity extends AppCompatActivity {

    EditText Tanggal, Waktu;
    Calendar calendar;
    TextView jumlahKursi, totalDp, textNamaTokoPesan;
    Button btnTambahKursi, btnKurangKursi;
    ImageView imageNongskuyPesan;
    int Hour, Minute;
    int count = 0, dp = 10000;
    Helper helper = new Helper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_tempat);

        //Id
        Tanggal = findViewById(R.id.Tanggal);
        Waktu = findViewById(R.id.Waktu);
        jumlahKursi = findViewById(R.id.jumlahKursi);
        totalDp = findViewById(R.id.totalDP);
        btnTambahKursi = findViewById(R.id.btnTambahKursi);
        btnKurangKursi = findViewById(R.id.btnKurangKursi);
        imageNongskuyPesan = findViewById(R.id.imageNongskuyPesan);
        textNamaTokoPesan = findViewById(R.id.textNamaTokoPesan);

        //Get data intent
        Intent intent = getIntent();
        textNamaTokoPesan.setText(intent.getStringExtra("NamaToko"));
        Glide.with(getApplicationContext())
                .load(Uri.parse(intent.getStringExtra("GambarToko")))
                .into(imageNongskuyPesan);

        calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, day);

                updateCalendar();
            }

            private void updateCalendar() {
                String formatTanggal = "dd/MM/yyyy";
                SimpleDateFormat dateformat = new SimpleDateFormat(formatTanggal, Locale.US);

                Tanggal.setText(dateformat.format(calendar.getTime()));
            }
        };

        Tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PesanTempatActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH)).show();
            }
        });

        Waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(PesanTempatActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Inisialisasi waktu
                        Hour = hourOfDay;
                        Minute = minute;

                        //Set hour and minute
                        calendar.set(0,0,0, Hour, Minute);
                        //Set selected time
                        String formatWaktu = "hh:mm aa";
                        SimpleDateFormat timeformat = new SimpleDateFormat(formatWaktu, Locale.US);
                        Waktu.setText(timeformat.format(calendar.getTime()));
                    }
                }, 24, 0, true);
                //Display
                timePickerDialog.updateTime(Hour, Minute);
                timePickerDialog.show();
            }
        });
    }

    public void increment(View v){
        if(count<=20){
            count++;
            btnTambahKursi.setEnabled(true);
        }
        else{
            btnTambahKursi.setEnabled(false);
        }
        jumlahKursi.setText("" + count);
        totalDp.setText(helper.mataUangRupiah(dp*count));
    }
    public void decrement (View v){
        if(count <= 0) {
            count = 0;
            btnKurangKursi.setEnabled(false);
        }
        else {
            count--;
            btnKurangKursi.setEnabled(true);
        }
        jumlahKursi.setText("" + count);
        totalDp.setText(helper.mataUangRupiah(dp*count));
    }
}