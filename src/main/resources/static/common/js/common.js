$(function(){
	var $tree = $('#nav-list');
	if($tree.length == 0){return;}
	var $treeWrap = $tree.parent();
	var winHei = $(window).height();
	$treeWrap.css('position', 'absolute')
		.width(180).height(winHei)
		
	$.ajax({
		url:'/home/menu'
	}).success(function(backdata){
		var treeData = backdata && backdata.data;
		$tree.treeview({
			data: treeData,
			levels: 10,
			collapseIcon:'glyphicon glyphicon-chevron-up',
			expandIcon:'glyphicon glyphicon-chevron-down',
			enableLinks:true,
			highlightSelected:false
		});
	});
});