package bastien.todolist.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView titre, description;

        //public CheckedTextView checkbox;

        public RelativeLayout layout_task;

        public MyViewHolder(View taskView) {
            super(taskView);

            titre = taskView.findViewById(R.id.titre);
            description = taskView.findViewById(R.id.description);

            layout_task = taskView.findViewById(R.id.layout_task_list);


            //checkbox = itemView.findViewById(R.id.task_checkbox);
            //checkbox.setClickable(false);
//            itemView.setOnClickListener(this);
        }

//        void bind(int position) {
//            // use the sparse boolean array to check
//            if (!list.get(position).getChecked()) {
//                mCheckedTextView.setChecked(false);}
//            else {
//                mCheckedTextView.setChecked(true);
//            }
//        }
//
//        @Override
//        public void onClick(View v) {
//            int adapterPosition = getAdapterPosition();
//            if (!list.get(adapterPosition).getChecked()) {
//                mCheckedTextView.setChecked(true);
//                list.get(adapterPosition).setChecked(true);
//            }
//            else  {
//                mCheckedTextView.setChecked(false);
//                list.get(adapterPosition).setChecked(false);
//            }
//        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }
    }


}
