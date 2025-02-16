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
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }

        header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 20px;
        }

        h1 {
            margin: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        form input, form select {
            padding: 10px;
            margin: 5px;
            width: 250px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            background-color: #fff;
        }

        form select {
            /* Custom styling for select dropdown */
            background-color: #fafafa;
            cursor: pointer;
            appearance: none; /* For removing the default arrow in some browsers */
            -webkit-appearance: none; /* For Safari */
            -moz-appearance: none; /* For Firefox */
            background-image: url('https://img.icons8.com/ios/452/expand-arrow.png');
            background-repeat: no-repeat;
            background-position: right 10px center;
            background-size: 16px 16px;
        }

        form button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        form button:hover {
            background-color: #45a049;
        }

        .actions a {
            padding: 8px 12px;
            text-decoration: none;
            background-color: #f44336;
            color: white;
            border-radius: 5px;
            margin-right: 5px;
        }

        .actions a:hover {
            background-color: #d32f2f;
        }

        .edit-btn {
            background-color: #007bff;
        }

        .edit-btn:hover {
            background-color: #0056b3;
        }
    </style>
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
