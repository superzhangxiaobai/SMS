
var menu=new Vue({
	el: '#app',
	data:{
		selected_node:'',
		menus:[],
		loading:true,       // 首页是否加载
		total:0,
		containerhei:0,
		stripe:true,
		menu_form:{},
		menu_columns:[
			{
				// type: 'expand',//需要调用组件 MenuExpand
				title:'',
				align: 'center',
				render: (h, params) => {
					var level=params.row.pid==0?1:params.row.type=="M"?2:3;
					return h('Icon', {
						style:{
							marginLeft:(level-1)*30+'px'
						},
						props: {
							type:level==3?'':params.row.isExpand?'ios-arrow-down':'ios-arrow-forward',
						},
						on: {
							click: () => { menu.onExpand(params.row,level) }
						}
					})
				}
			},
			{
				type: 'index',
				align: 'center',
			},
			{
				title: '菜单/权限名称',
				minWidth:60,
				key: 'menuname',
			},
			{
				title: '链接/编码',
				minWidth:60,
				key: 'url',
			},
			{
				title: '父级',
				key: 'pid',
			},
			{
				title: '菜单类型',
				key: 'type',
			},
			{
				title: '是否启用',
				key: 'isEnable',
			},
			{
				title: '图标',
				key: 'icon',
			},
			{
				title: '创建时间',
				key: 'createtime',
				width: 100,
				render:(h,params)=>{
					return h("span",params.row.createtime?params.row.createtime.split(' ')[0]:'');
				}
			},
			{
				title: '创建人',
				key: 'creator',
			},{
				title: '备注',
				width:100,
				key: 'memo',
			},
			{
				title: '操作',
				key: 'action',
				fixed: 'right',
				width:180,
				render: (h, params) => {
					return h('div', [
						h('Button', {
							props: Object.assign({}, this.buttonProps, {
								icon: 'ios-create-outline',
								type: 'primary',
								size: 'small',
								//ghost:'ghost',
							}),
							style: {
								marginRight: '8px'
							},
							on: {
								click: () => {
									menu.menu_upd(params);
								}
							}
						},'修改'),
						h('Button', {
							props: Object.assign({}, this.buttonProps, {
								icon: 'ios-remove',
								type: 'error',
								size: 'small',
								//ghost:'ghost',
							}),
							style: {
								marginRight: '8px'
							},
							on: {
								click: () => {
									menu.menu_del(params);
								}
							}
						},'删除')
					]);
				}
			}
		],
		menu_rules:{
			title:[
				{ required: true, message: '该项为必填项', trigger: 'blur' },
				{ type: 'string', min: 10, message: '改项字段长度最大为10', trigger: 'blur' }
			]
		},
		menu_types:[{type:'M',lable:'菜单'},{type:'F',lable:'功能'}],
		menus:[],
		param:{status:1,pid:0},
		is_show:false,
		is_show_icon:false,
		icons:['ios-image-outline','ios-radio-button-off','ios-add','ios-alarm-outline','ios-analytics-outline','ios-apps-outline','ios-at-outline','ios-basket-outline','ios-boat-outline','ios-book-outline','ios-body-outline','ios-bowtie-outline','ios-build-outline','ios-calculator-outline','ios-bus-outline','ios-chatboxes-outline','ios-chatbubbles-outline','ios-close-circle-outline','ios-cloud-outline','ios-cloud-done-outline','ios-cloud-download-outline','ios-cloud-upload-outline','ios-cloudy-night-outline','ios-contacts-outline','ios-desktop-outline','ios-image-outline','ios-key-outline','ios-lock-outline','ios-nuclear-outline','ios-paper-outline']
	},
	methods:{
		menu_add:function(){
			var newnode={
				p_menuname:'',
				id:"",
				url:'',
				menuname:'',
				pid:'',
				isEnable:0,
			};
			this.menu_form=newnode;
			this.is_show=true;
		},
		menu_upd:function(param){
			this.menu_form=common.deepCopy(param.row);
			this.menu_form.p_menuname=menu.menus.filter(item=>item.id==menu.menus[param.index].pid)[0].menuname;
			this.is_show=true;
		},
		menu_del:function(root, node, data){
			this.menu_form.id=data.id;
			this.menu_form.status=-1;
			menu.submit();
		},
		submit:function(){
			this.menu_form.isEnable=this.menu_form.isEnable?1:0;
			axios({
				url: ctxPath + 'sys/menu/addOrUpdate',
				method: "post",
				params:this.menu_form}).then(res => {
				return res.data
			}).then( data => {
				this.is_show=false;
				this.getMenus();
				this.$Message.success(data.msg);
			})
		},
		chooseIcon:function(){
			menu.is_show_icon=true;
		},
		onExpand: function (params, level) {
			//判断当前行是否展开，如果未展开，执行以下方法，先展开再请求接口加载到tabledata中当前data index 后
			//优化请求, 如果已存在数据, 不用请求 没有子级数据, 显示down 按钮
			if (!this.menus[params._index].isExpand) {
				$.ajax({
					url: ctxPath + 'sys/menu/getAll',
					type: "post",async:false,/**/
					data:{pid:menu.menus[params._index].id},
					success:function (data) {
						Vue.set(menu.menus[params._index], 'isExpand', true);//改变对象的值, 最好使用set
						let newArrayData = data.data;
						menu.menus[params._index].total = newArrayData.length; //将展开操作查询到的数据总条数加到当前行数据的totals上
						/*newArrayData.map( item =>{
							item.isExpand = true;
							item.upLevelIndex = params.index;
						});*/
						newArrayData.map( (value, key) =>{
							menu.menus.splice(params._index + key + 1, 0, value);
						});
					},
					error:function (xhr,status,error) {
						menu.$Message.error(error.response.data.message);
					}
				});
				//异步await 不生效, 待优化
				/*axios.post({
					url: ctxPath + 'sys/menu/getAll',
					method: "post",
					params:{pid:this.menus[params._index].id}
				}).then(res => {
					return res.data
				}).then( data => {
					this.menus[params._index].isDown = true;
					this.menus[params._index].isExpand = true;
					let newArrayData = data.data;
					this.menus[params._index].total = newArrayData.length; //将展开操作查询到的数据总条数加到当前行数据的totals上
					/!*newArrayData.map( item =>{
						item.isExpand = true;
						item.upLevelIndex = params.index;
					});*!/
					newArrayData.map( (value, key) =>{
						this.menus.splice(params._index + key + 1, 0, value);
					});
				}).catch(function (error) {
					menu.$Message.error(error.response.data.message);
				});*/
			} else {//如果当前行已展开，则隐藏
				Vue.set(menu.menus[params._index], 'isExpand', false);
				this.menus.splice(params._index + 1, params.total ? params.total : 0);
			}
		},
		getMenus:function () {
			axios({
				url: ctxPath + 'sys/menu/getAll',
				method: "post",
				params:menu.param})
				.then(function (response) {
					menu.menus=response.data.data;
					menu.total=response.data.count;
					menu.loading=false;
				})
				.catch(function (error) {
					menu.$Message.error(error.response.data.message);
				});
		},
		init:function (data) {

		}
	}
});
$(function () {
	menu.getMenus();
	menu.containerhei=$(".container-fluid").height()-35;
});