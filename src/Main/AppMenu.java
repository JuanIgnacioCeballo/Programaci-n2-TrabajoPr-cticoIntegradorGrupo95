
package Main;

import dao.FichaBibliograficaDAO;
import dao.GenericDAO;
import dao.LibroDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelos.FichaBibliografica;
import modelos.Libro;
import service.FichaBibliograficaServiceImpl;
import service.GenericService;
import service.LibroServiceImpl;

public class AppMenu {
    
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GenericDAO<FichaBibliografica> fichaDAO = new FichaBibliograficaDAO();
        GenericService<FichaBibliografica> fichaService = new FichaBibliograficaServiceImpl(fichaDAO);
        GenericDAO<Libro> libroDAO = new LibroDAO();
        GenericService<Libro> libroService = new LibroServiceImpl(libroDAO, fichaService);
        
        
        int opc = -1;
        while (opc != 0) {
            try {
                menu();
                opc = leerEntero("Seleccione una opción:");

                switch (opc) {

                    // ----------------------------
                    // LIBRO + FICHA
                    // ----------------------------
                    case 1 -> crearLibroConFicha(libroService);
                    case 2 -> listarLibro(libroService);
                    case 3 -> modificarLibro(libroService);
                    case 4 -> eliminarLibro(libroService);
                    case 5 -> listarLibros(libroService);

                    // ----------------------------
                    // FICHA
                    // ----------------------------
                    case 6 -> listarFicha(fichaService);
                    case 7 -> modificarFicha(fichaService);
                    case 8 -> eliminarFicha(fichaService);
                    case 9 -> listarFichas(fichaService);

                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opcion invalida.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    public static void menu() {
        System.out.println("1) Crear Libro y su Ficha bibliografica \n2) Listar Libro\n3) Modificar Libro \n4)Eliminar Libro \n5) Listar todos los libros \n6) Listar Ficha \n7) Modificar Ficha \n8) Eliminar Ficha \n9) Listar todas las Fichas");
    }
    
    // METODOS PARA VALIDAR EL TIPO DE DATO
    
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + " ");
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }
    
    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + " ");
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número decimal válido.");
            }
        }
    }
    
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje + " ");
        return sc.nextLine();
    }
    
    
    // METODOS CRUD LIBRO
    
    private static void crearLibroConFicha(GenericService<Libro> libroService) throws Exception {
        
        try {
            System.out.println("Datos sobre libro: ");
            String titulo = leerTexto("Ingrese título: ");
            String autor = leerTexto("Ingrese autor: ");
            String editorial = leerTexto("Ingrese editorial: ");
            int anio = leerEntero("Ingrese año de edición: ");
        
            System.out.println("Datos sobre ficha bibliografica: ");
            String isbn = leerTexto("Ingrese isbn: ");
            String clasificacion = leerTexto("Ingrese clasificacion Dewey: ");
            String estanteria = leerTexto("Ingrese estanteria: ");
            String idioma = leerTexto("Ingrese idioma: ");

            Libro libro = new Libro(1, false, titulo, autor, editorial, anio, isbn, clasificacion, estanteria, idioma);

            libroService.insert(libro);

            System.out.println("Libro "+libro.getTitulo()+" creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        

    }
    
    private static void listarLibro(GenericService<Libro> libroService) throws Exception {
        try {
            int id = leerEntero("Ingrese ID del libro:");
            Libro libro = libroService.getByIdActivo(id);

            System.out.println(libro); 
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    private static void modificarLibro(GenericService<Libro> libroService) throws Exception {
        
        try {
            int idActual = leerEntero("Ingrese el id del libro a modificar: ");
            Libro libro = libroService.getByIdActivo(idActual);
        
            System.out.println("Campos del libro a modificar: ");
            String nuevoTitulo = leerTexto("Ingrese nuevo título (Actual: " + libro.getTitulo() + "): ");
            String nuevoAutor = leerTexto("Ingrese nuevo Autor (Actual: "+libro.getAutor()+"): ");
            String nuevaEditorial = leerTexto("Ingrese nueva editoriual (Actual: "+libro.getEditorial()+ "): ");
            int nuevoAnioEdic = leerEntero("Ingrese nuevo año de edicion (Actual: "+libro.getAnioEdicion()+"): ");
            
            // Validaciones sobre datos ingresados (Los datos se actualizan solo si lo ingresado no es nulo o vacio y si no es igual a lo obtenido por la bd)
            if(!nuevoTitulo.trim().isEmpty() && !nuevoTitulo.equalsIgnoreCase(libro.getTitulo())) {
                libro.setTitulo(nuevoTitulo);
            }
            
            if(!nuevoAutor.trim().isEmpty() && !nuevoAutor.equalsIgnoreCase(libro.getAutor())) {
                libro.setAutor(nuevoAutor);
            }
            
            if(!nuevaEditorial.trim().isEmpty() && !nuevaEditorial.equalsIgnoreCase(libro.getEditorial())) {
                libro.setEditorial(nuevaEditorial);
            }
            
            if(nuevoAnioEdic != libro.getAnioEdicion()) {
                libro.setAnioEdicion(nuevoAnioEdic);
            }
            
            libroService.update(libro);

            System.out.println("Libro "+idActual+" modificado correctamente.");
            
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    private static void eliminarLibro(GenericService<Libro> libroService) throws Exception {
        
        try {
            int id = leerEntero("Ingrese ID del libro a eliminar:");
            libroService.delete(id);
            System.out.println("Libro "+id+" eliminado.");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    private static void listarLibros(GenericService<Libro> libroService) throws Exception {
        List<Libro> listaLibros = libroService.getAllActivos();
        for (Libro libro : listaLibros) {
            System.out.println(libro+"\n");
        }
    }
    
    
    // METODOS CRUD FICHA
    
    
    private static void listarFicha(GenericService<FichaBibliografica> fichaService) throws Exception {
        
        try {
            int id = leerEntero("Ingrese ID de la ficha:");
            System.out.println(fichaService.getByIdActivo(id));
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    
    private static void modificarFicha(GenericService<FichaBibliografica> fichaService) throws Exception {
        
        try {
            int idActual = leerEntero("Ingrese el id de la ficha a modificar: ");
            FichaBibliografica ficha = fichaService.getByIdActivo(idActual);
        
            System.out.println("Campos de la ficha a modificar: ");
            String nuevoIsbn = leerTexto("Ingrese nuevo ISBN (Actual: " + ficha.getIsbn()+ "): ");
            String nuevaClasificacion = leerTexto("Ingrese nueva clasificacin Dewey (Actual: "+ficha.getClasificacion_Dewey()+"): ");
            String nuevaEstanteria = leerTexto("Ingrese nueva estanteria (Actual: "+ficha.getEstanteria()+ "): ");
            String nuevoIdioma = leerTexto("Ingrese nuevo idioma (Actual: "+ficha.getIdioma()+"): ");
            
            // Validaciones sobre datos ingresados (Si los datos son iguales a los actuales, no se modifican, de otro modo si se actualizan)
            if (!nuevoIsbn.trim().isEmpty() && !nuevoIsbn.equalsIgnoreCase(ficha.getIsbn())) {
                ficha.setIsbn(nuevoIsbn);
            }

      
            if(!nuevaClasificacion.trim().isEmpty() && !nuevaClasificacion.equalsIgnoreCase(ficha.getClasificacion_Dewey())) {
                ficha.setClasificacion_Dewey(nuevaClasificacion);
            }
            
            if(!nuevaEstanteria.trim().isEmpty() && !nuevaEstanteria.equalsIgnoreCase(ficha.getEstanteria())) {
                ficha.setEstanteria(nuevaEstanteria);
            }
            
            if(!nuevoIdioma.trim().isEmpty() && !nuevoIdioma.equalsIgnoreCase(ficha.getIdioma())) {
                ficha.setIdioma(nuevoIdioma);
            }
            
            fichaService.update(ficha);

            System.out.println("Ficha "+idActual+" modificada correctamente.");
            
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    private static void eliminarFicha(GenericService<FichaBibliografica> fichaService) throws Exception {
        
        try {
            int id = leerEntero("Ingrese ID de la ficha a eliminar:");
            fichaService.delete(id);
            System.out.println("Ficha "+id+" eliminada.");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    private static void listarFichas(GenericService<FichaBibliografica> fichaService) throws Exception {
        List<FichaBibliografica> listaFichas = fichaService.getAllActivos();
        for (FichaBibliografica ficha : listaFichas) {
            System.out.println(ficha+"\n");
        }
    }
    
    
}
