
import { createSlice } from '@reduxjs/toolkit';
import { PayloadAction } from '@reduxjs/toolkit';
import { User } from '../models';
import { AppState } from './store';

export type AppStatus = {
	loading: boolean,
	user: User | undefined
}

const initialState: AppStatus = {
	loading: false,
	user: undefined,
}

const statusSlice = createSlice({
	name: "status",
	initialState,
	reducers: {
		setLoading: (state, action: PayloadAction<boolean>) => {
			state.loading = action.payload
		},
		setUser: (state, action: PayloadAction<User>) => {
			state.user = action.payload
		}

	}
})

export const isLogin = (state: AppState) => {
	return state.appStatus.user !== undefined;
};

export const { setLoading, setUser } = statusSlice.actions
export default statusSlice.reducer