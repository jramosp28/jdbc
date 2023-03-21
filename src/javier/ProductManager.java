package javier;

import javier.dao.ProductDAOImpl;
import javier.model.Product;
import javier.dao.ProductDAO;

public class ProductManager {
    public static void main(String[] args) {
        ProductDAO product = new ProductDAOImpl();
        
        //agregar nuevo producto
        product.insert(new Product(4,"pollo",7.99));
        
        //actualizar product
        Product updatedProduct = new Product(200, "Pollo", 7.99);
        product.update(updatedProduct);
        
        //borrar un producto
        ProductDAOImpl productDAO = new ProductDAOImpl();
        int productId = 200;
        productDAO.delete(productId);
        
        //obtener el producto con el ID = 100
        Product p = product.read(4);
        System.out.println(p);
    }
}
