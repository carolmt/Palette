    package com.example.palette;

    import android.app.Activity;
    import android.app.ActivityOptions;
    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.Toast;

    import androidx.recyclerview.widget.RecyclerView;

    import java.util.ArrayList;

    public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.TarjViewHolder> {

        private ArrayList<Tarjeta> items;
        private View.OnClickListener onClickListener;

        public CardsAdapter(ArrayList<Tarjeta> items) {
            this.items = items;
        }

        public void setOnClick(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public static class TarjViewHolder extends RecyclerView.ViewHolder {
            ImageView imagen;

            public TarjViewHolder(View itemView) {
                super(itemView);
                imagen = itemView.findViewById(R.id.image1);
            }

        }

        @Override
        public TarjViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cards, viewGroup, false);
            return new TarjViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(TarjViewHolder viewHolder, int pos) {
            Tarjeta item = items.get(pos);
            Context context = viewHolder.itemView.getContext();

            viewHolder.imagen.setImageResource(item.getImagen());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Iniciar la actividad ImagePalette con la información necesaria
                    Intent intent = new Intent(context, ImagePalette.class);

                    // Agregar la transición compartida
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context,
                            viewHolder.imagen, // ImageView de origen
                            "tranphoto" // Nombre de la transición compartida
                    );

                    intent.putExtra("selected_image", item.getImagen());  // Pasa la información necesaria a ImagePalette
                    context.startActivity(intent, options.toBundle());
                }
            });

        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
