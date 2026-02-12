export default function CourseCard({ course }) {
    return (
        <div className="border p-4 rounded shadow">
            <h2 className="font-bold text-xl">{course.title}</h2>
            <p>{course.description}</p>
        </div>
    );
}
