package com.stock.controller;

import com.stock.dao.ProductDAO;
import com.stock.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            productDAO = new ProductDAO();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            
        } // Initialize DAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            productDAO.deleteProduct(id);   // Delete product
            response.sendRedirect("index.jsp");
            return;
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = productDAO.getProductById(id);   // Get product by ID
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
            dispatcher.forward(request, response);
            return;
        }

        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            // Update existing product
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float price = Float.parseFloat(request.getParameter("price"));
            String category = request.getParameter("category");

            Product product = new Product();
            product.setId(id);   // Set the ID to update the product
            product.setName(name);
            product.setDescription(description);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setCategory(category);

            productDAO.updateProduct(product); // Update product

            response.sendRedirect("index.jsp");
        } else {
            // Add new product
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            float price = Float.parseFloat(request.getParameter("price"));
            String category = request.getParameter("category");

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setCategory(category);

            productDAO.addProduct(product);   // Add product

            response.sendRedirect("index.jsp");
        }
    }
}
