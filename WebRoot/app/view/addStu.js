Ext.define('XSGYGLXT.view.addStu', {
			extend : 'Ext.window.Window',
			alias : 'widget.addStu',
			title : '学生信息(新增)',
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
				url : 'saveOrUpdateStudentAction',
				defaults : {
					anchor : '90%',
					labelWidth : 80,
					labelAlign : 'right',
					allowBlank : false
				},
				items : [{
							name : 'xm',
							fieldLabel : '姓名'
						}, {
							name : 'xh',
							fieldLabel : '学号'
						}, {
							name : 'zy',
							fieldLabel : '专业'
						}, {
							name : 'bj',
							fieldLabel : '班级'
						}, {
							name : 'lxfs',
							fieldLabel : '联系方式'
						}, {
							xtype:'textfield',
							hidden:true,
							name : 'sfyyss',
							fieldLabel : '是否已有宿舍',
							value : '否'
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
