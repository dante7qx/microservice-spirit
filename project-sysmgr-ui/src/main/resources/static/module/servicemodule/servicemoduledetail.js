var ServiceModuleDetailPage = {
		SUBMIT_FLAG: false,
		paramServiceModuleId: '',
		initPage: function(paramServiceModuleId) {
			this.paramServiceModuleId = paramServiceModuleId;
			this.loadData();
		},
		loadData: function() {
			if(this.paramServiceModuleId) {
				hnasys.util.controlFormBtn(false, 'serviceModuleFormBtnContainer');
				this.loadDataById(false);
			} else {
				hnasys.util.controlFormBtn(true, 'serviceModuleFormBtnContainer');
				$('#serviceModuleDetailForm').form('clear');
			}
		},
		loadDataById: function(editable) {
			$.ajax({
				url: 'servicemodule/query_by_id',
				type: 'post',
				data: {
					id: ServiceModuleDetailPage.paramServiceModuleId
				},
				success: function(result) {
					if(result['resultCode'] != COMMON_CONFIG['SUCCESS']) {
						$.messager.alert('错误','系统错误，请联系系统管理员', 'error');
						return;
					}
					$('#serviceModuleDetailForm').form('clear').form('load', result['data']);
					ServiceModuleDetailPage.loadUpdateInfo(result['data']);
					setTimeout(function() {
						hnasys.util.isEditForm('serviceModuleDetailForm', editable);
					}, 0);
				}
			});
		},
		loadUpdateInfo: function(data) {
			var updateUserName = data['updateUserName'] ? data['updateUserName'] : '';
			var updateDate = data['updateDate'] ? data['updateDate'] : '';
			$('#serviceModuleUpdateInfo').text(updateUserName + ' ' + updateDate);
		},
		submit: function() {
			$('#serviceModuleDetailForm').form('submit', {
				iframe: false,
			    url: 'servicemodule/update_servicemodule',
			    success:function(result){
			    	var result = eval('(' + result + ')');
			    	if(result['resultCode'] == COMMON_CONFIG['SUCCESS']) {
			        	$('#serviceModuleGridlist').datagrid('reload');
			        	$('#serviceModuleWindow').window('close');
			        } else {
			        	$.messager.alert('错误','系统错误，请联系系统管理员', 'error');
			        }
			    }
			});
		},
		reset: function() {
			if(this.paramServiceModuleId) {
				this.loadDataById(true);
			} else {
				$('#serviceModuleDetailForm').form('clear');
			}
		},
		del: function() {
			$.messager.confirm('提示', '您确定要删除吗?', function(r){
				if (r){
					$.ajax({
						url: 'servicemodule/delete_by_id',
						type: 'post',
						data: {
							id: $('#id','#serviceModuleDetailForm').val()
						},
						success: function(result) {
							if(result['resultCode'] != COMMON_CONFIG['SUCCESS']) {
								$.messager.alert('错误','系统错误，请联系系统管理员', 'error');
								return;
							}
							$('#serviceModuleGridlist').datagrid('reload');
				        	$('#serviceModuleWindow').window('close');
						}
					});
				}
			});
		},
		edit: function() {
			hnasys.util.controlFormBtn(true, 'serviceModuleFormBtnContainer');
			hnasys.util.isEditForm('serviceModuleDetailForm', true);
		}
};