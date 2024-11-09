# Solo Sale (An inventory and sale management application)

'Solo Sale' provides features for managing products, recording purchases and sales. It is built using Spring Boot 3.3.3 for the backend and Angular 16 for the frontend.

## Getting Started

### Step 1: Backend (Spring Boot)

1. **Initialize Database with Sample Data**:
   - Create a database `inv_db_test` from your postgres cli
   - Run the `data.sql` file in the `src/main/resources` directory to populate initial data in your database.

3. **Start the Backend Server**:
   - Navigate to the backend project directory.
   - Run the following command to start the application:

     ```bash
     ./mvnw spring-boot:run
     ```

   - The server will start on `http://localhost:7070` by default.

### Step 2: Frontend (Angular)

1. **Install Dependencies**:
   - Navigate to the frontend project directory.
   - Run the following command to install necessary packages:

     ```bash
     npm install
     ```

2. **Start the Frontend Server**:
   - Run the following command to start the Angular development server:

     ```bash
     ng serve
     ```

   - The frontend server will start on `http://localhost:4200` by default.

3. **Access the Application**:
   - Open a web browser and go to `http://localhost:4200`.

4. **Login**:
		- Username: admin
		- Password: 123456
Alternatively you can create an User from swagger `http://localhost:7070/swagger-ui.html`

## Feature Guide

### Product
- Navigate to the **Product** menu. You should see a list of existing products. Also you can add or update products from this UI. Each product store information of its Brand, Category, MRP, Selling price. Selling price is the price which will be billed to the customer.
- You can search/filter the product by product name, brand, category and stock (less or equal).

### Purchase
- Navigate to the **Purchase** menu where you will find all previous purchase records in a pagination list with search/filter feature to find precise result. Select **New Purchase** to record a new purchase. Enter details such as vendor, purchase date, products and their quantity, price.
- Each new purchase of a product will increase the product stock. You can see the product stock in the product list.
- You can search/filter the purchase record by Vendor and date range.

### Sale
- Navigate to the **Sales Management** section to see all sales history. To add a new sales information, select **New Sale**. Enter customer details, details of sold products like quantities, discount given to the customer, and delivery charge. Bill amount will be calculated based upon product selling price, discount and delivery charge. Once saved you will see the new sale record in the list.
- Each new sald of a product will adjust the product stock according to sale quantity. You can see the product stock in the product list.
- You can search/filter the sale record by customer name, phone and date range.

## Technologies Used
- **Backend**: Spring Boot, Spring Secuirity, Hibernate, PostgreSQL
- **Frontend**: Angular, Angular Material, Tailwind CSS
