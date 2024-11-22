// Bakery Item Constructor
function BakeryItem(name, price, weight, image) {
  this.name = name;
  this.price = price;
  this.weight = weight;
  this.image = image;
}

// Sample Bakery Items
const items = [
  new BakeryItem("Chocolate Cake", 20, "1 kg", "images/chocolate-cake.jpg"),
  new BakeryItem("Vanilla Pastry", 5, "200 g", "images/vanilla-pastry.jpg"),
  new BakeryItem("Croissant", 3, "100 g", "images/croissant.jpg"),
];

// Cart Array
let cart = [];

// Function to display items
function displayItems() {
  const itemList = document.getElementById("item-list");
  items.forEach(item => {
    const card = document.createElement("div");
    card.className = "col-md-4";
    card.innerHTML = `
            <div class="card">
                <img src="${item.image}" class="card-img-top" alt="${item.name}">
                <div class="card-body">
                    <h5 class="card-title">${item.name}</h5>
                    <p class="card-text">Price: $${item.price} | Weight: ${item.weight}</p>
                    <button class="btn btn-primary" onclick="addToCart('${item.name}')">Add to Cart</button>
                </div>
            </div>
        `;
    itemList.appendChild(card);
  });
}

// Function to add item to cart
function addToCart(itemName) {
  const item = items.find(i => i.name === itemName);
  cart.push(item);
  displayCart();
}

// Function to display cart
function displayCart() {
  const cartDiv = document.getElementById("cart");
  cartDiv.innerHTML = ""; // Clear previous cart content
  if (cart.length === 0) {
    cartDiv.innerHTML = "<p>Your cart is empty.</p>";
    return;
  }
  cart.forEach((item, index) => {
    const cartItem = document.createElement("div");
    cartItem.className = "cart-item";
    cartItem.innerHTML = `
            <p>${item.name} - $${item.price} | Weight: ${item.weight}</p>
            <button class="btn btn-danger btn-sm" onclick="removeFromCart(${index})">Remove</button>
        `;
    cartDiv.appendChild(cartItem);
  });
}

// Function to remove item from cart
function removeFromCart(index) {
  cart.splice(index, 1);
  displayCart();
}

// Initial display of items
displayItems();
