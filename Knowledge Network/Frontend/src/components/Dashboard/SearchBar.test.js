import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import SearchBar from "./SearchBar";
import { MemoryRouter } from "react-router-dom";

test("updates search when data is inputted", async () => {
  const testSearch = jest.fn();
  const { getByLabelText, getByText } = render(
    <SearchBar handleSearch={testSearch} />,
    {
      wrapper: MemoryRouter,
    }
  );
  const searchField = getByLabelText("Enter a node name");
  const searchButton = getByLabelText("search");

  userEvent.type(searchField, "t");
  userEvent.click(searchButton);

  expect(testSearch).toHaveBeenCalled();
});
