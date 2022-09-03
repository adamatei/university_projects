import * as React from "react";
import Alert from "@mui/material/Alert";
import Collapse from "@mui/material/Collapse";

export default function CustomAlert(props) {
  return (
    <Collapse in={props.open}>
      <Alert color={props.color}>{props.text}</Alert>
    </Collapse>
  );
}
