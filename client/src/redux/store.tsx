// store.ts
import { createStore, combineReducers } from 'redux';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import { AppActionType, User } from './actions';

// Define your state and actions types
export interface AppState {
	// Define your state properties here
	user: User | null;
}

// Define your reducers
const userReducer = (state: User | null = null, action: AppActionType): User | null => {
	switch (action.type) {
		case 'SET_USER':
			return action.payload;
		default:
			return state;
	}
};

const rootReducer = combineReducers({
	user: userReducer,
});

// Configure Redux Persist
const persistConfig = {
	key: 'root',
	storage,
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

// Create the store
const store = createStore(persistedReducer);
const persistor = persistStore(store);

export { store, persistor };
