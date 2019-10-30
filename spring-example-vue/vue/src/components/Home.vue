<template>
    <el-container class="home_container">
        <el-header>
            <div class="home_title">图书管理平台</div>
            <div class="home_userinfoContainer">
                <el-dropdown @command="handleCommand">
  <span class="el-dropdown-link home_userinfo">
    {{currentUserName}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
  </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="sysMsg">系统消息</el-dropdown-item>
                        <el-dropdown-item command="MyArticle">我的文章</el-dropdown-item>
                        <el-dropdown-item command="MyHome">个人主页</el-dropdown-item>
                        <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </el-header>
        <el-container>
            <el-aside width="200px">

                <el-menu :default-active="defaultActiveIndex" router :collapse="collapsed">
                    <template v-for="(item,index) in $router.options.routes" v-if="item.menuShow">
                        <el-submenu v-if="!item.leaf" :index="index+''">
                            <template slot="title"><i :class="item.iconCls"></i><span slot="title">{{item.name}}</span></template>
                            <el-menu-item v-for="term in item.children" :key="term.path" :index="term.path" v-if="term.menuShow"
                                          :class="$route.path==term.path?'is-active':''">
                                <i :class="term.iconCls"></i><span slot="title">{{term.name}}</span>
                            </el-menu-item>
                        </el-submenu>
                        <el-menu-item v-else-if="item.leaf&&item.children&&item.children.length" :index="item.children[0].path"
                                      :class="$route.path==item.children[0].path?'is-active':''">
                            <i :class="item.iconCls"></i><span slot="title">{{item.children[0].name}}</span>
                        </el-menu-item>
                    </template>
                </el-menu>


            </el-aside>
            <el-container>
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
                    </el-breadcrumb>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </el-container>
</template>
<script>
    export default{
        methods: {
            handleCommand(command){
                var _this = this;
                if (command == 'logout') {
                    this.$confirm('注销登录吗?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(function () {
                        // getRequest("/logout")
                        localStorage.removeItem('access-user');
                        _this.currentUserName = '游客';
                        _this.$router.go('/login');
                    }, function () {
                        //取消
                    })
                }
            }
        },
        mounted: function () {
            // this.$alert('为了确保所有的小伙伴都能看到完整的数据演示，数据库只开放了查询权限和部分字段的更新权限，其他权限都不具备，完整权限的演示需要大家在自己本地部署后，换一个正常的数据库用户后即可查看，这点请大家悉知!', '友情提示', {
            //     confirmButtonText: '确定',
            //     callback: action => {
            //     }
            // });
            // var _this = this;
            // getRequest("/currentUserName").then(function (msg) {
            //     _this.currentUserName = msg.data;
            // }, function (msg) {
            //     _this.currentUserName = '游客';
            // });
            let user = JSON.parse(window.localStorage.getItem('access-user'))
            this.currentUserName = user
        },
        data(){
            return {
                currentUserName: '',
                defaultActiveIndex: "0",
                collapsed: false,
            }
        }
    }
</script>
<style>
    .home_container {
        height: 100%;
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
    }

    .el-header {
        background-color: #20a0ff;
        color: #333;
        text-align: center;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .el-aside {
        background-color: #ECECEC;
    }

    .el-main {
        background-color: #fff;
        color: #000;
        text-align: center;
    }

    .home_title {
        color: #fff;
        font-size: 22px;
        display: inline;
    }

    .home_userinfo {
        color: #fff;
        cursor: pointer;
    }

    .home_userinfoContainer {
        display: inline;
        margin-right: 20px;
    }
</style>
