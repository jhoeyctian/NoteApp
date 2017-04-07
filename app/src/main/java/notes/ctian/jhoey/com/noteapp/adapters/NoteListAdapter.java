package notes.ctian.jhoey.com.noteapp.adapters;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.activities.MainActivity;
import notes.ctian.jhoey.com.noteapp.fragments.NoteFragment;
import notes.ctian.jhoey.com.noteapp.fragments.NoteListFragment;
import notes.ctian.jhoey.com.noteapp.models.Note;

/**
 * Created by jhoey on 4/4/2017.
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder>{

    private List<Note> mNotes;
    private Context mContext;

    public NoteListAdapter(List<Note> notes, Context context){
        mNotes = notes;
        mContext = context;

    }

    public void removeItem(int position) {
        mNotes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mNotes.size());
    }

    public Note getItem(int position) {
        return mNotes.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.noteId.setText(mNotes.get(position).getId()+"");
        holder.noteContent.setText(mNotes.get(position).getContent()+"");
        holder.noteTitle.setText(mNotes.get(position).getTitle());
        holder.noteCreateDate.setText(mNotes.get(position).getReadableModifiedDate());

        holder.note_list_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment fragment = new NoteFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", holder.noteId.getText()+"");
                bundle.putString("title", holder.noteTitle.getText()+"");
                bundle.putString("note", holder.noteContent.getText()+"");
                fragment.setArguments(bundle);
                fragment.show(((MainActivity) mContext).getFragmentManager(), "newNote");

            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView noteTitle, noteCreateDate, noteId, noteContent;
        public final LinearLayout note_list_id;

        public ViewHolder(View itemView) {
            super(itemView);
            noteContent = (TextView) itemView.findViewById(R.id.text_view_note_content);
            noteId = (TextView)itemView.findViewById(R.id.text_view_note_id);
            noteTitle = (TextView)itemView.findViewById(R.id.text_view_note_title);
            noteCreateDate = (TextView)itemView.findViewById(R.id.text_view_note_date);
            note_list_id = (LinearLayout) itemView.findViewById(R.id.note_list_id);
        }

    }
}
