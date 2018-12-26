package bastien.todolist.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import bastien.todolist.Data.Task;
import bastien.todolist.R;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private Context context;
    private List<Task> list;


    public TaskAdapter(Context context, List<Task> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View taskView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_task, viewGroup, false);
        return new MyViewHolder(taskView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Task task = list.get(i);

        myViewHolder.titre.setText(task.getTitre());
        myViewHolder.description.setText(task.getDescription());

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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titre, description;

        public RelativeLayout layout_task;

        public MyViewHolder(View taskView) {
            super(taskView);

            titre = taskView.findViewById(R.id.titre);
            description = taskView.findViewById(R.id.description);

            layout_task = taskView.findViewById(R.id.layout_task_list);

        }

    }
}
