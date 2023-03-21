package javier.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import javier.model.Product;
import java.sql.*;

/**
 *
 * @author javierrampob
 */
public class ProductDAOImpl implements ProductDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/inventary";
    static final String DB_USR = "root";
    static final String DB_PWD = "";

    /**
     * * Load the driver class
     */
    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            //System.out.println("ERROR: failed to load MySQL JDBC Driver");
            System.err.println("ERROR: failed to load MySQL JDBC Driver"); // imprime en la salida standar de error
            ex.printStackTrace();
        }
    }

    @Override
    public void insert(Product product) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            Statement stmt = conn.createStatement();
            // enviar el comando insert
            stmt.executeUpdate("insert into product values ("
                    + product.getId() + ",'"
                    + product.getName() + "',"
                    + product.getPrice() + ");");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Product product) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            Statement stmt = conn.createStatement();
            // enviar el comando update
            stmt.executeUpdate("update product set name = '"
                    + product.getName() + "', price = "
                    + product.getPrice() + " where id = "
                    + product.getId() + ";");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void delete(int productId) {
        Connection conn = null;
        try {
            registerDriver();
            // abrir la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            Statement stmt = conn.createStatement();
            // enviar el comando delete
            stmt.executeUpdate("delete from product where id = " + productId);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public Product read(Integer id) {
        Connection conn = null;
        Product prod = null;
        try {
            registerDriver();
            // abrir la conexión
            conn = DriverManager.getConnection(DB_URL, DB_USR, DB_PWD);
            // consulta select (selecciona el producto con id especificado)
            try ( PreparedStatement ps = conn.prepareStatement("select * from product where id = ?")) {
                //indicar el id que buscamos
                ps.setInt(1, id);
                //ejecutar la consulta
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // obtener cada una de las columnas y mapearlas a la clase Product
                        prod = new Product(id, rs.getString("name"), rs.getDouble("price"));
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return prod;
    }

    @Override
    public void delete(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
