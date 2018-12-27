package bastien.todolist.Data;

public class Task {

    private long id;
    private String titre;
    private Long user_id;
    private String description;
    private String dateLimite;

    private boolean isChecked;

    public Task(String titre, Long user_id, String description, String dateLimite) {
        this.titre = titre;
        this.user_id = user_id;
        this.description = description;
        this.dateLimite = dateLimite;
    }

    public Task() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String title) {
        this.titre = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(String dateLimite) {
        this.dateLimite = dateLimite;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
