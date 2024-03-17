import "@testing-library/cypress/add-commands";

describe("Integration Testing", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173");
  });

  it("Add item to list Test", () => {
    for (let i = 1; i <= 5; i++) {
      const itemName = `Test Item ${i}`;

      cy.findByTestId("todo-box")
        .should("exist")
        .type(itemName)
        .should("have.value", itemName);

      cy.findByTestId("todo-submit-button").should("exist").click();
    }
    cy.findByTestId("items-list").children().should("have.length", 5);
  });

  it("Delete item from list Test", () => {
    for (let i = 1; i <= 5; i++) {
      const itemName = `Test Item ${i}`;

      cy.findByTestId("todo-box")
        .should("exist")
        .type(itemName)
        .should("have.value", itemName);

      cy.findByTestId("todo-submit-button").should("exist").click();
    }
    cy.findAllByTestId("list-delete-button").first().should("exist").click();
    cy.findByTestId("items-list").children().should("have.length", 4);
  });
  it("Clear items from list Test", () => {
    for (let i = 1; i <= 5; i++) {
      const itemName = `Test Item ${i}`;

      cy.findByTestId("todo-box")
        .should("exist")
        .type(itemName)
        .should("have.value", itemName);

      cy.findByTestId("todo-submit-button").should("exist").click();
    }

    cy.findAllByTestId("list-element-checkbox").eq(0).should("exist").click();
    cy.findAllByTestId("list-element-checkbox").eq(2).should("exist").click();

    cy.findByTestId("todo-clear-button").should("exist").click();
    cy.findByTestId("items-list").children().should("have.length", 3);
  });

  it("Edit item in the list Test", () => {
    for (let i = 1; i <= 5; i++) {
      const itemName = `Test Item ${i}`;

      cy.findByTestId("todo-box")
        .should("exist")
        .type(itemName)
        .should("have.value", itemName);

      cy.findByTestId("todo-submit-button").should("exist").click();
    }

    cy.findAllByTestId("list-edit-button").eq(1).should("exist").click();

    cy.findByTestId("edit-box")
      .should("exist")
      .type(" Edited")
      .should("have.value", "Test Item 2 Edited");

    cy.findByTestId("edit-submit-button").should("exist").click();

    cy.findAllByTestId("list-element-checkbox")
      .eq(1)
      .parent()
      .should("contain.text", "Test Item 2 Edited");
  });

  it("Search items in the list Test", () => {
    for (let i = 1; i <= 5; i++) {
      const itemName = `Test Item ${i}`;

      cy.findByTestId("todo-box")
        .should("exist")
        .type(itemName)
        .should("have.value", itemName);

      cy.findByTestId("todo-submit-button").should("exist").click();
    }
    cy.findByTestId("search-box").should("exist").type("Item 4 ");

    cy.findByTestId("items-list").children().should("have.length", 1);

    cy.findByTestId("search-box").should("exist").type("Item 10");

    cy.findByTestId("items-list")
      .children()
      .should("have.text", "No Match found");
  });
});
