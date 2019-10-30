<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
        <h3 class="login_title">系统登录</h3>
        <el-form-item prop="username">
            <el-input type="text" v-model="ruleForm.username" auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
            <el-input type="password" v-model="ruleForm.password" auto-complete="off" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item style="width: 100%">
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
    export default{
        data(){
            return {
                ruleForm: {
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入帐号', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'change' },
                        { len: 3, message: '长度 3 位', trigger: 'blur' }
                    ]
                },
                loading: false
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        var _this = this
                        this.loading = true
                        _this.loading = false
                        localStorage.setItem('access-user', JSON.stringify('张三'));
                        _this.$router.replace({path: '/'})
                        // axios.get('http://localhost:8181/account/login/'+this.ruleForm.username+"/"+this.ruleForm.password).then(function (resp) {
                        //     if(resp.data == 'success'){
                        //         _this.loading = false
                        //         _this.$router.replace({path: '/home'})
                        //     } else {
                        //         _this.loading = false
                        //         _this.$alert('登录失败!', '失败!');
                        //     }
                        // })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>
<style>
    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .login_title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }

    .login_remember {
        margin: 0px 0px 35px 0px;
        text-align: left;
    }
</style>
