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
			        {field:'ip',title:'客户端IP',width:110,halign:'center',align:'left',sortable:true},
			        {field:'userAccount',title:'用户帐号',width:90,halign:'center'},
			        {field:'requestUrl',title:'访问URL',width:120,halign:'center',align:'left'},
			        {field:'requestMethod',title:'请求方法',width:70,halign:'center',align:'left'},
			        {field:'visitTime',title:'访问时间',width:110,halign:'center',align:'left',sortable:true},
			        {field:'updateDate',title:'更新时间',width:100,halign:'center',align:'left',sortable:true}
			        
			    ]]
			});
		},
		search: function() {
			$('#sysLogGridlist').datagrid('load', {
				'q[name]': $('#queryName','#sysLogGridToolbar').val(),
				'q[url]': $('#queryUrl','#sysLogGridToolbar').val()
			});
		},
		reset: function() {
			$('#sysLogQueryForm').form('clear');
		}
};