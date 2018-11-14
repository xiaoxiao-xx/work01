


$("#btn_add").click(function () {
    $('#myModal').modal();
});



$("#myModal form").submit(function() {
    event.preventDefault();
    $.ajax({
        async: false,
        url: '/employeeController/add/exportEmployeeInfo.ajax',
        type: "post",
        data: $("#myModal form").serialize(),
        dataType: "json",
        success: function (result) {
            if (result.status == 0) {
                alert("000000");
                location.reload(true);
            } else if (result.status == 1) {
                alert("1111111");
            }
        },

        error: function () {
            alert("请求失败!");
        }
    });
});

