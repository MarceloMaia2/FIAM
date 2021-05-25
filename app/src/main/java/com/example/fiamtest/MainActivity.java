package com.example.fiamtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    Button btnClick;
    private static  final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(v -> {
            KeyPair keyPair = generateRSAKeyPair();
            byte[] serialized = serializeClientKeyPair(keyPair);
            KeyPair deserializedKeyPair = deserializeKeyPair(serialized);
        });
    }

    private KeyPair generateRSAKeyPair() {
        KeyPairGenerator keyGen = null;
        KeyPair keyPair = null;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            keyPair = keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }

    private byte[] serializeClientKeyPair(KeyPair clientKeyPair) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o;
        try {
            o = new ObjectOutputStream(b);
            o.writeObject(clientKeyPair);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] res = b.toByteArray();
        return res;
    }

    private KeyPair deserializeKeyPair(byte[] serializedKeyPair) {
        ByteArrayInputStream bi = new ByteArrayInputStream(serializedKeyPair);
        ObjectInputStream oi;
        Object obj = null;
        try {
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "ClassNotFoundException");
        }
        return ((KeyPair) obj);
    }
}