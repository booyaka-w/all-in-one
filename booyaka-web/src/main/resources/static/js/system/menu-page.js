var $table = $('#table');
$(function() {
	$table.bootstrapTable({
		url : '/system/menu/page/data',
		toolbar : '#toolbar',
		showColumns : true,
		showButtonIcons : true,
		showButtonText : true,
		showRefresh : true,
		showToggle: false,
		cache: false,
		detailView : false,
		detailViewIcon: false,
		detailViewByClick: false,
		buttonsClass:'info',
		idField : 'id',
		treeShowField : 'name',
		parentIdField : 'pid',
		columns : [{
			field : 'name',
			title : '名称',
			width : 200
		},{
			field : 'subordinate',
			title : '所属平台',
			width : 200
		},{
			field : 'path',
			title : '权限值',
			width : 200
		},{
			field : 'icon',
			title : 'ICON',
			width : 200
		},{
			field : 'type',
			title : '类型',
			width : 100,
			formatter : function(value, row, index) {
				if (value === 1) {
					return '<span class="text-primary">模块</span>'
				}
				if (value === 2) {
					return '<span class="text-success">菜单</span>'
				}
				if (value === 3) {
					return '<span class="text-info">按钮</span>'
				}
			}
		},{
			field : 'status',
			title : '状态',
			align : 'center',
			width : 100,
			formatter : function(value, row, index) {
				if(value===1){
					return "<div class=\"custom-control custom-switch\"><input type=\"checkbox\" checked=\"checked\" class=\"custom-control-input\" id="+index+" onchange=\"updateMenuStatus(this,'"+row.id+"','"+row.version+"')\"><label class=\"custom-control-label\" for="+index+"></label></div>"
				}
				if(value===2){
					return "<div class=\"custom-control custom-switch\"><input type=\"checkbox\" class=\"custom-control-input\" id="+index+" onchange=\"updateMenuStatus(this,'"+row.id+"','"+row.version+"')\"><label class=\"custom-control-label\" for="+index+"></label></div>"
				}
			}
		},{
			title : '操作',
			align : 'center',
			width : 150,
			formatter : function(value, row, index) {
				if (row.type === 1) {
					return "<a class=\"text-primary\" href=\"javascript:void(0)\" onclick=\"editModal('"+row.id+"')\">编辑模块</a> | <a class=\"text-success\" href=\"javascript:void(0)\" onclick=\"menuModal('"+row.id+"')\">添加菜单</a>"
				}
				if (row.type === 2) {
					return "<a class=\"text-success\" href=\"javascript:void(0)\" onclick=\"editMenu('"+row.id+"')\">编辑菜单</a> | <a class=\"text-info\" href=\"javascript:void(0)\" onclick=\"buttonModal('"+row.id+"')\">添加按钮</a>"
				}
				if (row.type === 3) {
					return "<a class=\"text-info\" href=\"javascript:void(0)\" onclick=\"editButton('"+row.id+"')\">编辑按钮</a>"
				}
			}
		} ],
		detailFormatter: function (index, row) {
			var html = []
		    $.each(row, function (key, value) {
		      html.push('<p><b>' + key + ':</b> ' + value + '</p>')
		    })
		    return html.join('')
	    },
		queryParams : function queryParams(params) {
			var params = {}
			$('#toolbar').find('select[name]').each(function() {
				params[$(this).attr('name')] = $(this).val()
			})
			return params;
		},
		// 如果回调只是获取行数据，做一些逻辑，不涉及表格更新操作，可以使用onPostBody()，否则要使用onLoadSuccess()
		onLoadSuccess : function() {
			$table.treegrid({
				treeColumn : 0,
				initialState: 'collapsed',
			});
		}
	})
})
/**
 * 搜索
 * 
 * @returns
 */
$('#search').click(function() {
	$table.bootstrapTable('refresh')
})
/**
 * 刷新
 * 
 * @returns
 */
$('#refresh').click(function() {
	$('#toolbar').find('select[name]').each(function() {
		$(this).val('')
	})
	$table.bootstrapTable('refresh')
})

/**
 * 显示模块模态框
 * 
 * @returns
 */
function showModal(){
	$("#modelTitle").text("添加模块");
	$("#modelId").val("");
	$("#modelSubordinate").removeAttr("disabled");
	$('#model').modal('show');
}
/**
 * 显示菜单模态框
 * 
 * @returns
 */
function menuModal(parentId){
	$("#menuTitle").text("添加菜单");
	$("#menuId").val("");
	$("#menuParentId").val(parentId);
	$("#menu").modal('show');
}
/**
 * 显示按钮模态框
 * 
 * @returns
 */
function buttonModal(parentId){
	$("#buttonTitle").text("添加按钮");
	$("#buttonId").val("");
	$("#buttonParentId").val(parentId);
	$('#button').modal('show');
}

/**
 * 关闭模态框
 * 
 * @param modal
 *            模态框
 * @param modalFrom
 *            表单
 * @returns
 */
function hideModal(modal,modalFrom){
	$(modalFrom)[0].reset();
	$(modal).modal('hide')
}

/**
 * 保存数据
 * 
 * @param modal
 *            模态框
 * @param modalFrom
 *            表单
 * @param obj
 *            当前对象
 * @returns
 */
