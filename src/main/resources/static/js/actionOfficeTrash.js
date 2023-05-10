
document.addEventListener('DOMContentLoaded', function() {
	var id;
	const deleteForm = document.forms['form-delete']
	const deleteObject = document.getElementById('delete')
	const checkBoxAll = $('#checkbox-all')
	const checkBox = $('input[name="ids"]')
	const submitBtn = $('#submit-btn')

	
	

	deleteObject.addEventListener('show.bs.modal', event => {
		const button = event.relatedTarget
		id = button.getAttribute('data-id')
	})
	
	document.getElementById('btn-destroy').onclick = () => {
		deleteForm.action = '/admin/office/destroy/' + id;
		deleteForm.submit()
	}
	

	checkBoxAll.change(function() {
		var isCheckedAll = $(this).prop('checked')
		checkBox.prop('checked', isCheckedAll)
		renderSubmitBtn()
	})
	checkBox.change(function() {
		var isCheckedAll = checkBox.length === $('input[name="ids"]:checked').length
		checkBoxAll.prop('checked', isCheckedAll)
		renderSubmitBtn()
	})

	function renderSubmitBtn() {
		if ($('input[name="ids"]:checked').length > 0) submitBtn.removeClass('disabled')
		else submitBtn.addClass('disabled')
	}
})