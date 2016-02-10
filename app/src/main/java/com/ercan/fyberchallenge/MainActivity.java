package com.ercan.fyberchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ercan.fyberchallenge.fragment.FyberFormFragment;
import com.ercan.fyberchallenge.fragment.OnSubmit;
import com.squareup.okhttp.Response;


public class MainActivity extends AppCompatActivity implements OnSubmit {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.lytFyberFormFragmentContainer, new FyberFormFragment(), FyberFormFragment.TAG).commit();


    }

    @Override
    public void onSubmit(Response response) {
        try {
            Toast.makeText(this, response.body().string(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
