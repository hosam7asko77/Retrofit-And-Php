package com.example.retrofitandphp.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.retrofitandphp.R;
import com.example.retrofitandphp.Storage.SharedPrefManager;
import com.example.retrofitandphp.fragments.HomeFragment;
import com.example.retrofitandphp.fragments.SettingsFragment;
import com.example.retrofitandphp.fragments.UserFragment;
import com.example.retrofitandphp.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView textWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //textWelcome=findViewById(R.id.welcome);
        //User user=SharedPrefManager.getInstance(this).getUser();
       // textWelcome.setText("Welcome back"+user.getName()
        //);
        BottomNavigationView navigationView=findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefManager.getInstance(this).isLogIn()){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment=null;
        switch (menuItem.getItemId()){
            case R.id.menu_home:
                fragment=new HomeFragment();
                break;
            case R.id.menu_user:
                fragment =new UserFragment();
                break;
            case R.id.menu_settings:
                fragment =new SettingsFragment();
                break;
        }
        if(fragment !=null){
            displayFragment(fragment);
        }
        return false;
    }
    public  void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment)
                .commit();

    }
}
