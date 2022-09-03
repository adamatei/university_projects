import React, { useState } from "react";
import SearchIcon from "@mui/icons-material/Search";
import IconButton from "@mui/material/IconButton";
import TextField from "@mui/material/TextField";

function SearchBar(props) {
  const [searchQuery, setSearchQuery] = useState("");

  const handleType = (e) => {
    setSearchQuery(e.target.value);
  };

  return (
    <div style={{display: 'flex', alignItems: 'center'}}>
      <TextField
        id="search-bar"
        className="text"
        onInput={handleType}
        label="Enter a node name"
        variant="filled"
        placeholder="Search..."
        InputLabelProps={{
          style: { color: "black" },
        }}
      />
      <IconButton
        type="submit"
        aria-label="search"
        onClick={(e) => props.handleSearch(e, searchQuery)}
      >
        <SearchIcon style={{ fill: "black" }} />
      </IconButton>
    </div>
  );
}

export default SearchBar;
