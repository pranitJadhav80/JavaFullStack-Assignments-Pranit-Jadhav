const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");

const app = express();
const port = 3000;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Set the view engine and directory
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");

// In-memory data for users
const users = [
  { username: "admin", password: "admin123", role: "admin" },
  { username: "user", password: "user123", role: "user" },
  { username: "pranit", password: "pranit123", role: "user" },
];

const products = [];

app.get("/", (req, res) => {
  res.render("home");
});

// login page for admin
app.get("/login/admin", (req, res) => {
  res.render("login", { role: "admin", action: "/login/admin" });
});

// User login page
app.get("/login/user", (req, res) => {
  res.render("login", { role: "user", action: "/login/user" });
});

// admin login handle
app.post("/login/admin", (req, res) => {
  const { username, password } = req.body;

  const admin = users.find(
    user =>
      user.username === username &&
      user.password === password &&
      user.role === "admin"
  );

  if (admin) {
    res.redirect("/admin");
  } else {
    res.send("<h1>Invalid Admin Credentials</h1><a href='/'>Go Back</a>");
  }
});

// user login handle
app.post("/login/user", (req, res) => {
  const { username, password } = req.body;

  const user = users.find(
    u => u.username === username && u.password === password && u.role === "user"
  );

  if (user) {
    res.redirect("/user");
  } else {
    res.send("<h1>Invalid User Credentials</h1><a href='/'>Go Back</a>");
  }
});

// admin page (to upload products details)
app.get("/admin", (req, res) => {
  res.render("admin", { products });
});

// admin form submission for adding products details...
app.post("/admin", (req, res) => {
  const {
    productId,
    productName,
    price,
    category,
    manufacturingDate,
    expirationDate,
  } = req.body;

  products.push({
    id: productId,
    name: productName,
    price,
    category,
    manufacturingDate,
    expirationDate,
  });

  res.render("admin");
});

// admin - view all products (button)
app.get("/admin/products", (req, res) => {
  res.render("product-list", { products });
});

// Admin route to delete a product after clicking view all products...
app.post("/admin/products/delete/:id", (req, res) => {
  const productId = req.params.id;
  const index = products.findIndex(product => product.id === productId);

  if (index !== -1) {
    products.splice(index, 1);
  }

  res.redirect("/admin/products");
});

// User product table display
app.get("/user", (req, res) => {
  const { search } = req.query;
  let filteredProducts = products;

  // If there's a search query, filter the products
  if (search) {
    filteredProducts = products.filter(
      product =>
        product.name.toLowerCase().includes(search.toLowerCase()) ||
        product.category.toLowerCase().includes(search.toLowerCase())
    );
  }

  res.render("user", { products: filteredProducts });
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
