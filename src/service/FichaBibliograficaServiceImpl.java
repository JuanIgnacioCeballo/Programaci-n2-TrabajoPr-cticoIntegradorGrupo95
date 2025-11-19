
package service;

import java.util.List;
import dao.GenericDAO;
import java.util.ArrayList;
import modelos.FichaBibliografica;


public class FichaBibliograficaServiceImpl implements GenericService<FichaBibliografica>{
    
    private final GenericDAO<FichaBibliografica> fichaDAO;
    
    public FichaBibliograficaServiceImpl(GenericDAO<FichaBibliografica> fichaDAO) {
        this.fichaDAO = fichaDAO;
    }

    @Override
    public void insert(FichaBibliografica ficha) throws Exception {
        if(ficha.getIsbn() == null || ficha.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede ser nulo");
        } else if(ficha.getIsbn().length() != 13) {
            throw new IllegalArgumentException("El ISBN debe ser de 13 caracteres");
        }
        if(ficha.getClasificacion_Dewey() == null || ficha.getClasificacion_Dewey().trim().isEmpty()) {
            throw new IllegalArgumentException("La clasificación Dewey no puede ser nula");
        }
        if(ficha.getEstanteria()== null || ficha.getEstanteria().trim().isEmpty()) {
            throw new IllegalArgumentException("La estanteria no puede ser nula");
        }
        if(ficha.getIdioma()== null || ficha.getIdioma().trim().isEmpty()) {
            throw new IllegalArgumentException("El idioma no puede ser nulo");
        }
        
        System.out.println("Insertando el ficha: "+ficha.getIsbn());
        
        fichaDAO.insert(ficha);
        
    }

    @Override
    public void update(FichaBibliografica ficha) throws Exception {
//        if(ficha.getId() <= 0) {
//            throw new IllegalArgumentException("El Id de la ficha debe ser un númnero natural"+ficha.getId());
//        }
        if(ficha.getIsbn() == null || ficha.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede ser nulo");
        } else if(ficha.getIsbn().length() != 13) {
            throw new IllegalArgumentException("El ISBN debe ser de 13 caracteres");
        }
        if(ficha.getClasificacion_Dewey() == null || ficha.getClasificacion_Dewey().trim().isEmpty()) {
            throw new IllegalArgumentException("La clasificación Dewey no puede ser nula");
        }
        if(ficha.getEstanteria()== null || ficha.getEstanteria().trim().isEmpty()) {
            throw new IllegalArgumentException("La estanteria no puede ser nula");
        }
        if(ficha.getIdioma()== null || ficha.getIdioma().trim().isEmpty()) {
            throw new IllegalArgumentException("El idioma no puede ser nulo");
        }
        
        System.out.println("Modificando ficha: "+ficha.getId());
        
        fichaDAO.update(ficha);
    }

    @Override
    public void delete(int id) throws Exception {
        if(id <= 0) {
            throw new IllegalArgumentException("El Id ingresado debe ser un número natural");
        }
        
        System.out.println("Eliminando ficha con id: "+id);
        
        fichaDAO.delete(id);
    }

    @Override
    public FichaBibliografica getById(int id) throws Exception {
        if(id <= 0){
            throw new IllegalArgumentException("El Id ingresado debe ser un número natural");
        }
        
        // Utilizar el método getById del DAO
        FichaBibliografica fichaById= fichaDAO.getById(id);
        
        if(fichaById == null) {
            throw new Exception("No existe ficha con el id: "+id);
        }
        
        return fichaById;
        
    }
    
    @Override
    public List<FichaBibliografica> getAll() throws Exception {
        
        List<FichaBibliografica> listaFichas = fichaDAO.getAll();
        return listaFichas;
    }
    
    
    // GETTERS DE FICHAS ACTIVAS
    
    @Override
    public FichaBibliografica getByIdActivo(int id) throws Exception {
        if(id <= 0){
            throw new IllegalArgumentException("El Id ingresado debe ser un número natural");
        }
        
        // Utilizar el método getById del DAO
        FichaBibliografica fichaById= fichaDAO.getByIdActivo(id);
        
        if(fichaById == null) {
            throw new Exception("No existe ficha con el id: "+id);
        }
        
        return fichaById;
        
    }
    
    @Override
    public List<FichaBibliografica> getAllActivos() throws Exception {
        
        List<FichaBibliografica> listaFichas = fichaDAO.getAllActivos();
        return listaFichas;
    }
    
    
    
}
