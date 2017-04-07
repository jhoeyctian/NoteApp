package notes.ctian.jhoey.com.noteapp.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.adapters.TodoItemsListAdapter;
import notes.ctian.jhoey.com.noteapp.models.TodoItem;

/**
 * Created by jhoey on 4/6/2017.
 */

public class ToDoItemsFragment extends BaseFragment {

    ListView todo_list_view;
    TodoItemsListAdapter todoListAdapter;
    List<TodoItem> toDoList;
    TextView todo_add_item;
    DialogFragment addItemFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        write("initializing ui");
        initUI(view);

        write("populating data");
        initData();

        write("data size : " + toDoList.size());

        todoListAdapter = new TodoItemsListAdapter(getActivity().getApplicationContext(), toDoList);
        todo_list_view.setAdapter(todoListAdapter);

        todo_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addItemFragment = new TodoAddItemFormFragment();
                addItemFragment.show(getActivity().getFragmentManager(), "newItem");

            }
        });
    }

    private void write(String log){
        System.out.println("********** "+log);
    }

    private void initData() {
        toDoList = new ArrayList<>();

        for (int x = 0; x < 5 ; x++){
            TodoItem toDo = new TodoItem();
            toDo.setTodo(x+" test");
            toDoList.add(toDo);
        }
    }

    private void initUI(View view) {
        todo_list_view = (ListView) view.findViewById(R.id.todo_list_view);
        todo_add_item = (TextView) view.findViewById(R.id.todo_add_item);
    }
}
