package dao;

import java.util.List;

public interface GenericDAO<T> {
    
    // Interfaz con métodos genéricos a sobreescribir por los DAOs de cada entidad
    
    public void insert(T entidad) throws Exception;
    public void update(T entidad) throws Exception;
    public void delete(int id) throws Exception;
    public T getById(int id) throws Exception;
    public List<T> getAll() throws Exception;
   
    // Relacionado con el borrado lógico
    public void recuperar(int id) throws Exception;
    
    // Obtner una entidad activa (relacionada con el eliminado lógico)
    public T getByIdActivo(int id) throws Exception;
    
    // Obtener lista de entidades activas (también relacionado con eliminado lógico)
    public List<T> getAllActivos() throws Exception;
}
