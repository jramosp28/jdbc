package javier;

import javier.dao.ProductDAOImpl;
import javier.model.Product;
import javier.dao.ProductDAO;

public class ProductManager {
    public static void main(String[] args) {
        ProductDAO product = new ProductDAOImpl();
        
        //agregar nuevo producto
        product.insert(new Product(5,"abra",7.99));
        
        //actualizar product
        product.update(new Product(4, "abra", 1.99));
        
        //borrar un producto
        product.delete(4);
        
        //obtener el producto con el ID = 100
        Product p = product.read(5);
        System.out.println(p);
    }
}
