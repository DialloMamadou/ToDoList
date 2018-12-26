//package bastien.todolist.Helper;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//
//    private final Context context;
//
//    public DatabaseHelper(Context _context) {
//        super(_context, "todolist.db", null, 1);
//        context = _context;
//
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase _db) {
//        try {
//            _db.execSQL("create table Task( id integer primary key autoincrement,contenu  text,user_id integer,createdAt text, modifiedAt text, FOREIGN KEY(user_id) REFERENCES User(id));");
//            _db.execSQL("create table User( id integer primary key autoincrement,username  text,email text,password text);");
//        }catch(Exception er){
//            Log.e("Error","exception");
//        }
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
//        _db.execSQL("DROP TABLE IF EXISTS " + "User");
//        _db.execSQL("DROP TABLE IF EXISTS " + "Task");
//
//        // Create a new one.
//        onCreate(_db);
//    }
//
//    // On insert un utilisateur dans la base de donnée
//    public boolean insererUtilisateur(String email, String password, String username) {
//
//        try {
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues newUser = new ContentValues();
//
////            newUser.put("firstname", firstname);
//            newUser.put("username", username);
//            newUser.put("email", email);
//            newUser.put("password", password);
//
//            long resultat = db.insert("User", null, newUser);
//            System.out.print(resultat);
//            Toast.makeText(context, "Informations sauvegardées !", Toast.LENGTH_LONG).show();
//
//            if(resultat == -1) return false;
//            else return true;
//        } catch (Exception ex) {
//            System.out.println("Exceptions " + ex);
//            Log.e("Note", "Veuillez remplir tous les champs !");
//        }
//
//        return false;
//
//    }
//
//    public boolean verificationMail(String email) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM User where email=?", new String[]{email});
//        if(cursor.getCount()>0) return false;
//        else return true;
//    }
//
//    public boolean verificationUtilisateur(String email,String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM User where email=? and password=?", new String[]{email,password});
//        if(cursor.getCount()>0) return false;
//        else return true;
//    }
//
//    // retourne le password de l'utilisateur
//    public String getPassword(String email) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query("User", null, "email=?", new String[]{email}, null, null, null);
//        if (cursor.getCount() < 1)
//            return "Le nom d'utilisateur n'existe pas !";
//        cursor.moveToFirst();
//        String getPassword = cursor.getString(cursor.getColumnIndex("password"));
//        return getPassword;
//    }
//
//    public String getUserName(String email){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query("User", null, "email=?", new String[]{email}, null, null, null);
//        if (cursor.getCount() < 1) // UserName Not Exist
//            return "";
//        cursor.moveToFirst();
//        String getName = cursor.getString(cursor.getColumnIndex("name"));
//        return getName;
//    }
//
//    // retourne l'id de l'utilisateur
//    public Integer getId(String email) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query("User", null, "email=?", new String[]{email}, null, null, null);
//        if (cursor.getCount() < 1) // UserName Not Exist
//            return 0;
//        cursor.moveToFirst();
//        Integer getId = cursor.getInt(cursor.getColumnIndex("id"));
//        return getId;
//    }
//
//
////    public List getAllTaskByUserId(Integer user_id) {
////        List movieDetailsList = new ArrayList();
////        String selectQuery = "SELECT * FROM " + Task
////                + " ORDER BY " + KEY_MOVIE_ID + " DESC";
////        SQLiteDatabase db = this.getReadableDatabase();
////        Cursor cursor = db.rawQuery(selectQuery, null);
////
////        //if TABLE has rows
////        if (cursor.moveToFirst()) {
////            //Loop through the table rows
////            do {
////                MovieDetailsVO movieDetails = new MovieDetailsVO();
////                movieDetails.setMovieId(cursor.getInt(0));
////                movieDetails.setMovieName(cursor.getString(1));
////                movieDetails.setGenre(cursor.getString(2));
////                movieDetails.setYear(cursor.getInt(3));
////                movieDetails.setRating(cursor.getDouble(4));
////
////                //Add movie details to list
////                movieDetailsList.add(movieDetails);
////            } while (cursor.moveToNext());
////        }
////        db.close();
////        return movieDetailsList;
////
////
////
////    }
//
//
//}
