package modelos;


public class FichaBibliografica extends Base{
    private String isbn;
    private String clasificacion_Dewey;
    private String estanteria;
    private String idioma;
    
    public FichaBibliografica(int id, boolean eliminado, String isbn, String clasificacion_Dewey, String estanteria, String idioma) {
        super(id, eliminado);
        this.isbn = isbn;
        this.clasificacion_Dewey = clasificacion_Dewey;
        this.estanteria = estanteria;
        this.idioma = idioma;
    }

//    public int getId() {
//        return id;
//    }
//
//    public boolean isEliminado() {
//        return eliminado;
//    }

    public String getIsbn() {
        return isbn;
    }

    public String getClasificacion_Dewey() {
        return clasificacion_Dewey;
    }

    public String getEstanteria() {
        return estanteria;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setClasificacion_Dewey(String clasificacion_Dewey) {
        this.clasificacion_Dewey = clasificacion_Dewey;
    }

    public void setEstanteria(String estanteria) {
        this.estanteria = estanteria;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "FichaBibliografica{ id="+this.getId()+", eliminado="+this.isEliminado()+", isbn=" + isbn + ", clasificacion_Dewey=" + clasificacion_Dewey + ", estanteria=" + estanteria + ", idioma=" + idioma + '}';
    }


    
    
    
}
