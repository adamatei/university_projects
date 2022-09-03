import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import RegistrationForm from "./RegistrationForm";
import { MemoryRouter } from "react-router-dom";

test("renders the register form", async () => {
  const { getByTestId } = render(<RegistrationForm />, {
    wrapper: MemoryRouter,
  });
  await waitFor(() => getByTestId("register-form"));
});

test("shows error message when passwords do not match", async () => {
  const { getByLabelText, getByTestId } = render(<RegistrationForm />, {
    wrapper: MemoryRouter,
  });
  const passwordField = getByLabelText("Password");
  const repeatField = getByLabelText("Repeat Password");
  userEvent.type(passwordField, "a");
  userEvent.type(repeatField, "b");
  await waitFor(() => {
    const warningText = screen.getByTestId("warning-text-register");
    expect(warningText).toBeInTheDocument();
  });
});
