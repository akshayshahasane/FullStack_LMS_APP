import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { Link } from "react-router-dom";

export default function Navbar() {
    const { user, logout } = useContext(AuthContext);

    return (
        <nav className="bg-blue-500 text-white p-4 flex justify-between">
            <h1 className="font-bold text-xl">LMS</h1>
            <div className="space-x-4">
                {user?.role === "INSTRUCTOR" && <Link to="/instructor">Instructor</Link>}
                {user?.role === "STUDENT" && <Link to="/student">Student</Link>}
                <button onClick={logout}>Logout</button>
            </div>
        </nav>
    );
}
