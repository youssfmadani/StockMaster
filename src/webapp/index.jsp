<%@ page import="java.util.List, com.stock.dao.ProductDAO, com.stock.model.Product" %>
<%
    ProductDAO productDAO = new ProductDAO();
    List<Product> productList = productDAO.getAllProducts();
    List<String> categories = List.of("Electronics", "Clothing", "Furniture", "Books"); // Example categories
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>

    <!-- External Stylesheet -->
   
</head>
<body>
    <header>
        <h1>Product List</h1>
    </header>

    <div class="container">
        <!-- Add Product Form -->
        <h2>Add a New Product</h2>
        <form action="ProductController?action=add" method="post">
            <input type="text" name="name" placeholder="Name" required>
            <input type="text" name="description" placeholder="Description" required>
            <input type="number" name="quantity" placeholder="Quantity" required>
            <input type="number" name="price" placeholder="Price" required>

            <!-- Category Select Dropdown -->
            <select name="category" required>
                <option value="">Select Category</option>
                <% for (String category : categories) { %>
                    <option value="<%= category %>"><%= category %></option>
                <% } %>
            </select>
            
            <button type="submit">Add Product</button>
        </form>

        <!-- Product Table -->
        <h2>All Products</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% if (productList != null) {
                    for (Product product : productList) { %>
                    <tr>
                        <td><%= product.getId() %></td>
                        <td><%= product.getName() %></td>
                        <td><%= product.getDescription() %></td>
                        <td><%= product.getQuantity() %></td>
                        <td><%= product.getPrice() %></td>
                        <td><%= product.getCategory() %></td>
                        <td class="actions">
                            <a href="ProductController?action=delete&id=<%= product.getId() %>">Delete</a>
                            <a class="edit-btn" href="ProductController?action=edit&id=<%= product.getId() %>">Edit</a>
                        </td>
                    </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
</body>
</html>
