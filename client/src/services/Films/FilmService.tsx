import { API } from "../APIS/constants";
import { serverA } from "./../APIS/index";

export class FilmService {
  static getAll = async (apiKey: string) => {
    try {
      const response = await serverA.get(API.FILMS, {
        headers: {
          Authorization: `${apiKey}`,
          "Content-Type": "application/json",
        },
      });

      return response.data;
    } catch (error) {
      throw error; // You can throw the error for better error handling in the component
    }
  };
}
