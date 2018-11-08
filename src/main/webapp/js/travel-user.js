var fieldCount=0;
var timestamp = (new Date()).valueOf();
//分割-------------------------------
var roleType="all";

var x = 0;
var y = 0;

$(function(){
	
	$("#numb").val(1);
	$("#travel_number").val(timestamp);
	addUser();
	
	//给新增角色表单添加submit事件
	$("#addUserPanel form").submit(function(){
//		parent.layer.close(index); //再执行关闭
        $.ajax({
            async: false,
            url:'user/adds',
            type: "post",
            data:$("#addUserPanel form").serialize(),
            dataType: "json",
            success: function (result) {
//            	alert(result.message);
            	location.reload(true);
            },
            error: function () {
            	alert("请求失败~");
            }
        })
        return false;
	});
	
	
	//分割-------------------------------
//	console.log("load user");
	//第一次访问时,第一页数据
	findUsers(1);
	//给搜索按钮添加click事件
	$("#user_search_btn").click(function(){
		findUsers(1);
	});

	//给"详情"标签添加点击事件
	$('#user_tab li:eq(1) a').click(function (e) {
		  e.preventDefault();
		  $(this).tab('show');
		  $("#detailPanel .editBack").html('请在"列表"中选择一个用户显示详情');
	});

	//给修改的模态框的"确定"添加click事件
	// $("#editUser form").submit(function(){
	// 	//alert("aaaaaaaaaaaaaaaaaaaaaaaaa");
	// 	//
	// 	return updateUser();
	// });
	
	//给修改的住宿的"确定"添加click事件
	// $("#editCost form").submit(function(){
	// 	//alert("aaaaaaaaaaaaaaaaaaaaaaaaa");
	// 	//
	// 	return updateCost();
	// });
	
	//给全部变量roleType赋值
	$("#role_type button").click(function(){
		roleType=$(this).html();
		if("全部"==roleType){
			roleType="all";
            // $("#user_table").html('');
		}
		if("出差中"==roleType){
			roleType="go";
            // $("#user_table").html('');
		}
		if("已返回"==roleType){
			roleType="back";
            // $("#user_table").html('');
		}
		$(this).siblings("button").removeClass("active");
		$(this).addClass("active");
		findUsers(1);
	});
});

//更新返回详情（弹框）
// function updateUser(){
// 	var inputPersons=$("#editUser #inputPersons").val();
// 	var inputDestination=$("#editUser #inputDestination").val();
// 	var inputGoTime=$("#editUser #inputGoTime").val();
// 	var inputCause=$("#editUser #inputCause").val();
// 	var inputBackTime=$("#editUser #inputBackTime").val();
// 	var inputBackMethod=$("#editUser #inputBackMethod").val();
// 	var inputPayMethods=$("#editUser #inputPayMethods").val();
// 	var payMoney=$("#editUser #payMoney").val();
// 	var travelNum=$("#editUser #inputTravelNum").val();
// 	var userNum=$("#editUser #inputUserNum").val();
// 	var up = {
// 		    "persons":inputPersons,
// 		    "destination":inputDestination,
// 		    "cause":inputCause,
// 		    "gmtBack":inputBackTime,
// 		    "backMethod":inputBackMethod,
// 		    "payMethods":inputPayMethods,
// 		    "payMoney":payMoney,
// 		    "travelNum":travelNum,
// 		    "userNum":userNum
// 		 }
//
// 	//异步请求更新数据
// 	$.ajax({
// 		url:"update/travel",
// 		type:"post",
// 		data:JSON.stringify(up),
// 		contentType:"application/json;charse=UTF-8",
// 		success:function(data,status){
// 			location.reload(true);
// 		},
// 		error:function(){
// 			alert("请求失败!");
// 		}
// 	});
// 	return false;
// }

//更新住宿详情（弹框）
// function updateCost(){
// 	var costTravelNum=$("#editCost #costTravelNum").val();
// 	var costPayMethod=$("#editCost #costPayMethod").val();
// 	var costpayMoney=$("#editCost #costpayMoney").val();
//
// 	alert("准备进入ajax");
// 	var up = {
// 		    "travelNum":costTravelNum,
// 		    "costPayMethod":costPayMethod,
// 		    "costPayMoney":costpayMoney
// 		 }
//
// 	//异步请求更新数据
// 	$.ajax({
// 		url:"update/cost",
// 		type:"post",
// 		data:JSON.stringify(up),
// 		contentType:"application/json;charse=UTF-8",
// 		success:function(result){
// 			if(result.status==0){
// 				location.reload(true);
// 			}
//
// 		},
// 		error:function(){
// 			alert("请求失败!");
// 		}
// 	});
// 	return false;
// }

