package com.example.grupo4_ta3_iniciosesionfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class registros extends AppCompatActivity {
    DatabaseReference db_reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Grupo4/Registros");
        leerRegistros();
    }
    public void leerRegistros(){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshotsnapshot) {
                for(DataSnapshot snapshot : dataSnapshotsnapshot.getChildren()){
                    mostrarRegistrosPorPantalla(snapshot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println(error.toException());
            }
        });
    }
    public void mostrarRegistrosPorPantalla(DataSnapshot snapshot){
        LinearLayout contTemp = (LinearLayout) findViewById(R.id.ContenedorTemp);
        LinearLayout contAxi = (LinearLayout) findViewById(R.id.ContenedorAxi);

        String tempVal = String.valueOf(snapshot.child("temp").getValue());
        String axiVal = String.valueOf(snapshot.child("axi").getValue());

        TextView temp = new TextView(getApplicationContext());
        temp.setText(tempVal+"C");
        contTemp.addView(temp);

        TextView axi = new TextView(getApplicationContext());
        axi.setText(axiVal);
        contAxi.addView(axi);
    }
}