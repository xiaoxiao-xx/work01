
/*ajax获取分页数据*/
var pn = 1;
var keyword = "none";
var personStatus = "none";
var dep = "none";
var techDirec = "none";
var techLev = "none";
var rank = "none";
var empStatus = "none";

$(function () {
    getPage();
    showpage(pn);
});

function getPage() {
    keyword = $("#test1").val();
    $.ajax({
        url: 'employeeController/getPage.ajax',
        type: 'get',
        data: {
            keyword: keyword,
            personStatus: personStatus,
            dep: dep,
            techDirec: techDirec,
            techLev: techLev,
            rank: rank,
            empStatus: empStatus
        },
        dataType: 'text',
        success: function (res) {
            var page = JSON.parse(res);
            console.log(page);
            // $("#number_customer").text("共有数据" + page.totalRecords + "条");
            //初始化分页插件
            kkpager.generPageHtml({
                pno: pn,
                mode: 'click', //设置为click模式
                //总页码
                total: page.total,
                //总数据条数
                totalRecords: page.totalRecords,
                //点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
                //适用于不刷新页面，比如ajax
                click: function (n) {
                    //这里可以做自已的处理
                    showpage(n);
                    pn = n;
                    //处理完后可以手动条用selectPage进行页码选中切换
                    this.selectPage(n);
                },
                //getHref是在click模式下链接算法，一般不需要配置，默认代码如下
                getHref: function (n) {
                    return '#';
                }

            }, true);
        },
        error: function () {

        }
    });
};

function formatId(id, pn) {
    return (pn - 1) * 10 + parseInt(id);
}

//显示分页记录
function showpage(pn) {
    var data = {
        pn: pn,
        keyword: keyword,
        personStatus: personStatus,
        dep: dep,
        techDirec: techDirec,
        techLev: techLev,
        rank: rank,
        empStatus: empStatus
    };
    $.getJSON('employeeController/listEmployeeInfo.ajax', data, function (re) {
        console.log(re);
        $("#ctable").html("");
        $.each(re, function (n, l) {
            let gmtBitrh = formatDate(l.gmtBitrh);
            let gmtEntry = formatDate(l.gmtEntry);
            $("#ctable").append("<tr>\n" +
                "                        <td>" + l.name + "</td>\n" +
                "                        <td>" + l.employeeNo + "</td>\n" +
                "                        <td>" + l.sex + "</td>\n" +
                "                        <td>" + l.education + "</td>\n" +
                "                        <td>" + gmtBitrh + "</td>\n" +
                "                        <td>" + gmtEntry+ "</td>\n" +
                "                        <td>" + l.techDirec + "</td>\n" +
                "                        <td>" + l.techLev + "</td>\n" +
                "                        <td>" + l.position + "</td>\n" +
                "                        <td>" + l.superName + "</td>\n" +
                "                        <td>" + l.department + "</td>\n" +
                "                        <td>" + l.tel + "</td>\n" +
                "                        <td>" + l.email + "</td>\n" +
                "                        <td>" + l.idCard + "</td>\n" +
                "                        <td>" + l.hobby + "</td>\n" +
                "                        <td><button class='btn btn-primary' onclick='edit(this)'>修改</button>" +
                "                            <button class='btn btn-danger' onclick='delPerson("+l.employeeNo+")'>删除</button></td>\n" +
                "                    </tr>");
        })
    });
};


/**
 * 导出到excel
 */
function exportInfo1() {
    if (confirm("确定导出")) {
        location.href = 'employeeController/exportEmployeeInfo.do';
    }

}
function person(i) {
    $('.person button').removeClass('btn-primary');
    $('#'+i).addClass('btn-primary');
    if(i.length===1){
        personStatus = i;
    }else {
        empStatus = i;
    }
    getPage();
    showpage(1);
    personStatus = 'none';
    empStatus = 'none';
}

//时间格式转换
function formatDate(time){
    if(time==null){
        return "日期不详"
    }
    var date = new Date(time);

    var year = date.getFullYear(),
        month = date.getMonth() + 1,//月份是从0开始的
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var newTime = year + '年' +
        month + '月' +
        day + '日' ;
    /*   hour + ':' +
      min + ':' +
      sec; */

    return newTime;
}
function searchPerson() {
    keyword = $('#test1').val();
    getPage();
    showpage(1);
}
