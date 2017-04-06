package notes.ctian.jhoey.com.noteapp.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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

import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.activities.MainActivity;
import notes.ctian.jhoey.com.noteapp.adapters.NoteListAdapter;
import notes.ctian.jhoey.com.noteapp.data.DBClass;
import notes.ctian.jhoey.com.noteapp.models.Note;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoteListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public View mainView;
    private Paint p = new Paint();

    public NoteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteListFragmentBak.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteListFragment newInstance(String param1, String param2) {
        NoteListFragment fragment = new NoteListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    private FloatingActionButton fab;
    private android.app.DialogFragment fragment;
    FragmentManager fm;

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mainView = view;
        setupList();

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fm = getActivity().getSupportFragmentManager();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new NoteFragment();
                fragment.show(getActivity().getFragmentManager(), "newNote");
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    List<Note> mNotes;
    NoteListAdapter mAdapter;

    public void setupList() {
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.note_recycler_view);
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

                int swipedPosition = viewHolder.getAdapterPosition();
                NoteListAdapter adapter = (NoteListAdapter) mRecyclerView.getAdapter();
                Note selectedNote = adapter.getItem(swipedPosition);

                DBClass dbClass = new DBClass(getActivity().getApplicationContext());
                dbClass.open();
                dbClass.deleteEntry(selectedNote.getId(), "note");
                dbClass.close();

                adapter.removeItem(swipedPosition);


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
                    Note selectedNote = mNotes.get(position);

                    //TODO
                    //1. create     - done
                    //2. retrieve   - done
                    //3. update     - done
                    //4. delete     - pending

                    //populate edit with data   - done
                    //delete function           - pending
                    //save function             - done

                    fragment = new NoteFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", selectedNote.getId()+"");
                    bundle.putString("title", selectedNote.getTitle());
                    bundle.putString("note", selectedNote.getContent());
                    fragment.setArguments(bundle);
                    fragment.show(getActivity().getFragmentManager(), "newNote");


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
