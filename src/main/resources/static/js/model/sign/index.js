var app=new Vue({
	el: '#app',
	data: {
		selected_node: '',
		data: [],
		stripe:true,//斑马纹
		loading: true,       // 首页是否加载
		containerhei: 0,
		is_show_app:false,
		app_form: {},
		param:{
			status:1,
			pageSize:15,
			pageNo:1,
			total:0,
			searchStr:''
		},
		app_columns:[
			{
				type: 'index',
				width: 60,
				align: 'center'
			},
			{
				title: '打卡人',
				key: 'creator',
				align: 'center'
			},
			{
				title: '打卡时间',
				key: 'createtime',
			},
			{
				title: '相对时间',
				key: 'createtime',
				render:(h,params)=>{
					console.log(new Date(params.row.createtime).getTime());
					return h("Time",
						{props:Object.assign({},
								{time:new Date(params.row.createtime).getTime()})});
				}
			},
			{
				title: '状态',
				key: 'status',
			},
			{
				title: '工作时长',
				key: 'workhour',
			},
			{
				title: '工作地点',
				key: 'workplace',
			},
			{
				title: '所在项目',
				key: 'projectid',
			},
			{
				title: '确认人',
				key: 'confirmor',
			},
			{
				title: '财务确认',
				key: 'confirmor',
			},
			{
				title: '是否生效',
				key: 'iseffective',
				render:(h,params)=>{
					return h("span",{style:{'color':params.row.iseffective?'green':'red'}},params.row.iseffective?'是':'否');
				}
			},
			{
				title: '备注',
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
									app.app_form_upd(params);
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
									app.app_form_del(params);
								}
							}
						},'删除')
					]);
				}
			}
		],
		app_rules: {//验证规则
			title: [
				{required: true, message: '该项为必填项', trigger: 'blur'},
				{type: 'string', min: 10, message: '改项字段长度最大为10', trigger: 'blur'}
			]
		},
	},
	methods:{
		on_page_size_change:function(page_size){
			app.param.pageSize=page_size;
			app.getData();
		},
		on_change:function(page){
			app.param.pageNo=page;
			app.getData();
		},
		app_form_upd:function(params){
			app.app_form=common.deepCopy(params.row);
			app.is_show_app=true;
		},
		app_form_del:function(params){
			app.app_form.id=params.row.id;
			app.app_form.status=-1;
			app.app_form_submit();
		},
		app_form_add:function(){
			app.app_form={status:1,username:'',};
			app.is_show_app=true;
		},
		app_form_submit:function(){
			//更新用户信息
			axios({
				url: ctxPath + 'model/sign/addOrUpdate',
				method: "post",
				params:this.app_form,
				paramsSerializer: function(params) {
					return common.stringify(params);
				}
			}).then(res => {
				return res.data
			}).then( data => {
				app.getData();
				app.$Message.success(data.msg);
				app.is_show_app=false;
			})
		},
		getPY:function(){
			var jx=common.getPinyin(this.app_form.username);
			this.app_form.loginname=jx;
		},
		getData:function () {
			axios({
				url: ctxPath + 'model/sign/getAllMap',
				method: "post",
				params:{
					pageSize:app.param.pageSize,
					pageNo:app.param.pageNo,
					status:app.param.status,
					project:app.param.searchStr
				}})
				.then(function (response) {
					app.data=response.data.data;
					app.param.total=response.data.total;
					app.loading=false;
				})
				.catch(function (error) {
					app.$Message.error(error.response.data.message);
				});;
		},
	}
});


$(function () {
	app.getData();
	app.containerhei=$(".container-fluid").height()-70;
});