laydate.render({
    elem: '#test1', //指定元素
    type: 'month',
    format: 'yyyy年MM月'
});

/*ajax获取分页数据*/
var pn = 1;
var keyword = "none";

function timechange() {
    keyword = $("#test1").val();
    if (keyword == null || keyword == " " || keyword == '') {
        keyword = "none";
    } else {
        $("#title").text("微核及中粒" + keyword + "差旅费明细表")
    }
    pn = 1;
    getPage();
    showpage(pn);
}

$(function () {
    getPage();
    showpage(pn);
});

function getPage() {
    console.log(keyword);
    if ('none' != keyword) {
        keyword = $("#test1").val();
    }
    $.ajax({
        url: 'travelCostController/getPage.ajax',
        type: 'get',
        data: {
            keyword: keyword
        },
        dataType: 'text',
        success: function (res) {
            var page = JSON.parse(res);
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

//显示分页记录
function showpage(pn) {
    var data = {
        pn: pn,
        keyword: keyword
    };
    $.getJSON('travelCostController/listTravel_cost.ajax', data, function (re) {
        $("#ctable").html("");
        $.each(re, function (n, l) {
            var costDep = levelFormate(l.costDep);
            var userLevel = levelFormate(l.userLevel);
            $("#ctable").append("<tr>\n" +
                "                        <td>" + l.id + "</td>\n" +
                "                        <td>" + l.userName + "</td>\n" +
                "                        <td>" + l.department + "</td>\n" +
                "                        <td>" + costDep + "</td>\n" +
                "                        <td>" + userLevel + "</td>\n" +
                "                        <td>" + l.cause + "</td>\n" +
                "                        <td>" + l.trip + "</td>\n" +
                "                        <td>" + l.gmtGo + "</td>\n" +
                "                        <td>" + l.goTimePoint + "</td>\n" +
                "                        <td>" + l.gmtBack + "</td>\n" +
                "                        <td>" + l.backTimePoint + "</td>\n" +
                "                        <td>" + l.days + "</td>\n" +
                "                        <td>" + l.trasportationComp + "</td>\n" +
                "                        <td>" + l.trasportationOther + "</td>\n" +
                "                        <td>" + l.stayOneDay + "</td>\n" +
                "                        <td>" + l.stayStandard + "</td>\n" +
                "                        <td>" + l.stayComp + "</td>\n" +
                "                        <td>" + l.stayOther + "</td>\n" +
                "                        <td>" + l.trafficAllowanceOneDay + "</td>\n" +
                "                        <td>" + l.trafficAllowanceStandard + "</td>\n" +
                "                        <td>" + l.trafficAllowanceReal + "</td>\n" +
                "                        <td>" + l.lifeAllowanceOneDay + "</td>\n" +
                "                        <td>" + l.lifeAllowanceStandard + "</td>\n" +
                "                        <td>" + l.lifeAllowanceReal + "</td>\n" +
                "                        <td>" + l.salaryAllowance + "</td>\n" +
                "                        <td>" + l.subTotal + "</td>\n" +
                "                        <td></td>\n" +
                "                    </tr>");
        })

    });
};

function levelFormate(str) {
    switch (str) {
        case "0":
            return "执行级";
        case "1":
            return "关联级";
        default:
            break;
    }
    return " "
}

/**
 * 导出到excel
 */
function exportInfo() {
    keyword = $("#test1").val();
    if (confirm("确定导出"+keyword+"差旅明细？")){
        $.post('travelCostController/exportInfo.ajax',{keyword:keyword},function (re,ree,reee) {
            console.log(re);
            console.log(ree);
            console.log(reee);
        })
    }
    return;
}
