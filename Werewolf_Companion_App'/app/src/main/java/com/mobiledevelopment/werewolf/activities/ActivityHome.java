package com.mobiledevelopment.werewolf.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mobiledevelopment.werewolf.R;

import java.util.Locale;


/**
 * {@link AppCompatActivity} that constitute the root activity of the Application.
 *
 * The user can either launch a new {@link com.mobiledevelopment.werewolf.model.Party}, see the rules or change the language
 */
public class ActivityHome extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_home);

        // Setting the events for the widgets
        doStuff_ButtonCreateParty();
        doStuff_ButtonRules();
        doStuff_ButtonLanguages();
    }



    /**
     * Associates methods to the Button that launch the creation phase
     */
    public void doStuff_ButtonCreateParty()
    {
        // We get the button
        Button button = findViewById(R.id.HomeButtonParty);

        // When clicked : launches the Activity creating a Party
        button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ActivityPartyNew.class)));
    }


    /**
     * Associates methods to the Button that displays the ActivityRules
     */
    public void doStuff_ButtonRules()
    {
        // We get the button
        Button button = findViewById(R.id.HomeButtonSeeRules);

        // When clicked : launches the Activity displaying the rules
        button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ActivityRules.class)));
    }


    /**
     * Associates methods to the Button changing the language
     */
    private void doStuff_ButtonLanguages()
    {
        // We get the button
        Button button = findViewById(R.id.HomeButtonLanguage);

        // We give a Listener to the button
        button.setOnClickListener(v -> {
            // call the method to create the Dialog Option
            showChangeLanguageDialog();
        });
    }


    /**
     * Displays a choice to choose a language
     */
    private void showChangeLanguageDialog()
    {
        // array of languages to display in alert dialog
        final String[] listLanguages = {"English", "Français"};

        // Creating the Alert Dialog
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);

        // Setting the choices
        mBuilder.setTitle("Choose a language :");
        mBuilder.setSingleChoiceItems(listLanguages, -1, (dialogInterface, i) -> {
            // If the choice corresponds to one of the following
            // Set the language and recreate the UI
            if(listLanguages[i].equals("English"))
            {
                setLocale("en");
                recreate();
            }
            if(listLanguages[i].equals("Français"))
            {
                setLocale("fr");
                recreate();
            }

            dialogInterface.dismiss();
        });

        // Display the Dialog
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    /**
     * Sets the language of the App' in the Locale
     */
    private void setLocale(String language) {
        // Creates and sets a Locale variable
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        // Creates and sets a Configuration variable
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Language", language);
        editor.apply();
    }


    /**
     * Loads the language of the App' from the Locale
     */
    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Language", "");
        setLocale(language);
    }
}
