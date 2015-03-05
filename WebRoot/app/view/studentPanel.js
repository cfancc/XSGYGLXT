Ext.define('XSGYGLXT.view.studentPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.studentPanel',
	title : '学生信息管理',
	layout : 'fit',
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [{
						xtype : 'gridpanel',
						autoScroll : true,
						store : 'studentStore',
						columns : [{
									xtype : 'gridcolumn',
									dataIndex : 'xm',
									text : '姓名',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'xh',
									text : '学号',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'zy',
									text : '专业',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'bj',
									text : '班级',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'lxfs',
									text : '联系方式',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'sfyyss',
									text : '是否已有宿舍',
									flex:1,
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
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'ssss',
									text : '所属宿舍',
									flex:1.5
								}],
						dockedItems : [{
									xtype : 'toolbar',
									dock : 'top',
									items : [{
												xtype : 'button',
												text : '新增',
												icon:'images/user_add.png'
											}, {
												xtype : 'button',
												text : '修改',
												icon:'images/user_edit.png'
											}, {
												xtype : 'button',
												text : '删除',
												icon:'images/user_delete.png'
											}, {
												xtype : 'textfield',
												fieldLabel : '查找学生信息',
												labelWidth : 90,
												labelAlign : 'right',
												width : 200
											}, {
												xtype : 'button',
												icon:'images/zoom.png'
//												iconCls : 'x-form-search-trigger'
											}]
								}]
					}]
		});
		me.callParent(arguments);
	}
});