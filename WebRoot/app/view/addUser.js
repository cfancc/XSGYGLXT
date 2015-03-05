Ext.define('XSGYGLXT.view.addUser', {
			extend : 'Ext.window.Window',
			alias : 'widget.addUser',
			title : '用户管理(新增)',
			width : 350,
			modal : true,
			layout : 'fit',
			border:false,
			titleAlign : 'center',
			items : {
				xtype : 'form',
				padding : '20 0 20 0',
				border : false,
				frame:true,
				defaultType : 'textfield',
				url : 'saveOrUpdateUserAction',
				defaults : {
					anchor : '90%',
					labelWidth : 80,
					labelAlign : 'right',
					allowBlank : false
				},
				items : [ {
							name : 'dlm',
							fieldLabel : '登录名'
						}, {
							name : 'mm',
							fieldLabel : '密码'
						}, {
							name : 'yhlx',
							fieldLabel : '用户类型'
						}, {
							name : 'lxfs',
							fieldLabel : '联系方式'
						}]
			},
			buttons : [{
						text : '保存'
					}, {
						text : '取消',
						listeners : {
							click : {
								fn : function(button) {
									button.up('window').close();
								}
							}
						}
					}]
		})
