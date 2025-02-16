package com.stock.dao;

import com.stock.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection conn;

    public ProductDAO() throws ClassNotFoundException {
        try {
            // Database connection
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockdb", "root", "admin");
            if (conn != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("⚠️ ERROR: Database connection failed.");
        }
    }

    // Add product
    
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, description, quantity, price, category) VALUES (?, ?, ?, ?, ?)";
        try {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, product.getName());
                stmt.setString(2, product.getDescription());
                stmt.setInt(3, product.getQuantity());
                stmt.setFloat(4, (float) product.getPrice());
                stmt.setString(5, product.getCategory());
                stmt.executeUpdate();
            } else {
                System.out.println("⚠️ ERROR: Database connection is NULL!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all products
    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setPrice(rs.getFloat("price"));
                    product.setCategory(rs.getString("category"));
                    products.add(product);
                }
            } else {
                System.out.println("⚠️ ERROR: Database connection is NULL!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Get product by ID (for editing)
    
    public Product getProductById(int id) {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setPrice(rs.getFloat("price"));
                    product.setCategory(rs.getString("category"));
                }
            } else {
                System.out.println("⚠️ ERROR: Database connection is NULL!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // Update product
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, quantity = ?, price = ?, category = ? WHERE id = ?";

        try {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, product.getName());
                stmt.setString(2, product.getDescription());
                stmt.setInt(3, product.getQuantity());
                stmt.setFloat(4, (float) product.getPrice());
                stmt.setString(5, product.getCategory());
                stmt.setInt(6, product.getId());
                stmt.executeUpdate();
            } else {
                System.out.println("⚠️ ERROR: Database connection is NULL!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete product
    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } else {
                System.out.println("⚠️ ERROR: Database connection is NULL!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
