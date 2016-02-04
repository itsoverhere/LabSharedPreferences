package mobapde.edu.labsharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivty extends AppCompatActivity {

    EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_log_in_activty);

        etUsername = (EditText) findViewById(R.id.et_username);
        Button buttonSubmit = (Button) findViewById(R.id.button_submit);

        if(getIntent().getExtras()!=null &&
                getIntent().getExtras().getBoolean(MainActivity.SP_KEY_USERNAME)){
            etUsername.setText(PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                    .getString(MainActivity.SP_KEY_USERNAME, null));
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the editor to update the shared preferences
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
                editor.putString(MainActivity.SP_KEY_USERNAME, etUsername.getText().toString());
                editor.commit();

                // SharedPreferences are "Shared" because components of the app may access the same preferences file
                // If you want your preferences to be activity-specific (ie Only LogInActivity can access it, call getPreferences instead.
                // An app may also create multiple Preference files:
                /*
                    getSharedPreferences(prefFileName, MODE_PRIVATE);
                    where
                        prefFileName is a String that will be the name of your preferences file
                        MODE_PRIVATE indicates that this preference file is only accessible by the app
                            Before, there were also MODE_WORLD_READABLE and MODE_WORLD_WRITABLE options
                                MODE_WORLD_READABLE means that any app can read your preference file
                                MODE_WORLD_WRITABLE means that any app can modify/update your preference file
                * */

                setResult(RESULT_OK);
                finish();
            }
        });

    }

}
