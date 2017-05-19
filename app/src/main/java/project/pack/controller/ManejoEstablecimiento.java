package project.pack.controller;

import java.util.ArrayList;
import java.util.List;

import project.pack.domain.Categoria;
import project.pack.domain.Coordenada;
import project.pack.domain.Establecimiento;
import project.pack.persistence.PersistenciaEstablecimiento;

/*
 * Created by Federico Vara on 9/4/2017.
 */

public class ManejoEstablecimiento {

    private PersistenciaEstablecimiento Persistencia = new PersistenciaEstablecimiento();

    public ManejoEstablecimiento() {}

    public Establecimiento crearEstablecimiento(Coordenada coordenada, String nombre, Categoria categoria) {

        Establecimiento establecimiento = new Establecimiento(coordenada, nombre, categoria);
        return establecimiento;
    }

    public Establecimiento guardarEstablecimiento(Establecimiento establecimiento) {
        return Persistencia.addEstablecimiento(establecimiento);
    }

    public Establecimiento getEstablecimiento(int id) {

        List<Establecimiento> List = getListaEstablecimientos();
        Establecimiento establecimiento = null;
        if(List!=null && List.size()>0){
            for (int i = 0; i < List.size(); i++){
                establecimiento = List.get(i);
                if(establecimiento.getId() == id){
                    establecimiento.generarRiesgo();
                    return establecimiento;
                }
            }
        }
        return establecimiento;
    }

    /**
     * Dada una coordenada, devuelvo los establecimientos que corresponden al rango de dicha coordenada.
     *
     * @param coordenada que se le envia.
     * @return devuelvo lista que cumple el rango.
     */
    public List<Establecimiento> getListaEstablecimientosConCoordenada(Coordenada coordenada) {

        List<Establecimiento> listaEstablecimientos = getListaEstablecimientos();
        List<Establecimiento> establecimientosAprobados = new ArrayList<Establecimiento>();

        Double distanciaMaxima = 10.5;

        for (int i = 0; i < listaEstablecimientos.size(); i++) {
            //Obtengo la distancia entre las coordenadas del Establecimiento
            Double distanciaEstablecimientos = listaEstablecimientos.get(i).getCoordenada().getDistancia(coordenada);

            if (distanciaEstablecimientos != 0.0 && distanciaEstablecimientos <= distanciaMaxima) {
                establecimientosAprobados.add(listaEstablecimientos.get(i));
            }
        }
        return establecimientosAprobados;
    }

    /**
     * Método que se encargar de devolver una lista de establecimientos.
     *
     * @return Devuelve una lista de establecimientos.
     */
    public List<Establecimiento> getListaEstablecimientos() {
        List<Establecimiento> Establecimiento = Persistencia.getListaEstablecimiento();
        return Establecimiento;
    }

    public void eliminar() {
        Persistencia.eliminarBD();
    }

}
