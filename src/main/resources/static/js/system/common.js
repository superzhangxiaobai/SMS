var common={};
//将集合变成树形,适合menu
common.filterArray=function(data, parent) {
	var tree = [];
	var temp;
	for (var i = 0; i < data.length; i++) {
		if (data[i].pid == parent) {
			var obj = data[i];
			temp = this.filterArray(data, data[i].id);
			if (temp.length > 0) {
				obj.sub = temp;
			}
			tree.push(obj);
		}
	}
	return tree;
}
//将集合变成树形,适合tree
common.filterArrayToTree=function(data, parent) {
	var tree = [];
	var temp;
	for (var i = 0; i < data.length; i++) {
		if (data[i].pid == parent) {
			var obj = data[i];
			temp = this.filterArray(data, data[i].id);
			if (temp.length > 0) {
				obj.sub = temp;
			}
			tree.push(obj);
		}
	}
	return tree;
}