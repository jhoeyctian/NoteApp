package notes.ctian.jhoey.com.noteapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.Date;
import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.adapters.NoteListAdapter;
import notes.ctian.jhoey.com.noteapp.data.DBClass;
import notes.ctian.jhoey.com.noteapp.models.Note;

public class NoteListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    public View mainView;

    public NoteListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    private android.app.DialogFragment fragment;
    FragmentManager fm;

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mainView = view;
        setupList();

        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    List<Note> mNotes;
    NoteListAdapter mAdapter;

    public void setupList() {
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.note_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int swipedPosition = viewHolder.getAdapterPosition();
                NoteListAdapter adapter = (NoteListAdapter) mRecyclerView.getAdapter();
                Note selectedNote = adapter.getItem(swipedPosition);

                DBClass dbClass = new DBClass(getActivity().getApplicationContext());
                dbClass.open();
                dbClass.deleteEntry(selectedNote.getId(), "note");
                dbClass.close();

                adapter.removeItem(swipedPosition);

            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        DBClass dbClass = new DBClass(getContext());
        dbClass.open();
        mNotes = dbClass.getNotes();
        dbClass.close();

        mAdapter = new NoteListAdapter(mNotes, getActivity());
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
