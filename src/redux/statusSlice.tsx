
import { createSlice } from '@reduxjs/toolkit';
import { PayloadAction } from '@reduxjs/toolkit';

export type AppStatus = {
	loading: boolean
}

const initialState: AppStatus = {
	loading: false
}

const statusSlice = createSlice({
	name: "status",
	initialState,
	reducers: {
		setLoading: (state, action: PayloadAction<boolean>) => {
			state.loading = action.payload
		}
	}
})
export const {setLoading} = statusSlice.actions
export default statusSlice.reducer