Ext.define('XSGYGLXT.view.dormPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.dormPanel',
	title : '宿舍信息管理',
	layout : 'fit',
	initComponent : function() {
		var me = this;
		Ext.applyIf(me, {
			items : [{
						xtype : 'gridpanel',
						autoScroll : true,
						store : 'dormStore',
						columns : [{
									xtype : 'gridcolumn',
									dataIndex : 'ssbh',
									text : '宿舍编号',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'ssyq',
									text : '宿舍园区',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'ssld',
									text : '宿舍楼栋',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'sslc',
									text : '宿舍楼层',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'ssbz',
									text : '宿舍备注',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'zdrs',
									text : '最大人数',
									flex:1
								}, {
									xtype : 'gridcolumn',
									dataIndex : 'sfym',
									text : '是否满员',
									flex:1
								}],
						dockedItems : [{
									xtype : 'toolbar',
									dock : 'top',
									items : [{
												xtype : 'button',
												text : '新增',
												icon:'images/group_add.png'
											}, {
												xtype : 'button',
												text : '修改',
												icon:'images/group_edit.png'
											}, {
												xtype : 'button',
												text : '删除',
												icon:'images/group_delete.png'
											}, {
												xtype : 'tbseparator'
											}, {
												xtype : 'textfield',
												fieldLabel : '查找宿舍信息',
												labelWidth : 90,
												labelAlign : 'right',
												width : 200
											}, {
												xtype : 'button',
												icon:'images/zoom.png'
											}]
								}]
					}]
		});
		me.callParent(arguments);
	}
});