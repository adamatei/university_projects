import React from "react";
import "./AdminPanel.css";
import {BsPeople } from "react-icons/bs";

function Card(props){
    return (
      <div className="card-single">
        <div className="card-flex">
          <div className="card-info">
            <div className="card-head">
              <span>{props.title}</span>
              <small>{props.title_description}</small>
            </div>
            <h2>{props.number}</h2>
            <small>{props.number_description}</small>
          </div>
          <div className="card-chart">
            <span className="las la-chart-line">
              <BsPeople color="blue" />
            </span>
          </div>
        </div>
      </div>
    );
}
export default Card;
