// frontend/src/api/testApi.ts
import { api } from "./axios";

export const testBackend = async (): Promise<string> => {
    const res = await api.get<string>("/test");
    return res.data;
};
