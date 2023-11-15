import React, { useState, useEffect } from "react";
import {
  DataGrid,
  GridColDef,
  GridRowParams,
  GridValueGetterParams,
} from "@mui/x-data-grid";
import { ActorService } from "../../../services/Actor/ActorService";
import { useSelector } from "react-redux";
import { AppState } from "../../../redux/store";

const columns: GridColDef[] = [
  { field: "id", headerName: "ID", width: 200 },
  { field: "firstName", headerName: "First name", width: 200 },
  { field: "lastName", headerName: "Last name", width: 200 },
  {
    field: "fullName",
    headerName: "Full name",
    description: "This column has a value getter and is not sortable.",
    sortable: false,
    width: 300,
    valueGetter: (params: GridValueGetterParams) =>
      `${params.row.firstName || ""} ${params.row.lastName || ""}`,
  },
];

const ActorTable: React.FC = () => {
  const [data, setData] = useState([]);

  const user = useSelector((state: AppState) => state.user);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const actors = await ActorService.getAll(user?.accessToken || "");

        setData(actors);
      } catch (err) {
        console.error(err);
      }
    };

    fetchData();
  }, []);

  return (
    <div style={{ height: 600, width: "100%" }}>
      {data.length > 0 ? (
        <>
          <DataGrid
            rows={data}
            columns={columns}
            initialState={{
              pagination: {
                paginationModel: { page: 0, pageSize: 20 },
              },
            }}
            pageSizeOptions={[20, 50]}
            checkboxSelection
          />
        </>
      ) : (
        <h1>Fetching data ...</h1>
      )}
    </div>
  );
};

export default ActorTable;
