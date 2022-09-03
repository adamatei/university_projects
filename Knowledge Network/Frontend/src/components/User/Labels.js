import React from "react";
import "./Labels.css";
import "react-confirm-alert/src/react-confirm-alert.css";
import Card from "@mui/material/Card";
import Typography from "@mui/material/Typography";
import { useState, useEffect } from "react";
import { getLabelSearch, updateUser } from "../../APIRequests";
import Alert from "@mui/material/Alert";
import Collapse from "@mui/material/Collapse";

function Labels(props) {
  const [lookedUpLabel, setLookedUpLabel] = useState("");
  const [searchedLabels, setSearchedLabels] = useState([]);
  const [showLookedUpLabels, setShowLookedUpLabels] = useState(false);
  const [userLabels, setUserLabels] = useState([]);
  const [open, setOpen] = useState(false);
  const [updateText, setUpdateText] = useState("");

  const handleLookedUpLabel = (event) => {
    setLookedUpLabel(event.target.value);
  };

  async function addLabelToUser(label) {
    let tl = props.user.topology_labels;
    if (!tl.includes(label.id)) {
      tl.push(label.id);
      let newUser = props.user;
      newUser.topology_labels = tl;

      console.log(tl);

      let response = await updateUser(props.user.id, newUser);
      let status = response.status;
      if (status === 200) {
        setUpdateText("Label successfully added");
        setOpen(true);
        setUserLabels((oldLabels) => [...oldLabels, label]);
      } else {
        setUpdateText("Error while adding label");
        setOpen(true);
      }
    }
  }

  async function displayLookedUpLabels() {
    let response = await getLabelSearch(lookedUpLabel);
    setShowLookedUpLabels(true);
    setSearchedLabels(response.data);
  }

  function GetCards(labels, pressable) {
    let rows = [];
    let maximumCardIndexPerRow = 3,
      maximumCardPerRow = maximumCardIndexPerRow + 1;
    for (let i = 0; i < labels.length / maximumCardPerRow; i++) {
      let finalIndex = Math.min(
        maximumCardIndexPerRow,
        labels.length - i * maximumCardPerRow - 1
      );
      let newLabels = [];
      for (let j = 0; j <= finalIndex; j++) {
        let finalSlash = "";
        if (j < finalIndex) {
          finalSlash = "/";
        }
        newLabels.push(
          <div className="oneLabelDiv" key={i*4+j}> 
            {!pressable ? (
              <Card className="labelCard">
                <Typography className="labelText">
                  {labels[j + i * maximumCardPerRow].name}
                </Typography>
              </Card>
            ) : (
              <Card className="pressableLabelCard">
                <Typography
                  className="labelText"
                  onClick={() =>
                    addLabelToUser(labels[j + i * maximumCardPerRow])
                  }
                >
                  {labels[j + i * maximumCardPerRow].name}
                </Typography>
              </Card>
            )}

            <Typography>{finalSlash}</Typography>
          </div>
        );
      }
      rows.push(
        <div className="labelsWrapper" key={i}>
          <div className="labelsDiv">{newLabels}</div>
        </div>
      );
    }
    return rows;
  }

  function GetUserLabelCards(labels) {
    let pressable = false;

    return GetCards(labels, pressable);
  }

  function GetAddableLabelCards(labels) {
    let pressable = true;

    return GetCards(labels, pressable);
  }

  useEffect(() => {
    setUserLabels(props.labels);
  }, [props.labels]);

  return (
    <div className="background">
      <div className="centerTopologyText">
        <h2>{props.user?.first_name}'s labels</h2>
      </div>

      {GetUserLabelCards(userLabels)}

      <div className="labelSearchDiv">
        <h2>Search for new labels</h2>
        <div className="labelInput">
          <input
            type="text"
            className="input"
            value={lookedUpLabel}
            onChange={handleLookedUpLabel}
          />
        </div>
        <button className="srchBttn" onClick={displayLookedUpLabels}>
          Search
        </button>
      </div>
      <Collapse in={open}>
        <Alert>{updateText}</Alert>
      </Collapse>
      {showLookedUpLabels === true ? (
        GetAddableLabelCards(searchedLabels)
      ) : (
        <div> </div>
      )}
    </div>
  );
}
export default Labels;
