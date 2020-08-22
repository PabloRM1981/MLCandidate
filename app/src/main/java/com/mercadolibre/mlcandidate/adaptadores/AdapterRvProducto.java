package com.mercadolibre.mlcandidate.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mercadolibre.mlcandidate.R;
import com.mercadolibre.mlcandidate.fragmentos.DetalleProductoFragment;
import com.mercadolibre.mlcandidate.modelos.Producto;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRvProducto extends RecyclerView.Adapter<AdapterRvProducto.ViewHolderProducto>{
    private ArrayList<Producto> listaProductos;
    private Context context;
    Activity actividad;
    FragmentTransaction transaction;
    Fragment detalleProducto;

    public AdapterRvProducto(ArrayList<Producto> listaProductos, Context context) {
        this.listaProductos = listaProductos;
        this.context = context;

        if(context instanceof Activity){
            this.actividad = (Activity) context;
            //iComunica= (IComunica) this.actividad;
        }
    }



    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_productos,null,false);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        holder.tvTitulo.setText(listaProductos.get(position).getTitle());
        holder.tvPrecio.setText(listaProductos.get(position).getPrice().toString());
        holder.tvCantidad.setText("Cantidad disponible: "+listaProductos.get(position).getAvailable_quantity().toString());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(listaProductos.get(position).getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgFila);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }





    public class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvPrecio, tvCantidad;
        ImageView imgFila;
        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCantidad = itemView.findViewById(R.id.tvCantidadDisponible);
            imgFila = itemView.findViewById(R.id.imgFila);
            imgFila.setLayoutParams(new LinearLayout.LayoutParams(600, 600));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "xxx", Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("producto",listaProductos.get(getAdapterPosition()));
                    detalleProducto = new DetalleProductoFragment();
                    detalleProducto.setArguments(bundle);
                    transaction = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment,detalleProducto);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
            });

        }
    }
}
