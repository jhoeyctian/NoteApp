package notes.ctian.jhoey.com.noteapp.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.adapters.TodoSummaryListAdapter;
import notes.ctian.jhoey.com.noteapp.models.TodoList;

/**
 * Created by jhoey on 4/7/2017.
 */

public class ToDoSummaryFragment extends Fragment {

    FloatingActionButton todoAdd;
    DialogFragment fragment;
    View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_todo_summary, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainView = view;

        initUI(view);
        addHandlers();
        setupList();
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

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    List<TodoList> mNotes;
    TodoSummaryListAdapter mAdapter;

    public void setupList() {
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.todo_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final GestureDetector mGestureDetector =
                new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener(){
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                });

        final ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                /*int swipedPosition = viewHolder.getAdapterPosition();
                NoteListAdapter adapter = (NoteListAdapter) mRecyclerView.getAdapter();
                Note selectedNote = adapter.getItem(swipedPosition);

                DBClass dbClass = new DBClass(getActivity().getApplicationContext());
                dbClass.open();
                dbClass.deleteEntry(selectedNote.getId(), "note");
                dbClass.close();

                adapter.removeItem(swipedPosition);
*/

            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView,
                                        RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    int position = recyclerView.getChildLayoutPosition(child);
                    /*Note selectedNote = mNotes.get(position);

                    fragment = new NoteFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", selectedNote.getId()+"");
                    bundle.putString("title", selectedNote.getTitle());
                    bundle.putString("note", selectedNote.getContent());
                    fragment.setArguments(bundle);
                    fragment.show(getActivity().getFragmentManager(), "newNote");*/


                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        /*DBClass dbClass = new DBClass(getContext());
        dbClass.open();
        mNotes = dbClass.getNotes();
        dbClass.close();*/

        mNotes = new ArrayList<>();

        for (int x = 0; x < 12; x++){
            TodoList todoList = new TodoList();
            todoList.setTitle("title " + x);
            todoList.setDate(new Date()+"");
            mNotes.add(todoList);
        }

        mAdapter = new TodoSummaryListAdapter(mNotes, getActivity());
        mRecyclerView.setAdapter(mAdapter);

    }
}