//给返回页面赋值（弹框）
// function updateUserClick(editName,destination,gmtGo,cause,travelNum,editNum){
//
// 	$("#editUser #inputPersons").val(editName);
// 	$("#editUser #inputDestination").val(destination);
// 	var gmtGoTime = new Date(gmtGo).toLocaleString().replace("/","-").replace("/","-");
// 	$("#editUser #inputGoTime").val(gmtGoTime);
// 	$("#editUser #inputCause").val(cause);
// 	$("#editUser #inputTravelNum").val(travelNum);
// 	$("#editUser #inputUserNum").val(editNum);
//
// }

//提交住宿详情（弹框）
// function updateCostClick(editName,destination,stayDays,cause,travelNum,editNum){
// 	var findCity = {
// 			"destination":destination
// 	}
// 	$.ajax({
// 		url:"find/budget",
// 		type:"post",
// 		data:JSON.stringify(findCity),
// 		contentType:"application/json;charse=UTF-8",
// 		success:function(result){
// 			if(result.status==0){
// 				if(result.data!=null){
// 					var standards = result.data.standard;
// 					var budget = standards*stayDays;
// 					$("#editCost #costPersons").val(editName);
// 					$("#editCost #costDays").val(stayDays);
// 					$("#editCost #costBudget").val(budget);
// 					$("#editCost #costTravelNum").val(travelNum);
// 				}
// 			}else if(result.status==1){
// 				alert(result.message);
// 			}
// 		},
// 		error:function(){
// 			alert("请求失败~");
// 		}
// 	})
//
// }

