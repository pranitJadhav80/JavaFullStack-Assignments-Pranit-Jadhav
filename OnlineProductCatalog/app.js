const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");

const app = express();
const port = 3000;

// Use body-parser to parse request bodies
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Set the view engine and views directory
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "pug");

// Global array to store products
const products = [
  {
    name: "Product A",
    description: "Description A",
    price: "555",
    availability: true, // Use boolean for availability
  },
];

// Home route to display products
app.get("/", (req, res) => {
  res.render("user", { products });
});

// Admin route to add new product
app.get("/admin", (req, res) => {
  res.render("admin");
});

// Route to handle form submission
app.post("/admin", (req, res) => {
  const { productName, productDesc, price, availability } = req.body;
  console.log(req.body);

  // Push new product object into the products array
  products.push({
    name: productName,
    description: productDesc,
    price,
    availability: availability === "yes", // Convert "yes" to boolean true
  });

  // Redirect to the homepage
  res.redirect("/");
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
