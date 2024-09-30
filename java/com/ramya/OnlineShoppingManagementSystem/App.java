package com.ramya.OnlineShoppingManagementSystem;

import java.util.HashSet;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
       Configuration con = new Configuration().configure();
       SessionFactory sf=con.buildSessionFactory();
       Session session = sf.openSession();
       try (Scanner sc = new Scanner(System.in)) {
		//performing CRUD operations on website entity
		   int choice=0;
		   while(choice!=5) {
			   System.out.println("online shopping system");
			   System.out.println("1:Insert website details");
			   System.out.println("2:Update website details");
			   System.out.println("3:Delete website details");
			   System.out.println("4:Retrieve website details");
			   System.out.println("5.Exit");
			   System.out.println("enter your choice");
			   choice=sc.nextInt();
			   sc.nextLine();  // Consume the newline
			   Transaction tx = null;
			   switch(choice) {
			   case 1 :// Insert into website
				   tx=session.beginTransaction();//begining transaction
		           System.out.println("Enter appName: ");
		           String app_Name = sc.nextLine();
		           System.out.println("Enter URL: ");
		           String url = sc.nextLine();
		           System.out.println("Enter Description: ");
		           String description = sc.nextLine();
		           Website website=new Website();
		           website.setApp_name(app_Name);
		           website.setUrl(url);
		           website.setDescription(description);
		           Admin admin = null;
				   website.setAdmin(admin);
		           session.save(website);
		           tx.commit();
		           System.out.println("Website inserted successfully!");
		           break;
			   case 2 :// Update website
		           tx = session.beginTransaction();
		           System.out.print("Enter appName of the website to update: ");
		           String updateAppName = sc.nextLine();
		           Object websiteToUpdate = session.get(Website.class, updateAppName);
		           if (websiteToUpdate != null) {
		        	   System.out.println("Enter new URL: ");
		               String newUrl = sc.nextLine();
		               System.out.println("Enter new Description: ");
		               String newDescription = sc.nextLine();
		               ((Website) websiteToUpdate).setUrl(newUrl);
		               ((Website) websiteToUpdate).setDescription(newDescription);
		        	   session.update(websiteToUpdate);
		               tx.commit();
		               System.out.println("Website updated successfully!");
		           } else {
		               System.out.println("Website not found.");
		           }
		           break;
			   case 3 : // Delete website details
		           tx= session.beginTransaction();
		           System.out.print("Enter appName of the website to delete: ");
		           String deleteAppName = sc.nextLine();

		           Website websiteToDelete = (Website) session.get(Website.class, deleteAppName);
		           if (websiteToDelete != null) {
		               session.delete(websiteToDelete);
		               tx.commit();
		               System.out.println("Website deleted successfully!");
		           } else {
		               System.out.println("Website not found.");
		           }
		           break;
			   case 4 :// Retrieve website details
		           System.out.print("Enter appName of the website to retrieve: ");
		           String retrieveAppName = sc.nextLine();

		           Website retrievedWebsite = (Website) session.get(Website.class, retrieveAppName);
		           if (retrievedWebsite != null) {
		        	   System.out.println("app name : "+retrievedWebsite.getApp_name());
		        	   System.out.println("url : "+retrievedWebsite.getUrl());
		        	   System.out.println("description : "+retrievedWebsite.getDescription());
		               System.out.println("Retrieved Website: " + retrievedWebsite);
		           } else {
		               System.out.println("Website not found.");
		           }
		           break;

		       case 5: // Exit
		           System.out.println("Exiting...");
		           break;

		       default://default case
		           System.out.println("Invalid choice. Please enter a valid option.");
		   }
   }
		   //performing CRUD operations on admin Entity
		   int choice1=0;
		   while (choice1 != 5) {
		       System.out.println("Online Shopping System");
		       System.out.println("1: Insert admin details");
		       System.out.println("2: Update admin details");
		       System.out.println("3: Delete admin details");
		       System.out.println("4: Retrieve admin details");
		       System.out.println("5: Exit");
		       System.out.println("Enter your choice:");
		       choice1 = sc.nextInt();
		       sc.nextLine();  // Consume the newline
		       Transaction tx = null;
		       switch (choice1) {
		           case 1:
		               // Insert into admin
		               tx = session.beginTransaction();
		               System.out.println("Enter name: ");
		               String name = sc.nextLine();
		               System.out.println("Enter password: ");
		               String password = sc.nextLine();
		               System.out.println("Enter app name: ");
		               String app_name = sc.nextLine();
		               
		               Admin admin = new Admin();
		               admin.setName(name);
		               admin.setPassword(password);
		               
		               Website website = (Website) session.get(Website.class, app_name);
		               if (website!= null) {
		                    admin.setWebsite(website);
		                    website.setApp_name(app_name);
		                    website.setAdmin(admin);
		                   session.save(admin);
		                   tx.commit();
		                   System.out.println("admin details inserted successfully.");
		               } else {
		                   System.out.println("admin details not inserted");
		                   
		               }
		               break;
		           
		           case 2:
		               // Updating admin details
		               try {
		                   tx = session.beginTransaction();
		                   System.out.println("Enter admin id to update: ");
		                   int adminId = sc.nextInt();
		                   sc.nextLine(); // Consume the newline

		                   admin = (Admin) session.get(Admin.class, adminId);
		                   if (admin != null) {
		                       System.out.println("Enter new name: ");
		                       name = sc.nextLine();
		                       System.out.println("Enter new password: ");
		                       password = sc.nextLine();
		                       
		                       admin.setName(name);
		                       admin.setPassword(password);
		                       session.update(admin);
		                       tx.commit();
		                       System.out.println("Admin updated successfully!");
		                   } else {
		                       System.out.println("Admin with id " + adminId + " not found.");
		                   }
		               } catch (Exception e) {
		                   if (tx != null) tx.rollback();
		                   e.printStackTrace();
		               }
		               break;
		           
		           case 3:
		               // Deleting admin details
		               try {
		                   tx = session.beginTransaction();
		                   System.out.println("Enter admin id to delete: ");
		                   int adminId = sc.nextInt();
		                   sc.nextLine(); // Consume the newline

		                   admin = (Admin) session.get(Admin.class, adminId);
		                   if (admin != null) {
		                       session.delete(admin);
		                       tx.commit();
		                       System.out.println("Admin deleted successfully!");
		                   } else {
		                       System.out.println("Admin with id " + adminId + " not found.");
		                   }
		               } catch (Exception e) {
		                   if (tx != null) tx.rollback();
		                   e.printStackTrace();
		               }
		               break;
		           
		           case 4:
		               // Retrieving from admin
		               System.out.println("Enter admin id to retrieve: ");
		               int adminId = sc.nextInt();
		               sc.nextLine(); // Consume the newline

		               admin = (Admin) session.get(Admin.class, adminId);
		               if (admin != null) {
		                   System.out.println("Admin ID: " + admin.getAdmin_id());
		                   System.out.println("Name: " + admin.getName());
		                   System.out.println("Password: " + admin.getPassword());
		                   if (admin.getWebsite() != null) {
		                       System.out.println("App Name: " + admin.getWebsite().getApp_name());
		                   } else {
		                       System.out.println("No website details available.");
		                   }
		               } else {
		                   System.out.println("Admin with id " + adminId + " not found.");
		               }
		               break;
		           
		           case 5:
		               // Exit
		               System.out.println("Exiting...");
		               break;
		           
		           default:
		               System.out.println("Invalid choice. Please enter a valid option.");
		       }
		   }
		   //performing CRUD  operations on customer entity
		   int choice2=0;
		   while(choice2!=5) {
			   System.out.println("online shopping system");
			   System.out.println("1:Insert customer details");
			   System.out.println("2:Update customer details");
			   System.out.println("3:Delete customer details");
			   System.out.println("4:Retrieve customer details");
			   System.out.println("5.Exit");
			   System.out.println("enter your choice");
			   choice2=sc.nextInt();//taking user choice
			   sc.nextLine();  // Consume the newline
			   Transaction tx = null;
			   switch(choice2) {
			   case 1 :
				   //insert customer details
				  tx = session.beginTransaction();
		         Customer customer = new Customer();
		         System.out.println("enter customer id");
		         customer.setC_id(sc.nextInt());
		         sc.nextLine();
		         System.out.print("Enter user name: ");
		         customer.setUser_name(sc.nextLine());
		  
		         System.out.print("Enter mobile number: ");
		         customer.setMobile_no(sc.nextInt());
		         sc.nextLine(); // Consume the newline
		         
		         System.out.print("Enter email: ");
		         customer.setEmail(sc.nextLine());
		         
		         System.out.print("Enter address: ");
		         customer.setAddress(sc.nextLine());
		         System.out.print("Enter password: ");
		         customer.setPassword(sc.nextLine()); // Assuming you have a setPassword method
		         System.out.print("Enter app name: ");
		         String appName=sc.nextLine();
		         sc.nextLine();
		         Website website=(Website) session.get(Website.class, appName);
		         if (website != null) {
		             // Initialize the website set if it's not already
		             if (customer.getWebsite() == null) {
		                 customer.setWebsite(new HashSet<>());
		             }
		             customer.getWebsite().add(website); // Add website to the customer's set
		         } else {
		             System.out.println("Website with name '" + appName + "' does not exist.");
		              
		         }
  
		         session.save(customer);
		         tx.commit(); // Commit the transaction if all is well
		         System.out.println("Customer details inserted successfully.");
		         break;
			   case 2:
				   //update customer details
				  tx = session.beginTransaction();
		         
		         System.out.print("Enter customer ID to update: ");
		         int customerId = sc.nextInt();
		         sc.nextLine(); // Consume the newline
		         
		         customer = (Customer) session.get(Customer.class, customerId);
		         if (customer != null) {
		             System.out.print("Enter new user name (leave blank to keep current): ");
		             String newName = sc.nextLine();
		             if (!newName.isEmpty()) customer.setUser_name(newName);
		             session.update(customer);
		             tx.commit();
		             System.out.println("Customer details updated successfully.");
		         } else {
		             System.out.println("Customer not found.");
		         }
		         break;
			   case 3:
				   //delete customer details
				  tx = session.beginTransaction();
		         
		         System.out.print("Enter customer ID to delete: ");
		         customerId = sc.nextInt();
		         sc.nextLine(); // Consume the newline
		         
		         customer = (Customer) session.get(Customer.class, customerId);
		         if (customer != null) {
		             session.delete(customer);
		             tx.commit();
		             System.out.println("Customer details deleted successfully.");
		         } else {
		             System.out.println("Customer not found.");
		         }
		         
		         break;
			   case 4:
				   //retrieve customer details
				  System.out.print("Enter customer ID to retrieve: ");
		         customerId = sc.nextInt();
		         sc.nextLine(); // Consume the newline
		         
		         customer = (Customer) session.get(Customer.class, customerId);
		         if (customer != null) {
		             System.out.println("Customer ID: " + customer.getC_id());
		             System.out.println("User Name: " + customer.getUser_name());
		             System.out.println("mobile number: " + customer.getMobile_no());
		             System.out.println("Email: "+ customer.getEmail());
		             System.out.println("password: " +customer.getPassword());
		             System.out.println("address: "+ customer.getAddress());
		         } else {
		             System.out.println("Customer not found.");
		         }
		         break;
			   case 5:
				  // Exit
		          System.out.println("Exiting...");
		          break;
		          
		      default:
		          System.out.println("Invalid choice. Please enter a number between 1 and 5.");
				   
			   }
		  
		}
		   //performing CRUD operations on shopping cart entity
		int choice3=0;
		while (choice3 != 5) {
		    System.out.println("Online Shopping System");
		    System.out.println("1: Insert shopping cart details");
		    System.out.println("2: Update shopping cart details");
		    System.out.println("3: Delete shopping cart details");
		    System.out.println("4: Retrieve shopping cart details");
		    System.out.println("5: Exit");
		    System.out.println("Enter your choice:");
		    choice3 = sc.nextInt();
		    sc.nextLine();  // Consume the newline
		    Transaction tx = null;
		    switch(choice3) {
		   case 1 :
			   // Insert shopping cart details
			   try {
		        tx = session.beginTransaction();
		       ShoppingCart cart =new ShoppingCart();
		       System.out.println("enter cart id");
		       cart.setCartId(sc.nextInt());
		       sc.nextLine();
		        System.out.print("Enter product name: ");
		        cart.setP_name(sc.nextLine());
		        sc.nextLine(); // Consume the newline

		        System.out.print("Enter billing products: ");
		        cart.setBilling_products(sc.nextInt());

		        System.out.print("Enter total price: ");
		        cart.setTotal_price(sc.nextInt());
		        session.save(cart);
		        tx.commit();
		       System.out.println("cart details inserted successfully");
			   }
			   catch(Exception e) {
				  if (tx != null) {
				        tx.rollback(); // Rollback on error
				    }
			   }
		        break;
		  case 2:
		        // Update shopping cart details
		        tx = session.beginTransaction();
		        System.out.print("Enter shopping cart ID to update: ");
		        int cartId = sc.nextInt();
		        sc.nextLine(); // Consume the newline

		        ShoppingCart existingCart = (ShoppingCart) session.get(ShoppingCart.class, cartId);
		        if (existingCart != null) {
		            // Prompt for new values
		            System.out.print("Enter new product name (leave blank to keep current): ");
		            String newName = sc.nextLine();
		            if (!newName.isEmpty()) {
		                existingCart.setP_name(newName);
		            }

		            System.out.print("Enter new billing products (enter -1 to keep current): ");
		            int newBillingProducts = sc.nextInt();
		            if (newBillingProducts != -1) {
		                existingCart.setBilling_products(newBillingProducts);
		            }

		            System.out.print("Enter new total price (enter -1 to keep current): ");
		            int newTotalPrice = sc.nextInt();
		            if (newTotalPrice != -1) {
		                existingCart.setTotal_price(newTotalPrice);
		            }

		            session.update(existingCart);
		            tx.commit();
		            System.out.println("Shopping cart updated successfully.");
		        } else {
		            System.out.println("Shopping cart with the given ID does not exist.");
		            if (tx != null) tx.rollback();
		        }
		        break;

		    case 3:
		        // Delete shopping cart details
		        tx = session.beginTransaction();
		        System.out.print("Enter shopping cart ID to delete: ");
		        int deleteCartId = sc.nextInt();
		        sc.nextLine(); // Consume the newline

		        ShoppingCart cartToDelete = (ShoppingCart) session.get(ShoppingCart.class, deleteCartId);
		        if (cartToDelete != null) {
		            session.delete(cartToDelete);
		            tx.commit();
		            System.out.println("Shopping cart deleted successfully.");
		        } else {
		            System.out.println("Shopping cart with the given ID does not exist.");
		            if (tx != null) tx.rollback();
		        }
		        break;

		    case 4:
		        // Retrieve shopping cart details
		        System.out.print("Enter shopping cart ID to retrieve: ");
		        int retrieveCartId = sc.nextInt();
		        sc.nextLine(); // Consume the newline

		        ShoppingCart cartToRetrieve = (ShoppingCart) session.get(ShoppingCart.class, retrieveCartId);
		        if (cartToRetrieve != null) {
		            System.out.println("Shopping Cart Details:");
		            System.out.println("Product Name: " + cartToRetrieve.getP_name());
		            System.out.println("Billing Products: " + cartToRetrieve.getBilling_products());
		            System.out.println("Total Price: " + cartToRetrieve.getTotal_price());

		        } else {
		            System.out.println("Shopping cart with the given ID does not exist.");
		        }
		        break;

		    case 5:
		        System.out.println("Exiting...");
		        break;

		    default:
		        System.out.println("Invalid choice. Please try again.");
		}

		}     
		//performing CRUD operations on product entity
		   int choice4=0;
		   while(choice4!=5) {
			   System.out.println("online shopping system");
			   System.out.println("1:Insert product details");
			   System.out.println("2:Update product details");
			   System.out.println("3:Delete product details");
			   System.out.println("4:Retrieve product details");
			   System.out.println("5.Exit");
			   System.out.println("enter your choice");
			   choice4=sc.nextInt();
			   sc.nextLine();  // Consume the newline
			   Transaction tx = null;
			   switch(choice4) {
			   case 1 :
				   // Insert product details
		           tx = session.beginTransaction();
		           Products product = new Products();
		           System.out.println("enter product id");
		           product.setP_id(sc.nextInt());
		           sc.nextLine();
		           System.out.print("Enter product name: ");
		           product.setP_name(sc.nextLine());
		           System.out.print("Enter product category: ");
		           product.setP_category(sc.nextLine());

		           System.out.print("Enter product availability: ");
		           product.setP_availability(sc.nextLine());

		           System.out.print("Enter product price: ");
		           product.setP_price(sc.nextInt());
		           sc.nextLine(); // Consume the newline

		           System.out.print("Enter admin ID: ");
		           int adminId = sc.nextInt();
		           sc.nextLine(); // Consume the newline

		           Admin admin = (Admin) session.get(Admin.class, adminId);
		           if (admin != null) {
		               product.setAdmin(admin);
		              
		           } else {
		               System.out.println("Admin with the given ID does not exist.");
		               tx.rollback();
		           }
		           System.out.print("Enter customer ID: ");
		           int c_id = sc.nextInt();
		           sc.nextLine(); // Consume the newline

		           Customer customer = (Customer) session.get(Customer.class, c_id);
		           if (customer != null) {
		               product.setCustomer(customer);
		            
		           } else {
		               System.out.println("Customer with the given ID does not exist.");
		               tx.rollback();
		           }
		           System.out.print("Enter cart ID: ");
		           int cartId = sc.nextInt();
		           sc.nextLine(); // Consume the newline
		           ShoppingCart cart = (ShoppingCart) session.get(ShoppingCart.class, cartId);
		           if (cart != null) {
		        	   product.setShoppingCart(cart);
		            
		           } else {
		               System.out.println("Customer with the given ID does not exist.");
		               tx.rollback();
		           }

		           session.save(product);
		           tx.commit();
		           System.out.println("Product details inserted successfully.");
		           break;

		       case 2:
		           // Update product details
		           tx = session.beginTransaction();

		           System.out.print("Enter product ID to update: ");
		           int updateId = sc.nextInt();
		           sc.nextLine(); // Consume the newline

		           Products updateProduct = (Products) session.get(Products.class, updateId);
		           if (updateProduct != null) {
		               System.out.print("Enter new product name (leave empty to keep current): ");
		               String newName = sc.nextLine();
		               if (!newName.isEmpty()) updateProduct.setP_name(newName);

		               System.out.print("Enter new product category (leave empty to keep current): ");
		               String newCategory = sc.nextLine();
		               if (!newCategory.isEmpty()) updateProduct.setP_category(newCategory);

		               System.out.print("Enter new product availability (leave empty to keep current): ");
		               String newAvailability = sc.nextLine();
		               if (!newAvailability.isEmpty()) updateProduct.setP_availability(newAvailability);

		               System.out.print("Enter new product price (enter 0 to keep current): ");
		               int newPrice = sc.nextInt();
		               sc.nextLine(); // Consume the newline
		               if (newPrice > 0) updateProduct.setP_price(newPrice);

		               System.out.print("Enter new admin ID (leave empty to keep current): ");
		               String newAdminIdStr = sc.nextLine();
		               if (!newAdminIdStr.isEmpty()) {
		                   int newAdminId = Integer.parseInt(newAdminIdStr);
		                   Admin newAdmin = (Admin) session.get(Admin.class, newAdminId);
		                   if (newAdmin != null) {
		                       updateProduct.setAdmin(newAdmin);
		                   } else {
		                       System.out.println("Admin with the given ID does not exist.");
		                   }
		               }

		               session.update(updateProduct);
		               tx.commit();
		               System.out.println("Product details updated successfully.");
		           } else {
		               System.out.println("Product with the given ID does not exist.");
		               tx.rollback();
		           }
		           break;

		       case 3:
		           // Delete product details
		           tx = session.beginTransaction();

		           System.out.print("Enter product ID to delete: ");
		           int deleteId = sc.nextInt();
		           sc.nextLine(); // Consume the newline

		           Products deleteProduct = (Products) session.get(Products.class, deleteId);
		           if (deleteProduct != null) {
		               session.delete(deleteProduct);
		               tx.commit();
		               System.out.println("Product details deleted successfully.");
		           } else {
		               System.out.println("Product with the given ID does not exist.");
		               tx.rollback();
		           }
		           break;

		       case 4:
		           // Retrieve product details
		           System.out.print("Enter product ID to retrieve: ");
		           int retrieveId = sc.nextInt();
		           sc.nextLine(); // Consume the newline

		           Products retrieveProduct = (Products) session.get(Products.class, retrieveId);
		           if (retrieveProduct != null) {
		               System.out.println("Product ID: " + retrieveProduct.getP_id());
		               System.out.println("Product Name: " + retrieveProduct.getP_name());
		               System.out.println("Product Category: " + retrieveProduct.getP_category());
		               System.out.println("Product Availability: " + retrieveProduct.getP_availability());
		               System.out.println("Product Price: " + retrieveProduct.getP_price());
		               System.out.println("Admin ID: " + retrieveProduct.getAdmin().getAdmin_id());
		               System.out.println("customer ID: " + retrieveProduct.getCustomer().getC_id());
		               System.out.println("cart ID: " + retrieveProduct.getShoppingCart().getCartId());
		           } else {
		               System.out.println("Product with the given ID does not exist.");
		           }
		           break;

		       case 5:
		           // Exit
		           System.out.println("Exiting the system...");
		           break;

		       default:
		           System.out.println("Invalid choice. Please try again.");
		           break;
		   }
   }
			//performing CRUD operations on login entity
			int choice5=0;
		    while (choice5 != 5) {
		        System.out.println("Online Shopping System");
		        System.out.println("1: Insert login details");
		        System.out.println("2: Update login details");
		        System.out.println("3: Delete login details");
		        System.out.println("4: Retrieve login details");
		        System.out.println("5: Exit");
		        System.out.println("Enter your choice:");
		        choice5 = sc.nextInt();
		        sc.nextLine();  // Consume the newline
		        Transaction tx = null;
		        switch (choice5) {
		        case 1:
		            // Insert login details
		            tx = session.beginTransaction();
		            Login login = new Login();
		            System.out.println("enter login id");
		            login.setLoginId(sc.nextInt());
		            sc.nextLine();
		            System.out.println("Enter user name : ");
		            login.setUser_name(sc.nextLine());
		            System.out.println("Enter password: ");
		            login.setPassword(sc.nextLine());
		            System.out.println("enter forgot password");
		            login.setForgot_password(sc.nextLine());
		            System.out.println("enter signed up or not");
		            login.setSign_up(sc.nextLine());
		            System.out.println("Enter customer id: ");
		            int c_id = sc.nextInt();
		            Customer customer= (Customer) session.get(Customer.class,c_id);
		            if (customer!= null) {
		                 login.setCustomer(customer);
		                 customer.setC_id(c_id);
		                 customer.setLogin(login);
		                session.save(login);
		                tx.commit();
		                System.out.println("admin details inserted successfully.");
		            } else {
		                System.out.println("admin details not inserted");
		                
		            }
		            break;
		        case 2:
		            // Update login details
		            System.out.print("Enter customer id to update: ");
		            int updateId = sc.nextInt();
		            sc.nextLine();  // Consume the newline
		            Login existingLogin = (Login) session.get(Login.class, updateId);
		            if (existingLogin != null) {
		                tx = session.beginTransaction();
		                System.out.print("Enter new user name (leave blank to keep current): ");
		                String newUserName = sc.nextLine();
		                if (!newUserName.isEmpty()) {
		                    existingLogin.setUser_name(newUserName);
		                }
		                System.out.print("Enter new password (leave blank to keep current): ");
		                String newPassword = sc.nextLine();
		                if (!newPassword.isEmpty()) {
		                    existingLogin.setPassword(newPassword);
		                }
		                // Update other fields as needed
		                session.update(existingLogin);
		                tx.commit();
		                System.out.println("Login details updated successfully.");
		            } else {
		                System.out.println("Login details not found for the given customer id.");
		            }
		            break;

		        case 3:
		            // Delete login details
		            System.out.print("Enter customer id to delete: ");
		            int deleteId = sc.nextInt();
		            sc.nextLine();  // Consume the newline
		            Login loginToDelete = (Login) session.get(Login.class, deleteId);
		            if (loginToDelete != null) {
		                tx = session.beginTransaction();
		                session.delete(loginToDelete);
		                tx.commit();
		                System.out.println("Login details deleted successfully.");
		            } else {
		                System.out.println("Login details not found for the given customer id.");
		            }
		            break;
		        case 4:
		            // Retrieve login details
		            System.out.print("Enter login id to retrieve: ");
		            int retrieveId = sc.nextInt();
		            sc.nextLine();  // Consume the newline
		            Login retrievedLogin = (Login) session.get(Login.class, retrieveId);
		            if (retrievedLogin != null) {
		                System.out.println("User Name: " + retrievedLogin.getUser_name());
		                System.out.println("Password: " + retrievedLogin.getPassword());
		                System.out.println("Forgot Password: " + retrievedLogin.getForgot_password());
		                System.out.println("Signed Up: " + retrievedLogin.getSign_up());
		            } else {
		                System.out.println("Login details not found for the given login id.");
		            }
		            break;

		        case 5:
		            System.out.println("Exiting...");
		            break;

		        default:
		            System.out.println("Invalid choice, please try again.");
		    }
		}
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
    }
}
      
       
    
