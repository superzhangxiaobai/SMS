var app=new Vue({
	el: '#app',
	data: {
		bg_color:'#ffa500',
		isLock:false,
	},
	methods:{
		addarea:function () {
			$("#draggable").append(
				'<div class="draggable">' +
				'<pre class="content">hello</pre>' +
				'<div class="bg" style="background:'+app.bg_color+';"></div> ' +
				'<div class="coor" style="display: block;"></div> ' +
				'</div>');
			$(".draggable").draggable();
			$(".draggable").resizable();
		},
		lock:function () {
			if(app.isLock){
				$(".draggable").draggable();
				$(".draggable").resizable();
			}else {
				$(".draggable").draggable("destroy");
				$(".draggable").resizable("destroy");
				app.isLock=true;
			}
		},
		getdata:function () {
			console.log("获取数据");
			/*
			1. 弹窗选填入需要内容
			2.样式调整
			3.刷新, 保存
			4.右键修改, 更换颜色等
			5.只允许右下角拖拽
			 */
		}
	}
});