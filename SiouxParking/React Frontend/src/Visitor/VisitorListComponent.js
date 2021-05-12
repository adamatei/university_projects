import React from "react";
import VisitorService from "../Service/VisitorService";
import "./VisitorListComponent.css";
import back from "../correctBack.png";
import "../App.css";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";

class VisitorListComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visitors: [],
      searchTerm: "",
    };
    this.deleteVisitor = this.deleteVisitor.bind(this);
    this.editVisitor = this.editVisitor.bind(this);
    this.add = this.add.bind(this);
    this.setSearchTerm = this.setSearchTerm.bind(this);
  }

  editVisitor(id) {
    this.props.history.push(`/editVisitor/${id}`);
  }

  deleteVisitor(id) {
    confirmAlert({
      customUI: ({ onClose }) => {
        return (
          <div className="custom-ui">
            <h1>Are you sure?</h1>
            <p>You want to delete this visitor?</p>
            <button
              onClick={onClose}
              style={({ padding: "7px 12px" }, { margin: "1px 0.2px" })}
            >
              No
            </button>
            <button
              style={({ padding: "7px 12px" }, { marginLeft: "10px" })}
              onClick={() => {
                VisitorService.deleteVisitor(id).then((res) => {
                  this.setState({
                    visitors: this.state.visitors.filter(
                      (visitor) => visitor.id != id
                    ),
                  });
                });
                onClose();
              }}
            >
              Yes, Delete it!
            </button>
          </div>
        );
      },
    });
  }

  setSearchTerm(event) {
    this.setState({
      searchTerm: event.target.value,
    });
  }

  add() {
    this.props.history.push("/addVisitor");
  }

  componentDidMount() {
    VisitorService.getVisitors().then((res) => {
      this.setState({ visitors: res.data });
    });
  }

  render() {
    return (
      <div>
        <div class="table-responsive">
          <table class="table table-fixed">
            <thead>
              <tr>
                <th scope="col" class="col-3 thColor">
                  ID
                </th>
                <th scope="col" class="col-3 thColor">
                  FIRST NAME
                </th>
                <th scope="col" class="col-3 thColor">
                  LAST NAME
                </th>
                <th scope="col" class="col-3 thColor">
                  LICENCE PLATE
                </th>
                <th scope="col" class="col-3 thColor">
                  PHONE NUMBER
                </th>
                <th scope="col" class="col-3 thColor">
                  ACTIONS
                </th>
              </tr>
            </thead>
            <tbody>
              {this.state.visitors
                .filter(
                  (unfilteredVisitor) =>
                    unfilteredVisitor.firstName
                      .toLowerCase()
                      .includes(this.state.searchTerm.toLowerCase()) |
                    unfilteredVisitor.licencePlate
                      .toLowerCase()
                      .includes(this.state.searchTerm.toLowerCase()) |
                    unfilteredVisitor.lastName
                      .toLowerCase()
                      .includes(this.state.searchTerm.toLowerCase()) |
                    unfilteredVisitor.phone
                      .toLowerCase()
                      .includes(this.state.searchTerm.toLowerCase())
                )
                .map((visitor) => (
                  //this.state.visitors.map((visitor) => (
                  <tr key={visitor.id}>
                    <td class="col-3">{visitor.id}</td>
                    <td class="col-3">{visitor.firstName}</td>
                    <td class="col-3">{visitor.lastName}</td>
                    <td class="col-3">{visitor.licencePlate}</td>
                    <td class="col-3">{visitor.phone}</td>
                    <td>
                      <button
                        className="button  delete"
                        onClick={() => this.deleteVisitor(visitor.id)}
                      >
                        {" "}
                        Delete
                      </button>

                      <button
                        className="button edit"
                        style={{ marginLeft: "10px" }}
                        onClick={() => this.editVisitor(visitor.id)}
                      >
                        {" "}
                        Edit
                      </button>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>{" "}
      </div>
    );
  }
}

export default VisitorListComponent;
