
document.addEventListener('DOMContentLoaded', function() {
	var id;
	const deleteForm = document.forms['form-delete']
	const deleteObject = document.getElementById('delete')
	

	deleteObject.addEventListener('show.bs.modal', event => {
		const button = event.relatedTarget
		id = button.getAttribute('data-id')
	})
	
	
	document.getElementById('btn-delete').onclick = () => {
		deleteForm.action = '/admin/customer/delete/' + id;
		deleteForm.submit()
	}

	
})