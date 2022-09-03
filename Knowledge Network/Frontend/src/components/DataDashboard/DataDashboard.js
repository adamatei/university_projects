import { CheckCircleOutline } from "@mui/icons-material";
import { ListItemButton, ListItemIcon, ListItemText } from "@mui/material";
import { ListItem } from "@mui/material";
import { List } from "@mui/material";
import { useEffect, useState } from "react";
import "./DataDashboard.css";
import DetailPanel from "./DetailPanel";
import {
  approvePendingData,
  getAllPendingData,
  getUserById,
} from "../../APIRequests";

function DataDashboard() {
  const [dataList, setDataList] = useState();
  const [selectedEntry, setSelectedEntry] = useState(null);
  const [showDetailPanel, setShowDetailPanel] = useState(false);

  useEffect(() => {
    getPendingData();
  }, []);

  function openDetailPanel(id) {
    setSelectedEntry(dataList[id]);
    setShowDetailPanel(true);
  }

  function closeDetailPanel() {
    setShowDetailPanel(false);
  }

  function getPendingData() {
    getAllPendingData()
      .then((response) => {
        setDataList(response.data);
      })
      .catch((error) => {
        console.log(`ERROR : ${error}`);
      });
  }

  function approveData(data) {
    data.approved = true;
    approvePendingData(data.id, data);
  }

  return (
    <div className="data-main-content">
      {showDetailPanel && (
        <DetailPanel
          data={selectedEntry}
          closeDetailPanel={closeDetailPanel}
          approve={approveData}
        />
      )}
      <main>
        <div className="data-list-container">
          <h1>User Submitted Data</h1>
          <List>
            {dataList?.map((data, index) => (
              <ListItem key={index}>
                <ListItemButton onClick={() => openDetailPanel(index)}>
                  <ListItemText
                    primary={data.data}
                    primaryTypographyProps={{ variant: "h6" }}
                  />
                </ListItemButton>
              </ListItem>
            ))}
          </List>
        </div>
      </main>
    </div>
  );
}

export default DataDashboard;
