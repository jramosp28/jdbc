package javier.dao;

import javier.model.Product;

public interface ProductDAO {
    public void insert(Product product);
    public void update(Product product);
    public void delete(Product product);
    public Product read(Integer id);
}


