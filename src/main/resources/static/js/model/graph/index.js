var app=new Vue({
	el: '#app',
	data: {
		bgcolor:'#ffa500',
		isLock:false,
		locktext:'锁定区域',
		locktext_list:["锁定区域","解锁区域"],
		modal:false,
		modal_title:'请输入内容说明',
		modal_content:'',
		select_div:false,
	},
	methods:{
		addarea:function () {
			if(app.select_div){
				// var content=app.modal_content.split(' ').join('<br/>').toString();
				app.select_div.children().filter(".content").text(app.modal_content);
				app.select_div=false;
			}else {
				var offset=$("#draggable").offset();
				var top=Math.abs(offset.top)+40;
				var left=Math.abs(offset.left)+20;
				$("#draggable").append(
					'<div class="draggable" style="top:'+top+'px;left:'+left+'px;position: absolute;">' +
					'<pre class="content">'+app.modal_content+'</pre>' +
					'<div class="bg" style="background:'+app.bgcolor+';"></div> ' +
					'<div class="coor" style="display: block;"></div> ' +
					'</div>');
				$(".draggable").draggable({
					containment:'#draggable',
					drag:function(){
						$(this).addClass("active");
					},
				});
				$(".draggable").resizable({
					maxHeight: 500,
					maxWidth: 500,
					minHeight: 50,
					minWidth: 50,
					containment:'parent',
				});
			}
			app.modal_content='';

			/*//所有html元素id为demo2的绑定此右键菜单
			$('.draggable').contextMenu('myMenu1', {
				//菜单样式
				menuStyle: {},
				//菜单项样式
				itemStyle: {},
				//菜单项鼠标放在上面样式
				itemHoverStyle: {},
				//事件
				bindings:
					{
						'item_1': function(t) {
							alert('Trigger was '+t.id+'\nAction was item_1');
						},
						'item_2': function(t) {
							alert('Trigger was '+t.id+'\nAction was item_2');
						},
						'item_3': function(t) {
							alert('Trigger was '+t.id+'\nAction was item_3');
						},
						'item_4': function(t) {
							alert('Trigger was '+t.id+'\nAction was item_4');
						}
					}
			});*/
			/*$(".draggable").contextmenu(function(e){
				e.preventDefault();//阻止网页默认事件
			});*/
			$(".draggable").mouseup(function(oEvent) {
				if (!oEvent) oEvent=window.event;
				if (oEvent.button==2) {//鼠标右击
					app.select_div=$(oEvent.currentTarget);
				}
			});
		},
		lock:function () {
			if(app.isLock){
				$(".draggable").draggable();
				$(".draggable").resizable();
				$(".coor").css("display","block");
				app.locktext=app.locktext_list[0];
				app.isLock=false;
			}else {
				$(".draggable").draggable("destroy");
				$(".draggable").resizable("destroy");
				$(".coor").css("display","none");
				app.locktext=app.locktext_list[1];
				app.isLock=true;
			}
		},
		submit:function(){
			this.$Notice.info({
				title: '提示',
				desc: '演示: 提交成功'
			});
		},
		refresh:function () {
			this.$Notice.info({
				title: '提示',
				desc: '演示: 获取数据'
			});
			/*
			1. 弹窗选填入需要内容
			2.样式调整
			3.刷新, 保存
			4.右键修改, 更换颜色等
			5.只允许右下角拖拽
			 */
		},
		bindContextMenu:function () {
			context.init({preventDoubleContext: false});
			context.attach('.draggable', [
				{header: '内容编辑'},
				{text: '修改内容', action:function (e) {
						app.modal=true;
					}},
				{header: '更换颜色'},
				{text: '<span style="color:red;">红色</span>',action:function (e) {
						app.bgcolor='red';
						app.select_div.children().filter(".bg").css("background",app.bgcolor);
					}
				},{text: '<span style="color:orange;">橙色</span>',action:function (e) {
						app.bgcolor='orange';
						app.select_div.children().filter(".bg").css("background",app.bgcolor);
					}
				},{text: '<span style="color:mediumpurple;">紫色</span>',action:function (e) {
						app.bgcolor='mediumpurple';
						app.select_div.children().filter(".bg").css("background",app.bgcolor);
					}
				},{text: '<span style="color:darkgreen;">绿色</span>',action:function (e) {
						app.bgcolor='darkgreen';
						app.select_div.children().filter(".bg").css("background",app.bgcolor);
					}
				},{text: '<span style="color:blue;">蓝色</span>',action:function (e) {
						app.bgcolor='blue';
						app.select_div.children().filter(".bg").css("background",app.bgcolor);
					}
				},{text: '<span style="color:dimgrey;">青色</span>',action:function (e) {
						app.bg_color='dimgrey';
						app.select_div.children().filter(".bg").css("background",app.bgcolor);
					}
				},
			]);
		}
	}
});

$(function () {
	$("#draggable").draggable();
	app.bindContextMenu();
});
