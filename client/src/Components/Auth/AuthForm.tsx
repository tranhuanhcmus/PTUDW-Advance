import { Box, Button, TextField, Typography } from "@mui/material";
import "./styles.scss";
import { useState } from "react";
import { AuthService } from "../../services/Auth/AuthService";
import { AxiosError } from "axios";
import { useDispatch, useSelector } from "react-redux";
import { AppState } from "./../../redux/store";
import { setUser } from "../../redux/actions";

export type AuthFieldForm = {
  userName: string;
  password: string;
};

const initialFields: AuthFieldForm = {
  userName: "",
  password: "",
};

const AuthForm = () => {
  const [fields, setFields] = useState(initialFields);
  const [status, setStatus] = useState<string>("");
  const dispatch = useDispatch();

  const user = useSelector((state: AppState) => state.user);

  const handleFieldChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFields({
      ...fields,
      [name]: value,
    });
  };

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const res = await AuthService.login(fields);
      setStatus("loginsuccess");
      dispatch(
        setUser({
          accessToken: res,
          userName: fields.userName,
          passWord: fields.password,
        })
      );
      alert("login success");
    } catch (error) {
      // Handle login error
      console.error("Login error:", error);
      alert("Login error");
    }
  };

  return (
    <Box component="form" id="AuthForm" onSubmit={handleLogin}>
      <div className="container">
        <TextField
          required
          name="userName"
          label="UserName"
          fullWidth
          value={fields.userName}
          onChange={handleFieldChange}
        />
        <TextField
          required
          name="password"
          label="Password"
          fullWidth
          value={fields.password}
          onChange={handleFieldChange}
        />
        <Typography>{status}</Typography>
        <Button type="submit" variant="contained">
          LOGIN
        </Button>
      </div>
    </Box>
  );
};

export default AuthForm;
