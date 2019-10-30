<template>
    <el-container class="home_container">
        <el-header>
            <div class="home_title">eBook图书管理平台</div>
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

            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

                <el-menu :default-active="active" router>
                    <el-submenu v-for="(item) in $router.options.routes" :index="item.path"  v-if="item.menuShow">
                        <template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>
                        <el-menu-item v-for="(item2) in item.children" :index="item2.path"  v-if="item2.menuShow">{{item2.name}}</el-menu-item>
                    </el-submenu>
                </el-menu>

            </el-aside>

            <el-main>
                <el-breadcrumb separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
                </el-breadcrumb>
                <router-view></router-view>
            </el-main>

        </el-container>

    </el-container>

</template>

<script>
    export default {
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
            let user = JSON.parse(window.localStorage.getItem('access-user'))
            this.currentUserName = user
        },
        data() {
            return {
                currentUserName: '',
                active: '/table'
            }
        }
    };
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
