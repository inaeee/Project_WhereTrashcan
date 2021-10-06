package com.example.theenvironment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback{
    static final int PERMISSIONS_REQUEST = 0x0000001;//요청 권한이 한개인 경우.

    GuideFragment guideFragment;
    PersonFragment personFragment;
    MapFragment mapFragment;
    GuideSearchFragment guidesearchFragment;
    
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    SensorManager sensorManager;
    Sensor stepCountSensor;
    TextView stepCount;

    int currentSteps=0;

    @RequiresApi (api= Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnCheckPermission();

        //해시 키 얻기
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.theenvironment", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        //사이드바 시작
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        guideFragment = new GuideFragment();
        personFragment = new PersonFragment();
        mapFragment = new MapFragment();
        guidesearchFragment = new GuideSearchFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.searchLine, mapFragment).commit();

        //활동 퍼미션 체크
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }
        //걸음 수 측정
        stepCount = findViewById(R.id.stepCount);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        if(stepCountSensor == null) {
            Toast.makeText(this, "No Step Sensor", Toast.LENGTH_SHORT).show();
        }
    }

    //사이드바
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_guide) {
            //Toast.makeText(this, "첫번째 메뉴 선택됨.", Toast.LENGTH_LONG).show();
            onFragmentSelected(0, null);
        } else if (id == R.id.nav_person) {
            //Toast.makeText(this, "두번째 메뉴 선택됨.", Toast.LENGTH_LONG).show();
            onFragmentSelected(1, null);
        } else if(id==R.id.nav_map) {
            onFragmentSelected(2, null);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;

        if (position == 0) {
            curFragment = guideFragment;
            //toolbar.setTitle("첫번째 화면");
        } else if (position == 1) {
            curFragment = personFragment;
            //toolbar.setTitle("두번째 화면");
        } else if (position == 2) {
            curFragment = mapFragment;
            //toolbar.setTitle("두번째 화면");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.searchLine, curFragment).commit();
    }

    public void OnCheckPermission() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "앱 실행을 위해서는 권한 설정이 필요합니다.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST);//권한 설정
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) { //실행 후 전달받을 코드
            case PERMISSIONS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//권한이 granted
                    Toast.makeText(this, "권한이 설정되었습니다.", Toast.LENGTH_LONG).show();
                }//권한 허가된 경우
                else {
                    Toast.makeText(this, "권한이 취소되었습니다.", Toast.LENGTH_LONG).show();
                }//권한 거절된 경우
                break;//switch문
        }
    }

    //Fragment 내부에서 Fragment를 전환할때 이 메소드를 사용한다. 뒤로가기 버튼까지 조정
    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.searchLine, fragment).addToBackStack(null).commit();
    }

    public void onFragmentChange(int index, String s) {
        if(index == 1){
            Bundle bundle = new Bundle();
            bundle.putString("Name", s);
            guidesearchFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.searchLine, guidesearchFragment).commit();
        }
        else if(index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.searchLine, guideFragment).commit();
        }
    }

    //걸음 수
    public void onStart(){
        super.onStart();
        if(stepCountSensor != null) {
            sensorManager.registerListener((SensorEventListener) this, stepCountSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

}