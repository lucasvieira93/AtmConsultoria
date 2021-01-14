package com.example.atmconsultoria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_servicos, R.id.nav_clientes, R.id.nav_contato, R.id.nav_sobre)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void enviarEmail() {

        String celular = "tel:13988825426";
        String imagem = "https://i.pinimg.com/originals/30/fe/35/30fe35b6cc327892ae463a1c224617b2.jpg";
        String endereco = "https://www.google.com.br/maps/place/Ritinha+Presentes/@-23.9345646,-46.4320996,17z/data=!3m1!4b1!4m5!3m4!1s0x94ce1b6657440813:0xb19022aea1192b38!8m2!3d-23.9345695!4d-46.4299109";

        //Documentações Intent - https://developer.android.com/guide/components/intents-filters?hl=pt-
//        Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse(celular));
//        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(imagem));
//        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(endereco));

        //configurando intent para email
        Intent intent = new Intent( Intent.ACTION_SEND );
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"lucasvieira@atm.com.br"} );
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contato pelo App");
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem gerada automaticamente!");

        // Mime tipes list - https://www.sitepoint.com/mime-types-complete-list/
        intent.setType("message/rfc822");
//        intent.setType("text/plain");
//        intent.setType("image/*");
//        intent.setType("application/pdf");

        startActivity(Intent.createChooser(intent, "Compartilhar"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Obrigado por utilizar nosso app!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}