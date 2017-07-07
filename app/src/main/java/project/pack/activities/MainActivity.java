package project.pack.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import project.pack.R;
import project.pack.facade.Facade;
import project.pack.utilities.ConnectionUtilities;

public class MainActivity extends AppCompatActivity {

    // Con ButterKnife Reemplazas      (Button) findViewById ->por   @Bind

    @Bind(R.id.btnVerListIncidentes)
    Button btnVerListIncidentes;

    @Bind(R.id.btnEliminarCache)
    Button btnEliminarCache;

    @Bind(R.id.btnCrearIncidenteCategoria)
    Button btnCrearIncidenteCategoria;

    @Bind(R.id.btnComprobarConexion)
    Button btnComprobarConexion;


    Facade facade = Facade.getInstance();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        context = this;

        // Envio la referencia del contexto
        facade.setContext(context);

        btnEliminarCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Elimino la cache.
                facade.eliminarCache();
                Toast.makeText(getApplicationContext(), "La cache se elimino correctamente", Toast.LENGTH_LONG).show();
            }
        });

        btnVerListIncidentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarIncidente = new Intent(MainActivity.this, VerIncidenteActivity.class);
                startActivity(mostrarIncidente);
            }
        });

        btnCrearIncidenteCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent layautCrearIncidenteCategoria = new Intent(MainActivity.this, CrearIncidenteConDetectorDeCategoria.class);
                startActivity(layautCrearIncidenteCategoria);
            }
        });

        btnComprobarConexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean HayConex = ConnectionUtilities.estaConectado(context);
                String HayConexion = (HayConex)? "Conectado!":"Desconectado!";
                Toast.makeText(getApplicationContext(), "Estado de conexion: "+ HayConexion, Toast.LENGTH_LONG).show();
            }
        });
    }
}