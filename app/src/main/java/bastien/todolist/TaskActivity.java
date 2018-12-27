package bastien.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bastien.todolist.Adapter.TaskAdapter;
import bastien.todolist.Data.Task;
import bastien.todolist.Database.TaskDAO;


public class TaskActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;

    TaskDAO taskDAO;

    Button showPopupBtn, closePopupBtn;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;

    private DrawerLayout menuDrawerlayout;
    private ActionBarDrawerToggle menuToggle;
    private ActionBarOverlayLayout actionBar;

    private List<Task> tasks;

    private TaskAdapter taskAdapter;
    private RecyclerView recyclerView;
    private DrawerLayout drawerLayout;


    private List<Task> checkedTasks = new ArrayList<Task>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // on charge les données de l'utilisateur
        sharedPreferences = getApplicationContext().getSharedPreferences("user", 0);
        Long user_id = sharedPreferences.getLong("userId", 0);

        taskDAO = new TaskDAO(this);


        // Menu Toggle

        menuDrawerlayout = findViewById(R.id.layout_task_list);
        menuToggle = new ActionBarDrawerToggle(this, menuDrawerlayout, R.string.ouvrir, R.string.fermer);
        menuDrawerlayout.addDrawerListener(menuToggle);
        menuToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.menu_toggle1);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }


        // Popup ajout rapide

        showPopupBtn = (Button) findViewById(R.id.showPopupBtn);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);


        /* RecyclerView */
        // on lit les données
        tasks = taskDAO.getAllTaskByUserId(user_id);


        taskAdapter = new TaskAdapter(this, tasks);
        recyclerView = findViewById(R.id.recycler_view);
        drawerLayout = findViewById(R.id.layout_task_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(taskAdapter);




    }

    public void shareTask(View view) {

        Iterator<Task> it = tasks.iterator();

        while(it.hasNext()){
            Task t = it.next();
            if(t.getChecked()) {
                checkedTasks.add(t);
            }
        }
        if(!checkedTasks.isEmpty()){
            Toast.makeText(this, checkedTasks.get(0).getTitre() + " clicked!", Toast.LENGTH_SHORT).show();
        }


    }

    public void addTask2(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) TaskActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.activity_add_task, null);

        closePopupBtn = (Button) customView.findViewById(R.id.quit);

        //instantiate popup window
        popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        //display the popup window
        popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

        //close the popup window on button click
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.ajoutTask) {
//            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AddTaskActivity.class);
            this.startActivity(intent);
        }

        if (id == R.id.patageTask) {
            Intent intent = new Intent(this, SendTaskActivity.class);
            this.startActivity(intent);
        }

        //noinspection SimplifiableIfStatement
        if (menuToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        Toast.makeText(this, Integer.toString(menuItem.getItemId()), Toast.LENGTH_LONG).show();


        if (id == R.id.nvl_tache) {
//            Toast.makeText(this, "Add liste", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AddTaskActivity.class);
            this.startActivity(intent);
            return true;
        }

        return false;
    }



//    public boolean getChecked() {
//        return isChecked;
//    }
//
//    public void setChecked(boolean checked) {
//        isChecked = checked;
//    }

//    public void addTask(View view) {
//        // Initialize a new instance of LayoutInflater service
//        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//
//        // Inflate the custom layout/view
//        View customView = inflater.inflate(R.layout.activity_add3,null);
//
//                /*
//                    public PopupWindow (View contentView, int width, int height)
//                        Create a new non focusable popup window which can display the contentView.
//                        The dimension of the window must be passed to this constructor.
//
//                        The popup does not provide any background. This should be handled by
//                        the content view.
//
//                    Parameters
//                        contentView : the popup's content
//                        width : the popup's width
//                        height : the popup's height
//                */
//        // Initialize a new instance of popup window
//        mPopupWindow = new PopupWindow(
//                customView,
//                ActionBar.LayoutParams.WRAP_CONTENT,
//                ActionBar.LayoutParams.WRAP_CONTENT
//        );
//
//        // Set an elevation value for popup window
//        // Call requires API level 21
//        if(Build.VERSION.SDK_INT>=21){
//            mPopupWindow.setElevation(5.0f);
//        }
//
//        // Get a reference for the custom view close button
//        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.dateLimit);
//
//        // Set a click listener for the popup window close button
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Dismiss the popup window
//                mPopupWindow.dismiss();
//            }
//        });
//
//                /*
//                    public void showAtLocation (View parent, int gravity, int x, int y)
//                        Display the content view in a popup window at the specified location. If the
//                        popup window cannot fit on screen, it will be clipped.
//                        Learn WindowManager.LayoutParams for more information on how gravity and the x
//                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
//                        to specifying Gravity.LEFT | Gravity.TOP.
//
//                    Parameters
//                        parent : a parent view to get the getWindowToken() token from
//                        gravity : the gravity which controls the placement of the popup window
//                        x : the popup's x location offset
//                        y : the popup's y location offset
//                */
//        // Finally, show the popup window at the center location of root relative layout
//        mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
//    }
}
