import React, { useState, useEffect } from 'react';
import { DataGrid, GridColDef, GridRowParams, GridValueGetterParams } from '@mui/x-data-grid';
import { FilmService } from '../../../services/Films/FilmService';


const columns: GridColDef[] = [
  { field: "id", headerName: 'ID' },
  { field: "title", headerName: 'Title', width: 200 },
  { field: "description", headerName: 'Description', width: 300 },
  { field: "releaseYear", headerName: 'Year'},
  { field: "rentalDuration", headerName: 'Duration' },
  { field: "rentalRate", headerName: 'Rate' },
  { field: "replacementCost", headerName: 'Cost'},
  { field: "rating", headerName: 'Rating' },
  { field: "specialFeatures", headerName: 'Features', width: 300 },
  { field: "language", headerName: 'Language'},
];

const FilmTable: React.FC = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const films = await FilmService.getAll();

        setData(films);

      } catch (err) {
        console.error(err);
      }
    };

    fetchData();
  }, []);



  return (
    <div style={{ height: 600, width: '100%' }}>
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

export default FilmTable;
