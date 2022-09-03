import 'chart.js/auto';
import './GraphEdit.css'
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';
import React, { useState } from "react";
import { setNode, deleteNode } from "../../APIRequests";

function NodeEntry(props) {
    const [open, setOpen] = useState(false);
    const [labelFields, setLabels] = useState(props.node.labels.map((el) => { return {label: el}}));
    const [name, setName] = useState(props.node.name);
    const [deleted, setDeleted] = useState(false);

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const saveChanges = () => {
        let new_node = {id: props.node.node_id, name: name, labels: ', n'};
        labelFields.map((el) => new_node.labels += ':' + el.label);
        if (new_node.labels === ', n') {
            new_node.labels = ''
        }
        setNode(new_node);
        setOpen(false);
    }

    const dltNode = () => {
        deleteNode(props.node.node_id);
        setDeleted(true);
    }

    const handleInputChange= (index, event) => {
        let data = [...labelFields];
        console.log(event.target)
        data[index][event.target.name] = event.target.value;
        setLabels(data);
    }

    const handleNameChange = (e) => {
        setName(e.target.value);
    }

    function nodeLabels() {
        let rows = [];
        if (labelFields.length){
            labelFields.map((input, index) => rows.push(<TextField name='label' key={index} value={input.label} onChange={event => handleInputChange(index, event)}/>));
        }
        return rows;
    }
    
    return (
        <TableRow key={props.node.node_id} style={{'display': deleted ? 'none' : ''}}>
            <TableCell>
                {props.node.node_id}
            </TableCell>
            <TableCell align="right">
                {props.node.name}
            </TableCell>
            <TableCell align="right">
                <button onClick={handleOpen} className="edit-btn">Edit</button>
                <Dialog
                    open={open}
                    onClose={handleClose}
                >
                    <DialogTitle>
                        Edit Node
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            <div>
                                Name:
                            </div>
                            <TextField value={name} onChange={handleNameChange} />
                            <div>
                                Labels:
                            </div>
                            {nodeLabels()}
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={saveChanges}>Save</Button>
                        <Button onClick={handleClose}>
                            Discard
                        </Button>
                    </DialogActions>
                </Dialog>
                <button className="delete-btn" onClick={dltNode}>
                    Delete
                </button>
            </TableCell>
        </TableRow>
    );
}

export default NodeEntry;
