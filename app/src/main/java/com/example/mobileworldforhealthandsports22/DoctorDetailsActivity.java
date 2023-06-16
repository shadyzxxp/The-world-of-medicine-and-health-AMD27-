package com.example.mobileworldforhealthandsports22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
public class DoctorDetailsActivity extends AppCompatActivity {
    private final String[][] doctor_details1 =
            {
                    {"Doctor Name : Ajit Saste", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No:9898989898", "600"},
                    {"Doctor Name : Prasad Pawar", "Hospital Address : niqdi", "Exp : 15yrs", "Mobile No:7898989898", "900"},
                    {"Doctor Name : Swapnil Kale", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No:9898900000", "300"},
                    {"Doctor Name : Deepak Deshmukh", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No:88989459898", "500"},
                    {"Doctor Name : Ashok Panda", "Hospital Address : Katraj", "Exp : 7yrs", "Mobile No:7798912398", "800"}
            };
    private final String[][] doctor_details2 =
            {
                    {"Doctor Name : Marica Beyza", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No:9898989898", "600"},
                    {"Doctor Name : Tamara Kenina", "Hospital Address : niqdi", "Exp : 15yrs", "Mobile No:7898989898", "900"},
                    {"Doctor Name : Aston Şevket", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No:9898900000", "300"},
                    {"Doctor Name : Tahmid Keara", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No:88989459898", "500"},
                    {"Doctor Name : Elfriede Timaeus", "Hospital Address : Katraj", "Exp : 7yrs", "Mobile No:7798912398", "800"}
            };
    private final String[][] doctor_details3 =
            {
                    {"Doctor Name : Werknesh Terttu", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No:9898989898", "600"},
                    {"Doctor Name : Selahattin Bahadır", "Hospital Address : niqdi", "Exp : 15yrs", "Mobile No:7898989898", "900"},
                    {"Doctor Name : Aurélia Paulína", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No:9898900000", "300"},
                    {"Doctor Name : Farzana Nanna", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No:88989459898", "500"},
                    {"Doctor Name : Erzsi Zakiya", "Hospital Address : Katraj", "Exp : 7yrs", "Mobile No:7798912398", "800"}
            };
    private final String[][] doctor_details4 =
            {
                    {"Doctor Name : Sholto Amun", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No:9898989898", "600"},
                    {"Doctor Name : Andria Fergus", "Hospital Address : niqdi", "Exp : 15yrs", "Mobile No:7898989898", "900"},
                    {"Doctor Name : Enki Gawain", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No:9898900000", "300"},
                    {"Doctor Name : Shivali Agnarr", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No:88989459898", "500"},
                    {"Doctor Name : Murat Muiris", "Hospital Address : Katraj", "Exp : 7yrs", "Mobile No:7798912398", "800"}
                    };
    private final String[][] doctor_details5 =
            {
                    {"Doctor Name : Galvão Hermina", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No:9898989898", "1600"},
                    {"Doctor Name : Ezra Usha", "Hospital Address : niqdi", "Exp : 15yrs", "Mobile No:7898989898", "1900"},
                    {"Doctor Name : Azazias Émilie", "Hospital Address : Pune", "Exp : 8yrs", "Mobile No:9898900000", "1300"},
                    {"Doctor Name : Rosella Kamal", "Hospital Address : Chinchwad", "Exp : 6yrs", "Mobile No:88989459898", "1500"},
                    {"Doctor Name : Adrien Ramazan", "Hospital Address : Katraj", "Exp : 7yrs", "Mobile No:7798912398", "1800"}
            };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textView_logo6);
        btn = findViewById(R.id.buttonBMDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.equals("Family Physicians"))
            doctor_details = doctor_details1;
        else if (title.equals("Dietician"))
            doctor_details = doctor_details2;
        else if (title.equals("Dentist"))
            doctor_details = doctor_details3;
        else if (title.equals("Surgeon"))
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        List<Map<String, String>> list = new ArrayList<>();
        for (String[] details : doctor_details) {
            Map<String, String> item = new HashMap<>();
            item.put("line1", details[0]);
            item.put("line2", details[1]);
            item.put("line3", details[2]);
            item.put("line4", details[3]);
            item.put("line5", "Cons Fees: " + details[4] + "/-");
            list.add(item);
        }

        SimpleAdapter sa = new SimpleAdapter(
                this,
                list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        ListView lst = findViewById(R.id.listViewBMCart);
        lst.setAdapter(sa);

        lst.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra( "text1" ,title);
                it.putExtra( "text2", doctor_details[i][0]);
                it.putExtra( "text3",doctor_details[i][1]);
                it.putExtra( "text4",doctor_details[i][3]);
                it.putExtra( "text5",doctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}
