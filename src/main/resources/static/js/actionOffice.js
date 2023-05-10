
document.addEventListener('DOMContentLoaded', function() {
	var id;
	const deleteForm = document.forms['form-delete']
	const deleteObject = document.getElementById('delete')
	

	deleteObject.addEventListener('show.bs.modal', event => {
		const button = event.relatedTarget
		id = button.getAttribute('data-id')
		console.log(id)
	})
	
	
	document.getElementById('btn-delete').onclick = () => {
		deleteForm.action = '/admin/office/delete/' + id;
		deleteForm.submit()
	}

	
})