function saveModal(modal,modalFrom,obj){
	var status = $(modalFrom).parsley().validate();
	if(status){
		var object= {
            url:"/menu/saveOrUpdate",
　　 		type:"post",
　　  		restForm:true,
　　  		timeout:6000,
    		beforeSend: function () {
		        $(obj).attr({ disabled: "disabled" });
		    },
　　　　　　  success:function(data){
		  		if(data.success){
	　　　　　　  		$(modalFrom)[0].reset();
	　　　　　　　　　　  $(modal).modal('hide');
	　　　　　　　　　　  $table.bootstrapTable('refresh');
		　　  		}else{
		　　  			Messenger().post({
		　　  				message:data.msg,
		    				type: 'error',
		    				showCloseButton: true
		　　  			});
		　　  		}
　　  		 	},
　　　　　　  complete: function (jqXHR) {
				$(obj).removeAttr("disabled");
		    },
		}
		$(modalFrom).ajaxSubmit(object);
	}
}

/**
 * 修改状态
 * 
 * @param obj
 *            当前对象
 * @param id
 *            主键ID
 * @param version
 *            版本号
 * @returns
 */
function updateMenuStatus(obj,menuId,version){
	var status = $(obj).is(':checked')?1:2;
	$.ajax({
		url:"/menu/saveOrUpdate",
		type:"post",
		data:{menuId:menuId,menuStatus:status,version:version},
		beforeSend: function () {
			$(obj).attr({ disabled: "disabled" });
	    },
　　  	success:function(data){
	　　  	if(data.success){
	　　  		$table.bootstrapTable('refresh');
	　　  	}else{
	　　  		Messenger().post({
	　　  			message:data.msg,
    				type: 'error',
    				showCloseButton: true
	　　  		});
		　　  }
		},
　　  	complete: function () {
			$(obj).removeAttr("disabled");
	    },
	});
}
/**
 * 模块编辑
 * 
 * @param menuId
 *            主键ID
 * @returns
 */
function editModal(menuId){
	$.ajax({
		url:"/menu/queryMenuById",
		type:"get",
		data:{menuId:menuId},
	　　 success:function(data){
	　　  	if(data.success){
	　　  		var menu = data.attributes.sysMenu;
	　　  		$('#modelId').val(menu.menuId);
	　　  		$('#modelVersion').val(menu.version);
	　　  		$('#modelName').val(menu.menuName);
	    		$('#modelIcon').val(menu.menuIcon);
	    		$("#modelSubordinate option").each(function(){
	    			if($(this).val() == menu.menuSubordinate){
			    	    $(this).attr("selected","selected");
			        }
			    });
				$("#modelSubordinate").attr("disabled","disabled");
	    		$("#modelStatus option").each(function(){
			        if($(this).val() == menu.menuStatus){
			    	    $(this).attr("selected","selected");
			        }
			    });
	    		$("#modelTitle").text("编辑模块");
	    		$('#modal').modal('show');
	　　  	}else{
	　　  		Messenger().post({
	　　  			message:data.msg,
    				type: 'error',
    				showCloseButton: true
	　　  		});
	　　  	}
		},
	});
}

/**
 * 模块菜单
 * 
 * @param menuId
 *            主键ID
 * @returns
 */
function editMenu(menuId){
	$.ajax({
		url:"/menu/queryMenuById",
		type:"get",
		data:{menuId:menuId},
　　  	success:function(data){
	　　  	if(data.success){
	　　  		var menu = data.attributes.sysMenu;
	　　  		$('#menuId').val(menu.menuId);
	　　  		$('#menuVersion').val(menu.version);
	　　  		$('#menuParentId').val(menu.parentId);
	　　  		$('#menuName').val(menu.menuName);
	    		$('#menuIcon').val(menu.menuIcon);
	    		$("#menuStatus option").each(function(){
			        if($(this).val() == menu.menuStatus){
			    	    $(this).attr("selected","selected");
			        }
			    });
	    		$('#menuPath').val(menu.menuPath);
	    		$("#menuTitle").text("编辑菜单");
	    		$('#menu').modal('show');
	　　  	}else{
	　　  		Messenger().post({
	　　  			message:data.msg,
    				type: 'error',
    				showCloseButton: true
	　　  		});
	　　  	}
		},
	});
}

/**
 * 模块按钮
 * 
 * @param menuId
 *            主键ID
 * @returns
 */
function editButton(menuId){
	$.ajax({
		url:"/menu/queryMenuById",
		type:"get",
		data:{menuId:menuId},
　　  	success:function(data){
	　　  	if(data.success){
	　　  		var menu = data.attributes.sysMenu;
	　　  		$('#buttonId').val(menu.menuId);
	　　  		$('#buttonVersion').val(menu.version);
	　　  		$('#buttonParentId').val(menu.parentId);
	　　  		$('#buttonName').val(menu.menuName);
	    		$("#buttonStatus option").each(function(){
			        if($(this).val() == menu.menuStatus){
			    	    $(this).attr("selected","selected");
			        }
			    });
	    		$('#resource').val(menu.resource);
	    		$('#buttonPath').val(menu.menuPath);
	    		$("#buttonTitle").text("编辑按钮");
	    		$('#button').modal('show');
	　　  	}else{
	　　  		Messenger().post({
	　　  			message:data.msg,
    				type: 'error',
    				showCloseButton: true
	　　  		});
	　　  	}
		},
	});
}
