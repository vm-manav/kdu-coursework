import { fireEvent, render } from "@testing-library/react";
import { expect, it, describe } from "vitest";
import App from "../src/App";
import React from "react";
import { Provider } from "react-redux";
import { store } from "../src/redux/Store";
import { TodoProvider } from "../src/Tsx-files/TodoContext";

describe("Unit Testing", () => {
  it("Search-box", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const inputElement = getByTestId("search-box") as HTMLInputElement;
    expect(inputElement.disabled).toBe(false);
    expect(inputElement).not.toBeNull();
    fireEvent.change(inputElement, { target: { value: "Test Value" } });
    expect(inputElement.value).toBe("Test Value");
  });

  it("todo-box", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const inputElement = getByTestId("todo-box") as HTMLInputElement;
    expect(inputElement.disabled).toBe(false);
    expect(inputElement).not.toBeNull();
    fireEvent.change(inputElement, { target: { value: "Test Value" } });
    expect(inputElement.value).toBe("Test Value");
  });

  it("todo-submit-button", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const submitButton = getByTestId("todo-submit-button") as HTMLButtonElement;
    expect(submitButton).not.toBeNull();
    expect(submitButton.disabled).toBe(false);
    fireEvent.click(submitButton);
  });
  it("todo-clear-button", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const submitButton = getByTestId("todo-clear-button") as HTMLButtonElement;
    expect(submitButton).not.toBeNull();
    expect(submitButton.disabled).toBe(false);
    fireEvent.click(submitButton);
  });

  it("list-edit-button", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const inputElement = getByTestId("todo-box") as HTMLInputElement;
    fireEvent.change(inputElement, { target: { value: "Test Value" } });

    const submitButton = getByTestId("todo-submit-button") as HTMLButtonElement;
    fireEvent.click(submitButton);

    const editButton = getByTestId("list-edit-button") as HTMLButtonElement;
    expect(editButton).not.toBeNull();
    fireEvent.click(editButton);
  });

  it("list-element-checkbox", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const checkBoxElement = getByTestId(
      "list-element-checkbox"
    ) as HTMLInputElement;
    expect(checkBoxElement.disabled).toBe(false);
    expect(checkBoxElement).not.toBeNull();
  });

  it("list-delete-button", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const deleteButton = getByTestId("list-delete-button") as HTMLButtonElement;
    expect(deleteButton).not.toBeNull();
    expect(deleteButton.disabled).toBe(false);
    fireEvent.click(deleteButton);
  });

  it("edit-box", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const inputElement = getByTestId("todo-box") as HTMLInputElement;
    fireEvent.change(inputElement, { target: { value: "Test Value" } });

    const submitButton = getByTestId("todo-submit-button") as HTMLButtonElement;
    fireEvent.click(submitButton);

    const listEditButton = getByTestId("list-edit-button") as HTMLButtonElement;
    fireEvent.click(listEditButton);

    const editInputElement = getByTestId("edit-box") as HTMLInputElement;
    expect(editInputElement.disabled).toBe(false);
    expect(editInputElement).not.toBeNull();
    fireEvent.change(editInputElement, { target: { value: "Test Value" } });
    expect(editInputElement.value).toBe("Test Value");
  });

  it("edit-submit-button", () => {
    const { getByTestId } = render(
      <React.StrictMode>
        <Provider store={store}>
          <TodoProvider>
            <App />
          </TodoProvider>
        </Provider>
      </React.StrictMode>
    );

    const listEditButton = getByTestId("list-edit-button") as HTMLButtonElement;
    fireEvent.click(listEditButton);

    const editSubmitButton = getByTestId(
      "edit-submit-button"
    ) as HTMLButtonElement;
    expect(editSubmitButton).not.toBeNull();
    expect(editSubmitButton.disabled).toBe(false);
    fireEvent.click(editSubmitButton);
  });
});
