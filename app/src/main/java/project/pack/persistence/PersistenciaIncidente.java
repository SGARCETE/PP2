package project.pack.persistence;

import java.util.ArrayList;

import project.pack.controller.CacheSingleton;
import project.pack.domain.Incidente;
import project.pack.facade.Facade;
import project.pack.persistence.DAO.IncidenteDAO;
import project.pack.persistence.DAOImpl.IncidenteDAOImpl;
import project.pack.utilities.ConnectionUtilities;

/**
 * Created by lukas on 14/05/2017.
 */

public class PersistenciaIncidente {
    private IncidenteDAO persistenciaDAO = new IncidenteDAOImpl();

    public Incidente addIncidente(Incidente item){
        // Comprueba si hay conexion a internet disponible
        // Si hay conexion, puede interactuar con la persistencia
        if( ConnectionUtilities.estaConectado(Facade.getInstance().getContext())){
            // Guarda en la BD
            Incidente In_guardado = persistenciaDAO.add(item);
            // Sincronizarlo en la cache
            CacheSingleton.getInstance().put(item, item.getId());

            return In_guardado;
        }
        // Si hay o no hay conexion, no se puede agregar
        return null;
    }

    public ArrayList<Incidente> getListaIncidente(){
        // Comprueba si hay conexion a internet disponible
        // Si hay conexion, puede interactuar con la persistencia
        if(ConnectionUtilities.estaConectado(Facade.getInstance().getContext())){
            ArrayList<Incidente> lista = persistenciaDAO.getListItem();
            if(lista!=null) {
                // Actualizo el cache
                for (int i = 0; i < lista.size(); i++) {
                    CacheSingleton.getInstance().put(lista.get(i), lista.get(i).getId());
                }
            }
            return lista;
        }
        // Si no hay conexion, tiene que interactuar con el cache
        else{
            return CacheSingleton.getInstance().obtenerLista(Incidente.class);
        }
    }


    public void eliminarBDPersistencia(){
        persistenciaDAO.eliminarBD();
    }
}
