Ext
		.application( {
			name : 'XSGYGLXT',
			controllers : [ 'studentCtr', 'dormCtr', 'assignCtr' ],
			launch : function() {
				if (document.getElementById('login').value==1) {
					
					Ext
							.create(
									'Ext.container.Viewport',
									{
										layout : {
											type : 'border'
										},
										items : [
												{
													xtype : 'box',
													region : 'north',
													html : '<br/><div style="font-weight: bolder;font-size:25px;text-align:center;color:#000000">学生公寓管理系统</div><br/>'
												},
												{
													xtype : 'treepanel',
													title : '功能选择',
													region : 'west',
													width : 150,
													store : Ext
															.create(
																	'Ext.data.TreeStore',
																	{
																		root : {
																			expanded : true,
																			children : [
																					{
																						text : "学生信息管理",
																						leaf : true
																					},
																					{
																						text : "宿舍信息管理",
																						leaf : true
																					},
																					{
																						text : "学生宿舍分配管理",
																						leaf : true
																					} ]
																		}
																	}),
													rootVisible : false,
													listeners : {
														'itemclick' : function(
																treeview,
																record, item,
																index) {
															treeview
																	.up(
																			'viewport')
																	.down(
																			'container[region=center]')
																	.getLayout()
																	.setActiveItem(
																			index);
														}
													},
													dockedItems : [ {
														xtype : 'toolbar',
														dock : 'bottom',
														layout : {
															pack : 'center'
														},
														items : [
																{
																	text : '用户管理',
																	icon : 'images/report_user.png',
																	handler : function() {
																		var win = Ext
																				.widget('userWin');
																		win
																				.show();
																	}
																},
																{
																	text : '注销',
																	icon : 'images/user_go.png',
																	handler : function() {
																		Ext.Ajax
																				.request( {
																					url : 'logoutAction',// 这里url为投票
																					method : 'GET',
																					async : false,
																				});
																		document.getElementById('login').setAttribute("value", 0);
																		window.location.href = "index.jsp";
																	}
																} ]
													} ]
												}, {
													xtype : 'container',
													region : 'center',
													layout : {
														type : 'card'
													},
													items : [ {
														xtype : 'studentPanel'
													}, {
														xtype : 'dormPanel'
													}, {
														xtype : 'assignPanel'
													} ]
												} ]
									});
				} else {
					Ext.Msg.alert("提示", "尚未登录!");
					window.location.href = "index.jsp";
				}
			}
		});