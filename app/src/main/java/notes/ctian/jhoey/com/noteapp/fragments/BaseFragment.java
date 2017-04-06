package notes.ctian.jhoey.com.noteapp.fragments;

import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by jhoey on 4/6/2017.
 */

public class BaseFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
    }
}