//带分页查询
function findUsers(currentPage){
	var userKeyword=$("#user_search").val();
	if(userKeyword==""){
		userKeyword="undefined";
	}
	$.ajax({
		url:"page/"+currentPage+"/"+userKeyword+"/"+roleType,
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//清空表格内容
				// $("#user_table tbody").html("");
				//清空分页内容
				$("#user_page").html("");
				var users=result.data.data;
				var arr = '';
//				$(users).each(function(n,value){
//					if(value.travelNum)
//				});
				if(roleType=="all"){
                    $("#user_table").html('');
                    var news = '<thead>'+
                        '<tr>'+
                        '<th class="text-center" style="width: 5%">#</th>'+
                        '<th class="text-center" style="width: 11% ">姓名</th>'+
                        '<th class="text-center" style="width: 11% ">工号</th>'+
                        '<th class="col-md-1 text-center">目的地</th>'+
                        '<th class="text-center" style="width: 20%">出差日期</th>'+
                        '</tr>'+
                        '</thead>'+
                        '<tbody></tbody>';
                    $("#user_table").append(news);
				}else if(roleType=="go"){
                    $("#user_table ").html('');
                    var news = '<thead>'+
                        '<tr>'+
                        '<th class="text-center" style="width: 5%">#</th>'+
                    '<th class="col-md-1 text-center">状态</th>'+
                    '<th class="text-center" style="width: 11% ">姓名</th>'+
                    '<th class="text-center" style="width: 11% ">工号</th>'+
                    '<th class="col-md-1 text-center">目的地</th>'+
                    '<th class="text-center" style="width: 20%">出差日期</th>'+
                    '<th class="text-center" style="width: 20%">返回日期</th>'+
                    '<th class="text-center" style="width: 11%">编辑</th>'+
                    '</tr>'+
                    '</thead><tbody></tbody>';
                    $("#user_table").append(news);
				}else if(roleType=="back"){
                    $("#user_table").html('');
                    var news = '<thead>'+
                        '<tr>'+
                        '<th class="text-center" style="width: 5%">#</th>'+
                        '<th class="col-md-1 text-center">状态</th>'+
                        '<th class="text-center" style="width: 11% ">姓名</th>'+
                        '<th class="text-center" style="width: 11% ">工号</th>'+
                        '<th class="col-md-1 text-center">目的地</th>'+
                        '<th class="text-center" style="width: 20%">出差日期</th>'+
                        '<th class="text-center" style="width: 20%">返回日期</th>'+
                        '</tr>'+
                        '</thead><tbody></tbody>';
                    $("#user_table").append(news);
				}
				$(users).each(function(n,value){
					if(roleType=="go"){
						var states = "出差中";
                        var gmtBack = '/';
                        if(value.gmtBack!=null){
                            gmtBack = new Date(value.gmtBack).toLocaleString();
                        }
                        var editName = value.userName;
                        var editNum = value.userNum;
                        editName = editName.replace(/,<\/br>/g,"\,");
                        editNum = editNum.replace(/,<\/br>/g,"\,");
                        // var selectUserName = selectEditName(editName,value.travelNum);
                        // var selectUserNum = selectEditNum(editNum,value.travelNum);
                        var gmtTime = new Date(value.gmtGo).toLocaleString().replace("/","-").replace("/","-");
                        var begin = '<tr>'+
                            '<td class="text-center">'+(n+1)+'</td>'+
                            '<td class="text-center" >'+states+'</td>'+
                            '<td class="text-center" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+editName+'</td>'+
                            '<td class="text-center" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+editNum+'</td>'+
                            '<td class="text-center" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+value.destination+'</td>'+
                            '<td class="text-center" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+gmtTime+'</td>'+
                            '<td class="text-center" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+gmtBack+'</td>';
                        var end='<td class="text-center">'+
							'<a href=javascript:editBackByTravelNum("'+editName+'","'+value.destination+'","'+value.gmtGo+'","'+value.cause+'","'+value.travelNum+'","'+editNum+'") id="update'+value.userNum+'" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑返回</a>'+
							'</td>'+
							'</tr>';
                        $("#user_table tbody").append(begin+end);
					}else if(roleType=="back"){
                        var states = "已返回";
                        var gmtBack = '/';
                        if(value.gmtBack!=null){
                            gmtBack = new Date(value.gmtBack).toLocaleString();
                        }
                        var editName = value.userName;
                        var editNum = value.userNum;
                        editName = editName.replace(/,<\/br>/g,"\,");
                        editNum = editNum.replace(/,<\/br>/g,"\,");
                        // var selectUserName = selectEditName(editName,value.travelNum);
                        // var selectUserNum = selectEditNum(editNum,value.travelNum);
                        var gmtTime = new Date(value.gmtGo).toLocaleString().replace("/","-").replace("/","-");
                        var begin = '<tr>'+
                            '<td class="text-center" >'+(n+1)+'</td>'+
                            '<td class="text-center">'+states+'</td>'+
                            '<td class="text-center" title="'+editName+'" style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+editName+'</td>'+
                            '<td class="text-center" title="'+editNum+'" style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+editNum+'</td>'+
                            '<td class="text-center" title="'+value.destination+'" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+value.destination+'</td>'+
                            '<td class="text-center" title="'+gmtTime+'" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+gmtTime+'</td>'+
                            '<td class="text-center" title="'+value.backTimes+'" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+value.backTimes+'</td>'+
                        	'</tr>';
                        $("#user_table tbody").append(begin);
					}else if(roleType=="all"){

                        var editName = value.userName;
                        var editNum = value.userNum;
                        editName = editName.replace(/,<\/br>/g,"\,");
                        editNum = editNum.replace(/,<\/br>/g,"\,");
                        // var selectUserName = selectEditName(editName,value.travelNum);
                        // var selectUserNum = selectEditNum(editNum,value.travelNum);
                        var gmtTime = new Date(value.gmtGo).toLocaleString().replace("/","-").replace("/","-");
                        var begin = '<tr>'+
                            '<td class="text-center">'+(n+1)+'</td>'+
                            '<td class="text-center" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+editName+'</td>'+
                            '<td class="text-center" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+editNum+'</td>'+
                            '<td class="text-center">'+value.destination+'</td>'+
                            '<td class="text-center" style=" white-space:nowrap;overflow:hidden;text-overflow: ellipsis;">'+gmtTime+'</td></tr>';

                        $("#user_table tbody").append(begin);
					}

				});
				//处理分页条
				var page=result.data;
				
				if(page.aNum.length>0){
					//处理前一页
					var previous='<li>'+
			            '<a href="javascript:findUsers('+page.previousPage+')" aria-label="Previous">'+
		              '<span aria-hidden="true">&laquo;</span>'+
		            '</a>'+
		          '</li>';
					$("#user_page").append(previous);
					//处理中间页数字
					$(page.aNum).each(function(n,value){
						var middle='<li><a href="javascript:findUsers('+value+')">'+value+'</a></li>';
						$("#user_page").append(middle);
					});
					//处理下一页
					var next='<li>'+
			            '<a href="javascript:findUsers('+page.nextPage+')" aria-label="Next">'+
		              '<span aria-hidden="true">&raquo;</span>'+
		            '</a>'+
		          '</li>';
					
					$("#user_page").append(next);
					
					
				}
			}else if(result.status==1){
				alert(result.message);
			}
		},
		error:function(){
			alert("请求失败!");
		}
		
	});
}

