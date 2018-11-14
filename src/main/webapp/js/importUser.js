$("#btn_add").click(function () {
    $('#myModal').modal();
});

$("#myModal form").submit(function() {
    alert("ssss")
   /* $.ajax({
        url: '/importUser.ajax',
        type: 'post',
        data:{
            keyword:keyword
        },
        success: function (res) {
            alert("添加成功")
        },
        error: function () {

        }
    });*/
});