import React, { useState, useEffect } from 'react';
import { DataGrid, GridColDef, GridRowParams, GridValueGetterParams } from '@mui/x-data-grid';
import { ActorService } from '../../../services/Actor/ActorService';
import { Button, Modal } from '@mui/material';
import ActorForm  from '../../Auth/AuthForm';
import { ActorFieldForm } from './ActorForm';


const columns: GridColDef[] = [
  { field: "id", headerName: 'ID', width: 200 },
  { field: "firstName", headerName: 'First name', width: 200 },
  { field: "lastName", headerName: 'Last name', width: 200 },
  {
    field: 'fullName',
    headerName: 'Full name',
    description: 'This column has a value getter and is not sortable.',
    sortable: false,
    width: 300,
    valueGetter: (params: GridValueGetterParams) =>
      `${params.row.firstName || ''} ${params.row.lastName || ''}`,
  },
];

const ActorTable: React.FC = () => {
  const [data, setData] = useState([]);
  const [open, setOpen] = useState(false)

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const actors = await ActorService.getAll();

        setData(actors);

      } catch (err) {
        console.error(err);
      }
    };

    fetchData();
  }, []);

  const handleAdd = () => {
    handleOpen()
  }
  const handleUpdate = (data: GridRowParams) => {
    handleOpen()
    const rowData = data.row as ActorFieldForm; // Cast the row data to ActorFieldForm

    if (rowData) {
      const { firstName, lastName } = rowData;
      console.log('First Name:', firstName);
      console.log('Last Name:', lastName);

      // Add your logic to update the row or perform any other actions here
    }

  }

  return (
    <div style={{ height: 600, width: '100%' }}>
      {data.length > 0 ? (
        <>
          <Button onClick={handleAdd}>
            Add New Actor
          </Button>
          <Modal
            open={open}
            onClose={handleClose}>
            <ActorForm />
          </Modal>
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
            onRowDoubleClick={handleUpdate}
          />
        </>

      ) : (
        <h1>Fetching data ...</h1>
      )}
    </div>

  );
};

export default ActorTable;
