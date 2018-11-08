function importExp() {
    var formData = new FormData();
    var name = $("#upfile").val();
    formData.append("file",$("#upfile")[0].files[0]);
    formData.append("name",name);
   // alert(formData)
    $.ajax({
        url : '/importExcel',
        type : 'POST',
        async : false,
        data : formData,
        // 告诉jQuery不要去处理发送的数据
        processData : false,
        // 告诉jQuery不要去设置Content-Type请求头
        contentType : false,
        beforeSend:function(){
            console.log("正在进行，请稍候");
        },
        success : function(responseStr) {
            if(responseStr=="successful"){
                alert("导入成功");
                $('#main').load("page/importExcel.html");
            }else{
                alert("导入失败");
            }
        },
        error:function(){
        	alert("导入失败，请重试！")
        }
    });
}
function select() {
  var  value= $("#select").val();
    $.ajax({
        url : 'getALL/selectExcel.ajax',
        type : 'post',
        data :{value:value} ,
        dataType:'json',
        success : function(result) {
            $("#example tbody").html("");
            $("#kkpager").html("");
            $(result).each(function(n,l){
                $("#ctable").append("<tr>\n" +
                    "                            <td>" + (n+1) + "</td>\n" +
                    "                        <td>" + l.name + "</td>\n" +
                    "                        <td>" + l.department + "</td>\n" +
                    "                        <td>" + l.departmentcost + "</td>\n" +
                    "                        <td>" + l.stafflevel + "</td>\n" +
                    "                        <td title=" + l.purpose + ">" + l.purpose + "</td>\n" +
                    "                        <td>" + l.trip + "</td>\n" +
                    "                        <td>" + l.startdate + "</td>\n" +
                    "                        <td>" + l.goff + "</td>\n" +
                    "                        <td>" + l.enddate + "</td>\n" +
                    "                        <td>" + l.endff + "</td>\n" +
                    "                        <td>" + l.days + "</td>\n" +
                    "                        <td>" + l.companyorder + "</td>\n" +
                    "                        <td>" + l.otherorder + "</td>\n" +
                    "                        <td>" + l.travelstandard + "</td>\n" +
                    "                        <td>" + l.livestandard + "</td>\n" +
                    "                        <td>" + l.companyset + "</td>\n" +
                    "                        <td>" + l.personalset + "</td>\n" +
                    "                        <td>" + l.trafficperday + "</td>\n" +
                    "                        <td>" + l.trafficreality + "</td>\n" +
                    "                        <td>" + l.trafficreal + "</td>\n" +
                    "                        <td>" + l.liveperday + "</td>\n" +
                    "                        <td>" + l.livereality + "</td>\n" +
                    "                        <td>" + l.livereal + "</td>\n" +
                    "                        <td>" + l.subsidy + "</td>\n" +
                    "                        <td>" + l.subplan + "</td>\n" +
                    "                        <td>" + l.remarks + "</td>\n" +
                    "                        <td></td>\n" +
                    "                    </tr>");

            });

        },
        error:function(){
            alert("查询失败，请重试！")
        }

    });
}
