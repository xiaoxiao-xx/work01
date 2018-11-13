function importExp() {
    var formData = new FormData();
    //获取文件后缀
    var upFileName  = $("#upfile").val();
    var index1=upFileName.lastIndexOf(".");
    var index2=upFileName.length;
    var suffix=upFileName.substring(index1+1,index2);//后缀名
    if (suffix!="xlsx"){
        alert("文件格式不对")
        return false;
    }
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

