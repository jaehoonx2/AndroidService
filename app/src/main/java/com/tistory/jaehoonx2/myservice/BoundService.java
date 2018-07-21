package com.tistory.jaehoonx2.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

public class BoundService extends Service {
   // 외부로 데이터를 전달하려면 바인더 사용

    // Binder 객체는 IBinder 인터페이스 상속구현 객체입니다.
    // public class Binder extends Object implement IBinder

    IBinder mBinder = new MyBinder();

    class MyBinder extends Binder {
        BoundService getService() {    // 서비스 객체를 리턴
            return BoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 액티비티에서 bindService()를 실행하면 호출됨
        // 리턴한 IBinder 객체는 서비스와 클라이언트 사이의 인터페이스를 정의한다.
        return mBinder;
    }

    int getRan(){   // 임의 랜덤값을 리턴하는 메서드
        return new Random().nextInt();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
