package com.nongskuy.nongskuy;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import com.bumptech.glide.Glide;
import com.nongskuy.nongskuy.data.MetodeBayarNongskuyData;
import com.nongskuy.nongskuy.model.MetodeBayarNongskuyClass;
import com.nongskuy.nongskuy.model.Nongskuy;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanTempatActivity extends AppCompatActivity {

    private EditText Tanggal, Waktu;
    private AutoCompleteTextView metodeBayar;
    private ArrayAdapter<Nongskuy> arrayAdapter;
    private Calendar calendar;
    private Time time;
    private TextView jumlahKursi, totalDp, textNamaTokoPesan;
    private Button btnTambahKursi, btnKurangKursi;
    private ImageView imageNongskuyPesan;
    private int Hour, Minute;
    private Integer count = 0, dp = 10000;
    private Helper helper;
    private SharedPreferences sharedPreferences;
    private Config config;

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
        metodeBayar = findViewById(R.id.metodeBayar);
        helper = new Helper();
        btnKurangKursi.setEnabled(false);
        config = new Config();
        sharedPreferences = sharedPreferences = getSharedPreferences("com.nongskuy.nongskuy.PREFS", Context.MODE_PRIVATE);


        //Get data intent
        Intent intent = getIntent();
        textNamaTokoPesan.setText(intent.getStringExtra("NamaToko"));
        Glide.with(getApplicationContext())
                .load(Uri.parse(intent.getStringExtra("GambarToko")))
                .into(imageNongskuyPesan);
        loadDataMetodeBayar(intent.getIntExtra("IdToko", 0));

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

        TimePickerDialog.OnTimeSetListener timePickerDialog = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Inisialisasi waktu
                calendar.set(calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                updateTime();
            }

            public void updateTime(){
                //Set selected time
                String formatWaktu = "kk:mm";
                SimpleDateFormat timeformat = new SimpleDateFormat(formatWaktu, Locale.US);
                Waktu.setText(timeformat.format(calendar.getTime()));
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
                new TimePickerDialog(PesanTempatActivity.this, timePickerDialog, calendar.getTime().getHours(),
                        calendar.getTime().getMinutes(), true).show();
            }
        });
    }

    public void increment(View v){
        count++;
        btnKurangKursi.setEnabled(true);

        if(count == 20){
            btnTambahKursi.setEnabled(false);
        }

        jumlahKursi.setText(String.valueOf(count));
        totalDp.setText(helper.mataUangRupiah(dp*count));
    }

    public void decrement (View v){
        count--;
        btnTambahKursi.setEnabled(true);

        if(count == 0){
            btnKurangKursi.setEnabled(false);
        }

        jumlahKursi.setText(String.valueOf(count));
        totalDp.setText(helper.mataUangRupiah(dp*count));
    }

    public void loadDataMetodeBayar(Integer id){
        String token = sharedPreferences.getString("Token", null);

        // load data
        Call<MetodeBayarNongskuyClass> call = config.configRetrofit().metodeBayarNongskuy(id, token);
        call.enqueue(new Callback<MetodeBayarNongskuyClass>() {
            @Override
            public void onResponse(Call<MetodeBayarNongskuyClass> call, Response<MetodeBayarNongskuyClass> response) {
                if(response.code() == 200){
                    if(response.isSuccessful()){
                        MetodeBayarNongskuyClass metodeBayarNongskuyClass = response.body();
                        List<MetodeBayarNongskuyData> listMetodeBayarNongskuy = metodeBayarNongskuyClass.getMetodeBayar();
                        int i = 0;
                        Nongskuy[] items = {};

                        for(MetodeBayarNongskuyData metodeBayarNongskuyData : listMetodeBayarNongskuy){
                            Nongskuy nongskuy = new Nongskuy(
                                    metodeBayarNongskuyData.getId(),
                                    metodeBayarNongskuyData.getNamaMetodePembayaran(),
                                    metodeBayarNongskuyData.getNoRek()
                            );

                            items[i] = nongskuy;
                            i++;
                        }

//                        arrayAdapter = new ArrayAdapter<Nongskuy>(this, R.layout.list_metode_bayar_nongskuy, items);

                    }
                }
            }

            @Override
            public void onFailure(Call<MetodeBayarNongskuyClass> call, Throwable t) {

            }
        });
    }
}