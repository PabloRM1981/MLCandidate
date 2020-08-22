package com.mercadolibre.mlcandidate.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.ConnectivityManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.mercadolibre.mlcandidate.R;
import com.mercadolibre.mlcandidate.fragmentos.ListaProductosFragment;
import com.mercadolibre.mlcandidate.interfaces.MercadoLibreAPI;
import com.mercadolibre.mlcandidate.modelos.CaracteristicasProducto;
import com.mercadolibre.mlcandidate.modelos.Producto;
import com.mercadolibre.mlcandidate.modelos.Productos;
import com.mercadolibre.mlcandidate.utilidades.Utilidades;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultarProductos extends AppCompatActivity {
    private TextView tvRespuesta;
    private EditText edProducto;
    private ImageView imgBuscarProducto;


    FragmentTransaction transaction;
    Fragment listaProductosFragment;
    private Context context = ConsultarProductos.this;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_productos);
        //tvRespuesta = findViewById(R.id.tvRespuesta);
        imgBuscarProducto = findViewById(R.id.imgBuscarProducto);
        edProducto = findViewById(R.id.edProducto);



        imgBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocultarTeclado();
                if(!validar(view)){
                    pDialog = Utilidades.showProgress(context);
                    consultarProductos(edProducto.getText().toString());
                }




            }
        });
    }
    private void ocultarTeclado() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    private boolean validar(View v){
        boolean estado = false;
        Integer error = -1;
        if(edProducto.getText().toString().equals("")){
            estado = true;
            error = 0;
        }
        if(!isNetDisponible()) {
            estado = true;
            error = 1;
        }
        switch (error) {
            case 0:
                Snackbar.make(v, "Digite el producto a buscar", Snackbar.LENGTH_LONG).show();
                break;
            case 1:
                Snackbar.make(v, "El dispositivo no está conectado a Internet", Snackbar.LENGTH_LONG).show();
                break;

        }
        return estado;
    }
    private boolean isNetDisponible() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }
    private void consultarProductos(String buscar) {
        //Toast.makeText(context,buscar,Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/sites/MLA/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MercadoLibreAPI mercadoLibreAPI = retrofit.create(MercadoLibreAPI.class);
        Call<Productos> call = mercadoLibreAPI.find(buscar);
        //Call<Productos> call = mercadoLibreAPI.getProductos();
        call.enqueue(new Callback<Productos>() {
            @Override
            public void onResponse(Call<Productos> call, Response<Productos> response) {
                try {
                    if(response.isSuccessful()){
                        Productos res = response.body();
                        ArrayList<Producto> productos = res.getResults();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("productos",res);
                        listaProductosFragment = new ListaProductosFragment();
                        listaProductosFragment.setArguments(bundle);
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment,listaProductosFragment);
                        //transaction.addToBackStack(null);
                        transaction.commit();
                        if (pDialog != null)
                            pDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (pDialog != null)
                        pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Productos> call, Throwable t) {
                Toast.makeText(ConsultarProductos.this, "No se obtuvo resultados", Toast.LENGTH_LONG).show();
                if (pDialog != null)
                    pDialog.dismiss();
            }
        });
    }
}