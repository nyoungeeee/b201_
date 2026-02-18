// src/pages/Home.tsx
import {useEffect, useState} from "react";
import {getUsers} from "../api/userApi";
import type {User} from "../types/User";

const Home = () => {
    const [users, setUsers] = useState<User[]>([]);

    useEffect(() => {
        getUsers().then(setUsers);
    }, []);

    return (
        <div>
            <h1>Frontend Ready 🚀</h1>
    <ul>
    {users.map((u) => (
            <li key={u.id}>{u.email}</li>
        ))}
    </ul>
    </div>
);
};

export default Home;