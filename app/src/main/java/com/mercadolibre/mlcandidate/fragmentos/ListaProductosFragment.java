package com.mercadolibre.mlcandidate.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mercadolibre.mlcandidate.R;
import com.mercadolibre.mlcandidate.adaptadores.AdapterRvProducto;
import com.mercadolibre.mlcandidate.modelos.Producto;
import com.mercadolibre.mlcandidate.modelos.Productos;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaProductosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaProductosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Producto> listaProductos;
    private RecyclerView rvProductos;

    public ListaProductosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaProductosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaProductosFragment newInstance(String param1, String param2) {
        ListaProductosFragment fragment = new ListaProductosFragment();
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
        return inflater.inflate(R.layout.fragment_lista_productos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            Bundle objProductos = this.getArguments();
            if(objProductos != null){
                Productos productos = (Productos) objProductos.getSerializable("productos");
                listaProductos = productos.getResults();
                rvProductos = view.findViewById(R.id.rvProducto);
                rvProductos.setLayoutManager(new LinearLayoutManager(getContext()));

                AdapterRvProducto adapterRvProducto = new AdapterRvProducto(listaProductos,getContext());
                rvProductos.setAdapter(adapterRvProducto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}