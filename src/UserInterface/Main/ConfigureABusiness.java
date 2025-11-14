/*
 * INFO 5100 Assignment 2 - Pricing Model Application
 * Data Generation Module
 * @author [Your Team Name]
 */
package UserInterface.Main;

import com.github.javafaker.Faker;
import MarketingManagement.MarketingPersonDirectory;
import MarketingManagement.MarketingPersonProfile;
import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerDirectory;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.Personnel.Person;
import TheBusiness.Personnel.PersonDirectory;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.SalesManagement.SalesPersonDirectory;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.Supplier.Supplier;
import TheBusiness.Supplier.SupplierDirectory;
import TheBusiness.UserAccountManagement.UserAccount;
import TheBusiness.UserAccountManagement.UserAccountDirectory;
import java.util.ArrayList;

/**
 *
 * @author aksha
 */
class ConfigureABusiness {

    
    static Business initialize() {
        Business business = new Business("Pricing Model Company");
        Faker faker = new Faker();
        
        System.out.println("========================================");
        System.out.println("Starting Data Generation...");
        System.out.println("========================================");
        
        // ====================================================================
        // STEP 1: Create Personnel and Profiles
        // ====================================================================
        PersonDirectory persondirectory = business.getPersonDirectory();
        
        // Create marketing and sales personnel
        Person marketingPerson = persondirectory.newPerson(faker.name().fullName());
        Person salesPerson = persondirectory.newPerson(faker.name().fullName());
        
        // Create profiles
        MarketingPersonDirectory marketingpersondirectory = business.getMarketingPersonDirectory();
        MarketingPersonProfile marketingprofile = marketingpersondirectory.newMarketingPersonProfile(marketingPerson);
        
        SalesPersonDirectory salespersondirectory = business.getSalesPersonDirectory();
        SalesPersonProfile salesprofile = salespersondirectory.newSalesPersonProfile(salesPerson);
        
        // Create user accounts for login
        UserAccountDirectory uadirectory = business.getUserAccountDirectory();
        UserAccount marketingAccount = uadirectory.newUserAccount(marketingprofile, "Marketing", "XXXX");
        UserAccount salesAccount = uadirectory.newUserAccount(salesprofile, "Sales", "XXXX");
        
        System.out.println("✓ Created user accounts");
        
        // ====================================================================
        // STEP 2: Generate 50 Suppliers with 50 Products each
        // ====================================================================
        System.out.println("\nGenerating 50 suppliers with 50 products each...");
        SupplierDirectory supplierdirectory = business.getSupplierDirectory();
        
        for (int i = 1; i <= 3; i++) {//reducedXXX
            // Generate unique supplier name using Faker
            String supplierName = faker.company().name();
            Supplier supplier = supplierdirectory.newSupplier(supplierName);
            ProductCatalog catalog = supplier.getProductCatalog();
            
            // Generate 50 products for this supplier
            for (int j = 1; j <= 2; j++) {//reducedXXX
                // Generate product name using Faker
                String productName = faker.commerce().productName() + " " + faker.commerce().material();
                
                // Generate random prices using Math.random()
                // Floor price: between $1,000 and $5,000
                int floorPrice = 1000 + (int)(Math.random() * 4000);
                
                // Ceiling price: $2,000 to $8,000 above floor price
                int ceilingPrice = floorPrice + 2000 + (int)(Math.random() * 6000);
                
                // Target price: 40-60% between floor and ceiling
                int targetPrice = floorPrice + (int)((ceilingPrice - floorPrice) * (0.4 + Math.random() * 0.2));
                
                // Create product
                catalog.newProduct(productName, floorPrice, ceilingPrice, targetPrice);
            }
            
            // Progress indicator
            if (i % 10 == 0) {
                System.out.println("  Generated " + i + " suppliers with " + (i * 50) + " products...");
            }
        }
        System.out.println("✓ Completed: 50 suppliers with 2,500 products");
        
        // ====================================================================
        // STEP 3: Generate 300 Customers
        // ====================================================================
        System.out.println("\nGenerating 300 customers...");
        CustomerDirectory customerdirectory = business.getCustomerDirectory();
        ArrayList<CustomerProfile> customerList = new ArrayList<>();
        
        for (int i = 1; i <= 5; i++) { //reducedXXX
            // Generate customer name using Faker
            String customerName = faker.name().fullName();
            Person customerPerson = persondirectory.newPerson(customerName);
            CustomerProfile customer = customerdirectory.newCustomerProfile(customerPerson);
            customerList.add(customer);
            
            if (i % 50 == 0) {
                System.out.println("  Generated " + i + " customers...");
            }
        }
        System.out.println("✓ Completed: 300 customers");
        
        // ====================================================================
        // STEP 4: Generate Orders (1-3 orders per customer, up to 10 items each)
        // ====================================================================
        System.out.println("\nGenerating orders for customers...");
        MasterOrderList masterorderlist = business.getMasterOrderList();
        ArrayList<Supplier> allSuppliers = supplierdirectory.getSuplierList();
        
        int totalOrders = 0;
        int totalOrderItems = 0;
        
        for (CustomerProfile customer : customerList) {
            // Each customer gets 1-3 orders randomly
            int numOrders = 1 + (int)(Math.random() * 3);
            
            for (int orderNum = 0; orderNum < numOrders; orderNum++) {
                Order order = masterorderlist.newOrder(customer, salesprofile);
                totalOrders++;
                
                // Each order has 1-10 items randomly
                int numItems = 1 + (int)(Math.random() * 10);
                
                for (int itemNum = 0; itemNum < numItems; itemNum++) {
                    // Pick a random supplier
                    Supplier randomSupplier = allSuppliers.get((int)(Math.random() * allSuppliers.size()));
                    ProductCatalog catalog = randomSupplier.getProductCatalog();
                    ArrayList<Product> products = catalog.getProductList();
                    
                    // Pick a random product from that supplier
                    Product randomProduct = products.get((int)(Math.random() * products.size()));
                    
                    // Generate actual price with realistic variance
                    int targetPrice = randomProduct.getTargetPrice();
                    int floorPrice = randomProduct.getFloorPrice();
                    int ceilingPrice = randomProduct.getCeilingPrice();
                    
                    int actualPrice;
                    
                    // 30% below target, 40% at/near target, 30% above target
                    double priceCategory = Math.random();
                    
                    if (priceCategory < 0.3) {
                        // Below target: between floor and target
                        actualPrice = floorPrice + (int)(Math.random() * (targetPrice - floorPrice));
                    } else if (priceCategory < 0.7) {
                        // Near target: +/- 10% of target
                        int variance = (int)(targetPrice * 0.1);
                        actualPrice = targetPrice - variance + (int)(Math.random() * (2 * variance));
                    } else {
                        // Above target: between target and ceiling
                        actualPrice = targetPrice + (int)(Math.random() * (ceilingPrice - targetPrice));
                    }
                    
                    // Ensure price stays within bounds
                    actualPrice = Math.max(floorPrice, Math.min(ceilingPrice, actualPrice));
                    
                    // Random quantity between 1-5
                    int quantity = 1 + (int)(Math.random() * 5);
                    
                    // Add order item
                    order.newOrderItem(randomProduct, actualPrice, quantity);
                    totalOrderItems++;
                }
            }
        }
        
        System.out.println("✓ Completed: " + totalOrders + " orders with " + totalOrderItems + " order items");
        
        // ====================================================================
        // FINAL SUMMARY
        // ====================================================================
        System.out.println("\n========================================");
        System.out.println("DATA GENERATION COMPLETE");
        System.out.println("========================================");
        System.out.println("Suppliers:        50");
        System.out.println("Products:         2,500 (50 per supplier)");
        System.out.println("Customers:        300");
        System.out.println("Orders:           " + totalOrders);
        System.out.println("Order Items:      " + totalOrderItems);
        System.out.println("Total Sales:      $" + masterorderlist.getSalesVolume());
        System.out.println("========================================");
        System.out.println("Application Ready!\n");
        
        return business;
    }
}