document.getElementById('editButton').addEventListener('click', function() {

    editForm = document.querySelector('.edit');
    showForm = document.querySelector('.show');

    console.log(editForm);

    editForm.style.display = 'flex';
    showForm.style.display = 'none';
    document.getElementById('editButton').style.display = 'none';
});

document.getElementById('cancelEdit').addEventListener('click', function() {
	editForm = document.querySelector('.edit');
	showForm = document.querySelector('.show');
	editForm.style.display = 'none';
	showForm.style.display = 'flex';
	document.getElementById('editButton').style.display = 'flex';
});