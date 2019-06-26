var index=new Vue({
	el: '#menu',
	data:{
		menus:[]
	},
	methods:{
		menu_add:function(){

		},
		menu_upd:function(){

		},
		menu_del:function(){

		}
	}
});
$(function () {
	var memu_list=common.filterArrayToTree(result.data,0);
	menu.menus=memu_list;
});