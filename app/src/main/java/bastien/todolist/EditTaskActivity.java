package bastien.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

import bastien.todolist.Data.Task;
import bastien.todolist.Database.TaskDAO;

/***
 * Activité permettant de modifier une tâche
 */
public class EditTaskActivity extends AppCompatActivity {

    TaskDAO database;
    Long user_id;
    int id;

    SharedPreferences sharedPreferences;

    MaterialEditText titre, description;
    MaterialButton dateLimite;

    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        id = getIntent().getIntExtra("id",0);
        String titre = getIntent().getStringExtra("titre");
        String description = getIntent().getStringExtra("description");
        String date = getIntent().getStringExtra("date");

        ((MaterialEditText) findViewById(R.id.description)).setText(description);
        ((MaterialButton) findViewById(R.id.dateLimite)).setText(date);
        ((MaterialEditText) findViewById(R.id.titre)).setText(titre);


        dateLimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(EditTaskActivity.this, R.style.MyDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateLimite.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    // modification d'une tâche dans la base de donnée
    public void EditTask(View view) {

        String Titre = titre.getText().toString();
        String Description = description.getText().toString();
        String DateLimite = dateLimite.getText().toString();

        if (Titre.equals("") || Description.equals("")) {
            Toast.makeText(getApplicationContext(), "Remplissez tous les champs !", Toast.LENGTH_SHORT).show();

        } else {

            Task t = new Task(Titre, user_id, Description, DateLimite);

            int verifModif = database.modifier(t);

            if (verifModif > 0) {
                Intent intent = new Intent(this, TaskActivity.class);
                this.startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Erreur, la tâche n'a pas été ajoutée !", Toast.LENGTH_SHORT).show();
            }

        }

    }


}