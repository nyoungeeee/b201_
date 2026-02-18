import { useEffect, useState } from "react";
import { helloApi } from "./api/test";

function App() {
    const [msg, setMsg] = useState("");

    useEffect(() => {
        helloApi().then(setMsg);
    }, []);

    return <h1>{msg}</h1>;
}

export default App;