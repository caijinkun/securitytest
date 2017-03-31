$(function(){
	initSel();
	initModel();
	initTree();
	initList($('#resourceTab'));
	initToolbar($('#toolbar'));
});

function initSel(){
	var typeData = [ {
		"id" : "1",
		"text" : "菜单"
	}, {
		"id" : "2",
		"text" : "按钮"
	} ];
	var $addHidden = $('#addHidden');
	var $alterHidden = $('#alterHidden');
	
	$('#addType, #alterType').select2({
		data:typeData
	}).on('click', function(){
		var $this = $(this);
		var id = $this.prop('id');
		var data = $this.select2('data');
		var value = data.id;
		$('#'+id+"Hidden").val(value);
	});
}

function initModel(){
	var $addModal = $('#addModal');
	var $alterModal = $('#alterModal');
	
	$addModal.modal({
		backdrop:false, 
		show:false
	}).on('click', 'button[data-custom-commit]', function(){
		var data = getFormData($('form', $addModal));
		$.post('/admin/resource/create', data)
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
		$.post('/admin/resource/update', data)
			.success(function(){
				reloadAll();
				$alterModal.modal('hide');
			});
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
	treeData = [];
	var $table = $('#resourceTab')
	$.ajax({
		url:'/admin/resource/getResourceTree',
		async: false
	}).success(function(backdata){
		treeData = backdata && backdata.data;
	});
	
	$tree.treeview({
		data: treeData,
		levels: 3,
		showIcon:false,
		onNodeSelected:function(event, node){
			console.log(node);
			$table.bootstrapTable('refresh', {
				query: {
					pId: node.resourceId
				}
			});
		}
	});
}

function reloadTree(){
	var $tree = $('#tree');
	var $treeParent = $tree.parent();
	$tree.remove();
	$treeParent.append($('<div id="tree"></div>'));
	initTree();
}

function reloadAll(){
	reloadTree();
	$('#resourceTab').bootstrapTable('refresh');
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
				title : 'resourceId',
				field : 'resourceId',
				visible : false
			},{
				field : 'resourceName',
				title : '资源描述'
			},{
				field : 'type',
				title : '资源类型',
				formatter : function(value, row, index) {
					return value == 1?'菜单':'按钮';
				}
			},{
				field : 'url',
				title : '路径',
			},{
				field : 'parentId',
				title : '父节点'
			},{
				field : 'permision',
				title : '权限表达式'
			},{
				field : 'resourceOrder',
				title : '排序'
			},{
				field : '备注',
				title : 'remark'
			}
	];

	$tabList.bootstrapTable({
	    method: 'get', 
	    singleSelect:true,
	    height: 700,
	    url:'/admin/resource/getAll',
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
	var $table = $('#resourceTab');
	var $tree = $('#tree');

	var $addModalBut = $('#addModalBut');
	var $addModal = $('#addModal');
	
	var $alterModalBut = $('#alterModalBut');
	var $alterModal = $('#alterModal');
	
	var $deleteBut = $('#deleteBut');
	
	$addModalBut.on('click', function(){
		var selArr = $tree.treeview('getSelected');
		if(!selArr || selArr.length == 0){
			bootbox.alert('请选择父节点');
			return;
		}
		var sel = selArr[0];
		$('#addParentIdHidden').val(sel.parentId);
		$addModal.modal('show');
	});
	
	$alterModalBut.on('click', function(){
		var selArr = $table.bootstrapTable('getSelections');
		if(!selArr || selArr.length == 0){
			bootbox.alert('请选择修改项');
			return;
		}
		var sel = selArr[0];
		var selRoleSet = sel.roleSet;
		var roleIds = $.map(selRoleSet, function(a){
			return a.roleId;
		});
		
		$('#alterIdHidden').val(sel.resourceId);
		$('#alterResourceName').val(sel.resourceName);
		$('#alterUrl').val(sel.url);
		$('#alterResourceOrder').val(sel.resourceOrder);
		$('#alterPermision').val(sel.permision);
		$('#alterRemark').val(sel.remark);
		$('#alterType').select2('val', sel.type);
		$('#alterTypeHidden').val(sel.type);
		
		$alterModal.modal('show');
	});
	
	$deleteBut.on('click', function(){
	    bootbox.alert('TODO');
	});
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