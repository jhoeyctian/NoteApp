package notes.ctian.jhoey.com.noteapp.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.models.ToDo;

/**
 * Created by jhoey on 4/6/2017.
 */

public class TodoListAdapter extends BaseAdapter {

    private List<ToDo> toDoList;
    private Context context;

    public TodoListAdapter(Context context, List<ToDo> toDoList) {
        this.context = context;
        this.toDoList = toDoList;
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        View view = convertView;
        final ViewHolder viewHolder;

        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.todo_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.todo.setText(toDoList.get(position).getTodo()+"");
        viewHolder.todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.todo.setPaintFlags(viewHolder.todo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.status.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    class ViewHolder {
        TextView todo;
        ImageView status;

        public ViewHolder(View view){
            todo = (TextView) view.findViewById(R.id.todo_text);
            status = (ImageView) view.findViewById(R.id.todo_img);
        }
    }
}
