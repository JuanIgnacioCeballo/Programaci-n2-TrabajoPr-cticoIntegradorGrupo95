package Main;

import Main.AppMenu;
//import dao.FichaBibliograficaDAO;
//import dao.GenericDAO;
//import dao.LibroDAO;
//import java.util.ArrayList;
//import java.util.List;
//import modelos.FichaBibliografica;
//import modelos.Libro;
//import service.FichaBibliograficaServiceImpl;
//import service.GenericService;
//import service.LibroServiceImpl;

public class Main {

    
    public static void main(String[] args) {
        AppMenu.main(args);
//        GenericDAO<FichaBibliografica> fichaDAO = new FichaBibliograficaDAO();
//        GenericService<FichaBibliografica> fichaService = new FichaBibliograficaServiceImpl(fichaDAO);
//        GenericDAO<Libro> libroDAO = new LibroDAO();
//        GenericService<Libro> libroService = new LibroServiceImpl(libroDAO, fichaService);
        /*
        try {
            Libro libro1 = new Libro(4, false, "Morias", "Autor asdasd", "Editorial mock", 2001, "isbn785412339", "acción", "3", "portuges");
            libroService.insert(libro1);
            System.out.println("Libro ingresado correctamente");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        */
        /*
        try {
            FichaBibliografica fichaActualizada = new FichaBibliografica(4, false, "9789504987239", "terror cósmico", "258", "Español");
            fichaService.update(fichaActualizada);
            System.out.println("Ficha actualizada correctamente");
        } catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
        }

        */
        /*
        try {
            Libro libroActualizado = new Libro();
            libroActualizado.setId(1);
            libroActualizado.setAutor("Howard Phillip Lovecraft");
            libroActualizado.setTitulo("La llamada de Cthulhu");
            libroActualizado.setEditorial("Al mundo");
            libroActualizado.setEliminado(false);
            libroActualizado.setAnioEdicion(1928);
            
            libroService.update(libroActualizado);
            System.out.println("Libro "+libroActualizado.getId()+" actualizado correctamente");
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        */
        
        /*
        try {
            libroService.delete(3);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        */
        
        
        
        // Obtener fichas y libros
         /*
        try {
            List<FichaBibliografica> listaDeFichas = fichaService.getAll();
            for (FichaBibliografica ficha : listaDeFichas) {
                System.out.println(ficha+"\n");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        
        try {
            List<Libro> listaDeLibros = libroService.getAll();
            for (Libro libro : listaDeLibros) {
                System.out.println(libro+"\n");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        */
         
         // Obtener fichas y libros activas (eliminado = 0)
//        try {
//            List<FichaBibliografica> listaFichasActivas = fichaService.getAllActivos();
//            for (FichaBibliografica fichaActiva : listaFichasActivas) {
//                System.out.println(fichaActiva+"\n");
//            }
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
//        
//        try {
//            List<Libro> listaDeLibros = libroService.getAllActivos();
//            for (Libro libroActivo : listaDeLibros) {
//                System.out.println(libroActivo+"\n");
//            }
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
//        
//        try {
//            FichaBibliografica fichaEncontrada = fichaService.getByIdActivo(8);
//            System.out.println(fichaEncontrada);
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
//        
//        try {
//            Libro libroEncontrado = libroService.getByIdActivo(3);
//            System.out.println(libroEncontrado);
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
        
    }
    
}
