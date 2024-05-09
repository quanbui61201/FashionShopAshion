<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--Modal: modalConfirmDelete-->
<div class="modal fade" id="modalConfirmDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm modal-notify modal-primary" role="document">
		<div class="modal-content text-center">
			<div class="d-flex justify-content-center">
				<h1 class="text-primary mt-3">Are you sure to delete?</h1>
			</div>
		
			<div class="modal-body">
				<i class="fas fa-times fa-4x animated rotateIn"></i>
			</div>
			
			<div class="modal-footer flex-center">
				<a href="#" class="btn btn-outline-primary" id="deleteLink">Yes</a>
				<a type="button" class="btn btn-primary waves-effect" data-dismiss="modal">No</a>
			</div>
		</div>
	</div>
</div>