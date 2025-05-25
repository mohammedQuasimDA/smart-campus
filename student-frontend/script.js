const form = document.getElementById('studentForm');
const tableBody = document.getElementById('studentTableBody');
const BASE_URL = 'http://localhost:4000/api/students';


// Load all students on page load
window.onload = () => {
    fetchStudents();
};

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const student = {
        name: document.getElementById('name').value,
        rollNumber: document.getElementById('rollNumber').value,
        email: document.getElementById('email').value
    };

    try {
        const response = await fetch(BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(student)
        });

        if (!response.ok) throw new Error('Failed to create student');

        form.reset();
        fetchStudents();
    } catch (error) {
        alert('Error: ' + error.message);
    }
});

async function fetchStudents() {
    try {
        const response = await fetch(BASE_URL);
        const students = await response.json();

        tableBody.innerHTML = '';
        students.forEach((student) => {
            const row = document.createElement('tr');
            row.innerHTML = `
        <td>${student.name}</td>
        <td>${student.rollNumber}</td>
        <td>${student.email}</td>
        <td>
          <button class="delete-btn" onclick="deleteStudent('${student.rollNumber}')">Delete</button>
        </td>
      `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        alert('Failed to fetch students');
    }
}

async function deleteStudent(rollNumber) {
    if (!confirm(`Are you sure to delete student ${rollNumber}?`)) return;

    try {
        const response = await fetch(`${BASE_URL}/${rollNumber}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error('Failed to delete student');
        fetchStudents();
    } catch (error) {
        alert('Error deleting student');
    }
}
