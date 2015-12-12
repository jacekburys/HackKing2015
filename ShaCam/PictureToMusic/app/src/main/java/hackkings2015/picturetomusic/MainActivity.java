package hackkings2015.picturetomusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText messageInput;
    Button send;
    String MESSAGES_ENDPOINT = "INSERT URL HERE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageInput = (EditText)findViewById(R.id.message_input);
        send = (Button)findViewById(R.id.send_button);
        setContentView(R.layout.activity_main);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        postMessage();
    }

    private void postMessage(){
        String text = messageInput.getText().toString();
        String username = "Anonymous";

        // return if the text is blank
        if (text.equals("")) {
            return;
        }


        RequestParams params = new RequestParams();

        // set our JSON object
        params.put("text", text);
        params.put("name", username);
        params.put("time", new Date().getTime());

        // create our HTTP client
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MESSAGES_ENDPOINT, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                messageInput.setText("");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(
                        getApplicationContext(),
                        "Something went wrong :(",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
