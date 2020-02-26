package com.mobiledevelopment.werewolf;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobiledevelopment.werewolf.R;
import com.mobiledevelopment.werewolf.model.Role;


public class ActivityRole extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        // We get some widgets reference
        ImageView imageView = findViewById(R.id.RoleImage);
        TextView textPlayer = findViewById(R.id.RoleTextPlayer);
        TextView textName = findViewById(R.id.RoleTextName);
        TextView textDescription = findViewById(R.id.RoleTextDescription);


        // If we receive the Intent extra - display it
        if(getIntent().hasExtra("role"))
        {
            // We get the role and the player's name
            Role role = (Role) getIntent().getSerializableExtra("role");
            String playerName = (String) getIntent().getSerializableExtra("playerName");

            // If not null, we set the data accordingly
            if(role != null && playerName != null)
            {
                imageView.setImageResource(role.getImageRes());
                textName.setText(role.getName());
                textDescription.setText(role.getDescription());

                // If there is no player name
                if(playerName.equals(""))
                {
                    textPlayer.setVisibility(View.INVISIBLE);
                }
                // If there is a player name
                else
                {
                    textPlayer.setVisibility(View.VISIBLE);

                    textPlayer.setText(R.string.RoleTextPlayer);
                    String player = (String) textPlayer.getText();
                    textPlayer.setText( "Jamie ".concat(player) );
                }
            }
        }
        // Otherwise - we display a Toast
        else
        {
            Toast.makeText(this, "No Data was found", Toast.LENGTH_SHORT).show();
        }
    }
}