Ext.define('XSGYGLXT.view.addDorm', {
			extend : 'Ext.window.Window',
			alias : 'widget.addDorm',
			title : '宿舍信息(新增)',
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
				url : 'saveOrUpdateDormAction',
				defaults : {
					anchor : '90%',
					labelWidth : 80,
					labelAlign : 'right',
					allowBlank : false
				},
				items : [{
							name : 'ssbh',
							fieldLabel : '宿舍编号'
						}, {
							name : 'ssyq',
							fieldLabel : '宿舍园区'
						}, {
							xtype:'numberfield',
							name : 'ssld',
							minValue: 1,
							maxValue: 99,
							fieldLabel : '宿舍楼栋'
						}, {
							xtype:'numberfield',
							name : 'sslc',
							minValue: 1,
							maxValue: 6,
							fieldLabel : '宿舍楼层'
						}, {
							name : 'ssbz',
							fieldLabel : '宿舍备注'
						}, {
							xtype:'numberfield',
							name : 'zdrs',
							minValue: 1,
							maxValue: 6,
							fieldLabel : '最大人数'
						}, {
							name : 'sfym',
							hidden:true,
							fieldLabel : '是否已满',
							value:'否'
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
