package notes.ctian.jhoey.com.noteapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import notes.ctian.jhoey.com.noteapp.R;
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

        holder.noteTitle.setText(mNotes.get(position).getTitle());
        holder.noteCreateDate.setText(mNotes.get(position).getReadableModifiedDate());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView noteTitle, noteCreateDate;



        public ViewHolder(View itemView) {
            super(itemView);
            noteTitle = (TextView)itemView.findViewById(R.id.text_view_note_title);
            noteCreateDate = (TextView)itemView.findViewById(R.id.text_view_note_date);
        }

    }
}
