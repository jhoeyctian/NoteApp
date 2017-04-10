package notes.ctian.jhoey.com.noteapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.models.TodoList;

/**
 * Created by jhoey on 4/4/2017.
 */
public class TodoSummaryListAdapter extends RecyclerView.Adapter<TodoSummaryListAdapter.ViewHolder>{

    private List<TodoList> todoLists;
    private Context mContext;

    public TodoSummaryListAdapter(List<TodoList> todoList, Context context){
        todoLists = todoList;
        mContext = context;
    }

    public void removeItem(int position) {
        todoLists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, todoLists.size());
    }

    public TodoList getItem(int position) {
        return todoLists.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo_summary_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.todoTitle.setText(todoLists.get(position).getTitle()+"");
        holder.todoCreateDate.setText(todoLists.get(position).getReadableModifiedDate());
    }

    @Override
    public int getItemCount() {
        return todoLists.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView todoTitle, todoCreateDate;

        public ViewHolder(View itemView) {
            super(itemView);
            todoTitle = (TextView)itemView.findViewById(R.id.text_view_todo_title);
            todoCreateDate = (TextView)itemView.findViewById(R.id.text_view_todo_date);
        }

    }
}
