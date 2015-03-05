Ext.define('XSGYGLXT.view.assignPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.assignPanel',
	title : '学生宿舍分配',
	layout : {
		type : 'hbox',
		align : 'stretch'
	},
	defaults : {
		margins : 5
	},
	addarray : [],
	delarray : [],
	resetarray : [],
	initComponent : function() {
		var me = this;
		var zdrs;
		var ssid;
		Ext.applyIf(me, {
			buttons : [{
				xtype : 'button',
				text : '保存',
				handler : function(button) {
					console.log(me.addarray);
					console.log(me.delarray);
					Ext.Ajax.request({
								url : 'saveAssignAction',
								method : 'POST',
								jsonData : {
									ssid : me.ssid,
									addarray : me.addarray,// 将这里面的学生标记为已有宿舍
									delarray : me.delarray // 将这里面的学生标记为没有宿舍
								},
								success : function(response, opts) {
									var rt = Ext.decode(response.responseText);
									Ext.Msg.alert("提示", rt.msg);
									var store1 = button.up('assignPanel')
									.down('gridpanel[title=未分配学生]').getStore();
									var store2 = button.up('assignPanel')
									.down('gridpanel[title=宿舍列表]').getStore();
									store1.reload();
									store2.reload();
									me.addarray = [];
									me.delarray = [];
								},
								failure : function(response, opts) {
									Ext.Msg.alert("提示", "服务器错误！");
								}
							})
				}
			}, {
				xtype : 'button',
				text : '放弃调整',
				handler : function(button) {
					var store1 = button.up('assignPanel')
							.down('gridpanel[title=未分配学生]').getStore();
					var store2 = button.up('assignPanel')
							.down('gridpanel[title=宿舍列表]').getStore();
					store1.reload();
					store2.reload();
					me.addarray = [];
					me.delarray = [];
				}
			}],
			items : [{
				xtype : 'gridpanel',
				title : '未分配学生',
				store : new Ext.data.Store({
							fields : ['xsid', 'xm', 'xh', 'zy', 'sfyyss'],
							autoLoad:true,
							proxy : {
								url : 'queryUnStudentAction',
								type : 'ajax',
								reader : {
									type : 'json',
									root : 'unstudentlist'
								}
							}
						}),
				flex : 1,
				autoScroll : true,
				viewConfig : {
					plugins : {
						ptype : 'gridviewdragdrop',
						dragGroup : 'group1',
						dropGroup : 'group2'
					},
					listeners : {
						beforedrop : function(node, data, overModel,
								dropPosition, dropHandlers) {
							// 暂缓执行
							dropHandlers.wait = true;
							if (this.up('assignPanel')
									.down('gridpanel[title=宿舍列表]')
									.down('combo[fieldLabel=宿舍编号]').value) {
								if (!Ext.Array.contains(me.resetarray,
										data.records[0].get('xsid'))){
									//如果不属于原来的集合，就删了add列表中的值
									console.log("我本来就在左边！");
									Ext.Array
									.remove(me.addarray,
											data.records[0]
													.get('xsid'));
										
//									this.up('assignPanel')
//									.down('gridpanel[title=未分配学生]').getStore().getAt(index);
									dropHandlers.processDrop();
									this.up('assignPanel')
									.down('gridpanel[title=未分配学生]').getStore().findRecord('xsid',data.records[0].get('xsid')).set('sfyyss','否');
								}else{
									//不然就添加到del列表里
									me.delarray.push(data.records[0].get('xsid'));
									//顺便删了add列表中值（如果存在的话）
									if (Ext.Array.contains(me.addarray,
											data.records[0].get('xsid'))) {
										Ext.Array
												.remove(me.addarray,
														data.records[0]
																.get('xsid'));
									}
									dropHandlers.processDrop();
									this.up('assignPanel')
									.down('gridpanel[title=未分配学生]').getStore().findRecord('xsid',data.records[0].get('xsid')).set('sfyyss','否');
								}
							} else {
								dropHandlers.cancelDrop();
								Ext.Msg.alert('提示', '请先选择宿舍!');
							}

						}
					}
				},
				columns : [{
					xtype : 'gridcolumn',
					dataIndex : 'xsid',
					text : 'ID',
					flex : 1
				},{
							xtype : 'gridcolumn',
							dataIndex : 'xm',
							text : '姓名',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'xh',
							text : '学号',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'zy',
							text : '专业',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'sfyyss',
							text : '是否已有宿舍',
							flex : 1,
							renderer : function(value) {
								if (value == '否') {
									value = '<span style="color:red;">' + value
											+ '</span>'
								} else if (value == '是') {
									value = '<span style="color:green;">'
											+ value + '</span>'
								}
								return value;
							}
						}],
				tbar : [{
							xtype : 'combo',
							fieldLabel : '是否已有宿舍',
							displayField : 'sfyyss',
							value:'否',
							hidden:true,
							valueField : 'sfyyss',
							store : new Ext.data.Store({
										fields : ['sfyyss'],
										data : [{
													'sfyyss' : '所有'
												}, {
													'sfyyss' : '是'
												}, {
													'sfyyss' : '否'
												}]
									}),
							listeners : {
								select : function(combo) {
									combo.up('grid').getStore().load({
												params : {
													sfyyss : combo.getValue()
												}
											});
								}
							},
//							labelWidth : 80,
//							labelAlign : 'right',
//							width : 160
						}, '->',{
							xtype : 'textfield',
							fieldLabel : '查找学生信息',
							labelWidth : 90,
							labelAlign : 'right',
							width : 200
						}, {
							xtype : 'button',
							icon : 'images/zoom.png',
						}]
			}, {
				xtype : 'gridpanel',
				title : '宿舍列表',
				store : new Ext.data.Store({
							fields : ['xsid', 'xm', 'xh', 'zy', 'ssid'],
							proxy : {
								url : 'queryUnDormAction',
								type : 'ajax',
								reader : {
									type : 'json',
									root : 'undormlist'
								}
							}
						}),
				flex : 1,
				autoScroll : true,
				viewConfig : {
					plugins : {
						ptype : 'gridviewdragdrop',
						dragGroup : 'group2',
						dropGroup : 'group1'
					},
					listeners : {
						beforedrop : function(node, data, overModel,
								dropPosition, dropHandlers, eOpts) {
							// 暂缓执行
							dropHandlers.wait = true;
							if (this.up('grid').down('combo[fieldLabel=宿舍编号]').value) {
								// 判断条件
								if (this.up('grid').getStore().count() >=me.zdrs) {
									dropHandlers.cancelDrop();
									Ext.Msg.alert('提示', '该宿舍已满员!');
								} else {
									if (Ext.Array.contains(me.resetarray,
											data.records[0].get('xsid'))){
										//如果属于原来的集合，就删了del列表中的值
										console.log("我本来就在右边！");
										Ext.Array
										.remove(me.delarray,
												data.records[0]
														.get('xsid'));
										dropHandlers.processDrop();
										this.up('assignPanel')
										.down('gridpanel[title=宿舍列表]').getStore().findRecord('xsid',data.records[0].get('xsid')).set('sfyyss','是');
									}else{
										//不然就添加到add列表里
										me.addarray.push(data.records[0].get('xsid'));
										//顺便删了del列表中值（如果存在的话）
										if (Ext.Array.contains(me.delarray,
												data.records[0].get('xsid'))) {
											Ext.Array
													.remove(me.delarray,
															data.records[0]
																	.get('xsid'));
										}
										dropHandlers.processDrop();
										this.up('assignPanel')
										.down('gridpanel[title=宿舍列表]').getStore().findRecord('xsid',data.records[0].get('xsid')).set('sfyyss','是');
									}
								}
							} else {
								dropHandlers.cancelDrop();
								Ext.Msg.alert('提示', '请先选择宿舍!');
							}

						}
					}
				},
				columns : [{
							xtype : 'gridcolumn',
							dataIndex : 'xm',
							text : '姓名',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'xh',
							text : '学号',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'zy',
							text : '专业',
							flex : 1
						}, {
							xtype : 'gridcolumn',
							dataIndex : 'sfyyss',
							hidden:true,
							text : '是否已有宿舍',
							flex : 1
						}],
				dockedItems : [{
					xtype : 'toolbar',
					dock : 'top',
					defaults : {
						labelWidth : 60,
						labelAlign : 'right',
						width : 130
					},
					items : [{
						xtype : 'combo',
						fieldLabel : '宿舍园区',
						displayField : 'ssyq',
						valueField : 'ssyq',
						store : new Ext.data.Store({
									fields : ['ssyq'],
									data : [{
												'ssyq' : '恬园'
											}, {
												'ssyq' : '憬园'
											}, {
												'ssyq' : '怡园'
											}]
								}),
						listeners : {
							select : function(combo, records, options) {
								combo.nextNode().clearValue();
								combo.nextNode().nextNode().clearValue();
								combo.nextNode().nextNode().nextNode()
										.clearValue();
								combo.nextNode().getStore().load({
											params : {
												ssyq : combo.getValue()
											}
										});
							}
						}
					}, {
						xtype : 'combo',
						fieldLabel : '宿舍楼栋',
						displayField : 'ssld',
						queryMode:'local',
						valueField : 'ssld',
						store : new Ext.data.Store({
									fields : ['ssld'],
									proxy : {
										url : 'queryLouDongAction',
										type : 'ajax',
										reader : {
											type : 'json',
											root : 'ssldlist'
										}
									}
								}),
						listeners : {
							select : function(combo, records, options) {
								combo.nextNode().clearValue();
								combo.nextNode().nextNode().clearValue();
								combo.nextNode().getStore().load({
											params : {
												ssyq : combo.previousNode()
														.getValue(),
												ssld : combo.getValue()
											}
										});
							}
						}
					}, {
						xtype : 'combo',
						fieldLabel : '宿舍楼层',
						displayField : 'sslc',
						queryMode:'local',
						valueField : 'sslc',
						store : new Ext.data.Store({
									fields : ['sslc'],
									proxy : {
										url : 'queryLouCengAction',
										type : 'ajax',
										reader : {
											type : 'json',
											root : 'sslclist'
										}
									}
								}),
						listeners : {
							select : function(combo, records, options) {
								combo.nextNode().clearValue();
								combo.nextNode().getStore().load({
									params : {
										ssyq : combo.previousNode()
												.previousNode().getValue(),
										ssld : combo.previousNode().getValue(),
										sslc : combo.getValue()
									}
								});
							}
						}
					}, {
						xtype : 'combo',
						fieldLabel : '宿舍编号',
						displayField : 'ssbh',
						queryMode:'local',
						valueField : 'ssid',
						store : new Ext.data.Store({
									fields : ['ssbh', 'ssid','zdrs'],
									proxy : {
										url : 'queryBianHaoAction',
										type : 'ajax',
										reader : {
											type : 'json',
											root : 'ssbhlist'
										}
									}
								}),
						listeners : {
							select :function(combo,records){
								combo.up('grid').getStore().load({
									params:{
										ssid:records[0].get('ssid'),
									},
									callback:function(){
										me.zdrs=records[0].get('zdrs');
										me.ssid=records[0].get('ssid');
										console.log('最大人数：'+me.zdrs);
										console.log('宿舍id：'+me.ssid);
									}
								});
							},
							render : function(combo) {
								combo.up('grid').getStore().on('load',
										function() {
											me.resetarray = combo.up('grid')
													.getStore().collect('xsid');
											console.log(me.resetarray);
										})
							}
						}
					}]
				}]
			}]
		});
		me.callParent(arguments);
	}
});