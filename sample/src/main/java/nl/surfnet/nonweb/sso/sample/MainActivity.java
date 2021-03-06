package nl.surfnet.nonweb.sso.sample;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import nl.surfnet.nonweb.sso.SSOCallback;
import nl.surfnet.nonweb.sso.SSOServiceActivity;
import nl.surfnet.nonweb.sso.data.Credential;

public class MainActivity extends AppCompatActivity {

    public static final String SERVER_ENDPOINT = "https://nonweb.demo.surfconext.nl/php-oauth-as/authorize.php";
    public static final String CLIENT_ID = "4dca00da67c692296690e90c50c96b79";
    public static final String SCHEME = "sfoauth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button authorizeButton = (Button)findViewById(R.id.authorize_btn);
        authorizeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Make a callback to handle the response
                SSOCallback callback = new SSOCallback() {

                    @Override
                    public void success(Credential credential) {

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.authorize)
                                .setMessage("Token : " + credential.getAccessToken())
                                .show();
                    }

                    @Override
                    public void failure(String message) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.authorize)
                                .setMessage(message)
                                .show();
                    }
                };

                // Now call to authorize with a clientId/customerId, endpoint, schema and the option callback
                SSOServiceActivity.authorize(v.getContext(), CLIENT_ID, SERVER_ENDPOINT, SCHEME, callback);
            }
        });
    }
}
