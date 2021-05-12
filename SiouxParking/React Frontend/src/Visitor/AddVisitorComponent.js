import React from "react";
import VisitorService from "../Service/VisitorService";
import "./AddVisitorComponent.css";

class AddVisitorComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visitorId: "",
      firstname: "",
      lastname: "",
      licencePlate: "",
      phone: "",
    };
    this.saveVisitor = this.saveVisitor.bind(this);
    this.changeFirstName = this.changeFirstName.bind(this);
    this.changeLastName = this.changeLastName.bind(this);
    this.changeLicencePlate = this.changeLicencePlate.bind(this);
    this.changePhone = this.changePhone.bind(this);
    this.changeId = this.changeId.bind(this);
  }

  componentDidMount() {
    VisitorService.getVisitor(this.state.visitorId).then((res) => {
      let visitor = res.data;
      this.setState({
        firstname: visitor.firstName,
        lastname: visitor.lastName,
        licencePlate: visitor.licencePlate,
        phone: visitor.phone,
      });
    });
  }

  saveVisitor = (c) => {
    c.preventDefault();
    let visitor = {
      id: this.state.visitorId,
      firstName: this.state.firstname,
      lastName: this.state.lastname,
      licencePlate: this.state.licencePlate,
      phone: this.state.phone,
    };
    console.log("visitor =>" + JSON.stringify(visitor));

    VisitorService.createVisitor(visitor).then((res) => {
      this.props.history.push("/visitors");
    });
    //this.props.history.push("/visitors");
  };

  changeFirstName(event) {
    this.setState({
      firstname: event.target.value,
    });
  }

  changeId(event) {
    this.setState({
      visitorId: event.target.value,
    });
  }

  changeLastName(event) {
    this.setState({
      lastname: event.target.value,
    });
  }

  changeLicencePlate(event) {
    this.setState({
      licencePlate: event.target.value,
    });
  }

  changePhone(event) {
    this.setState({
      phone: event.target.value,
    });
  }

  cancel() {
    this.props.history.push("/visitors");
  }

  render() {
    return (
      <form id="msform">
        <fieldset>
          <h2 class="fs-title">New visitor</h2>
          <h3 class="fs-subtitle">Please enter the required information</h3>
          <h3>Id</h3>
          <input
            type="text"
            name="id"
            placeholder={this.state.visitorId}
            onChange={this.changeId}
          />
          <h3>First Name</h3>
          <input
            type="text"
            name="firstname"
            placeholder={this.state.firstname}
            onChange={this.changeFirstName}
          />
          <h3>Last Name</h3>
          <input
            type="text"
            name="lastName"
            placeholder={this.state.lastname}
            onChange={this.changeLastName}
          />
          <h3>Licence Plate</h3>
          <input
            type="text"
            name="licencePlate"
            placeholder={this.state.licencePlate}
            onChange={this.changeLicencePlate}
          />
          <h3>Phone Number</h3>
          <input
            type="text"
            name="phone"
            placeholder={this.state.phone}
            onChange={this.changePhone}
          />
          <input
            type="button"
            name="edit"
            class="next action-button"
            value="Save"
            onClick={this.saveVisitor}
          />
          <input
            type="button"
            name="cancel"
            className="next action-button cancel"
            value="Cancel"
            onClick={this.cancel.bind(this)}
          />
        </fieldset>
      </form>
    );
  }
}

export default AddVisitorComponent;
