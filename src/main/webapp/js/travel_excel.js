/*ajax获取分页数据*/
var pn = 1;

$(function () {
    getPage1();
    showpage1(pn);
});

function getPage1() {
    $.ajax({
        url: 'getALL/getPage.ajax',
        type: 'get',
        success: function (res) {
            var page = JSON.parse(res);
            console.log(page)
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
                    showpage1(n);
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
 function showpage1(pn) {
    var data = {
        pn: pn
    };
    $.getJSON('getALL/listTravel.ajax', data, function (re) {
        console.log(re);
        $("#ctable").html("");
        $.each(re, function (n, l) {
            var id = wrapId(l.id, pn);
            $("#ctable").append("<tr>\n" +
                "                            <td>" + id + "</td>\n" +
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
    });
};

function wrapId(id, pn) {
   return (pn-1)*10+id;
}

