$(function(){
	initModel();
	initTree();
	initList($('#roleTab'));
	initToolbar($('#toolbar'));
});

function initModel(){
	var $addModal = $('#addModal');
	var $alterModal = $('#alterModal');
	var $resourceModal = $('#resourceModal');
	var $tree = $('#tree');
	
	$addModal.modal({
		backdrop:false, 
		show:false
	}).on('click', 'button[data-custom-commit]', function(){
		var data = getFormData($('form', $addModal));
		$.post('/admin/role/create', data)
			.success(function(){
				reloadAll();
				$addModal.modal('hide');
			});
	});
	
	$alterModal.modal({
		backdrop:false, 
		show:false
	}).on('click', 'button[data-custom-commit]', function(){
		var data = getFormData($('form', $alterModal));
		$.post('/admin/role/update', data)
			.success(function(){
				reloadAll();
				$alterModal.modal('hide');
			});
	});
	
	$resourceModal.modal({
		backdrop:true, 
		show:false
	}).on('click', 'button[data-custom-commit]', function(){
		var selArr = $tree.treeview('getChecked');
		var resourceIdArr = [];
		var textArr = [];
		$.each(selArr, function(index, item){
			resourceIdArr.push(item.resourceId);
			textArr.push(item.text);
		});
		var $active = get$ActiveFiled();
		$active.$textField.val(textArr.join(','));
		$active.$hidenFiled.val(resourceIdArr.join(','));
		
		$resourceModal.modal('hide');
		$tree.treeview('uncheckAll');
	}).on('click', 'button[data-custom-cancel]', function() {
		$resourceModal.modal('hide');
		$tree.treeview('uncheckAll');
	});

	$addModal.add($alterModal).each(function(index, ele) {
		var $ele = $(ele);
		$ele.on('click', 'button[data-custom-cancel]', function() {
			$ele.modal('hide');
			clean($ele);
		});
	});
}

function initTree(){
	var $tree = $('#tree');
	var treeData = [];
	var $table = $('#resourceTab')
	$.ajax({
		url:'/admin/resource/getResourceTree',
		async: false
	}).success(function(backdata){
		treeData = backdata && backdata.data;
	});
	
	$tree.treeview({
		data: treeData,
		levels: 10,
		highlightSelected: false,
		highlightSearchResults: false,
		showCheckbox: true,
		showIcon:false
	});
}

function initList($tabList){
	var cols = [
	        {
	        	checkbox:true
	        },{
				title : '序号',
				formatter : function(value, row, index) {
					return index + 1;
				}
			},{
				title : 'roleId',
				field : 'roleId',
				visible : false
			},{
				field : 'roleName',
				title : '角色'
			},{
				field : 'description',
				title : '角色描述'
			},{
				field : 'resourceList',
				title : '资源',
				formatter : function(value, row, index) {
					var resourceArr = [];
					$.each(value, function(index, item){
						resourceArr.push(item.resourceName);
					});
					
					return resourceArr.join(',');
				}
			}
	];

	$tabList.bootstrapTable({
	    method: 'get', 
	    singleSelect:true,
	    height: 700,
	    url:'/admin/role/getAll',
	    uniqueId: 'id',
	    toolbar: '#toolbar',              
	    striped: true,
	    sidePagination: "server",
	    pagination: true,                  
	    pageNumber:1,                       
	    pageSize: 15,                       
	    pageList: [15, 25, 50, 100],      
	    columns: cols,
	    responseHandler:function(backdata){
	    	if(backdata||backdata.data){
    			return backdata.data;
	    	}
	    }
	});
}

function initToolbar(){
	var $table = $('#roleTab');
	var $tree = $('#tree');

	var $addModalBut = $('#addModalBut');
	var $addModal = $('#addModal');
	
	var $alterModalBut = $('#alterModalBut');
	var $alterModal = $('#alterModal');
	
	var $deleteBut = $('#deleteBut');
	
	$addModalBut.on('click', function(){
		$addModal.modal('show');
	});
	
	$alterModalBut.on('click', function(){
		var selArr = $table.bootstrapTable('getSelections');
		if(!selArr || selArr.length == 0){
			bootbox.alert('请选择修改项');
			return;
		}
		var sel = selArr[0];
		var resourceList = sel.resourceList;
		var resourceNameArr = [];
		var resourceIdArr = [];
		
		$.each(resourceList, function(index, item){
			resourceNameArr.push(item.resourceName);
			resourceIdArr.push(item.resourceId);
		});
		
		$('#alterRoleIdHidden').val(sel.roleId);
		$('#alterRoleName').val(sel.roleName);
		$('#alterDescription').val(sel.description);
		$('#alterResource').val(resourceNameArr.join(','));
		$('#alterResourceHidden').val(resourceIdArr.join(','));
		
		$alterModal.modal('show');
	});
	
	$deleteBut.on('click', function(){
	    bootbox.alert('TODO');
	});
	
	var $addResource = $('#addResource');
	var $alterResource = $('#alterResource');
	var $resourceModal = $('#resourceModal');
	
	$addResource.add($alterResource).on('click', function(){
		var resources = $.trim($(this).val());
		var resourceArr = resources.length>0?resources.split(','):[];
		$.each(resourceArr, function(index, item){
			var node = $tree.treeview('search', [item, {exactMatch: true}]);
			$tree.treeview('checkNode', node);
		});
		$resourceModal.modal('show');
	});
}

function get$ActiveFiled(){
	var $addModal = $('#addModal');
	var $alterModal = $('#alterModal');
	var $activeFieldObj = {
		$textField :null,
		$hidenFiled :null
	};
	var isAdd = $addModal.hasClass('in');
	var isAlter = $alterModal.hasClass('in');
	if((isAdd&&isAlter)||(!isAdd&&!isAlter)){
		throw new error("新增和修改同时存在或都不存在");
	}
	if(isAdd){
		$activeFieldObj.$textField = $('#addResource');
		$activeFieldObj.$hidenFiled = $('#addResourceHidden');
	}else{
		$activeFieldObj.$textField = $('#alterResource');
		$activeFieldObj.$hidenFiled = $('#alterResourceHidden');
	}
	return $activeFieldObj;
	
}

function reloadAll(){
	$('#roleTab').bootstrapTable('refresh');
}

function clean($container){
	var $form = $container.find('form');
	var $hidden = $container.find('div.hidden input[type="hidden"]');
	var $sel2 = $container.find('input.select2-offscreen');
	
	$form.each(function(index, ele){
		ele.reset();
	});
	$hidden.each(function(index, ele) {
		$(ele).val('');
	});
	$sel2.each(function(index, ele){
		$(ele).select2('data', null);
	});
}

function getFormData($form){
	var data = $form.serializeArray();
	var formData = {};
	$.each(data, function(index, ele){
		formData[ele.name] = ele.value; 
	});
	return formData;
}