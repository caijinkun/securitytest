$(function(){
	initSel();
	initModel();
	initList($('#userTab'));
	initToolbar($('#toolbar'));
});

function getRoleList(){
	var roleList = [];
	$.ajax({
		url:'/admin/role/getAll',
		async: true
	}).success(function(backdata){
		data = backdata && backdata.data;
		$.each(data, function(index, elem){
			roleList[index] = {};
			roleList[index].id = elem.roleId;
			roleList[index].text = elem.description;
		});
	});
	getRoleList = function(){
		return roleList;
	}
	return roleList;
}

function initSel(){
	var lockData = [ {
		"id" : "0",
		"text" : "是"
	}, {
		"id" : "1",
		"text" : "否"
	} ];
	var roleList = getRoleList();
	var $addHidden = $('#addHidden');
	var $alterHidden = $('#alterHidden');
	
	$('#alterIsLocked, #addIsLocked').select2({
		data:lockData
	}).on('click', function(){
		var $this = $(this);
		var id = $this.prop('id');
		var data = $this.select2('data');
		var value = data.id;
		$('#'+id+"Hidden").val(value);
	});
	
	$('#addRole, #alterRole').select2({
		data:roleList,
		multiple: true
	}).on('click', function(){
		var $this = $(this);
		var id = $this.prop('id');
		var data = $this.select2('data');
		var value = [];
		$.each(data, function(index, item){
			value.push(item.id);
		});
		$('#'+id+"Hidden").val(value.sort().join(','));
	});
}

function initModel(){
	var $addModal = $('#addModal');
	var $alterModal = $('#alterModal');
	var $alterPassModal = $('#alterPassModal');
	
	$addModal.modal({
		backdrop:false, 
		show:false
	}).on('click', 'button[data-custom-commit]', function(){
		var data = getFormData($('form', $addModal));
		$.post('/admin/user/create', data);
	});
	
	$alterModal.modal({
		backdrop:false, 
		show:false
	}).on('click', 'button[data-custom-commit]', function(){
		var data = getFormData($('form', $alterModal));
		$.post('/admin/user/update', data);
	});
	
	$alterPassModal.modal({
		backdrop:false, 
		show:false
	}).on('click', 'button[data-custom-commit]', function(){
		var data = getFormData($('form', $alterPassModal));
		if(data.password.length < 6){
			bootbox.alert('密码不能小于6位');
			return;
		}
		if(data.password != $('#passAgain').val()){
			bootbox.alert('两次输入密码不一致');
			return;
		}
		$.post('/admin/user/updatePassword', data);
	});
	

	$addModal.add($alterModal).add($alterPassModal).each(function(index, ele) {
		var $ele = $(ele);
		$ele.on('click', 'button[data-custom-cancel]', function() {
			$ele.modal('hide');
			clean($ele);
		});
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
				title : 'userId',
				field : 'userId',
				visible : false
			},{
				field : 'username',
				title : '用户名'
			},{
				field : 'roleSet',
				title : '角色ID',
				formatter : function(value, row, index){
					var ids = []; 
					$.each(value, function(index, item){
						ids.push(item.roleId);
					});
					return ids; 
				}
			},{
				field : 'roleSet',
				title : '角色',
				formatter : function(value, row, index){
					var text = []; 
					$.each(value, function(index, item){
						text.push(item.roleName);
					});
					return text.join(','); 
				}
			},{
				field : 'roleSet',
				title : '角色描述',
				formatter : function(value, row, index){
					var text = []; 
					$.each(value, function(index, item){
						text.push(item.description);
					});
					return text.join(','); 
				}
			},{
				field : 'locked',
				title : '是否锁定', 
				formatter : function(value, row, index) {
					return value==0?"否":"是";
				}
			}
	];

	$tabList.bootstrapTable({
	    method: 'get', 
	    singleSelect:true,
	    height: 700,
	    url:'/admin/user/getAll',
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
	var $addModalBut = $('#addModalBut');
	var $addModal = $('#addModal');
	
	var $alterModalBut = $('#alterModalBut');
	var $alterModal = $('#alterModal');
	
	var $alterPassModalBut = $('#alterPassModalBut');
	var $alterPassModal = $('#alterPassModal');
	
	var $deleteBut = $('#deleteBut');
	
	var $userTab = $('#userTab');
	
	$addModalBut.on('click', function(){
		$addModal.modal('show');
	});
	
	$alterModalBut.on('click', function(){
		var selArr = $userTab.bootstrapTable('getSelections');
		if(!selArr || selArr.length == 0){
			bootbox.alert('请选择修改项');
			return;
		}
		var sel = selArr[0];
		var selRoleSet = sel.roleSet;
		var roleIds = $.map(selRoleSet, function(a){
			return a.roleId;
		});
		
		$('#alterUserName').val(sel.username);
		$('#alterIdHidden').val(sel.userId);
		$('#alterRoleHidden').val(roleIds.sort().join(','));
		$('#alterIsLockedHidden').val(sel.locked);
		$('#alterRole').select2('val', roleIds);
		$('#alterIsLocked').select2('val', sel.locked);
		
		$alterModal.modal('show');
	});
	
	$alterPassModalBut.on('click', function(){
		var selArr = $userTab.bootstrapTable('getSelections');
		if(!selArr || selArr.length == 0){
			bootbox.alert('请选择修改项');
			return;
		}
		var sel = selArr[0];
		$('#alterPassIdHidden').val(sel.userId);
		$alterPassModal.modal('show');
	});
	
	$deleteBut.on('click', function(){
		var selArr = $userTab.bootstrapTable('getSelections');
		if(!selArr || selArr.length == 0){
			bootbox.alert('请选择修改项');
			return;
		}
		var sel = selArr[0];
	    bootbox.confirm({
	        message: "确定删除吗?",
	        buttons: {
	            confirm: {
	                label: '确定',
	                className: 'btn-success'
	            },
	            cancel: {
	                label: '取消',
	                className: 'btn-danger'
	            }
	        },
	        callback: function (result) {
	        	$.post('/admin/user/delete', {"userId":sel.userId});
	        }
	    });
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