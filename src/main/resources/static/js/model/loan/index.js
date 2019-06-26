var app=new Vue({
	el: '#app',
	data: {
		selected_node: '',
		users: [],
		stripe:true,//斑马纹
		loading: true,       // 首页是否加载
		containerhei: 0,
		user_form: {},
		role_form: {},
		param:{
			username:'',
			status:1,
		},
		is_show_user:false,
		is_show_role:false,
		user_columns:[
			{
				type: 'index',
				width: 60,
				align: 'center'
			},
			{
				title: '用户名',
				key: 'username',
				width: 100,
			},
			{
				title: '账号',
				key: 'loginname',
				width: 100,
			},
			{
				title: '电话',
				width: 100,
				key: 'tel',
			},
			{
				title: '住址',
				width: 100,
				key: 'address',
			},
			{
				title: '邮箱',
				width: 100,
				key: 'email',
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
				width: 100,
			},
			{
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
									app.user_upd(params);
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
									app.user_del(params);
								}
							}
						},'删除')
					]);
				}
			}
		],
		role_columns:[
			{
				type: 'index',
				width: 60,
				align: 'center'
			},
			{
				title: '角色编码',
				key: 'rolename',
				width:150,
				align: 'center'
			},
			{
				title: '角色名称',
				key: 'role',
				width:150,
				align: 'center'
			},
			{
				title: '备注',
				key: 'memo',
				width:150,
				align: 'center'
			},
			{
				title: '操作',
				key: 'action',
				fixed: 'right',
				width: 180,
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
									app.role_upd(params);
								}
							}
						}, '修改'),
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
									app.role_del(params);
								}
							}
						}, '删除')
					]);
				}
			}
		],
		user_rules: {//验证规则
			title: [
				{required: true, message: '该项为必填项', trigger: 'blur'},
				{type: 'string', min: 10, message: '改项字段长度最大为10', trigger: 'blur'}
			]
		},
		role_rules: {//验证规则
			title: [
				{required: true, message: '该项为必填项', trigger: 'blur'},
				{type: 'string', min: 10, message: '改项字段长度最大为10', trigger: 'blur'}
			]
		},
		roles:[],//所有角色对象
	},
	methods:{
		user_upd:function(params){
			app.user_form=common.deepCopy(params.row);
			app.user_form.roles=params.row.roles?params.row.roles.split(',').map(Number):[];
			app.is_show_user=true;
		},
		user_del:function(params){
			app.user_form.id=params.row.id;
			app.user_form.status=-1;
			app.user_submit();
		},
		user_add:function(){
			app.user_form={status:1,username:'',};
			app.is_show_user=true;
		},
		role_upd:function(params){
			app.role_form=common.deepCopy(params.row);
			app.is_show_role=true;
		},
		role_del:function(params){
			app.role_form.id=params.row.id;
			app.role_form.status=-1;
			app.role_submit();
		},
		role_add:function(){
			app.role_form={status:1,rolename:'',role:''};
			app.is_show_role=true;
		},
		user_submit:function(){
			//更新用户信息
			axios({
				url: ctxPath + 'sys/app/addOrUpdate',
				method: "post",
				params:this.user_form,
				paramsSerializer: function(params) {
					return common.stringify(params);
				}
			}).then(res => {
				return res.data
			}).then( data => {
				app.getUsers();
				app.$Message.success(data.msg);
			})
		},
		role_submit:function(){
			//更新角色信息
			axios({
				url: ctxPath + 'sys/role/addOrUpdate',
				method: "post",
				params:this.role_form,
				paramsSerializer: function(params) {
					return common.stringify(params);
				}
			}).then(res => {
				return res.data
			}).then( data => {
				app.getRoles();
				app.$Message.success(data.msg);
			})
		},
		getPY_user:function(){
			var jx=common.getPinyin(this.user_form.username);
			this.user_form.loginname=jx;
		},
		getPY_role:function(){
			var jx=common.getAlpha(this.role_form.role);
			this.role_form.rolename='ROLE_'+jx;
		},
		getRoles:function(){
			axios({
				url: ctxPath + 'sys/role/getAll',
				method: "post",
				params:app.param})
				.then(function (response) {
					app.roles=response.data.data;
				})
				.catch(function (error) {
					app.$Message.error(error.response.data.message);
				});;
		},
		getUsers:function () {
			axios({
				url: ctxPath + 'sys/app/getAll',
				method: "post",
				params:app.param})
				.then(function (response) {
					app.users=response.data.data;
					app.loading=false;
				})
				.catch(function (error) {
					app.$Message.error(error.response.data.message);
				});;
		},
	}
});


$(function () {
	app.getUsers();
	app.getRoles();
	app.containerhei=$(".container-fluid").height()-35;
});