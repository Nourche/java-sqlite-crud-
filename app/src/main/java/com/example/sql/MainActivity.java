package com.example.sql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnviewUpdate;
    Button btnDelete;


    @Override

    protected void onCreate (Bundle savedInstanceState) { super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id. name);
        editTextId = findViewById(R.id. id);

        editSurname =  findViewById(R.id.surname);

        editMarks =  findViewById(R.id.marks);

        btnAddData =  findViewById(R.id.button_add);
         btnViewAll =  findViewById(R.id.all);
        btnviewUpdate =  findViewById(R.id.update);
        btnDelete=  findViewById(R.id.delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();


    }





    public void AddData() {

        btnAddData.setOnClickListener(

                new View.OnClickListener() {


                    @Override

                    public void onClick(View v) {

                        boolean isInserted = myDb.insertData(editName.getText().toString(),

                                editSurname.getText().toString(),

                                editMarks.getText().toString());

                        if (isInserted == true)

                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

                        else

                        Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }

                }
        );
    }





    public void viewAll() {

        btnViewAll.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        Cursor res = myDb.getAllData();

                        if (res.getCount() == 0) {

showMessage("error","nothing found");

                            return;

                        }

                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()) {

                            buffer.append("Id :" + res.getString(0) + "\n");

                            buffer.append("Name :" + res.getString(1) + "\n");

                            buffer.append("Surname :" + res.getString(2) + "\n");
                            buffer.append("Marks :" + res.getString(3) + "\n\n");

                        }
                        showMessage("Data",buffer.toString());
                    }
                }// Show all data

        );
    }


    public void showMessage (String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);

        builder.setMessage(Message);

        builder.show();
    }


    public void UpdateData() {

        btnviewUpdate.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(), editName.getText().toString(),

                                editSurname.getText().toString(), editMarks.getText().toString());

                        if (isUpdate == true)

                            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();

                        else

                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();

                    }
                }

                 );


    }



    public void DeleteData() {

        btnDelete.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {


                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());

                        if (deletedRows > 0)

                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();

                        else

                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    }