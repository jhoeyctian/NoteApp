package notes.ctian.jhoey.com.noteapp.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;

import notes.ctian.jhoey.com.noteapp.R;

/**
 * Created by jhoey on 4/7/2017.
 */

public class ToDoSummaryFragment extends Fragment {

    FloatingActionButton todoAdd;
    DialogFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_todo_summary, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        addHandlers();
    }

    private void addHandlers() {
        todoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** show to do editor */

                fragment = new ToDoItemsFragment();
                fragment.show(getActivity().getFragmentManager(), "todo");

            }
        });
    }

    private void initUI(View view) {
        todoAdd = (FloatingActionButton) view.findViewById(R.id.todoAdd);
    }
}
