export async function helloApi(): Promise<string> {
    const res = await fetch("http://localhost:8080/api/users/1");
    return res.text();
}