package com.ramya.OnlineShopping;

import OnlineShopping.Services.AdminServices;
import OnlineShopping.Services.OrderService;
import OnlineShopping.Services.PaymentService;
import OnlineShopping.Services.ProductService;
import OnlineShopping.Services.UserService;
import OnlineShopping.entity.Order;
import OnlineShopping.entity.OrderItem;
import OnlineShopping.entity.Payment;
import OnlineShopping.entity.Product;
import OnlineShopping.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static AdminServices adminService = new AdminServices();
    private static UserService userService = new UserService();
    private static ProductService productService = new ProductService();
    private static OrderService orderService = new OrderService();

    // Change loggedInUser to be of type User
    private static User loggedInUser;

    public static void main(String[] args) {
    	
    	System.out.println("**** ONLINE SHOPPING SYSTEM ****");
    	while(true) {
    		System.out.println("1.Admin Login");
    		System.out.println("2.User Login");
    		System.out.println("3.Exit");
    		System.out.print("Choose an Option: ");
    		int choice = sc.nextInt();
    		sc.nextLine();
    		switch(choice) {
    		case 1 :
    			if (!loginAdmin()) {
    	            return; // Exit if admin login fails
    	        }
    	        else {
    	        	System.out.println();
    	        	handleProductManagement();
    	        }
    			break;
    		case 2 :

    	        while (true) {
    	        	System.out.println();
    	            System.out.println("1. User Services");
    	            System.out.println("2. Exit");
    	            System.out.print("Choose an Option: ");
    	            int option = sc.nextInt();
    	            sc.nextLine(); // Consume newline

    	            if (option == 1) {
    	                handleUserService();
    	            } else {
    	                System.out.println("Exiting...");
    	                break;
    	            }
    	        }
    	        break;
    		case 3 :
    			System.out.println("exiting");
    			break;
    		default:
    			System.out.println("Invalid Option. Please try again.");
    			
    		}
    	}
    	}
    	

    private static boolean loginAdmin() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        try {
            if (adminService.login(username, password)) {
                System.out.println("*** Login successful! ***");
                return true;
            } else {
                System.out.println("Login failed. Please check your username and password.");
            }
            System.out.println();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void handleUserService() {
        while (true) {
        	System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Back");
            System.out.print("Choose an Option: ");
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    userLogin();
                    break;
                case 2:
                    userRegister();
                    break;
                case 3:
                    return; // Go back to main menu
                default:
                    System.out.println("Invalid Option. Please try again.");
            }
        }
    }

    private static void userLogin() {
    	System.out.println();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        if (userService.login(username, password)) {
            System.out.println("*** Login Successful!! ***");
            System.out.println("Products Menu");
        
            
            loggedInUser = userService.getUserByUsername(username);
            if (loggedInUser != null) {
            	 
                     // Fetch and display all products
                     List<Product> products = productService.getAllProducts();
                     if (products.isEmpty()) {
                         System.out.println("No products available.");
                     } else {
                         System.out.println("Available Products:");
                         for (Product product : products) {
                        	 System.out.println("ID: " + product.getId() + " - Name: " + product.getName());
                         }
                     }
           
            handleOrderManagement(); // Call order management after product management
            }
            else {
                System.out.println("User not found after login.");
            }
        } else {
            System.out.println("Login Failed.. Kindly check username and password.");
        }
        System.out.println();
    }

    private static void userRegister() {
    	System.out.println();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        userService.register(username, password);
        System.out.println("*** User Registered Successfully!! ***");
        System.out.println();
    }

    private static void handleProductManagement() {
        while (true) {
        	System.out.println();
            System.out.println("1. Add Product");
            System.out.println("2. View Product by ID");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProductById();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Exiting product management.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addProduct() {
    	System.out.println();
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product description: ");
        String description = sc.nextLine();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        productService.addProduct(name, description, price);
        System.out.println("** Product added successfully! **");
    }

    private static void viewProductById() {
    	System.out.println();
    	System.out.print("Enter product ID: ");
        Long id = sc.nextLong();
        Product product = productService.getProductById(id);
        
        if (product != null) {
            System.out.println("Product Name: " + product.getName());
            // If you want to display more product details, you can do so here
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void updateProduct() {
    	System.out.println();
        System.out.print("Enter product ID to update: ");
        Long updateId = sc.nextLong();
        sc.nextLine();  // Consume newline
        System.out.print("Enter new product name: ");
        String newName = sc.nextLine();
        System.out.print("Enter new product description: ");
        String newDescription = sc.nextLine();
        System.out.print("Enter new product price: ");
        double newPrice = sc.nextDouble();
        productService.updateProduct(updateId, newName, newDescription, newPrice);
        System.out.println("** Product updated successfully! **");
    }

    private static void deleteProduct() {
    	System.out.println();
        System.out.print("Enter product ID to delete: ");
        Long deleteId = sc.nextLong();
        productService.deleteProduct(deleteId);
        System.out.println("** Product deleted successfully! **");
    }

    private static void handleOrderManagement() {
    	System.out.println();
        if (loggedInUser == null) {
            System.out.println("User not found.");
            return;
        }

        // Sample order process (for demonstration purposes)
        List<OrderItem> orderItems = new ArrayList<>();
        while (true) {
            System.out.print("Enter product ID to add to order (or -1 to finish): ");
            Long productId = sc.nextLong();
            if (productId == -1) break; // End order input

            Product product = productService.getProductById(productId);
            if (product != null) {
                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();
                orderItems.add(new OrderItem(product, quantity));
                System.out.println("Added " + product.getName() + " to the order.");
            } else {
                System.out.println("Product not found.");
            }
        }

        if (!orderItems.isEmpty()) {
            Order order = orderService.createOrder(loggedInUser, orderItems);
            System.out.println("Created Order: " + order);
        
            // Payment processing example
            Payment payment = new Payment();
            payment.setAmount(order.getTotalAmount());
            System.out.println(order.getTotalAmount());
            sc.nextLine();
            System.out.println("enter payment type");
            payment.setPaymentMethod(sc.nextLine()); // Example method
            System.out.println("enter status of payment");
            payment.setStatus(sc.nextLine()); // Initial status

            PaymentService paymentService = new PaymentService();
            paymentService.processPayment(payment);
            System.out.println("*** Payment Done Successfully: ***" + payment);
        } else {
            System.out.println("No items added to the order.");
        }
    }
}
