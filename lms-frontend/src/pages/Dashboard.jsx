import { useEffect, useState } from "react";
import API from "../api/axios";

export default function Dashboard() {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        API.get("/student/courses")
            .then(res => setCourses(res.data))
            .catch(err => console.log(err));
    }, []);

    return (
        <div className="p-6">
            <h1 className="text-3xl font-bold mb-4">Courses</h1>
            <div className="grid grid-cols-3 gap-4">
                {courses.map(course => (
                    <div key={course.id} className="border p-4 rounded shadow">
                        <h2 className="text-xl font-bold">{course.title}</h2>
                        <p>{course.description}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}
