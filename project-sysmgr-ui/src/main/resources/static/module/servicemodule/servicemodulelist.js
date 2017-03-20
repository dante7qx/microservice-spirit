var ServiceModulePage = {
		loadServiceModuleList: function() {
			$('#serviceModuleGridlist').datagrid({
			    url:'servicemodule/query_page',
			    title: '服务模块列表',
			    rownumbers: true,
			    rownumberWidth: 20,
			    singleSelect: true,
			    fitColumns: true,
			    pagination: true,
			    pageSize: COMMON_CONFIG['PAGESIZE'],
			    pageList: COMMON_CONFIG['PAGELIST'],
			    toolbar: '#serviceModuleGridToolbar',
			    sortName: 'name',
			    sortOrder: 'desc',
			    remoteSort: false,
			    height: $(window).height() - 20,
			    frozenColumns: [[
                     {field:'ck',checkbox:true}
                ]],
			    columns:[[
			        {field:'name',title:'服务模块名称',width:130,halign:'center',align:'left',sortable:true,formatter:function(value,row,index) {
			        	return '<a class="bdhref" href="javascript:;" onclick="ServiceModulePage.editServiceModule('+row['id']+')">'+value+'</a>';
			        }},
			        {field:'url',title:'服务模块URL',width:180,halign:'center'},
			        {field:'updateUserName',title:'更新人',width:80,halign:'center',align:'left'},
			        {field:'updateDate',title:'更新时间',width:100,halign:'center',align:'left'}
			        
			    ]]
			});
		},
		search: function() {
			$('#serviceModuleGridlist').datagrid('load', {
				'q[name]': $('#queryName','#serviceModuleGridToolbar').val(),
				'q[url]': $('#queryUrl','#serviceModuleGridToolbar').val()
			});
		},
		reset: function() {
			$('#serviceModuleQueryForm').form('clear');
		},
		editServiceModule: function(id) {
			$('#serviceModuleWindow').show().window({
				title: (id ? '编辑服务模块' : '新增服务模块'),
				closed: false,
				cache: false,
				href: (id ? 'editservicemodule?id='+id : 'editservicemodule'),
				extractor: function(data) {
					return data;
				}	
			});
		},
		del: function() {
			var checkRow = $('#serviceModuleGridlist').datagrid('getChecked');
			if(checkRow.length == 0) {
				$.messager.alert('提示','请至少选择一条记录！');
				return;
			}
			$.messager.confirm('提示', '您确定要删除吗?', function(r){
				if (r){
					$.ajax({
						url: 'servicemodule/delete_by_id',
						type: 'post',
						data: {
							id: checkRow[0]['id']
						},
						success: function(result) {
							if(result['resultCode'] != COMMON_CONFIG['SUCCESS']) {
								$.messager.alert('错误','系统错误，请联系系统管理员', 'error');
								return;
							}
							$('#serviceModuleGridlist').datagrid('reload');
						}
					});
				}
			});
		}
};