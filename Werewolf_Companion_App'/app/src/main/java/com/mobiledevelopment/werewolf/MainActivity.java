 package com.mobiledevelopment.werewolf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiledevelopment.werewolf.model.Role;
import java.util.Locale;


 public class MainActivity extends AppCompatActivity
 {
     TextView textView;
     ImageView imageView;
     int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Necessary methods
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We set some widgets references
        textView = findViewById(R.id.textViewToDelete);
        imageView = findViewById(R.id.imageWithNoUse);

        // Adding the listeners to all the widgets
        doStuff_ButtonBrowseRoles();
    }




     /** Sets the content of the TextView */
     public void setContent(Role role)
     {
         // We set the message
         String message = (role == null) ? "Click Me !" : role.getName() + "\n" + role.getDescription();

         // We display the message
         textView.setText( message );

         // We set the icon
         Drawable drawable = getResources().getDrawable(role.getIcon());
         textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

         // Code to load an image from the resources
         int imageResource = getResources().getIdentifier(role.getImagePath(), null, getPackageName());
         Drawable res = getResources().getDrawable(imageResource);
         imageView.setImageDrawable(res);
     }


     /**
      * Associates methods to the Button displaying Roles data
      */
     public void doStuff_ButtonBrowseRoles()
     {
         // We get the button
         Button button = findViewById(R.id.buttonToDelete);

         // We give a Listener to the button
         button.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick (View v)
             {
                 Role roleToDisplay;
                 switch((index++)%Role.values().length)
                 {
                     case 0 : roleToDisplay = Role.Villager; break;
                     case 1 : roleToDisplay = Role.Werewolf; break;
                     case 2 : roleToDisplay = Role.Cupid; break;

                     default : roleToDisplay = null; break;
                 }

                 setContent(roleToDisplay);
             }
         });
     }
 }
