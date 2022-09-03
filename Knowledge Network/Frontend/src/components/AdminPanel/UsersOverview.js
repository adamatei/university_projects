import React from "react";
import "./AdminPanel.css";
import UserRow from "./UserRow";
//import { PieChart } from "react-minimal-pie-chart";
import { useState, useEffect } from "react";
import { getAllUsers } from "../../APIRequests";
//import { countUsers, getPercentage } from "./FilterUsers";

function UsersOverview(props) {
  const [users, setUsers] = useState([]);
  const getUsers = () => {
    getAllUsers()
      .then((response) => {
        setUsers(response.data);
      })
      .catch((error) => console.log(`ERROR : ${error}`));
  };

  useEffect(() => {
    getUsers();
  }, []);
  return (
    <div className="main-content">
      <main>
        <div className="page-header">
          <div>
            <h1>Users overview</h1>
            <small>See all users</small>
          </div>
          <div>
            <div className="header-actions">
              <button>
                <span className="las la-file-export"></span>
                Export
              </button>
              <button>
                <span className="las la-tools"></span>
                Settings
              </button>
            </div>
          </div>
        </div>
        <div className="jobs-grid">
          <div className="jobs">
            <div className="table-responsive">
              <table>
                <tbody>
                  {users?.map((user, index) => (
                    <UserRow user = {user}/>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}
export default UsersOverview;
