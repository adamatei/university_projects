import { Button, ClickAwayListener, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import "./DataDashboard.css";

function DetailPanel(props) {
  return (
    <div className="detail-panel-container">
      <ClickAwayListener onClickAway={props.closeDetailPanel}>
        <div className="detail-panel">
          <table>
            <tbody>
              <tr>
                <td>
                  <Typography variant="h6">
                    <b>data :</b>
                  </Typography>
                </td>
                <td>
                  <Typography variant="h6">{props.data.data}</Typography>
                </td>
              </tr>
              <tr>
                <td>
                  <Typography variant="h6">
                    <b>relationships :</b>
                  </Typography>
                </td>
                <td>
                  {props.data.relationships?.map((relationship, index) => (
                    <Typography variant="h6" key={index}>
                      - {relationship.related_data_name}
                    </Typography>
                  ))}
                </td>
              </tr>
            </tbody>
          </table>
          <div className="detail-panel-button">
            <Button size="large" onClick={() => props.approve(props.data)}>
              Approve
            </Button>
          </div>
        </div>
      </ClickAwayListener>
    </div>
  );
}

export default DetailPanel;
