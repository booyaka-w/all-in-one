var $table = $('#table');
$(function() {
	$table.bootstrapTable({
		url : '/system/menu/page/data',
		toolbar : '#toolbar',
		showButtonText : true,
		showRefresh : true,
		cache: false,
		buttonsClass:'outline-info',
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
		onPostBody : function() {
			$table.treegrid({
				treeColumn : 0,
				initialState: 'collapsed',
			});
		}
	})
})

/**
 * 搜索
 */
$('#search').click(function() {
	$table.bootstrapTable('refresh')
})

/**
 * 刷新
 */
$('#refresh').click(function() {
	$('#toolbar').find('select[name]').each(function() {
		$(this).val('')
	})
	$table.bootstrapTable('refresh')
})

/**
 * 时间控件
 */
laydate.render({
	elem: '#time',
	/*type: 'date',*/
	range:'~'
});