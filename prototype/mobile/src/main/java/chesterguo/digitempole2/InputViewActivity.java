package chesterguo.digitempole2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

/**
 * Created by ChesterGuo on 12/11/14.
 */
public class InputViewActivity extends Activity {

    public String indexnumber;
    private Firebase ref = new Firebase("https://intense-heat-6260.firebaseio.com/beacon1/beacon1/topicList/topics/0/postList/");
    Button mButton;
    EditText mEditName;
    EditText mEditText;
    private String userName;
    private String userText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        // get content from input
        mButton = (Button)findViewById(R.id.buttonSubmit);
        mEditName   = (EditText)findViewById(R.id.input_name);
        mEditText   = (EditText)findViewById(R.id.input_text);
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        // send the input to firebase
                        userName = mEditName.getText().toString();
                        userText = mEditText.getText().toString();
                        Firebase usersRef = ref.child("posts");

                        indexnumber = getCurrentTimeStamp();

                        Map<String, String> indexMap = new HashMap<String, String>();
                        indexMap.put("postText", userText);
                        indexMap.put("postUser", userName);
                        indexMap.put("postTime", indexnumber);

                        Map<String, Map<String, String>> users = new HashMap<String, Map<String, String>>();
                        users.put(indexnumber, indexMap);

                        usersRef.setValue(users);
                    }
                });

        String myURL = "";
        myURL += "https://intense-heat-6260.firebaseio.com/beacon1/beacon1/topicList/topics/";
        myURL += "postList/posts/";
        //public Firebase ref = new Firebase(myURL);

    }

    //get the current time stamp
    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

