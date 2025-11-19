
package service;

import dao.GenericDAO;
import java.util.List;
import modelos.Libro;
import java.time.LocalDate;
import modelos.FichaBibliografica;


public class LibroServiceImpl implements GenericService<Libro>{
    
    // atributo constante que utiliza la interfaz generica DAO para acceder a la base de datos
    private final GenericDAO<Libro> libroDAO;
    private final GenericService<FichaBibliografica> fichaService;
    
    // Constructor que recibe el DAO de libro
    public LibroServiceImpl(GenericDAO<Libro> libroDAO, GenericService<FichaBibliografica> fichaService) {
        this.libroDAO = libroDAO;
        this.fichaService = fichaService;
    }

    
    @Override
    public void insert(Libro libro) throws Exception {
        int anioActual = LocalDate.now().getYear();
//        if(libro.getId() <= 0) {
//            throw new IllegalArgumentException("El Id debe ser un número natural");
//        }
//        if(libro.isEliminado() != false) {
//            throw new IllegalArgumentException("El campo eliminado debe ser Falso");
//        }
        if(libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo no puede ser nulo");
        }
        if(libro.getAutor() == null ||libro.getAutor().trim().isEmpty()){
            throw new IllegalArgumentException("El autor no puede ser nulo");
        }
        if(libro.getEditorial() == null || libro.getEditorial().trim().isEmpty()){
            throw new IllegalArgumentException("La editorial no puede ser nula");
        }
        if(libro.getAnioEdicion() > anioActual) {
            throw new IllegalArgumentException("El año de edición no puede ser mayor al actual");
        }
        
        // se obtiene ficha a partir de libro
        FichaBibliografica ficha = libro.getFichaBibliografica();

        // Primero se valida e inserta la ficha a través del service de ficha
        fichaService.insert(ficha);

        // Se asigna el id generado por la base al libro
        libro.setId(ficha.getId());

        // Se inserta el libro con el nuevo id generado (guardado en memoria en el objeto libro)
        libroDAO.insert(libro);
    }

    @Override
    public void update(Libro libro) throws Exception {
        int anioActual = LocalDate.now().getYear();
        if(libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo no puede ser nulo");
        }
        if(libro.getAutor() == null ||libro.getAutor().trim().isEmpty()){
            throw new IllegalArgumentException("El autor no puede ser nulo");
        }
        if(libro.getEditorial() == null || libro.getEditorial().trim().isEmpty()){
            throw new IllegalArgumentException("La editorial no puede ser nula");
        }
        if(libro.getAnioEdicion() > anioActual) {
            throw new IllegalArgumentException("El año de edición no puede ser mayor al actual");
        }
        
        System.out.println("Actualizando el libro: "+libro.getId());
  
        // utilizar el DAO para actualizar el libro
        libroDAO.update(libro);
    }

    @Override
    public void delete(int id) throws Exception {
        if(id <= 0) {
            throw new IllegalArgumentException("El Id ingresado debe ser un número natural");
        }
        
        // El eiliminado lógico debe hacerse primero sobre la ficha y luego sobre el libro 
        // Por ende primero se debe obtener la ficha y su id para poder borrarla
        
        
        // TENGO QUE IMPLEMENTAR UN GETBYID CON EN ID QUE LLEGA POR PARAMETRO PARA PODER OBTENER EL OBJETO LIBRO
        // CON ESO PUEDO ACCEDER A SU FICHA Y ASI PUEDO PASARLE EL ID PARA HACER EL BORRADO LOGICO DE LA FICHA
        
        // Se obtiene el libro a través del metodo getbyid para tener también los datos de la ficha 
        Libro libroABorrar = libroDAO.getByIdActivo(id);
        
        // Se accede al id de la ficha
        int idFicha = libroABorrar.getFichaBibliografica().getId();
        
        // Se elimina logicamente primero la ficha
        fichaService.delete(idFicha);
        
        System.out.println("Eliminando libro con id: "+id);
        
        // se utiliza el DAO para efectuar el eliminado lógico de libro
        libroDAO.delete(id);
    }

    @Override
    public Libro getById(int id) throws Exception {
        if(id <= 0) {
            throw new IllegalArgumentException("El Id ingresado debe ser un número natural");
        }
        
        Libro libroObtenido = libroDAO.getById(id);
        
        return libroObtenido;
    }

    @Override
    public List<Libro> getAll() throws Exception {
        List<Libro> listaLibros = libroDAO.getAll();
        return listaLibros;
    }
    
    
    // GETTERS DE LIBROS ACTIVOS
    
    @Override
    public Libro getByIdActivo(int id) throws Exception {
        if(id <= 0) {
            throw new IllegalArgumentException("El Id ingresado debe ser un número natural");
        }
        
        Libro libroObtenido = libroDAO.getByIdActivo(id);
        
        if(libroObtenido == null) {
            throw new Exception("No existe libro con el id: "+id);
        }
        
        return libroObtenido;
    }

    @Override
    public List<Libro> getAllActivos() throws Exception {
        List<Libro> listaLibros = libroDAO.getAllActivos();
        return listaLibros;
    }
    
    
    
}
