// src/api/userApi.ts
import { api } from "./axios";
import type {User} from "../types/User.ts";

export const getUsers = async (): Promise<User[]> => {
    const res = await api.get<User[]>("/users");
    return res.data;
};