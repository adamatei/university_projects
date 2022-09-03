// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

import "cypress-localstorage-commands";

Cypress.Commands.add("loginAsUser", () => {
  let request = {
    username: "test1",
    password: "password1",
  };
  cy.request(
    "POST",
    "http://localhost:59613/api/Authentication/authenticate",
    request
  ).then(($res) => {
    window.localStorage.setItem("token", $res.body.Authorization);
    window.localStorage.setItem("username", "test1");
  });
});

Cypress.Commands.add("loginAsAdmin", () => {
  let request = {
    username: "test1",
    password: "password1",
  };
  cy.request(
    "POST",
    "http://localhost:59613/api/Authentication/authenticate",
    request
  ).then(($res) => {
    window.localStorage.setItem("token", $res.body.Authorization);
    window.localStorage.setItem("username", "test1");
  });
});
