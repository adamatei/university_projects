import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import LoginForm from "./LoginForm";
import { MemoryRouter } from "react-router-dom";

test("renders the login form", async () => {
  const { getByTestId } = render(<LoginForm />, {
    wrapper: MemoryRouter,
  });
  await waitFor(() => getByTestId("login-form"));
});
