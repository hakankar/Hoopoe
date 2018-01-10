package karyagdi.hakan.hoopoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Opinion op = new Opinion(37,"deneme37","denek",null);
        Opinion op2 = new Opinion(36,"deneme36","denek",null);
        op.save(getApplicationContext());
        op2.save(getApplicationContext());
        List<Opinion> all = Opinion.getAllEntities(Opinion.class,getApplicationContext());
        Log.v("KAYIT ADEDI::: ",String.valueOf(all.size()));
        Log.v("KAYIT ID::: ",String.valueOf(all.get(0).getOpinionDesc()));
        Log.v("KAYIT ID::: ",String.valueOf(all.get(1).getOpinionDesc()));
    }
}
