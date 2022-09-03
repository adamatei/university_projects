import Grid from '@mui/material/Grid';
import Box from "@mui/material/Box";
import 'chart.js/auto';
import './GraphEdit.css';
import { getNodes } from "../../APIRequests";
import React, { useState, useEffect } from "react";
import NodeEntry from './NodeEntry';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

function GraphEdit() {
  const [nodes, setNodes] = useState([]);

  useEffect(() => {
    const fetchNodes = async () => {
      const raw_nodes = await getNodes();
      if (raw_nodes.data !== undefined) {
        setNodes(raw_nodes['data']);
      } else {
        setNodes([]);
      }
    }
    fetchNodes();
  }, []);

  function graphEntries() {
    let rows = [];
    if (nodes['results']){
      nodes['results'].map((b, index) => rows.push(<NodeEntry key={index} node={b} />));
    }
    return rows;
  }

  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
      >
        <Grid padding={2}>
          <TableContainer style={{marginTop: '5px'}} component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell>Node Id</TableCell>
                  <TableCell align="right">Name</TableCell>
                  <TableCell align="right">Actions</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {graphEntries()}
              </TableBody>
            </Table>
          </TableContainer>
        </Grid>
      </Box>
    </div>
  );
}

export default GraphEdit;