//分割-----------------------------
//添加出差人员
function addUser() {

    var maxInputs    = 10;
    var addTbody  = $("#addTbody");
    var addButton    = $("#addUser");
    $(addButton).click(function (e)
    {
        if(fieldCount < maxInputs)
        {
            fieldCount++;
            var result = '<tr>'+
                '<td class="text-center"><input type="test" id="addName" class="form-control" name="addName"></td>'+
                '<td class="text-center"><input type="test" id="addJobNumber" class="form-control" name="addJobNumber"></td>'+
                '<td class="text-center"><select class="form-control" id="addTransportation" name="addTransportation"><option value="1">飞机</option><option value="2">高铁</option><option value="3">汽车</option><option value="4">其他</option></select></td>'+
                '<td class="text-center"><select id="addPayMethod" class="form-control" name="addPayMethod"><option value="1">公司</option><option value="2">个人</option></select></td>'+
                '<td class="text-center"><input type="text" id="addMoney" class="form-control" name="addMoney"></td>'+
                '<td class="text-center"><button class="btn btn-danger" type="button" onclick="deleteUserRow(this)">删除</button></td>'+
                '</tr>';
            $(addTbody).append(result);

            $("#numb").val(fieldCount);

        }
        return false;
    });
};

//通过传来的姓名，生成下拉选择框
function selectEditName(editName,travelNum){
	var split = editName.split(",");
	var middle = "";
	for(var i=0;i<split.length;i++){
		middle+='<option>'+split[i]+'</option>';
	}
	var result = '<select class="form-control" id="selectUserName_'+travelNum+'" name="selectUserName_'+travelNum+'">'+middle+'</select>';
	return result;
};
//通过传来的工号，生成下拉选择框
function selectEditNum(editNum,travelNum){
	var split = editNum.split(",");
	var middle = "";
	for(var i=0;i<split.length;i++){
		middle+='<option>'+split[i]+'</option>';
	}
	var result = '<select class="form-control" id="selectUserNum_'+travelNum+'" name="selectUserNum_'+travelNum+'">'+middle+'</select>';
	return result;
};

//通过差旅编号编辑返回信息
function editBackByTravelNum(editName,destination,gmtGo,cause,travelNum,editNum){
	x = 0;
	y = 0;
	$('#user_tab li:eq(1) a').tab('show');
	$("#detailPanel .editBack").html("");
	
	var backDetail='<form class="form-horizontal">'+
	'<div class="form-group">'+
	'<label class="col-md-1 control-label" for="backUsers">出差人员</label>'+
	'<div class="col-md-3">'+
	'<input type="text" id="backUsers" class="form-control" name="backUsers" readonly="readonly">'+
	'</div>'+
	'<label class="col-md-1 control-label" for="backDestination">目的地</label>'+
	'<div class="col-md-3">'+
	'<input type="text" id="backDestination" class="form-control" name="backDestination" readonly="readonly">'+
	'</div>'+
	'<label class="col-md-1 control-label" for="backStandard">报销标准</label>'+
	'<div class="col-md-3">'+
	'<input type="text" id="backStandard" class="form-control" name="backStandard" readonly="readonly">'+
	'</div>'+
	'<div >'+
	'<input type="hidden" id="backTravelNum" name="backTravelNum" value="">'+
	'</div>'+
	'<div>'+
	'<input type="hidden" id="backUserNum" name="backUserNum" value="">'+
	'</div>'+
	'<div>'+
	'<input type="hidden" id="backNumb" name="backNumb" value="0">'+
	'</div>'+
	'</div>'+
	'<div class="form-group">'+
	'<label class="col-md-1 control-label" for="backGmtGo">出差时间</label>'+
	'<div class="col-md-3">'+
	'<input type="text" id="backGmtGo" class="form-control" name="backGmtGo" readonly="readonly">'+
	'</div>'+
	'<label class="col-md-1 control-label" for="backCause">出差目的</label>'+
	'<div class="col-md-3">'+
	'<input type="text" id="backCause" class="form-control" name="backCause" readonly="readonly">'+
	'</div>'+
	'</div>'+
	'<div class="form-group">'+
	'<table id="backTable" class="table table-bordered">'+
	'<thead>'+
	'<tr>'+
	'<th class="col-md-1 text-center">姓名</th>'+
	'<th class="col-md-1 text-center">返回时间</th>'+
	'<th class="col-md-1 text-center">交通工具</th>'+
	'<th class="col-md-1 text-center">交通支付方式</th>'+
	'<th class="col-md-1 text-center">交通费用</th>'+
	'<th class="col-md-1 text-center">住宿支付方式</th>'+
	'<th class="col-md-1 text-center">住宿支付费用</th>'+
	'<th class="col-md-1 text-center">编辑</th>'+
	'</tr>'+
	'</thead>'+
	'<tbody id="backTbody">'+

	'</tbody>'+
	'</table>'+
	'</div>'+
	'<div class="form-group">'+
	'<div class="col-md-4 col-md-offset-4">'+
	'<button class="btn" type="button" id="addBackUser">添加员工</button>'+
	'<button type="submit" class="btn btn-primary" id="submitBack">确认</button>'+
	'</div>'+
	'</div>'+
	'</form>';
	$("#detailPanel .editBack").append(backDetail);
	
	$("#detailPanel #backUsers").val(editName);
	$("#detailPanel #backDestination").val(destination);
	var gmtGoTime = new Date(gmtGo).toLocaleString().replace("/","-").replace("/","-");
	$("#detailPanel #backGmtGo").val(gmtGoTime);
	$("#detailPanel #backCause").val(cause);
	$("#detailPanel #backTravelNum").val(travelNum);
	$("#detailPanel #backUserNum").val(editNum);
	
	addBackUser(editName,editNum);


    $("#detailPanel form").submit(function(){
        $.ajax({
            async: false,
            url:'edit/back/travel',
            type: "post",
            data:$("#detailPanel form").serialize(),
            dataType: "json",
            success: function (result) {
            	alert(result.message);

                // location.reload(true);
            },
			error:function(){
				alert("请求失败!");
			}
		});
        return false;
    });
}

