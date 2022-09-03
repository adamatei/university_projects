import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useState } from "react";
import AuthService from "../../auth.service";
import { createData, addLink } from "../../APIRequests";
import CustomAlert from "./Alert";
import ReactTooltip from "react-tooltip";
import InfoOutlinedIcon from "@mui/icons-material/InfoOutlined";

const theme = createTheme({
  palette: {
    primary: {
      main: "#111111",
    },
  },
});

export default function SubmitDataForm() {
  const [open, setOpen] = useState(false);
  const [openLink, setOpenLink] = useState(false);
  const [updateText, setUpdateText] = useState("");
  const [linkText, setLinkText] = useState("");
  const [numberOfRelationships, setNumberOfRelationships] = useState(0);

  const handleSubmit = async (event) => {
    event.preventDefault();
    let auth = AuthService.getCurrentUser();
    const formData = new FormData(event.currentTarget);
    let relationships = [];
    for (let i = 0; i <= numberOfRelationships; i++) {
      if (formData.get(`relationship${i + 1}`).length > 0) {
        let obj = { related_data_name: formData.get(`relationship${i + 1}`) };
        relationships.push(obj);
      }
    }
    let data = {
      data: formData.get("data"),
      relationships: relationships,
      user_id: auth.user_id,
    };

    try {
      await createData(data);
      setUpdateText("Data submitted for review");
    } catch {
      console.log("error");
      setUpdateText("Error while submitting data");
    }
    setOpen(true);
  };

  const handleLink = async (event) => {
    event.preventDefault();
    let auth = AuthService.getCurrentUser();
    const formData = new FormData(event.currentTarget);
    const text = formData.get("link");
    let model = {
      user: auth.user_id,
      url: text,
      approved: false,
    };
    try {
      addLink(model)
        .then((response) => {
          console.log("Link " + model.url + " submitted!");
          console.log(response.data);
          setLinkText("Link submitted for review");
        })
        .catch((error) => console.log(error));
    } catch {
      setLinkText("Error while submitting link");
    }
    setOpenLink(true);
  };

  function GetRelationships() {
    let rows = [];
    for (let i = 0; i < numberOfRelationships; i++) {
      rows.push(
        <Grid item xs={12} key={i + 2}>
          <TextField
            required
            key={i + 2}
            fullWidth
            name={`relationship${i + 2}`}
            label={`Relationship ${i + 2}`}
            id={`relationship${i + 2}`}
            autoComplete={`relationship${i + 2}`}
            onKeyDown={(e) => {
              if (e.key === "Tab") {
                if (i === numberOfRelationships - 1) {
                  setNumberOfRelationships(numberOfRelationships + 1);
                }
              }
            }}
          />
        </Grid>
      );
    }
    return rows;
  }
  return (
    <ThemeProvider theme={theme}>
      <Container
        maxWidth="xs"
        style={{
          backgroundColor: "#D6D6D6",
          paddingBottom: "2.5rem",
          borderRadius: "10px",
        }}
      >
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Box
            sx={{
              marginTop: 4,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Typography component="h1" variant="h5">
              Submit Data Form
            </Typography>

            <InfoOutlinedIcon data-tip data-for="registerTip">
              Register
            </InfoOutlinedIcon>
            <ReactTooltip id="registerTip" place="top" effect="solid">
              Press TAB to add new relationships
            </ReactTooltip>
            <CustomAlert open={open} text={updateText} color="primary" />

            <Box
              component="form"
              noValidate
              onSubmit={handleSubmit}
              sx={{ mt: 3 }}
            >
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="data"
                    label="Data Name"
                    name="data"
                    autoComplete="data"
                  />
                </Grid>
                <Grid item xs={12} key={1}>
                  <TextField
                    required
                    fullWidth
                    key="1"
                    name="relationship1"
                    label="Relationship 1"
                    id="relationship1"
                    autoComplete="relationship1"
                    onKeyDown={(e) => {
                      if (e.key === "Tab") {
                        if (numberOfRelationships === 0) {
                          setNumberOfRelationships(numberOfRelationships + 1);
                        }
                      }
                    }}
                  />
                </Grid>
                {GetRelationships()}
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Submit Data
              </Button>
            </Box>
          </Box>
        </Box>
      </Container>
      <Container
        maxWidth="xs"
        style={{
          backgroundColor: "#D6D6D6",
          paddingBottom: "2.5rem",
          borderRadius: "10px",
        }}
      >
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Box
            sx={{
              marginTop: 4,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Typography component="h1" variant="h5">
              Submit Link Form
            </Typography>
            <CustomAlert open={openLink} text={linkText} color="primary" />
            <Box
              component="form"
              noValidate
              onSubmit={handleLink}
              sx={{ mt: 3 }}
            >
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="link"
                    label="Link URL"
                    name="link"
                    autoComplete="link"
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Submit Link URL
              </Button>
            </Box>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
