package com.example.palette;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

public class ImagePalette extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Añadimos esta linea antes del setContentView
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_image_palette);

        // Indicar animación de entrada en este caso Fade
        Fade explodeAnimation = new Fade();
        explodeAnimation.setDuration(1000);
        getWindow().setEnterTransition(explodeAnimation);

        ActionBar actionBar = getSupportActionBar();
        // Obtén la imagen seleccionada del Intent
        int selectedImage = getIntent().getIntExtra("selected_image", 0);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(selectedImage);

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        // Extrae la paleta de colores de la imagen
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {

                int colorVibrant = palette.getVibrantColor(getResources().getColor(R.color.colorDefaultVibrant));
                if (actionBar != null) {
                    actionBar.setBackgroundDrawable(new ColorDrawable(colorVibrant));
                }

                int colorDarkVibrant = palette.getDarkVibrantColor(getResources().getColor(R.color.colorDefaultDarkVibrant));
                getWindow().setStatusBarColor(colorDarkVibrant);

                TextView lightVibrantTextView = findViewById(R.id.lightVibrantTextView);
                lightVibrantTextView.setBackgroundColor(palette.getLightVibrantColor(getResources().getColor(R.color.colorDefaultLightVibrant)));

                TextView mutedTextView = findViewById(R.id.mutedTextView);
                mutedTextView.setBackgroundColor(palette.getMutedColor(getResources().getColor(R.color.colorDefaultMuted)));

                TextView lightMutedTextView = findViewById(R.id.lightMutedTextView);
                mutedTextView.setBackgroundColor(palette.getMutedColor(getResources().getColor(R.color.colorDefaultMuted)));

                TextView darkMutedTextView = findViewById(R.id.darkMutedTextView);
                darkMutedTextView.setBackgroundColor(palette.getDarkMutedColor(getResources().getColor(R.color.colorDefaultDarkMuted)));
            }
        });
    }
}