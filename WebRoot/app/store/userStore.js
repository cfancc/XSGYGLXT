Ext.define('XSGYGLXT.store.userStore', {
			extend : 'Ext.data.Store',
			fields : [{
						name : 'yhid'
					}, {
						name : 'dlm'
					}, {
						name : 'mm'
					}, {
						name : 'yhlx'
					}, {
						name : 'lxfs'
					}],
			autoLoad : true,
			constructor : function(cfg) {
				var me = this;
				cfg = cfg || {};
				me.callParent([Ext.apply({
							proxy : {// store的代理
								type : 'ajax', // 类型为ajax，异步请求数据
								api : {
									read : 'queryUserAction'// 读取数据的url
								},
								reader : {
									type : 'json', // 解析数据的类型，这里认为从后台来的数据是json类型
									root : 'userlist',// json的root
									totalProperty : 'totalSize'
								}
							}
						}, cfg)]);
			}
		});