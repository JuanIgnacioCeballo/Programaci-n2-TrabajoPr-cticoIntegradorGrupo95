
package service;

import java.util.List;

public interface GenericService<T>{
    public void insert(T entidad)throws Exception;
    public void update(T entidad)throws Exception;
    public void delete(int id)throws Exception;
    public T getById(int id)throws Exception;
    public List<T> getAll() throws Exception;
    
    // Obtner una entidad activa (relacionada con el eliminado lógico)
    public T getByIdActivo(int id) throws Exception;
    
    // Obtener lista de entidades activas (también relacionado con eliminado lógico)
    public List<T> getAllActivos() throws Exception;
    
}
