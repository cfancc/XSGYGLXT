Ext.onReady(function() {
			Ext.create('Ext.window.Window', {
				title : '学生公寓管理系统登录入口',
				width : 300,
				modal : true,
				layout : 'fit',
				border : false,
				closable : false,
				resizable:false,
				titleAlign : 'center',
				items : {
					xtype : 'form',
					padding : '20 0 20 0',
					border : false,
					frame : true,
					defaultType : 'textfield',
					url : '',
					defaults : {
						anchor : '90%',
						labelWidth : 75,
						labelAlign : 'right',
						allowBlank : false
					},
					items : [{
								xtype : 'textfield',
								name : 'dlm',
								fieldLabel : '账号'
							}, {
								xtype : 'textfield',
								name : 'mm',
								fieldLabel : '密码',
								inputType : 'password'
							}]
				},
				buttons : [{
					text : '登录',
					listeners : {
						click : {
							fn : function(button) {
								console.log('登录');
								if (button.up('window').down('form').isValid()) {
									var dlm = button.up('window')
											.down('textfield[name=dlm]')
											.getValue();
									var mm = button.up('window')
											.down('textfield[name=mm]')
											.getValue();
									Ext.Ajax.request({
												url : 'loginAction',//
												method : 'GET',
												params : {
													dlm : dlm,
													mm : mm
												},
												success : function(res, o) {
													var rt = Ext.JSON
															.decode(res.responseText);
													Ext.Msg.alert("提示", rt.msg);
													window.location.href = "app.jsp";
												},
												failure : function(res, o) {
													var rt = Ext.JSON
															.decode(res.responseText);
													Ext.Msg.alert("提示", rt.msg);
												}
											});
								} else {
									Ext.Msg.alert("提示", "用户名或密码无效!");
								}

							}
						}
					}
				}, {
					text : '重置',
					handler : function(button) {
						button.up('window').down('form').getForm().reset();
					}
				}]
			}).show();
		});
