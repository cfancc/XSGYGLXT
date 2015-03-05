Ext.define('XSGYGLXT.view.userWin', {
	extend : 'Ext.window.Window',
	alias : 'widget.userWin',
	title : '用户管理',
	icon : 'images/report_user.png',
	modal : true,
	width : 450,
	height : 300,
	layout : 'fit',
	border:false,
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [{
				xtype : 'gridpanel',
				autoScroll : true,
				store : 'userStore',
				columns : [ {
							xtype : 'gridcolumn',
							dataIndex : 'dlm',
							text : '登录名',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'mm',
							text : '密码',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'yhlx',
							text : '用户类型',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'lxfs',
							text : '联系方式',
							flex : 1
						}],
				dockedItems : [{
					xtype : 'toolbar',
					dock : 'top',
					items : [{
						xtype : 'button',
						text : '新增',
						icon:'images/user_add.png',
						handler : function(button) {
							var win = Ext.widget('addUser');
							win.down('button[text=保存]').setHandler(
									function(but) {
										var form = but.up('window')
												.down('form').getForm();
										if (form.isValid()) {
											form.submit({
												success : function(form, action) {
													button.up('grid')
															.getStore()
															.reload();
													win.close();
													Ext.Msg.alert('成功',
															action.result.msg);
												},
												failure : function(form, action) {
													Ext.Msg.alert('失败',
															action.result.msg);
												}
											});
										}
									});
								win.show();
						}
					}, {
						xtype : 'button',
						text : '修改',
						icon:'images/user_edit.png',
						handler : function(button) {
							if (button.up('grid').getSelectionModel()
									.hasSelection()) {
								var win = Ext.create('widget.addUser');
								win.title='用户管理(修改)';
								var record = button.up('grid')
										.getSelectionModel().getSelection()[0];
								win.down('form').loadRecord(record);
								win.down('button[text=保存]').on('click',
										function(but) {
											var form = but.up('window')
													.down('form').getForm();
											if (form.isValid()) {
												form.submit({
													params : {
														yhid : record.data.yhid
													},
													success : function(form,
															action) {
														button.up('grid')
																.getStore()
																.reload();
														win.close();
														Ext.Msg
																.alert(
																		'成功',
																		action.result.msg);
													},
													failure : function(form,
															action) {
														Ext.Msg
																.alert(
																		'失败',
																		action.result.msg);
													}
												});
											}
										})
								win.show();
							} else {
								Ext.Msg.alert("提示", "请先选择一条记录！");
							}
						}
					}, {
						xtype : 'button',
						text : '删除',
						icon:'images/user_delete.png',
						handler : function(button) {
							if (button.up('grid').getSelectionModel()
									.hasSelection()) {
								Ext.Msg.confirm('提示', '确定删除所选中的用户吗?', function(
										btn) {
									if (btn == 'yes') {
										var yhid = button.up('grid')
												.getSelectionModel()
												.getSelection()[0].data.yhid;
										var store = button.up('grid')
												.getStore();
										Ext.Ajax.request({
											url : 'deleteUserAction',
											params : {
												yhid : yhid
											},
											success : function(response, opts) {
												store.load();
												var rt = Ext
														.decode(response.responseText);
												Ext.Msg.alert("提示", rt.msg);
											},
											failure : function(response, opts) {
												Ext.Msg.alert("提示", "服务器错误！");
											}
										})
									}
								})
							} else {
								Ext.Msg.alert("提示", "请先选择一条记录！");
							}

						}
					}]
				}]
			}]
		});
		me.callParent(arguments);
	}
});