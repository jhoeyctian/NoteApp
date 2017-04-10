package notes.ctian.jhoey.com.noteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /*final boolean customTitleSupported =
                requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_test);
        if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                    R.layout.test_title_bar);
        }*/
       /* final TextView myTitleText = (TextView) findViewById(R.id.myTitle);
        if (myTitleText != null) {
            myTitleText.setText("NEW TITLE");
            // user can also set color using "Color" and then
            // "Color value constant"
            // myTitleText.setBackgroundColor(Color.GREEN);
        }*/
    }
}
