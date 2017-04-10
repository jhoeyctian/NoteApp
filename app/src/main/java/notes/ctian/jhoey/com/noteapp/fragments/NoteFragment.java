package notes.ctian.jhoey.com.noteapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.activities.HomeHolder;
import notes.ctian.jhoey.com.noteapp.activities.MainActivity;
import notes.ctian.jhoey.com.noteapp.data.DBClass;
import notes.ctian.jhoey.com.noteapp.models.LinedEditText;

/**
 * Created by jhoey on 4/5/2017.
 */

public class NoteFragment extends BaseFragment {

    EditText edit_text_id;
    EditText edit_text_title;
    LinedEditText edit_text_note;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edit_text_title = (EditText) view.findViewById(R.id.edit_text_title);
        edit_text_note = (LinedEditText) view.findViewById(R.id.edit_text_note);
        edit_text_id = (EditText) view.findViewById(R.id.edit_text_id);

        if(getArguments()!=null && !getArguments().isEmpty()){
            edit_text_id.setText(getArguments().getString("id"));
            edit_text_title.setText(getArguments().getString("title"));
            edit_text_note.setText(getArguments().getString("note"));
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if(edit_text_id.getText().toString().length()>0){
            /** for updating */

            DBClass dbClass = new DBClass(getActivity().getApplicationContext());
            dbClass.open();
            dbClass.updateEntry(

                    edit_text_id.getText()+"",
                    (edit_text_title.getText().toString().length()>0?
                            edit_text_title.getText()+"" : (edit_text_note.getText().toString().split(" ")[0]) ),
                    edit_text_note.getText()+"", "note"

            );
            dbClass.close();

        } else if(edit_text_note.getText().toString().length()>0){
            /** if no title is given, first word of the note will be the title */
            DBClass dbClass = new DBClass(getActivity().getApplicationContext());
            dbClass.open();
            dbClass.createEntry(

                    (edit_text_title.getText().toString().length()>0?
                    edit_text_title.getText()+"" : (edit_text_note.getText().toString().split(" ")[0]) ),
                    edit_text_note.getText()+"", "note"

            );
            dbClass.close();
        }

        //TODO refresh list here

        

        dismiss();

    }
}
