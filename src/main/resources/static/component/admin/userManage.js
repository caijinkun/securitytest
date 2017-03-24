$(function(){
	initSel();
	initModel();
	initList($('#userTab'));
	initToolbar($('#toolbar'));
});

function initSel(){
	
}

function initModel(){
	var $addModal = $('#addModal');
	var $alterModal = $('#alterModal');
	var $alterPassModal = $('#alterPassModal');
	
	$alterModal.modal({
		backdrop:false, 
		show:false
	});
	
	$alterPassModal.modal({
		backdrop:false, 
		show:false
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
	var roleData = getRoleMap();
	var cols = [
	        {
	        	checkbox:true
	        },{
				title : '序号',
				formatter : function(value, row, index) {
					return index + 1;
				}
			},{
				title : 'id',
				field : 'id',
				visible : false
			},{
				field : 'username',
				title : '用户名'
			},{
				field : 'roleIds',
				title : '角色',
				formatter : function(value, row, index){
					var text = []; 
					$.each(value, function(index, item){
						text.push(roleData[item]);
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
	    height: 400,
	    url:'/user/getAll',
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
	    	if(backdata){
    			return backdata;
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
	
	$addModalBut.on('click', function(){
		$addModal.modal('show');
	});
	$alterModalBut.on('click', function(){
		$alterModal.modal('show');
	});
	$alterPassModalBut.on('click', function(){
		$alterPassModal.modal('show');
	});
}

function getRoleList(){
	return $('#userTab').data('customRoleList');
}

function getRoleMap(){
	var roleList = getRoleList();
	var roleMap = {};
	$.each(roleList, function(index, item){
		roleMap[item.id] = item.text;
	});
	return roleMap;
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