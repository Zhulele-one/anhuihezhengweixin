$(document).ready(function(){
    $(".side ul li").hover(function(){
        $(this).find(".sidebox").stop().animate({"width":"150px"},200).css({"opacity":"1","filter":"Alpha(opacity=100)","background":"#ae1c1c"})
    },function(){
        $(this).find(".sidebox").stop().animate({"width":"54px"},200).css({"opacity":"0.8","filter":"Alpha(opacity=80)","background":"#000"})
    });

    $(".side ul .showSideView").hover(function(){
        $(this).next().stop(true,false).delay(500).slideDown(200)
    },function(){
        $(this).next().stop(true,false).slideUp(200)
    });
});

$(function() {
    var Accordion = function(el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;

        // Variables privadas
        var links = this.el.find('.link');
        // Evento
        links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
    }

    Accordion.prototype.dropdown = function(e) {
        var $el = e.data.el;
        $this = $(this),
            $next = $this.next();

        $next.slideToggle();
        $this.parent().toggleClass('open');

        if (!e.data.multiple) {
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        };
    }

    var accordion = new Accordion($('#accordion'), false);
});
//
// $("#pager").zPager({
//     totalData:50,//总条数
//     pageData: 7, //每页条数
//     pageCount:0,//总页数
//     current:1,//当前页数
//     pageStep: 8, //当前可见最多页码个数
//     minPage: 5, //最小页码数，页码小于此数值则不显示上下分页按钮
//     htmlBox: $('#wraper'),
//     btnShow: true,
//     ajaxSetData: false
// });
//
// function currentPage(currentPage){
//     /*
//         触发页码数位置： Page/js/jquery.z-pager.js 64行
//     */
//     console.log("当前页码数：" + currentPage);
// }



$(function () {
    var input = $('#pcFilePath')
    $('#pcFilePathBtn').click(function () {
        input.click()
    })
    input.change(function () {
        $('#pcFilePathName').html($(this).val())
    })
})


$(function () {

    $('.editSidebar').click(function () {
        let url = $(this).attr('edit_siderbar');

        swal({
            text: '请输入你要更改的侧边栏名字',
            content: "input",
            button: {
                text: "更改",
                closeModal: false,
            },
        }).then(name => {
            if (!name) throw null;
            $.ajax({
                type:"GET",
                url:"/edit/editSiderbar?name="+name+"&id="+url,
                dataType:"json",
                success:function(data){
                    if(data.code){
                        swal({
                            title: "编辑成功,请重启服务器查看",
                            text: data.msg,
                            icon: "success",
                            button: "确定",
                        }).then((value) => {
                            window.location.reload()
                        });
                    }else{
                        swal({
                            title: "编辑失败",
                            text: data.msg,
                            icon: "info",
                            button: "确定",
                        });
                    }
                },
                error:function(jqXHR){
                    swal({
                        title: "生成",
                        text: "出错了!链接中断",
                        icon: "error",
                        button: "确定",
                    });
                }
            });
        })
        return false;
    })
})


$(function () {

    $('.editHTML').click(function () {
        let url = $(this).attr('edit_HTML');

        swal({
            text: '请输入你要更改的侧边栏头部名字',
            content: "input",
            button: {
                text: "更改",
                closeModal: false,
            },
        }).then(name => {
            if (!name) throw null;
            $.ajax({
                type:"GET",
                url:"/edit/editHTML?name="+name+"&id="+url,
                dataType:"json",
                success:function(data){
                    if(data.code){
                        swal({
                            title: "编辑成功,请重启服务器查看",
                            text: data.msg,
                            icon: "success",
                            button: "确定",
                        }).then((value) => {
                            window.location.reload()
                        });
                    }else{
                        swal({
                            title: "编辑失败",
                            text: data.msg,
                            icon: "info",
                            button: "确定",
                        });
                    }
                },
                error:function(jqXHR){
                    swal({
                        title: "生成",
                        text: "出错了!链接中断",
                        icon: "error",
                        button: "确定",
                    });
                }
            });
        })
        return false;
    })
})


$(function () {

    $('.editTop').click(function () {
        let url = $(this).attr('edit_Top');

        swal({
            text: '请输入你要更改的头部名字',
            content: "input",
            button: {
                text: "更改",
                closeModal: false,
            },
        }).then(name => {
            if (!name) throw null;
            $.ajax({
                type:"GET",
                url:"/edit/editTop?name="+name+"&id="+url,
                dataType:"json",
                success:function(data){
                    if(data.code){
                        swal({
                            title: "编辑成功,请重启服务器查看",
                            text: data.msg,
                            icon: "success",
                            button: "确定",
                        }).then((value) => {
                            window.location.reload()
                        });
                    }else{
                        swal({
                            title: "编辑失败",
                            text: data.msg,
                            icon: "info",
                            button: "确定",
                        });
                    }
                },
                error:function(jqXHR){
                    swal({
                        title: "生成",
                        text: "出错了!链接中断",
                        icon: "error",
                        button: "确定",
                    });
                }
            });
        })
        return false;
    })
})


$(function () {

    $('.editTop1').click(function () {
        let url = $(this).attr('edit_Top');

        swal({
            text: '请输入你要更改的别名',
            content: "input",
            button: {
                text: "更改",
                closeModal: false,
            },
        }).then(name => {
            if (!name) throw null;
            $.ajax({
                type:"GET",
                url:"/edit/editTop1?name="+name+"&id="+url,
                dataType:"json",
                success:function(data){
                    if(data.code){
                        swal({
                            title: "编辑成功,请重启服务器查看",
                            text: data.msg,
                            icon: "success",
                            button: "确定",
                        }).then((value) => {
                            window.location.reload()
                        });
                    }else{
                        swal({
                            title: "编辑失败",
                            text: data.msg,
                            icon: "info",
                            button: "确定",
                        });
                    }
                },
                error:function(jqXHR){
                    swal({
                        title: "生成",
                        text: "出错了!链接中断",
                        icon: "error",
                        button: "确定",
                    });
                }
            });
        })
        return false;
    })
})