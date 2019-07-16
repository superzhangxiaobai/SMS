var index=new Vue({
	el : '#index',
	data : {
		theme:'dark',//深色主题:#515A6E 浅色#fff
		theme2:'light',//深色主题:#515A6E 浅色#fff
		isDark:false,
		active_menu:0,//
		active_tab:0,
		font_color:'#515A6E',
		open_menu:[],//打开的主菜单
		containerhei:0,//iframe高度
		windowHeight:0,
		closable:'closable',
		menus:[],
		tabs:[
			{id:1,tabname:"首页",icon:"social-apple",closable:false,show:true,content_url:"home"},
		],
		tools:[
			{id:1,toolname:"帮助中心",icon:'ios-help-circle-outline',url:'/help'},
			{id:2,toolname:"退出",icon:'ios-exit-outline',url:'/loginout'},
		],
		bg:'#515A6E',
		isCollapsed: false//是否折叠
	},
	watch:{
		isCollapsed:function(status){
			if(status){
				console.log(1)
				this.$nextTick(()=>{
					this.$refs.menus.updateActiveName();
				});
			}
		}
	},
	computed: {
		menuIcon () {
			return this.isCollapsed ? 'ios-apps' : 'ios-apps-outline';
		},
		//折叠显示的icon
		menuitemClasses: function () {
			if(this.isCollapsed) {
				$(".ivu-menu-submenu-title-icon").css("display", "none");
			}else {
				$(".ivu-menu-submenu-title-icon").css("display", "");
			}
			return [
				'menu-item',
				this.isCollapsed ? 'collapsed-menu' : ''
			]
		}
	},
	methods:{
		collapsedSider () {
			console.log(this.isCollapsed);
			this.$refs.side1.toggleCollapse();
		},
		closeCurrentPage () {//关闭当前页
			index.tabs.find(function(item, i){
				if(index.active_tab==item.id){
					index.active_tab=0;
					index.tabs[i].show=false;
				}
			});
		},
		refreshCurrentPage () {//刷新当前页
			document.getElementById('iframe'+index.active_tab).contentWindow.location.reload(true);
		},
		//刷新当前选中的菜单栏
		refreshCurrentTab:function(){
			this.$Message.success('刷新成功');
		},
		changeTheme2:function(data){
			if(data){
				index.theme2='dark';
				// index.bg="#515A6E";
				index.font_color='rgba(255,255,255,0.7)';
			}else {
				index.theme2='light';
				// index.bg="#fff";
				index.font_color='#515A6E';
			}
		},
		//选中的主菜单
		openMenu:function(menu_name){
			if(index.isCollapsed){
				index.isCollapsed=false;
			}
			if(menu_name.length==0)return;
			if(index.open_menu.indexOf(menu_name[0])==-1){
				index.open_menu.push(menu_name[0]);
			}
            var menu=this.getMenuItem(menu_name);
            var menu_url=menu.url;
            var allowAddTab=this.allowAddTab(menu.id);
            if(allowAddTab&&menu_url){
                this.tabs.push({id:menu.id,name:menu.menuname,icon:"social-apple",closable:true,content_url:menu.url});
            	this.active_tab=menu.id;
            }
		},
		selectTool:function(menu_name){
			if(menu_name==2){
				window.open(ctxPath+"logout","_self");
			}
		},
		//选中的子菜单
		selectMenu:function(menu_name){
			if(index.isCollapsed){//打开菜单
				index.isCollapsed=false;
			}
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
		},
		//获取当前的Menuitem
		getMenuItem:function(menu_name){
            for(var i=0; i<this.menus.length; i++){
                if(this.menus[i].id==menu_name){
                    return this.menus[i];
                }
                if(this.menus[i].sub){
					for(var j=0; j<this.menus[i].sub.length; j++){
						if(this.menus[i].sub[j].id==menu_name){
							return this.menus[i].sub[j];
						}
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
	mounted:function(){

	}
});

$(function () {
	index.windowHeight=$(window).height();
	index.containerhei=$(window).height()-165;
	axios
		.get(ctxPath + "sys/permission/user")
		.then(function (response) {
			var tree=common.filterArray(response.data,0);
            $.each(tree, function (n, item) {
                index.menus.push(item);
            });
		});
});
