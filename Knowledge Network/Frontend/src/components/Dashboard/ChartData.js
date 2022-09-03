export const chartData = (node, edges) => {
  let node_edges = [];
  let labels = [];
  let colors = [];
  let values = [];
  for (var i = 0; i < edges.length; i++) {
    if (
      edges[i]["from"] === String(node["id"]) ||
      edges[i]["to"] === String(node["id"])
    ) {
      console.log("found");
      node_edges.push(edges[i]);
    }
  }
  for (i = 0; i < node_edges.length; i++) {
    if (labels.indexOf(node_edges[i]["label"]) === -1) {
      labels.push(node_edges[i]["label"]);
      colors.push(node_edges[i]["color"]);
      values.push(1);
    } else {
      values[labels.indexOf(node_edges[i]["label"])]++;
    }
  }
  return {
    labels: labels,
    datasets: [
      {
        label: node["label"],
        data: values,
        backgroundColor: colors,
        hoverBackgroundColor: colors,
        hoverOffset: 4,
      },
    ],
  };
};
