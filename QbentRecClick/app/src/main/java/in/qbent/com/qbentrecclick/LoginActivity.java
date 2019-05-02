package in.qbent.com.qbentrecclick;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    Button backButton;
    EditText username,password;
    TextView textView;
    String data = " ";
    String userData = " ";
    private User userModel;
    //ConstraintLayout userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.myLogin);//login button//
        backButton = (Button) findViewById(R.id.backHome);//back button//

        username = findViewById(R.id.uName);//username field//
        password = findViewById(R.id.uPass);//password field//
        textView = (TextView) findViewById(R.id.finalResult);

    }

    private String httpPost(String myUrl) throws IOException, JSONException
    {
        String result = "";

        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        // 2. build JSON object
        JSONObject jsonObject = buidJsonObject();

        // 3. add JSON content to POST request body
        setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        InputStream inputStream = conn.getInputStream();//read the data//
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//read the data from input stream//
        String line = " ";
        while(line != null)
        {
            line=bufferedReader.readLine();//read each lines of the JSON file//
            data =data + line;//All JSON file will be in data//
        }

//        JSONObject jsonObject1 = new JSONObject(data);
//
//        User user = new User(
//                jsonObject1.getString("name"),
//                jsonObject1.getString("token")
//        );
//
//        userModel = user;
        // 5. return response message
        return data;


    }

    public class FetchData extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                return httpPost(strings[0]);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return e.toString();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return "Error!";
            }
        }
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            textView.setText(s);
//            userModel = userModel;
        }
    }

    public void goMain(View view)
    {
        new FetchData().execute("http://148.72.209.27:8888/api/external/authenticate");
}

    private JSONObject buidJsonObject() throws JSONException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("userName",username.getText().toString());
        jsonObject.accumulate("password",password.getText().toString());
        return jsonObject;
    }

    private void setPostRequestContent(HttpURLConnection httpURLConnection, JSONObject jsonObject) throws IOException
    {
        OutputStream os = httpURLConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i(MainActivity.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();
    }

    public void backToHome(View view)
    {
        Intent intentBack = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intentBack);
    }

}
