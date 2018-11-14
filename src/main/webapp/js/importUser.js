


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
                alert(result.message);
                location.reload(true);
            } else if (result.status == 1) {
                alert(result.message);
            }
        },
        error: function () {
            alert("请求失败!");
        }
    });
});

