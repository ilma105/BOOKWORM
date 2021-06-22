package com.example.system_project;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import static com.nightonke.boommenu.ButtonEnum.*;

public class NotesActivity extends AppCompatActivity {


    static ArrayList<String> notename = new ArrayList<>();
    static ArrayList<String> notedate = new ArrayList<>();
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdaptername;
    static ArrayAdapter arrayAdapter2;

    static ArrayAdapter arrayAdapter;


    ImageButton notesbtn;
    BoomMenuButton bmb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        this.setTitle("Notes...");
        Toast.makeText(NotesActivity.this,"Write your thoughts \uD83D\uDCAD",Toast.LENGTH_SHORT).show();

        notesbtn = (ImageButton) findViewById(R.id.notesIconID);
        bmb = (BoomMenuButton) findViewById(R.id.boombtnID);
        bmb.setButtonEnum(Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);

        ListView listView = (ListView) findViewById(R.id.NoteListViewID);

        notesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotesActivity.this,"Write your thoughts \uD83D\uDCAD",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                startActivity(intent);
            }
        });

        for(int i=0;i<bmb.getPiecePlaceEnum().pieceNumber();i++){
            int position = i;
            if(i==0){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.user)
                        .normalText("Profile").subNormalText("My world")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                                startActivity(intent);
                                Toast.makeText(NotesActivity.this,"Wohho",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==1){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.search)
                        .normalText("Search User").subNormalText("Other Users")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),Search.class);
                                startActivity(intent);
                                Toast.makeText(NotesActivity.this,"Connect with other users",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==2){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.cont)
                        .normalText("App Developer's Info").subNormalText("Need any help?")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),authorinfo.class);
                                startActivity(intent);
                                Toast.makeText(NotesActivity.this,"Contact with us",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==3){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.bright)
                        .normalText("Wish List").subNormalText("Wanna see others wishes")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),notepad.class);
                                startActivity(intent);
                                Toast.makeText(NotesActivity.this,"Others wishes",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }

        }
      /*  SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);


        HashSet<String> set1 = (HashSet<String>) sharedPreferences1.getStringSet("notename",null);
        HashSet<String> set = (HashSet<String>) sharedPreferences1.getStringSet("notes",null);
        HashSet<String> set2 = (HashSet<String>) sharedPreferences1.getStringSet("notedate",null);*/



        arrayAdaptername = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notename);
        arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notedate);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notes);

        listView.setAdapter(arrayAdaptername);
        listView.setAdapter(arrayAdapter2);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);

                intent.putExtra("noteId2", i);
                intent.putExtra("noteId", i);
                intent.putExtra("noteId1", i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int itemToDelete = i;

                new AlertDialog.Builder(NotesActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are You Want To Delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                notes.remove(itemToDelete);
                                notedate.remove(itemToDelete);
                                notename.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();
                                arrayAdaptername.notifyDataSetChanged();
                                arrayAdapter2.notifyDataSetChanged();
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);
                                SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);
                                SharedPreferences sharedPreferences2 = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);

                                HashSet<String> set = new HashSet<>(NotesActivity.notes);
                                HashSet<String> set1 = new HashSet<>(NotesActivity.notename);

                                HashSet<String> set2 = new HashSet<>(NotesActivity.notedate);

                                sharedPreferences.edit().putStringSet("notes",set).apply();
                                sharedPreferences1.edit().putStringSet("notename",set1).apply();
                                sharedPreferences2.edit().putStringSet("notedate",set2).apply();

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.addNote){
            Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}