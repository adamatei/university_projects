import React from "react";
import "./AdminPanel.css";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";
import { Link } from "react-router-dom";
import { deleteUser } from "../../APIRequests";

function UserRow(props) {
  const removeUser = (id) => {
    deleteUser(id)
      .then((response) => {})
      .catch((error) => console.log(error));
  };

  const removeData = (id) => {
    confirmAlert({
      customUI: ({ onClose }) => {
        return (
          <div className="custom-ui">
            <br />
            <h1>Are you sure?</h1>
            <br />
            <p>Do you want to delete this account?</p>
            <br />
            <button
              style={({ padding: "7px 12px" }, { margin: "1px 0.2px" })}
              onClick={onClose}
            >
              No
            </button>
            {"    "}
            <button
              style={({ padding: "7px 12px" }, { margin: "1px 0.2px" })}
              onClick={() => {
                removeUser(id);
                onClose();
                window.location.reload(false);
              }}
            >
              Yes, Delete it!
            </button>
          </div>
        );
      },
    });
  };

  return (
    <tr>
      <td>
        <div>
          <span className="indicator"></span>
        </div>
      </td>
      <td>
        <div>{`${props.user.first_name} ${props.user.last_name}`}</div>
      </td>
      <td>
        <div>{props.user.role}</div>
      </td>
      <td>
        <div>{props.user.job_title}</div>
      </td>
      <td>
        <div>{props.user.email}</div>
      </td>
      <td>
        <div>
          {" "}
          <Link to={`/user/${props.user.id}`}>
            <button className="view-btn">View</button>
          </Link>
        </div>
      </td>
      <td>
        <div>
          {" "}
          <Link to={`edit/user/${props.user.id}`}>
            <button className="edit-btn">Edit</button>
          </Link>
        </div>
      </td>
      <td>
        <div>
          {" "}
          <button
            onClick={() => removeData(props.user.id)}
            className="delete-btn"
          >
            Delete
          </button>
        </div>
      </td>
    </tr>
  );
}
export default UserRow;
