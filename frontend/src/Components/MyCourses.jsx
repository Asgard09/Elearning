import React from 'react';

// Import hình ảnh từ thư mục pictures
import course1 from './../pictures/android.jpg';
import course2 from './../pictures/django.jpg';
import course3 from './../pictures/swift.png';

const courseData = [
  {
    id: 1,
    title: 'Khóa học Android cơ bản',
    image: course1,
    progress: 75
  },
  {
    id: 2, 
    title: 'Lập trình Django',
    image: course2,
    progress: 30
  },
  {
    id: 3,
    title: 'Lập trình Swift',
    image: course3,
    progress: 100
  }
];

export default function MyCourses() {
  return (
    <div className="container py-4">
      <h2 className="text-center mb-4">Khóa học của tôi</h2>
      <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        {courseData.map(course => (
          <div key={course.id} className="col">
            <div className="card h-100 shadow-sm">
              <img 
                src={course.image} 
                alt={course.title}
                className="card-img-top"
                style={{ height: '200px', objectFit: 'cover' }}
              />
              <div className="card-body">
                <h5 className="card-title">{course.title}</h5>
                <div className="progress mt-3" style={{ height: '10px' }}>
                  <div
                    className={`progress-bar ${course.progress === 100 ? 'bg-success' : 'bg-primary'}`}
                    role="progressbar"
                    style={{ width: `${course.progress}%` }}
                    aria-valuenow={course.progress}
                    aria-valuemin="0"
                    aria-valuemax="100"
                  ></div>
                </div>
                <p className="text-muted small mt-2 mb-0">
                  {course.progress}% hoàn thành
                </p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
