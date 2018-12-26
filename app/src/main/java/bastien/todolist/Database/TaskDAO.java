package bastien.todolist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import bastien.todolist.Data.Task;

public class TaskDAO extends DatabaseDAO {

    public static final String TABLE_NAME = "Task";
    public static final String KEY = "id";
    public static final String USER_ID = "user_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DATE_LIMITE = "date_limite";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_ID + " INTEGER" + TITLE + " TEXT, " + DESCRIPTION + " TEXT, " + DATE_LIMITE + " TEXT);";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public TaskDAO(Context pContext) {
        super(pContext);
    }

    /**
     * @param m le métier à ajouter à la base
     */
    public long ajouter(Task m) {
        open();
        ContentValues value = new ContentValues();
        value.put(TaskDAO.TITLE, m.getTitre());
        value.put(TaskDAO.USER_ID, m.getUser_id());
        value.put(TaskDAO.DESCRIPTION, m.getDescription());
        value.put(TaskDAO.DATE_LIMITE, m.getDateLimite());
        return database.insert(TaskDAO.TABLE_NAME, null, value);
    }

    /**
     * @param id l'identifiant du métier à supprimer
     */
    public void supprimer(long id) {
        database.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * @param m le métier modifié
     */
    public void modifier(Task m) {
        open();
        ContentValues value = new ContentValues();
        value.put(DESCRIPTION, m.getDescription());
        database.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(m.getId())});
    }

    /**
     * @param id l'identifiant du métier à récupérer
     */
    public void selectionner(long id) {
        Cursor c = database.rawQuery("select " + TABLE_NAME + " from " + TABLE_NAME + " where id = ?", new String[]{Long.toString(id)});

    }

    public List<Task> getAllTaskByUserId(Long user_id) {
        List<Task> list = new ArrayList<Task>();
        open();
        Cursor cursor = database.query("Task", null, "user_id=?", new String[]{Long.toString(user_id)}, null, null, null);
//        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME + " where id = ?", new String[]{Long.toString(user_id)});

        if (cursor.moveToFirst()) {
            do {
                Task t = new Task();
                t.setId(cursor.getInt(0));
                t.setUser_id(cursor.getLong(1));
                t.setDescription(cursor.getString(3));
                t.setTitre(cursor.getString(2));
                t.setDateLimite(cursor.getString(4));

                list.add(t);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }


}
