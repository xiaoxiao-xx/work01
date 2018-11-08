function login_form(){
        //获取表单中的用户名和密码
        var lName=$("#inputName").val();
        var lPwd=$("#inputPassword").val();
        //断点跟踪
        $.ajax({
            url:"/login.ajax",
            type:"post",
            data:{
                lName:lName,
                lPwd:lPwd
            },
            dataType:"text",
            success:function(result){
                console.log(result);
                //result是服务端返回的数据
                if(result == "yes"){
                    window.location.href="index.html";
                }else {
                    alert("密码错了")
                }
            },
            error:function(){
                alert("请求失败!");
            }
	});
}