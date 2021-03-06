package com.example.josh.hello_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Magic8BallAnswer extends AppCompatActivity {
    String question;
    String thinking;
    String answer;

    EditText nameInput;
    private TextView textOut;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic8_ball_answer);

        //Defines the input (this is the String that contains the user's question)
        nameInput = (EditText) findViewById(R.id.userQuestion);

        textOut = (TextView) findViewById(R.id.MagicResponse);

        //What the program will say before it comes up with its answer.
        thinking = "Hmmmm let me think about this";

        //back button so that the user can go back to the main menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        //This defines the button the user presses when they want their question to be answered
        submitButton = (Button) findViewById(R.id.SubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets the question and calls for it to get the answer
                question = nameInput.getText().toString();
                textOut.setText(thinking);
                getAnswer(question);
                //*TODO* delay for 5 seconds
                textOut.setText(answer);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void getAnswer(String x){
        //checks to see if the users actually put any text in the Question text box
        if (x.length() == 0) {
            Toast.makeText(getApplicationContext(), "Oops, you didn't enter anything into the question prompt. Ask a question first before hitting submit", Toast.LENGTH_LONG).show();
        }
        //will now get a random number from 1 to 20 and reply a specific response that correlates to that number
        else {
            Random r = new Random();
            int number = r.nextInt(20) + 1;
            if (number == 1){
                //It is certain
                answer = "It is certain";

            }
            if (number == 2){
                //It is decidedly so
                answer = "It is decidedly so";

            }
            if (number == 3){
                //Without a doubt
                answer = "Without a doubt";

            }
            if (number == 4){
                //Yes, definitely
                answer = "Yes definitely";

            }
            if (number == 5){
                //You may rely on it
                answer = "You may rely on it";

            }
            if (number == 6){
                //As I see it, yes
                answer = "As I see it, yes";

            }
            if (number == 7){
                //Most Likely
                answer = "Most Likely";

            }
            if (number == 8){
                //Outlook = Good
                answer = "Outlook = Good";

            }
            if (number == 9){
                //Yes
                answer = "Yes";

            }
            if (number == 10){
                //All signs point to yes
                answer = "All signs point to yes";

            }
            if (number == 11){
                //Reply is Hazy, Try again
                answer = "Reply is Hazy, try again please";

            }
            if (number == 12){
                //Ask me again later
                answer = "Ask me again later";

            }
            if (number == 13){
                //I can't tell you now
                answer = "I can't tell you now";

            }
            if (number == 14){
                //I cannot predict that now
                answer = "I cannot predict that now";

            }
            if (number == 15){
                //Let me concentrate and ask again, please
                answer = "Let me concentrate and ask again, please";

            }
            if (number == 16){
                //Don't count on it
                answer = "Don't count on it";

            }
            if (number == 17){
                //No
                answer = "No";

            }
            if (number == 18){
                //My sources say no
                answer = "My sources say no";

            }
            if (number == 19){
                //The outlook is not so good.
                answer = "The outlook is not so good";

            }
            if (number == 20){
                //Very doubtful
                answer = "Very doubtful";

            }

        }

    }
}
