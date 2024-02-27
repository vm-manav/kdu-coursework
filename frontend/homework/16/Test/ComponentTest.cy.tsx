import "@testing-library/cypress/add-commands";
import { Provider } from "react-redux";
import { store } from "../src/redux/Store";
import React from "react";
import { TodoProvider } from "../src/Tsx-files/TodoContext";
import App from "../src/App";

describe("Header Component Testing", () => {
  it("Header Component Testing", () => {
    cy.viewport(1280, 720);

    cy.mount(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    cy.findByTestId("search-heading")
      .should("exist")
      .and("have.text", "Item Lister");

    cy.findByTestId("search-box").should("exist");
  });

  it("Main Component Testing", () => {
    cy.viewport(1280, 720);
    cy.mount(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    cy.findByTestId("todo-heading")
      .should("exist")
      .and("have.text", "Add Items");

    cy.findByTestId("todo-box")
      .should("exist")
      .type("Test Item")
      .should("have.value", "Test Item");

    cy.findByTestId("todo-submit-button").should("exist").click();

    cy.findByTestId("todo-clear-button").should("exist").click();

    cy.findByTestId("todo-items-heading")
      .should("exist")
      .and("have.text", "Items");
  });

  it("ListItem Component Testing", () => {
    cy.viewport(1280, 720);
    cy.mount(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    cy.findByTestId("list-element-checkbox").should("exist").click();

    cy.findByTestId("list-edit-button").should("exist").click();

    cy.findByTestId("edit-box")
      .should("exist")
      .type(" 2")
      .should("have.value", "Test Item 2");

    cy.findByTestId("edit-submit-button").should("exist").click();

    cy.findByTestId("list-delete-button").should("exist").click();
  });
});
