package bastien.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bastien.todolist.Database.UserDAO;

public class MainActivity extends AppCompatActivity {


    UserDAO database;

    EditText email, password;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new UserDAO(getApplicationContext());

        sharedPreferences = getApplicationContext().getSharedPreferences("user", 0);

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);

    }

    // lorsque l'utilisateur clique sur le bouton connexion
    public void Connexion(View view) {

        String Email = email.getText().toString();
        String Password = password.getText().toString();


        // Verifie que les champs sont bien remplie sinon un message avertie l'utilisateur
        if (Email.equals("") || Password.equals("")) {

            Toast.makeText(this, "Veuillez remplir tous les champs !", Toast.LENGTH_LONG).show();
        } else {

            SharedPreferences.Editor editor = sharedPreferences.edit();

            boolean verifIdentifiants = database.verificationUtilisateur(Email, Password);

            if (verifIdentifiants) {
                editor.putString("Email", Email);
                editor.putString("Username", database.getUserName(Email));
                editor.putLong("userId", database.getId(Email));
                editor.commit();

                Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, TaskActivity.class);
                this.startActivity(intent);
            } else {
                Toast.makeText(this, "Identifiants erronés !", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void Inscription(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
