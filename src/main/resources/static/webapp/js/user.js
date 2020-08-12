var vue = new Vue({
    el: "#app",
    data: {
        user: {id:"",username:"",password:"",age:"",sex:"",email:""},
        userList: []
    },
    methods: {
        findAll: function(){
            var _this = this;
                    axios.get("/user/findAll.do").then(function (response) {
                        _this.userList = response.data;//响应数据给userList赋值
                        console.log(_this.userList);
                    }).catch(function (err) {
                        console.log(err);
            });
        },
        findById: function (userId) {
            var _this = this;
            axios.get("/user/findById.do", {
                params: {
                    id: userId
                }
            }).then(function (response) {
              _this.user = response.data;
                $('#myModal' ).modal("show");
            }).catch(function (err) {
                console.log(err);
            });

        },
        update: function (user) {//post请求
        //在当前方法中定义一个变量，表明是vue对象
            var _this = this;
            axios.post("/user/update.do",_this.user).then(function (response) {
                _this.findAll();
            }).catch(function (err){
            });
        }
    },

    created(){
        this.findAll();
    }
});
