package notes.ctian.jhoey.com.noteapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.adapters.TodoListAdapter;
import notes.ctian.jhoey.com.noteapp.models.ToDo;

/**
 * Created by jhoey on 4/6/2017.
 */

public class ToDoFragment extends BaseFragment {

    ListView todo_list_view;
    TodoListAdapter todoListAdapter;
    List<ToDo> toDoList;

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

        todoListAdapter = new TodoListAdapter(getActivity().getApplicationContext(), toDoList);
        todo_list_view.setAdapter(todoListAdapter);
    }

    private void write(String log){
        System.out.println("********** "+log);
    }

    private void initData() {
        toDoList = new ArrayList<>();

        for (int x = 0; x < 10 ; x++){
            ToDo toDo = new ToDo();
            toDo.setTodo(x+" test");
            toDoList.add(toDo);
        }
    }

    private void initUI(View view) {
        todo_list_view = (ListView) view.findViewById(R.id.todo_list_view);
    }
}
