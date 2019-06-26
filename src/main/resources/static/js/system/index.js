var index=new Vue({
	el : '#index',
	data : {
		theme:'light',
		active_menu:0,//
		active_tab:0,
		open_menu:0,//
		closable:'closable',
		menus:[],
		tabs:[{id:1,tabname:"首页",icon:"social-apple",closable:false,show:true,content_url:"home"},	]
	},
	methods:{
		//选中的主菜单
		openMenu:function(menu_name){
			if(menu_name.length==0)return;
			this.open_menu=menu_name;
            var menu=this.getMenuItem(menu_name);
            var menu_url=menu.url;
            var allowAddTab=this.allowAddTab(menu.id);
            if(allowAddTab&&menu_url){
                this.tabs.push({id:menu.id,name:menu.menuname,icon:"social-apple",closable:true,content_url:menu.url});
            	this.active_tab=menu.id;
            }
		},
		//选中的子菜单
		selectMenu:function(menu_name){
			this.active_menu=menu_name;
			var menu=this.getMenuItem(menu_name);
			var allowAddTab=this.allowAddTab(menu.id);
			if(allowAddTab){
				this.tabs.push({id:menu.id,tabname:menu.menuname,icon:"social-apple",closable:true,show:true,content_url:menu.url});
			}else {
                var tab_index=this.getTabIndex(menu_name);
                var rev_tab=this.tabs[tab_index];
                rev_tab.show=true;
			}
			this.active_tab=menu.id;
		},
		//选中的tab
		selectTab:function(tab_name) {
            this.active_tab=tab_name;
            var menu=this.getMenuItem(tab_name);
            this.active_menu=menu.id;
            this.open_menu=menu.pid;
		},
		//获取当前的Menuitem
		getMenuItem:function(menu_name){
            for(var i=0; i<this.menus.length; i++){
                if(this.menus[i].id==menu_name){
                    return this.menus[i];
                }
                for(var j=0; j<this.menus[i].sub.length; j++){
                    if(this.menus[i].sub[j].id==menu_name){
						return this.menus[i].sub[j];
                    }
				}
            }
		},
		//获取当前选中的tabIndex
        getTabIndex:function(menu_name){
            for(var i=0; i<this.tabs.length; i++){
                if(this.tabs[i].id==menu_name){
                    return i;
                }
            }
		},
        removeTab:function(tab_name){
			var tab_index=this.getTabIndex(tab_name);
			var rev_tab=this.tabs[tab_index];
            rev_tab.show=false;
		},
		allowAddTab:function (menu_id) {
			for(var i=0; i<this.tabs.length; i++){
				if(this.tabs[i].id==menu_id){
					return false;
				}
			}
			return true;
		},
	},
	computed:{

	},
	mounted:function(){

	}
});

$(function () {
	axios
		.get(ctxPath + "sys/permission/app")
		.then(function (response) {
			var tree=common.filterArray(response.data,0);
            $.each(tree, function (n, item) {
                index.menus.push(item);
            });
		});
});
