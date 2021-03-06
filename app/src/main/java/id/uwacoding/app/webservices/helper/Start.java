package id.uwacoding.app.webservices.helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import id.uwacoding.app.webservices.R;
import id.uwacoding.app.webservices.ReadActivity;

/**
 * Created by Firman on 6/7/2017.
 */

public class Start extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(4000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), " " + e, Toast.LENGTH_LONG).show();
                }finally{

                    startActivity(new Intent(Start.this, ReadActivity.class));
//                    getContext().startActivity(new Intent(getContext().getApplicationContext(), SearchActivity.class));
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
