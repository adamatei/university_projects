import "cypress-localstorage-commands";

describe("Cypress Tests", () => {
  //test login page
  it("Login Page", () => {
    cy.visit("http://localhost:3000/ping.com");
  });
  
  //test register page
  it("Register Page", () => {
    cy.visit("http://localhost:3000/ping/register");
  });

  //test login link
  it("Click login link", () => {
    cy.visit("http://localhost:3000/ping/register");

    cy.get('a[href*="/ping.com"]').click();
    cy.location("pathname").should("eq", "/ping.com");
    cy.go("back");
  });

  //test register link
  it("Click register link", () => {
    cy.visit("http://localhost:3000/ping.com");

    cy.get('a[href*="/ping/register"]').click();
    cy.location("pathname").should("eq", "/ping/register");
    cy.go("back");
  });

  //login functionality
  it("Login functionality", function () {
    cy.visit("http://localhost:3000/ping.com");

    cy.get("input[id=username]").type("test1");

    // {enter} causes the form to submit
    cy.get("input[id=password]").type(`password1`);
    cy.get("button[title=login]").click();
    cy.location("pathname", { timeout: 120000 }).should(
      "include",
      "/ping.com"
    );
    cy.url().should("eq", "http://localhost:3000/ping.com");
  });
});
