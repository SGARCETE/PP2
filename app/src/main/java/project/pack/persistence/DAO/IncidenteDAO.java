package project.pack.persistence.DAO;

import java.util.ArrayList;

import project.pack.domain.Incidente;

/**
 * Created by lukas on 14/05/2017.
 */
    public interface IncidenteDAO {

        ArrayList<Incidente> getListItem();

        Incidente add(Incidente item);

        boolean remove(Incidente item);

        void vaciarBD();

    }