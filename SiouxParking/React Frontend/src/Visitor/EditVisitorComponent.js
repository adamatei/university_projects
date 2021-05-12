import React from "react";
import VisitorService from "../Service/VisitorService";
import "./AddVisitorComponent.css";

class EditVisitorComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visitorId: this.props.match.params.id,
      firstname: "",
      lastname: "",
      licencePlate: "",
      phone: "",
    };
    this.editVisitor = this.editVisitor.bind(this);
    this.changeFirstName = this.changeFirstName.bind(this);
    this.changeLastName = this.changeLastName.bind(this);
    this.changeLicencePlate = this.changeLicencePlate.bind(this);
    this.changePhone = this.changePhone.bind(this);
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

  editVisitor(v) {
    v.preventDefault();
    let visitor = {
      id: this.state.visitorId,
      firstName: this.state.firstname,
      lastName: this.state.lastname,
      licencePlate: this.state.licencePlate,
      phone: this.state.phone,
    };
    console.log("visitor =>" + JSON.stringify(visitor));
    VisitorService.updateVisitor(this.state.visitorId, visitor).then((res) => {
      this.props.history.push("/visitors");
    });
  }

  changeFirstName(event) {
    this.setState({
      firstname: event.target.value,
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
          <h2 class="fs-title">Edit visitor</h2>
          <h3 class="fs-subtitle">Personal information:</h3>
          <h3>First Name</h3>
          <input
            type="text"
            name="firstname"
            placeholder={this.state.firstname}
            onChange={this.changeFirstName}
            value={this.state.firstname}
          />
          <h3>Last Name</h3>
          <input
            type="text"
            name="lastName"
            placeholder={this.state.lastname}
            onChange={this.changeLastName}
            value={this.state.lastname}
          />
          <h3>Licence Plate</h3>
          <input
            type="text"
            name="licencePlate"
            placeholder={this.state.licencePlate}
            onChange={this.changeLicencePlate}
            value={this.state.licencePlate}
          />
          <h3>Phone Number</h3>
          <input
            type="text"
            name="phone"
            placeholder={this.state.phone}
            onChange={this.changePhone}
            value={this.state.phone}
          />
          <input
            type="button"
            name="edit"
            class="next action-button"
            value="Edit"
            onClick={this.editVisitor}
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

export default EditVisitorComponent;
