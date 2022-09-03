import React from "react";
import "./AdminPanel.css";
import Card from "./Card";
import UserRow from "./UserRow";
import { BsThreeDots, BsArrowRight } from "react-icons/bs";
import { PieChart } from "react-minimal-pie-chart";
import { useState, useEffect } from "react";
import { getAllUsers } from "../../APIRequests";
import { countUsers, getPercentage } from "./FilterUsers";
import { Link } from "react-router-dom";

function AdminPanel() {
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

  let numberOfCurators = countUsers(users, "curator"),
    numberOfAdmins = countUsers(users, "admin"),
    numberOfUsers = countUsers(users, "user");
  return (
    <div className="main-content">
      <main>
        <div className="page-header">
          <div>
            <h1>Admin Dashboard</h1>
            <small>
              Monitor key metrics. Check reporting and review insights
            </small>
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
        <div className="cards">
          <Card
            title="Users"
            title_description="Number of users"
            number={numberOfUsers}
            number_description={`${getPercentage(
              numberOfUsers,
              users.length
            )}% of accounts`}
          />
          <Card
            title="Curators"
            title_description="Number of curators"
            number={numberOfCurators}
            number_description={`${getPercentage(
              numberOfCurators,
              users.length
            )}% of accounts`}
          />
          <Card
            title="Admins"
            title_description="Number of admins"
            number={numberOfAdmins}
            number_description={`${getPercentage(
              numberOfAdmins,
              users.length
            )}% of accounts`}
          />
        </div>
        <div className="jobs-grid">
          <div className="analytics-card">
            <div className="analytics-head">
              <h3>High Level Overview</h3>
              <span>
                <BsThreeDots />
              </span>
            </div>
            <div className="analytics-chart">
              <div className="chart-container">
                <PieChart
                  animation
                  animationDuration={500}
                  animationEasing="ease-out"
                  center={[50, 50]}
                  data={[
                    { title: "Users", value: numberOfUsers, color: "#5850ec" },
                    {
                      title: "Curators",
                      value: numberOfCurators,
                      color: "#8da2fb",
                    },
                    { title: "Admins", value: numberOfAdmins, color: "#555" },
                  ]}
                  labelPosition={50}
                  lengthAngle={360}
                  lineWidth={30}
                  paddingAngle={0}
                  radius={50}
                  rounded
                  startAngle={0}
                  viewBoxSize={[100, 100]}
                />
              </div>
              <br />
              <div className="analytics-node">
                <small>Note: Hover over the chart for more details</small>
              </div>
            </div>
            <div className="analytics-btn">
              <button>{users.length} current accounts</button>
            </div>
          </div>
          <div className="jobs">
            <Link to={`/usersoverview`}>
              <h2>
                Accounts{" "}
                <small>
                  See all <span></span>
                  <BsArrowRight />
                </small>
              </h2>
            </Link>
            <div className="table-responsive">
              <table>
                <tbody>
                  {users.length > 0 ? <UserRow user={users[0]} /> : <tr></tr>}
                  {users.length > 1 ? <UserRow user={users[1]} /> : <tr></tr>}
                  {users.length > 2 ? <UserRow user={users[2]} /> : <tr></tr>}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}
export default AdminPanel;
