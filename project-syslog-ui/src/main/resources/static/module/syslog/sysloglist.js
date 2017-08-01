var SysLogPage = {
		loadSysLogList: function() {
			$('#sysLogGridlist').datagrid({
			    url:'syslog/query_page',
			    title: '系统日志列表',
			    rownumbers: true,
			    rownumberWidth: 20,
			    singleSelect: true,
			    fitColumns: true,
			    pagination: true,
			    pageSize: COMMON_CONFIG['PAGESIZE'],
			    pageList: COMMON_CONFIG['PAGELIST'],
			    toolbar: '#sysLogGridToolbar',
			    sortName: 'visitTime',
			    sortOrder: 'desc',
			    remoteSort: false,
			    height: $(window).height() - 20,
			    columns:[[
			        {field:'ip',title:'客户端IP',width:90,halign:'center',align:'center',sortable:true},
			        {field:'userAccount',title:'用户帐号',width:90,halign:'center',sortable:true},
			        {field:'requestUrl',title:'访问URL',width:150,halign:'center',align:'left'},
			        {field:'requestMethod',title:'请求方法',width:70,halign:'center',align:'left'},
			        {field:'visitTime',title:'访问时间',width:100,halign:'center',align:'left',sortable:true},
			        {field:'updateDate',title:'更新时间',width:100,halign:'center',align:'left',sortable:true}
			        
			    ]]
			});
		},
		search: function() {
			$('#sysLogGridlist').datagrid('load', {
				'q[account]': $('#queryAccount','#sysLogGridToolbar').val(),
				'q[url]': $('#queryUrl','#sysLogGridToolbar').val(),
				'q[visitStartTime]': $('#visitStartTime','#sysLogGridToolbar').datebox('getValue'),
				'q[visitEndTime]': $('#visitEndTime','#sysLogGridToolbar').datebox('getValue')
			});
		},
		reset: function() {
			$('#sysLogQueryForm').form('clear');
		}
};