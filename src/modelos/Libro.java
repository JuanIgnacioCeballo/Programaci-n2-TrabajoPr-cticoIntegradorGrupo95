package modelos;

public class Libro extends Base{
    
    private String titulo;
    private String autor;
    private String editorial;
    private int anioEdicion;
    private FichaBibliografica fichaBibliografica; 
    
    public Libro(int id, boolean eliminado, String titulo, String autor, String editorial, int anioEdicion, String isbn, String clasificacion_Dewey, String estanteria, String idioma) {
        super(id, eliminado);
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioEdicion = anioEdicion;
        this.fichaBibliografica = new FichaBibliografica(id, false, isbn, clasificacion_Dewey, estanteria, idioma);
    }
    
    public Libro(int id, boolean eliminado, String titulo, String autor, String editorial, int anioEdicion, int id_ficha, boolean ficha_eliminado, String isbn, String clasificacion_Dewey, String estanteria, String idioma) {
        super(id, eliminado);
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioEdicion = anioEdicion;
        this.fichaBibliografica = new FichaBibliografica(id_ficha, false, isbn, clasificacion_Dewey, estanteria, idioma);
    }
    
    public Libro() {
        super();
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public int getAnioEdicion() {
        return this.anioEdicion;
    }

    public FichaBibliografica getFichaBibliografica() {
        return this.fichaBibliografica;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAnioEdicion(int anioEdicion) {
        this.anioEdicion = anioEdicion;
    }

    public void setFichaBibliografica(FichaBibliografica fichaBibliografica) {
        this.fichaBibliografica = fichaBibliografica;
    }

    @Override
    public String toString() {
        return "Libro{ id_libro=" +this.getId()+ ", eliminado=" +this.isEliminado()+ ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", anioEdicion=" + anioEdicion + ", fichaBibliografica=" + fichaBibliografica + '}';
    }
    
    
}
