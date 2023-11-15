export interface User {
  accessToken: string;
  userName: string;
  passWord: string;
}

export type AppActionType = {
  type: string;
  payload: any;
}; // Add other action types as needed

export const setUser = (user: User): AppActionType => {
  return {
    type: "SET_USER",
    payload: user,
  };
};
