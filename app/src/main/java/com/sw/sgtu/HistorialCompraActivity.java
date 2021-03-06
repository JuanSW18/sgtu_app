package com.sw.sgtu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sw.sgtu.adapter.CompraAdapter;
import com.sw.sgtu.conexion.ApiAdapter;
import com.sw.sgtu.conexion.ApiService;
import com.sw.sgtu.modelo.Compra;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistorialCompraActivity extends AppCompatActivity {

    private Bundle bundle;
    int ID_USUARIO;

    Intent intent;

    ApiService apiService;
    private final String TAG = ComprarActivity.class.getSimpleName();

    TextView tvToolBar;
    Toolbar myToolbar;

    private RecyclerView recyclerView;
    private List<Compra> listaCompras = new ArrayList<>();
    private CompraAdapter compraAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_compra);

        myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        tvToolBar =  myToolbar.findViewById(R.id.appToolBar_title);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvToolBar.setText("Mis compras");

        recyclerView = findViewById(R.id.recycler_view_compras);
        compraAdapter = new CompraAdapter(HistorialCompraActivity.this, listaCompras);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistorialCompraActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(compraAdapter);

        bundle = getIntent().getExtras();
        ID_USUARIO = bundle.getInt("ID_USUARIO");
        //getListCompraPrueba();
        getListCompra(ID_USUARIO);

    }

    public void getListCompra(int id_usuario){
        apiService = ApiAdapter.createServiceSecondAPI(ApiService.class);
        Call<List<Compra>> call = apiService.getListCompra(id_usuario);
        call.enqueue(new Callback<List<Compra>>() {
            @Override
            public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                if(response.isSuccessful()){
                    for (Compra compra: response.body()){
                        listaCompras.add(compra);
                    }
                    compraAdapter.notifyDataSetChanged();
                    /*if(listaCompras.size() == 0){
                        tvSinCompras.setVisibility(View.VISIBLE);
                    }else{
                        tvSinCompras.setVisibility(View.GONE);
                    }*/
                }
            }

            @Override
            public void onFailure(Call<List<Compra>> call, Throwable t) {
                Log.e(TAG, "PASO ALGO:\n Unable to submit post to API.");
            }
        });
    }

    public void getListCompraPrueba() {
        Compra c1 = new Compra();
        c1.setFecha("2018-10-11 15:13:00");
        c1.setLinea_transporte("Chama 7606");
        c1.setParadero_inicial("Parque Villa Alegre");
        c1.setParadero_final("Av. Salaverry");
        c1.setCosto(2.50);

        Compra c2 = new Compra();
        c2.setFecha("2018-10-11 15:13:00");
        c2.setLinea_transporte("Chama 7530");
        c2.setParadero_inicial("Parque Villa Alegre");
        c2.setParadero_final("Av. Tupac Amaru");
        c2.setCosto(2.50);

        listaCompras.add(c1);
        listaCompras.add(c2);
        compraAdapter.notifyDataSetChanged();
    }

    public void redirectCompra(View view) {
        intent = new Intent(HistorialCompraActivity.this, ComprarActivity.class);
        intent.putExtra("ID_USUARIO", ID_USUARIO);
        startActivity(intent);
        finish();
    }
}
