const express = require("express");
const bodyParser = require("body-parser");
const app = express();
const port = process.env.PORT || 3000;

// Middleware
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));
app.set("view engine", "ejs");

// Predefined interest rates for different loan types
const interestRates = {
  "Home Loan": 0.08,
  "Car Loan": 0.07,
  "Education Loan": 0.06,
};

// Home route
app.get("/", (req, res) => {
  res.render("index");
});

// Result route to process form data
app.post("/calculate", (req, res) => {
  const { salary, loanAmount, loanType } = req.body;

  const monthlySalary = parseFloat(salary);
  const amount = parseFloat(loanAmount);
  const interestRate = interestRates[loanType];

  // Calculate EMI
  const duration = Math.min(Math.floor(monthlySalary / 1000) * 12, 30 * 12); // Max duration based on salary
  const monthlyInterest = interestRate / 12;
  const emi =
    (amount * monthlyInterest * Math.pow(1 + monthlyInterest, duration)) /
    (Math.pow(1 + monthlyInterest, duration) - 1);

  res.render("result", { emi: emi.toFixed(2), duration: duration });
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