//添加返程员工
function addBackUser(editName,editNum) {

	var maxInputs    = editNum.split(",").length; //maximum input boxes allowed
	var backTbody  = $("#backTbody"); //Input boxes wrapper ID
	var addButton    = $("#addBackUser"); //Add button ID
	 //to keep track of text box added
	$(addButton).click(function (e) //on add input button click
	{
	    if(x < maxInputs) //max input box allowed
	    {
            var arrNum = editNum.split(",");
            var arrName = editName.split(",");
            var middle = "";
            for(var i=0;i<arrNum.length;i++){
                middle+='<option value="'+arrNum[i]+'">'+arrName[i]+'</option>';
            }
            var backName = '<select class="form-control" id="backName_'+y+'" name="backName">'+middle+'</select>';

            var result = '<tr>'+
                '<td class="text-center">'+backName+'</td>'+
                '<td class="text-center"><input type="datetime-local" id="backTime_'+y+'" class="form-control" name="backTime"></td>'+
                '<td class="text-center"><select class="form-control" id="backTrasportation_'+y+'" name="backTrasportation"><option value="1">飞机</option><option value="2">高铁</option><option value="3">汽车</option><option value="4">其他</option></select></td>'+
                '<td class="text-center"><select id="backTrasportation_payMethod_'+y+'" class="form-control" name="backTrasportation_payMethod"><option value="1">公司</option><option value="2">个人</option></select></td>'+
                '<td class="text-center"><input type="text" id="backTrasportation_payMoney_'+y+'" class="form-control" name="backTrasportation_payMoney"></td>'+
                '<td class="text-center"><select id="backCost_payMethod_'+y+'" class="form-control" name="backCost_payMethod"><option value="1">公司</option><option value="2">个人</option></select></td>'+
                '<td class="text-center"><input type="text" id="backCost_payMoney_'+y+'" class="form-control" name="backCost_payMoney"></td>'+
                '<td class="text-center"><button type="button" class="btn btn-danger" onclick="deleteRow(this)">删除</button></td>'+
                '</tr>';

	      //add input box
	      $(backTbody).append(result);
	      x++; //text box increment
		  y++;
	      $("#backNumb").val(x);

	    }
	return false;
	});
};

//返回详情中删除已添加的人员
function deleteRow(r){
	var i=r.parentNode.parentNode.rowIndex;
	document.getElementById('backTable').deleteRow(i);
	x--;
    $("#backNumb").val(x);
}
function deleteUserRow(r){
    var i=r.parentNode.parentNode.rowIndex;
    document.getElementById('addTable').deleteRow(i);
    fieldCount--;
    $("#numb").val(fieldCount);x
}







