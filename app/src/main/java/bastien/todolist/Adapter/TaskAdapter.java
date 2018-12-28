package bastien.todolist.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bastien.todolist.Data.Task;
import bastien.todolist.R;

/**
 * Cette classe permet de gérer le RecyclerView de TaskActivity qui permet d'afficher en temps réel les tâches de l'utilisateur
 * Si une tâche est supprimer, ajouter, modifier, elle doit être automatiquement être mise à jour visuellement sur l'application, c'est le travail
 * du RecyclerView
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private Context context;
    private List<Task> list;

    public ButtonAdapterListener onClickListener;

    public TaskAdapter(Context context, List<Task> list, ButtonAdapterListener onClickListener) {
        this.context = context;
        this.list = list;
        this.onClickListener = onClickListener;
    }


    /**
     * Ici, on définit le layout que chaque tâche va avoir dans le RecyclerView
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View taskView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_task, viewGroup, false);
        return new MyViewHolder(taskView);
    }

    /**
     * On attribut les valeurs du layout à celles de la tâche
     * Si, on clique sur une checkbox, on met à jour sa valeur
     * La classe Task possède un attribut isChecked qui sera mis à jour si on clique sur la checkBox
     * @param myViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final Task task = list.get(i);

        myViewHolder.titre.setText(task.getTitre());
        myViewHolder.description.setText(task.getDescription());

        //myViewHolder.checkbox.setChecked(list.get(i).getChecked());

        /*yViewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Integer pos = (Integer) myViewHolder.checkbox.getTag();
                Toast.makeText(context, task.getTitre() + " clicked!", Toast.LENGTH_SHORT).show();

                if (task.getChecked()) {
                    task.setChecked(false);
                    myViewHolder.checkbox.setChecked(false);
                } else {
                    task.setChecked(true);
                    myViewHolder.checkbox.setChecked(true);
                }
            }
        });*/


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeTask(int position) {

        Task task = list.get(position);

        list.remove(position);
        notifyItemRemoved(position);

    }


    public void restoreTask(Task task, int position) {
        list.add(position, task);
        notifyItemInserted(position);
    }

    /**
     * On construit le visuel de la tâche en attribuant chaque attribut -> un id
     */
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView titre, description;


        public RelativeLayout layout_task;

        public Button share, edit, delete;

        public MyViewHolder(View taskView) {
            super(taskView);

            titre = taskView.findViewById(R.id.titre);
            description = taskView.findViewById(R.id.description);

            layout_task = taskView.findViewById(R.id.layout_task_list);

            share = taskView.findViewById(R.id.buttonPartager);
            edit = taskView.findViewById(R.id.buttonModifier);
            delete = taskView.findViewById(R.id.buttonSupprimer);


            // si l'utilisateur clique sur le bouton partager, on transmet View et la position
            // de l'adapter où le bouton déclenché se trouve à l'interface correspondante qui sera définie dans TaskActivity
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.shareOnClick(v,getAdapterPosition());
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.editOnClick(v,getAdapterPosition());
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.deleteOnClick(v,getAdapterPosition());
                }
            });

        }

    }

    public interface ButtonAdapterListener {

        void shareOnClick(View v, int position);

        void editOnClick(View v, int position);

        void deleteOnClick(View v, int position);
    }


}