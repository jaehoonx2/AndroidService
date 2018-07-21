package com.tistory.jaehoonx2.myservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BoundActivity extends AppCompatActivity {

    BoundService boundService;  // 서비스 객체
    boolean isService = false;  // 서비스 중인지 아닌지 확인하는 변수

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 서비스와 연결되었을 때 호출되는 메서드
            // 서비스 객체를 전역변수로 저장
            BoundService.MyBinder mb = (BoundService.MyBinder) service;
            boundService = mb.getService(); // 서비스가 제공하는 메소드를 호출하여 서비스쪽 객체를 전달받을 수 있음.
            isService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 서비스와 연결이 끊겼을 때 호출되는 메서드
            isService = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);
        // 데이터를 전달할 수 있는 서비스 사용하기
        // 1. Service (*.java)를 작성한다.
        // 2. Service 를 Manifest.xml 파일에 등록한다.
        // 3. Service 를 시작한다.

        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                       // 서비스 시작
                Intent intent = new Intent(
                        BoundActivity.this,     // 현재 화면
                        BoundService.class                     // 다음 넘어갈 컴퍼넌트
                );

                bindService(intent,                             // intent 객체
                        conn,                                   // 서비스와 연결에 대한 정의
                        Context.BIND_AUTO_CREATE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                       // 서비스 종료
                unbindService(conn);                            // 서비스 종료
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                       // 서비스 데이터 확인
                if(!isService){
                    Toast.makeText(getApplicationContext(),
                            "서비스중이 아닙니다, 데이터 받을 수 없음",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = boundService.getRan();                    // 서비스쪽 메소드로 값 전달 받아 호출
                Toast.makeText(getApplicationContext(),
                        "받아온 데이터 : " + num,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }   // end of onCreate
}       // end of class
