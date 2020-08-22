package com.mercadolibre.mlcandidate.fragmentos;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mercadolibre.mlcandidate.R;
import com.mercadolibre.mlcandidate.modelos.CaracteristicasProducto;
import com.mercadolibre.mlcandidate.modelos.Producto;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleProductoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tvCondicion, tvTitulo, tvPrecio;
    private ImageView imgProducto;
    private LinearLayout llCaracteristicasIzq, llCaracteristicasDer;
    //private

    public DetalleProductoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleProductoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleProductoFragment newInstance(String param1, String param2) {
        DetalleProductoFragment fragment = new DetalleProductoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_producto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCondicion = view.findViewById(R.id.tvCondicion);
        tvTitulo = view.findViewById(R.id.tvTitulo);
        tvPrecio = view.findViewById(R.id.tvPrecio);
        imgProducto = view.findViewById(R.id.imgProducto);
        llCaracteristicasIzq = view.findViewById(R.id.llCaracteristicasIzq);
        llCaracteristicasDer = view.findViewById(R.id.llCaracteristicasDer);
        try {
            Bundle objProducto = this.getArguments();
            if(objProducto != null){
                Producto producto = (Producto) objProducto.getSerializable("producto");
                Picasso.Builder builder = new Picasso.Builder(getContext());
                builder.downloader(new OkHttp3Downloader(getContext()));
                builder.build().load(producto.getThumbnail())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(imgProducto);
                imgProducto.setLayoutParams(new LinearLayout.LayoutParams(900, 900));
                tvCondicion.setText(producto.getCondition());
                tvTitulo.setText(producto.getTitle());
                tvPrecio.setText("$" + producto.getPrice().toString());

                for(CaracteristicasProducto cp:producto.getAttributes()){
                    TextView textView = new TextView(getContext());
                    textView.setText(cp.getName());
                    textView.setTextColor(Color.BLACK);
                    llCaracteristicasIzq.addView(textView);

                    TextView textView2 = new TextView(getContext());
                    textView2.setText(cp.getValue_name());
                    textView2.setTextColor(Color.BLACK);
                    llCaracteristicasDer.addView(textView2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}