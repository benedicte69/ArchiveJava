package com.example.bened.helloandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Remove underlinings from the phone number, email and website links

        TextView phoneTextLink = (TextView) findViewById(R.id.phone_text);
        if (phoneTextLink != null) {
            removeUnderlines((Spannable) phoneTextLink.getText());
        }

        TextView emailTextLink = (TextView) findViewById(R.id.email_text);
        if (emailTextLink != null) {
            removeUnderlines((Spannable) emailTextLink.getText());
        }

        TextView websiteTextLink = (TextView) findViewById(R.id.website_text);
        if (websiteTextLink != null) {
            removeUnderlines((Spannable) websiteTextLink.getText());
        }
    }

    /**
     * Links the networking icon to the browser of the user
     * </p>
     * this methods are used to create web browsing common intents with ACTION_VIEW
     *
     * @param view the networking icon view
     */

    public void goToGooglePlus(View view) {
        Uri webpage = Uri.parse("https://plus.google.com/+PHILEVENEMENTIEL");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    public void goToFacebook(View view) {
        Uri webpage = Uri.parse("https://www.facebook.com/philevenementiel/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    public void goToPinterest(View view) {
        Uri webpage = Uri.parse("https://www.pinterest.fr/philevenement/?etslf=13606&eq=phil");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    public void goToYouTube(View view) {
        Uri webpage = Uri.parse("https://www.youtube.com/channel/UCssgfuo399ApIp0TDE_5SKw");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    /**
     * Removes URL underlines in a string by replacing URLSpan occurrences by
     * URLSpanNoUnderline objects.
     *
     * @param p_Text A Spannable object. For example, a TextView casted as
     *               Spannable.
     */

    public void removeUnderlines(Spannable p_Text) {
        URLSpan[] spans = p_Text.getSpans(0, p_Text.length(), URLSpan.class);

        for (URLSpan span : spans) {
            int start = p_Text.getSpanStart(span);
            int end = p_Text.getSpanEnd(span);
            p_Text.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            p_Text.setSpan(span, start, end, 0);
        }
    }
}
