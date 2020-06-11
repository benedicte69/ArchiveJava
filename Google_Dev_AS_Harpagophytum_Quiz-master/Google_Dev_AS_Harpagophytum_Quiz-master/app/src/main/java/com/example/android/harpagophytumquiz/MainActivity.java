package com.example.android.harpagophytumquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    //RadioGroups'score
    private int scoreRadioButtonSetQ1 = 0;
    private int scoreRadioButtonSetQ2 = 0;
    private int totalScoreRadio = 0;
    //Checkboxes'score question 4
    private int boxQ4a = 0;
    private int boxQ4b = 0;
    private int boxQ4c = 0;
    private int boxQ4d = 0;
    private int boxQ4e = 0;
    //Checkboxes'score question 5
    private int boxQ5a = 0;
    private int boxQ5b = 0;
    private int boxQ5c = 0;
    private int boxQ5d = 0;
    //Checkboxes'score question 6
    private int boxQ6a = 0;
    private int boxQ6b = 0;
    private int boxQ6c = 0;
    private int boxQ6d = 0;
    //Multiple right answers'score
    private int  scoreCheckboxQ5=boxQ5c+boxQ5d;
    private int  scoreCheckboxQ6=boxQ6a+boxQ6b;
    private int totalScoreCheckbox = 0;
    //EditText' s score
    private int totalScoreEditText = 0;
    //ranking by question
    private int ranking = 0;
    //final grading
    private int grading = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //The reset button is not visible
        Button resetButton = this.findViewById(R.id.btn_toast_reset);
        resetButton.setVisibility(View.GONE);
        // Removes underlines from the email link
        TextView emailLink = findViewById(R.id.link_email);
        // Makes sure the TextView was instantiated correctly
        if (emailLink != null) {
            removeUnderlines((Spannable) emailLink.getText());
        }
    }

    /**
     * hide keyboard when user taps anywhere else
     * <p>
     * <p>
     * this method comes from:
     * https://stackoverflow.com/questions/8697499/hide-keyboard-when-user-taps-anywhere-else-on-the-screen-in-android
     *
     * @param ev MotionEvent handling
     */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrubs[] = new int[2];
            view.getLocationOnScreen(scrubs);
            float x = ev.getRawX() + view.getLeft() - scrubs[0];
            float y = ev.getRawY() + view.getTop() - scrubs[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * checks which radio button was clicked and adds points (question 1)
     * <p>
     * This method figures out which radio button has been chosen by the user
     * in the RadioGroup of question 1. It adds one point to the ranking if
     * the answer is correct and keeps ranking as it is if the answer is wrong
     *
     * @param view RadioButton views: question 1
     */

    public void onRadioButtonSetQ1Clicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // What is the current ranking?
        scoreRadioButtonSetQ1 = ranking;
        // Checks which radio button was clicked and calls the adequate ranking method if checked
        switch (view.getId()) {
            case R.id.radio_botswana_q1a_true:
                if (checked) {
                    scoreRadioButtonSetQ1 = 1;
                }
                break;
            case R.id.radio_bangladesh_q1b:
                if (checked) {
                    scoreRadioButtonSetQ1 = 0;
                }
                break;
            case R.id.radio_burundi_q1c:
                if (checked) {
                    scoreRadioButtonSetQ1 = 0;
                }
                break;
        }//What is the new ranking?
        ranking = totalScoreRadio = scoreRadioButtonSetQ1 + scoreRadioButtonSetQ2;
    }

    /**
     * checks which radio button was clicked and adds points (question 2)
     * <p>
     * This method figures out which radio button has been chosen by the user
     * in the RadioGroup of question 2. It adds one point to the ranking if
     * the answer is correct and keeps the ranking as it is if the answer is wrong.
     *
     * @param view RadioButton views: question 2
     */

    public void onRadioButtonSetQ2Clicked(View view) {
        // Is the view now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // What is the current ranking?
        scoreRadioButtonSetQ2 = ranking;
        // Check which radio button was clicked and calls the adequate ranking method if checked
        switch (view.getId()) {
            case R.id.radio_migraine_q2a:
                if (checked) {
                    scoreRadioButtonSetQ2 = 0;
                }
                break;
            case R.id.radio_joint_pain_q2b_true:
                if (checked) {
                    scoreRadioButtonSetQ2 = 1;
                }
                break;
            case R.id.radio_lung_edema_q2c:
                if (checked) {
                    scoreRadioButtonSetQ2 = 0;
                }
                break;
        }//What is the new ranking?
        ranking = totalScoreRadio = scoreRadioButtonSetQ2 + scoreRadioButtonSetQ1;
    }

    /**
     * checks which checkBoxes have been clicked in the quiz and adds points
     * <p>
     * This method figures out which checkboxes have been chosen by the user during
     * the quiz. It adds points if the user checks all the good answer and does not if
     * the good answers are partial.
     *
     * @param view Checkboxes
     */

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // What is the current ranking?
        totalScoreCheckbox = ranking;
        int scoreCheckboxQ4 = 0;
        int scoreCheckboxQ5 = 0;
        int scoreCheckboxQ6 = 0;
        // Check which checkbox was clicked
        switch (view.getId()) {
            // Question 4
            case R.id.checkbox_grapple_plant_q4a_true:
                if (checked) {
                    boxQ4a = 1;
                }
                if (!checked) {
                    boxQ4a = 0;
                }
                break;
            case R.id.checkbox_wrestle_root_q4b:
                if (checked) {
                    boxQ4b = 1;
                }
                if (!checked) {
                    boxQ4b = 0;
                }
                break;
            case R.id.checkbox_wood_spider_q4c_true:
                if (checked) {
                    boxQ4c = 1;
                }
                if (!checked) {
                    boxQ4c = 0;
                }
                break;
            case R.id.checkbox_steel_scorpion_q4d:
                if (checked) {
                    boxQ4d = 1;
                }
                if (!checked) {
                    boxQ4d = 0;
                }
                break;
            case R.id.checkbox_devil_claw_q4e_true:
                if (checked) {
                    boxQ4e = 1;
                }
                if (!checked) {
                    boxQ4e = 0;
                }
                break;
            // Question 5
            case R.id.checkbox_Hypnaceae_q5a:
                if (checked) {
                    boxQ5a = 1;
                }
                if (!checked) {
                    boxQ5a = 0;
                }
                break;
            case R.id.checkbox_Ophioglossaceae_q5b:
                if (checked) {
                    boxQ5b = 1;
                }
                if (!checked) {
                    boxQ5b = 0;
                }
                break;
            case R.id.checkbox_Pedaliaceae_q5c_true:
                if (checked) {
                    boxQ5c = 1;
                }
                if (!checked) {
                    boxQ5c = 0;
                }
                break;
            case R.id.checkbox_Sesame_q5d_true:
                if (checked) {
                    boxQ5d = 1;
                }
                if (!checked) {
                    boxQ5d = 0;
                }
                break;
            // Question 6
            case R.id.checkbox_img01_q6a_true:
                if (checked) {
                    boxQ6a = 1;
                }
                if (!checked) {
                    boxQ6a = 0;
                }
                break;
            case R.id.checkbox_img02_q6b_true:
                if (checked) {
                    boxQ6b = 1;
                }
                if (!checked) {
                    boxQ6b = 0;
                }
                break;
            case R.id.checkbox_img03_q6c:
                if (checked) {
                    boxQ6c = 1;
                }
                if (!checked) {
                    boxQ6c = 0;
                }
                break;
            case R.id.checkbox_img04_q6d:
                if (checked) {
                    boxQ6d = 1;
                }
                if (!checked) {
                    boxQ6d = 0;
                }
                break;
        }
        // Question 4
        if((boxQ4d == 0) && ((boxQ4a + boxQ4c + boxQ4e) == 3)){
            scoreCheckboxQ4 = 0;
        }
        if((boxQ4b == 0) && ((boxQ4a + boxQ4c + boxQ4e) ==3)){
            scoreCheckboxQ4=0;
        }
        if((boxQ4d == 0)&& (boxQ4b == 0) && ((boxQ4a + boxQ4c + boxQ4e) ==3)){
            scoreCheckboxQ4 = 3;
        }
        // Question 5
        if((boxQ5a == 0) && ((boxQ5c + boxQ5d) == 2)){
            scoreCheckboxQ5 = 0;
        }
        if((boxQ5b == 0) && ((boxQ5c + boxQ5d) ==2)){
            scoreCheckboxQ5 = 0;
        }
        if((boxQ5a == 0) && (boxQ5b==0) && ((boxQ5c +boxQ5d) == 2)){
            scoreCheckboxQ5 = 2;
        }
        // Question 6
        if((boxQ6c == 0) && ((boxQ6a + boxQ6b) == 2)){
            scoreCheckboxQ6 = 0;
        }
        if((boxQ6d == 0) && ((boxQ6a + boxQ6b) ==2)){
            scoreCheckboxQ6 = 0;
        }
        if((boxQ6c == 0) && (boxQ6d==0) && ((boxQ6a +boxQ6b) == 2)){
            scoreCheckboxQ6 = 2;
        }
        //What is the new ranking?
        ranking = totalScoreCheckbox = scoreCheckboxQ4 + scoreCheckboxQ5 + scoreCheckboxQ6;
    }

    /**
     * checks the EditText(question 3) and adds 1 point
     * <p>
     * This method figures out the answer set by the user.
     * It adds one point if the answer matches the solution.
     *
     * @param view EditText quiz question 3
     */

    public void checkEditTextQ3(View view) {
        EditText textNumberQ3 = findViewById(R.id.text_number_30_60_90true_q3);
        String numberChosen = textNumberQ3.getText().toString();
        // What is the current ranking?
        totalScoreEditText = ranking;
        String correctAnswer = "90";
        if (numberChosen.equals(correctAnswer)) {
            totalScoreEditText = 1;
        }else{
            totalScoreEditText = 0;
        }
        /*The user may change his answer and grade again
        if (!(numberChosen.equals(correctAnswer))) {
            totalScoreEditText = 0;
        }*/
    }

    /**
     * displays the final score in a toast
     * <p>
     * This method is called when the GRADING button is clicked. A toast
     * displays the final score obtained by the user. The RESET button
     * appears once the custom toast layout fades out.
     *
     * @param view The custom toast layout showing the grading
     * @see "showResetButton()"
     */

    public void showGrading(View view) {
        //What is the final grading?
        grading = totalScoreRadio + totalScoreCheckbox + totalScoreEditText;
        // Inflates the Layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_grading,
                (ViewGroup) findViewById(R.id.custom_toast_layout));
        // Set the Text to show in TextView
        TextView text01 = layout.findViewById(R.id.text_grading01);
        text01.setText(R.string.toast01_congrats);
        TextView text02 = layout.findViewById(R.id.text_grading02);
        text02.setText(MessageFormat.format("{0}{1}{2}", String.format("%d", grading),
                getString(R.string.grading_out_of), getString(R.string.grading_10)));
        TextView text03 = layout.findViewById(R.id.text_grading03);
        text03.setText(R.string.toast01_plant);
        // show the toast
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        showResetButton();
    }

    /**
     * makes the RESET button visible
     * <p>
     * This method is called once the toast of the final score fades out.
     * It displays the view of the RESET button
     *
     * @see "showGrading"
     * @see "onCreate(Bundle savedInstanceState)"
     */

    private void showResetButton() {
        Button resetButton = this.findViewById(R.id.btn_toast_reset);
        resetButton.setVisibility(View.VISIBLE);
    }

    /**
     * resets the grading to 0 points and refreshes the activity
     * <p>
     * This method is called when the RESET button is clicked, a toast
     * confirms the reset.
     *
     * @param view the custom toast layout02 confirming the reset
     */

    public void resetGrading(View view) {
        //Reset the final score
        grading = 0;
        // Inflate the Layout
        LayoutInflater inflater02 = getLayoutInflater();
        View layout02 = inflater02.inflate(R.layout.toast_reset,
                (ViewGroup) findViewById(R.id.custom_toast_layout02));
        // Set the Text to show in TextView
        TextView text04 = layout02.findViewById(R.id.text_reset01);
        text04.setText(R.string.toast02_reset);
        // show the toast
        Toast toast02 = new Toast(getApplicationContext());
        toast02.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast02.setDuration(Toast.LENGTH_LONG);
        toast02.setView(layout02);
        toast02.show();
        switch (view.getId()) {
            case R.id.btn_toast_reset:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * Removes URL underlines in a string
     * <p>
     * This method avoids that automatic links be underlined by replacing URLSpan occurrence
     * by URLSpanNoUnderline objects.
     *
     * @param p_Text A Spannable object. For example, a TextView casted as
     *               Spannable.
     */

    private void removeUnderlines(Spannable p_Text) {
        URLSpan[] spans = p_Text.getSpans(0, p_Text.length(), URLSpan.class);
        for (URLSpan span : spans) {
            int start = p_Text.getSpanStart(span);
            int end = p_Text.getSpanEnd(span);
            p_Text.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            p_Text.setSpan(span, start, end, 0);
        }
    }

    /**
     * figures out the email message
     * <p>
     * This method is called when the SUBMIT button is clicked.
     * The data entered by the user are fetched back and the
     * answers to the quiz are displayed in an email
     *
     * @param view EdiText of the user's name
     */

    public void sendTheAnswers(View view) {
        String newLine = getString(R.string.new_line);
        String space = getString(R.string.space);
        String emailSubject = getString(R.string.email_subject);
        String emailBody =
                getString(R.string.email_body)
                        + newLine + newLine;
        String question1 = getString(R.string.q1) + space + getString(R.string.radio_q1a_botswana_true) + newLine;
        String question2 = getString(R.string.q2) + space + getString(R.string.radio_q2b_joint_pain_true) + newLine;
        String question3 = getString(R.string.q3) + newLine;
        String question4 = getString(R.string.q4) + space + getString(R.string.check_q4a_grapple_plant_true) + ", "
                + getString(R.string.check_q4c_wood_spider_true) + ", "
                + getString(R.string.check_q4e_devil_s_claw_true) + ", " + newLine;
        String question5 = getString(R.string.q5) + space + getString(R.string.check_q5c_pedaliaceae_true) + ", "
                + getString(R.string.check_q5d_sesame_true) + newLine;
        String question6 = getString(R.string.q6) + space + "image " + getString(R.string.img_01_q6a_true) + ", "
                + "image " + getString(R.string.img_02_q6b_true) + newLine + newLine;
        String emailEnd01 = getString(R.string.email_end_01) + newLine;
        String emailEnd02 = getString(R.string.email_end_02) + newLine;
        //Allows the user to enter is email
        EditText userName = findViewById(R.id.user_name);
        String hasEnteredHisName = userName.getText().toString();
        //Figures out the subject and the body of the mail
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        //subject
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject + space + hasEnteredHisName);
        //body
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                emailBody + question1 + question2 + question3 + question4 + question5
                        + question6 + emailEnd01 + emailEnd02);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}




