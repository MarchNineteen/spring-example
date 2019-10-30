<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="form-container">
        <el-form-item label="图书编号" prop="id">
            <el-input v-model="ruleForm.id" readonly=""></el-input>
        </el-form-item>
        <el-form-item label="图书名称" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="作者" prop="author">
            <el-input v-model="ruleForm.author"></el-input>
        </el-form-item>
        <el-form-item label="出版社" prop="publish">
            <el-select style="margin-left: -308px;" v-model="ruleForm.publish" placeholder="请选择出版社">
                <el-option v-for="(item,index) in publishData" :label="item.value" :value="item.value"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="总页数" prop="pages">
            <el-input v-model="ruleForm.pages"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
            <el-input v-model="ruleForm.price"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="bookCaseId">
            <el-select style="margin-left: -308px;" v-model="ruleForm.bookCaseId" placeholder="请选择分类">
                <el-option v-for="(item,index) in bookCaseData" :label="item.value" :value="item.id"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
        </el-form-item>
    </el-form>

</template>

<script>
    export default {
        data() {
            return {
                publishData:[
                    {
                        value:'人民邮电出版社'
                    },
                    {
                        value:'电子工业出版社'
                    },
                    {
                        value:'机械工业出版社'
                    },
                    {
                        value:'南海出版公司'
                    },
                    {
                        value:'上海人民出版社'
                    },
                    {
                        value:'作家出版社'
                    },
                    {
                        value:'百花洲文艺出版社'
                    },
                    {
                        value:'湖南少儿出版社'
                    },
                    {
                        value:'北京联合出版公司'
                    },
                    {
                        value:'生活.读书.新知三联书店'
                    },
                    {
                        value:'三联出版社'
                    }
                ],
                bookCaseData:[
                    {
                        id:1,
                        value:'社会'
                    },
                    {
                        id:2,
                        value:'情感'
                    },
                    {
                        id:3,
                        value:'国学'
                    },
                    {
                        id:4,
                        value:'推理'
                    },
                    {
                        id:5,
                        value:'绘画'
                    },
                    {
                        id:6,
                        value:'心理学'
                    },
                    {
                        id:7,
                        value:'传记'
                    },
                    {
                        id:8,
                        value:'科技'
                    },
                    {
                        id:9,
                        value:'计算机'
                    },
                    {
                        id:10,
                        value:'小说'
                    }
                ],
                ruleForm: {
                    id: '',
                    name: '',
                    author: '',
                    publish:'',
                    pages: '',
                    price: '',
                    bookCaseId:''
                },
                rules: {
                    id: [
                        { required: true, message: '请输入图书编号', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入书名', trigger: 'blur' }
                    ],
                    author: [
                        { required: true, message: '请输入作者', trigger: 'blur' }
                    ],
                    publish: [
                        { required: true, message: '请选择出版社', trigger: 'change' }
                    ],
                    pages: [
                        { required: true, message: '请输入总页数', trigger: 'blur' }
                    ],
                    price: [
                        { required: true, message: '请输入价格', trigger: 'blur' }
                    ],
                    bookCaseId: [
                        { required: true, message: '请选择分类', trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.ruleForm)
                        this.ruleForm.bookCase = {id:this.ruleForm.bookCaseId}
                        const _this = this
                        axios.put('http://localhost:8181/book/update',this.ruleForm).then(function (resp) {
                            if(resp.data == 1){
                                _this.$alert('修改成功！', '', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push("/")
                                    }
                                });
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/book/findById/'+this.$route.query.id).then(function (resp) {
                _this.ruleForm.id = resp.data.id
                _this.ruleForm.name = resp.data.name
                _this.ruleForm.author = resp.data.author
                _this.ruleForm.publish = resp.data.publish
                _this.ruleForm.pages = resp.data.pages
                _this.ruleForm.price = resp.data.price
                _this.ruleForm.bookCaseId = resp.data.bookCase.id
            })
        }
    }
</script>

<style>
    .form-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 0px auto;
        width: 600px;
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