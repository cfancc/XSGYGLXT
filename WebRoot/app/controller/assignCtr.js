Ext.define('XSGYGLXT.controller.assignCtr', {
	extend : 'Ext.app.Controller',
	stores : ['userStore'],
	views : ['assignPanel','userWin','addUser'],
	//学生信息搜索
	stuButtonClick:function(button)
		{
				button.up('grid').getStore().load({
					params : {// 这里传的是查找框里的内容,后台接收"query"参数
						sfyyss:button.up('toolbar').down('combo').getValue(),
						query : button.up('toolbar').down('textfield[fieldLabel=查找学生信息]')
								.getValue(),
						sfyyss :'否'
						
					}
				})
		},
	init : function(application) {
		this.control({
					"assignPanel gridpanel[title=未分配学生] toolbar button" : {
						click : this.stuButtonClick
					}
				});
	}

});