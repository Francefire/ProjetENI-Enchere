document.getElementById('editButton').addEventListener('click', function() {

	editForm = document.querySelector('.edit');
	showForm = document.querySelector('.show');

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

document.getElementById('deleteButton').addEventListener('click', function() {

	editForm = document.querySelector('.edit');
	showForm = document.querySelector('.show');
	deleteForm = document.querySelector('.delete');
	editForm.style.display = 'none';
	showForm.style.display = 'none';
	deleteForm.style.display = 'flex';

});

document.getElementById('cancelDelete').addEventListener('click', function() {
	editForm = document.querySelector('.edit');
	showForm = document.querySelector('.show');
	deleteForm = document.querySelector('.delete');
	editForm.style.display = 'flex';
	showForm.style.display = 'none';
	deleteForm.style.display = 'none';
});
confirmEditPassword = document.getElementById('confirmEditPassword');
document.getElementById('editPassword').addEventListener('input', function() {
console.log(confirmEditPassword);
	if (this.value.length > 0) {
		this.setAttribute("required", "");
		confirmEditPassword.setAttribute("required", "");
	} else {
		this.setAttribute("required", "");
		confirmEditPassword.setAttribute("required", "");
	}
});