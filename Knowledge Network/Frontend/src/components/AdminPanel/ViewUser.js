import React from "react";
import "./ViewUser.css";
import {
  BsPersonCircle,
  BsFillEnvelopeFill,
  BsFillBriefcaseFill,
  BsTagsFill,
} from "react-icons/bs";
import { useParams } from "react-router-dom";
import { getUserById, getLabelById } from "../../APIRequests";
import { useState, useEffect, useCallback } from "react";

function ViewUser() {
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const [labels, setLabels] = useState([]);

  const getUser = useCallback(() => {
    getUserById(id)
      .then((response) => {
        setUser(response.data);
        getLabels(response.data.topology_labels);
      })
      .catch((error) => console.log(`ERROR : ${error}`));
  }, [id]);

  useEffect(() => {
    getUser();
  }, [getUser]);

  const getLabels = (listOfLabelsID) => {
    listOfLabelsID.forEach((label) => {
      getLabelById(label)
        .then((response) => {
          setLabels((oldLabels) => [...oldLabels, response.data]);
        })
        .catch((error) => console.log(`ERROR : ${error}`));
    });
  };

  return (
    <main className="main">
      <div className="card">
        <div>
          <BsPersonCircle height={1000} width={1000} />
          <h1>
            {user?.first_name} <br />
            <span>{user?.role}</span>
          </h1>

          <p>
            {" "}
            <BsFillEnvelopeFill /> {"   "}
            {user?.email}
          </p>
          <br />

          <p>
            <BsFillBriefcaseFill />
            {"   "}
            {user?.job_title}
          </p>
          <br />

          <p>
            <BsTagsFill /> {"   "}Tags:
          </p>
          <ul>
            {labels.map((label, index) => (
              <li key={index}>{label.name}</li>
            ))}
          </ul>
          <a className="btn" href={`/edit/user/${id}`}>
            Edit
          </a>
        </div>
      </div>
    </main>
  );
}
export default ViewUser;
