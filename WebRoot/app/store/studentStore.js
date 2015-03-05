Ext.define('XSGYGLXT.store.studentStore', {
			extend : 'Ext.data.Store',
			fields : [{
						name : 'xsid'
					}, {
						name : 'xm'
					}, {
						name : 'xh'
					}, {
						name : 'zy'
					}, {
						name : 'bj'
					}, {
						name : 'lxfs'
					}, {
						name : 'sfyyss'
					}, {
						name : 'ssss'
					}],
			autoLoad : true,
			constructor : function(cfg) {
				var me = this;
				cfg = cfg || {};
				me.callParent([Ext.apply({
							proxy : {// store的代理
								type : 'ajax', // 类型为ajax，异步请求数据
								api : {
									read : 'queryStudentAction'// 读取数据的url
								},
								reader : {
									type : 'json', // 解析数据的类型，这里认为从后台来的数据是json类型
									root : 'studentlist',// json的root
									totalProperty : 'totalSize'
								}
							}
						}, cfg)]);
			}
		});