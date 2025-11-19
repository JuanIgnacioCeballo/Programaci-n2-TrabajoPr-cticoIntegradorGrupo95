package modelos;

public abstract class Base {
    // Atributos que ambas clases tienen en común
    private int id;
    private boolean eliminado; // Atributo que iundica la eliminación lógica del objeto
    
    public Base(int id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    };
    
    public Base(){
        
    }

    public int getId() {
        return this.id;
    }

    public boolean isEliminado() {
        return this.eliminado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    
    
    
}
