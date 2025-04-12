document.getElementById('registrationForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const studentID = document.getElementById('studentID').value;
    const studentName = document.getElementById('studentName').value;
    const studentEmail = document.getElementById('studentEmail').value;
    const studentMobile = document.getElementById('studentMobile').value;
    const studentCourse = document.getElementById('studentCourse').value;
    const feesPaid = document.getElementById('feesPaid').value;
    const room = document.getElementById('roomSelect').value;

    if (studentID && studentName && studentMobile && studentCourse && feesPaid && room) {
        const allocationList = document.getElementById('allocationList');

        const newRow = document.createElement('tr');
        newRow.setAttribute('data-id', studentID);
        newRow.innerHTML = `
            <td>${studentID}</td>
            <td>${studentName}</td>
            <td>${room}</td>
            <td>
                <button class="update-btn">Update</button>
                <button class="delete-btn">Delete</button>
            </td>`;

        allocationList.appendChild(newRow);

        alert(`Student ${studentName} (ID: ${studentID}) registered successfully for Room ${room}!`);

        this.reset();
    } else {
        alert('Please fill all the fields before submitting.');
    }
});

document.getElementById('allocationList').addEventListener('click', function(event) {
    if (event.target.classList.contains('update-btn')) {
        const row = event.target.closest('tr');
        const studentID = row.querySelector('td').textContent;
        const studentName = row.querySelectorAll('td')[1].textContent;
        const room = row.querySelectorAll('td')[2].textContent;

        const newStudentName = prompt('Update student name:', studentName);
        const newRoom = prompt('Update room number:', room);

        if (newStudentName && newRoom) {
            row.querySelectorAll('td')[1].textContent = newStudentName;
            row.querySelectorAll('td')[2].textContent = newRoom;

            alert(`Student ${studentName} updated successfully!`);
        }
    }

    if (event.target.classList.contains('delete-btn')) {
        const row = event.target.closest('tr');
        row.remove();
        alert('Student record deleted successfully!');
    }
});
