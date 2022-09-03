import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Graph from "react-graph-vis";
import { Pie, Bar } from "react-chartjs-2";
import "chart.js/auto";
import { chartData } from "./ChartData";
import React, { useRef, useState, useEffect } from "react";
import { v4 as uuidv4 } from "uuid";
import { getGraphNodes, getGraphConnections } from "../../APIRequests";
import { getNodesByName, getRelantionshipColors } from "APIRequests";
import SearchBar from "./SearchBar";

function Dashboard() {
  const emptyData = {
    labels: [],
    datasets: [
      {
        label: "",
        data: [],
        backgroundColor: [],
        hoverOffset: 4,
      },
    ],
  };
  const [nodes, SetNodes] = useState([]);
  const [edges, SetEdges] = useState([]);

  //GET RELANTIONSHIPS COLORS
  const getEdges = () => {
    getRelantionshipColors().then((response) => {
      let dict = {};
      let rgb = "";
      let hex = "";
      for (let j = 0; j < response.data["relationship_types"].length; j++) {
        rgb =
          "rgb(" +
          (200 * j).toString() +
          "," +
          (10 * (j + 1)).toString() +
          "," +
          (100 * (j + 2)).toString() +
          ")";
        hex = "#" + Math.random().toString(16).substr(-6);
        dict[response.data["relationship_types"][j]] = hex;
      }

      let edges = [];
      getGraphConnections().then((response) => {
        for (let i = 0; i < response.data["results"].length; i++) {
          edges.push({
            from: response.data["results"][i]["first_node"],
            to: response.data["results"][i]["second_node"],
            label: response.data["results"][i]["type"],
            font: { size: 12, color: "white" },
            color: dict[response.data["results"][i]["type"]],
            arrows: "none",
          });
        }
        SetEdges(edges);
      });
    });
  };

  //GET NODES FOR GRAPH
  const getNodes = () => {
    getGraphNodes().then((response) => {
      let nodes = [];
      for (let i = 0; i < response.data["results"].length; i++) {
        nodes.push({
          id: response.data["results"][i]["node_id"],
          label: response.data["results"][i]["name"],
          font: { size: 12, color: "white", face: "arial" },
        });
      }
      SetNodes(nodes);
    });
  };

  useEffect(() => {
    getNodes();
    getEdges();
  }, []);

  const data = useRef(emptyData);
  const updateData = useRef(null);
  const graph = { nodes: nodes, edges: edges };
  const options = {
    layout: {
      hierarchical: false,
    },
    nodes: {
      shape: "dot",
      scaling: {
        min: 5,
        max: 20,
      },
    },
    edges: {
      color: { inherit: true },
      smooth: { roundness: 0.5 },
      font: { strokeWidth: 0 },
    },
    interaction: {
      hideEdgesOnDrag: true,
      tooltipDelay: 200,
    },
    physics: false,
    height: "100%",
    width: "100%",
  };

  const events = {
    select: ({ nodes }) => {
      if (nodes) {
        data.current = chartData(
          graph.nodes.find((obj) => {
            return obj.id === nodes[0];
          }),
          graph.edges
        );
        updateData.current();
      } else {
        data.current = emptyData;
      }
    },
  };

  const handleSearch = (event, searchQuery) => {
    event.preventDefault();
    if (searchQuery === "") {
      getGraphNodes().then((response) => {
        let nodes = [];
        for (let i = 0; i < response.data["results"].length; i++) {
          nodes.push({
            id: response.data["results"][i]["node_id"],
            label: response.data["results"][i]["name"],
            font: { size: 12, color: "white", face: "arial" },
          });
        }
        SetNodes(nodes);
      });
    } else {
      getNodesByName(searchQuery).then((response) => {
        let nodes = [];
        console.log(response);
        for (let i = 0; i < response.data["results"].length; i++) {
          nodes.push({
            id: response.data["results"][i]["node_id"],
            label: response.data["results"][i]["name"],
            font: { size: 12, color: "white", face: "arial" },
          });
        }
        SetNodes(nodes);
      });
    }
  };

  const ChildCharts = ({ updateData }) => {
    const [newData, setData] = useState(emptyData);
    useEffect(() => {
      updateData.current = update;
    }, [updateData]);
    function update() {
      setData(data.current);
    }
    return (
      <Grid padding={0}>
        <Grid container direction="column">
          <Grid padding={2} marginTop={7}>
            <div
              style={{ width: "35em", height: "27em", borderStyle: "solid" }}
            >
              <Pie
                data={newData}
                style={{ background: "#282c34", color: "black" }}
                width={"100%"}
                options={{
                  maintainAspectRatio: false,
                  elements: { arc: { borderWidth: 0 } },
                }}
              />
            </div>
          </Grid>
          <Grid padding={2}>
            <div
              style={{ width: "35em", height: "21em", borderStyle: "solid" }}
            >
              <Bar
                data={newData}
                style={{ background: "#282c34" }}
                width={"100%"}
                options={{ maintainAspectRatio: false }}
              />
            </div>
          </Grid>
        </Grid>
      </Grid>
    );
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Box display="flex" justifyContent="center" alignItems="center">
        <Grid padding={2}>
          <SearchBar handleSearch={handleSearch} />
          <div
            style={{
              background: "#282c34",
              width: "70em",
              height: "50em",
              borderStyle: "solid",
            }}
          >
            <Graph
              key={uuidv4()}
              graph={graph}
              options={options}
              events={events}
              getNetwork={(network) => {
                //  if you want access to vis.js network api you can set the state in a parent component using this property
              }}
            />
          </div>
        </Grid>
        <ChildCharts updateData={updateData} />
      </Box>
    </div>
  );
}

export default Dashboard;
