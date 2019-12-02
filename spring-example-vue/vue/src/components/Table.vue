<template>
    <div>
        <div>
            <el-upload
                    class="upload-demo"
                    action="http://localhost:8181/book/upload/"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :before-remove="beforeRemove"
                    multiple
                    :limit="3"
                    :on-exceed="handleExceed"
                    :file-list="fileList">
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>

            <el-table
                    :data="tableData"
                    border
                    style="width: 80%;margin-left: 100px;margin-top: 30px;">
                <el-table-column
                        fixed
                        prop="id"
                        label="编号"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="图书"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="author"
                        label="作者"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="publish"
                        label="出版社"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="pages"
                        label="总页数"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="price"
                        label="价格"
                        width="100">
                </el-table-column>
                <el-table-column
                        prop="bookCase.name"
                        label="分类"
                        width="120">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button @click="findById(scope.row.id)" type="text" size="small">修改</el-button>
                        <el-button @click="deleteById(scope.row)" type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>

            </el-table>
            <el-pagination
                    background
                    layout="prev, pager, next"
                    :total="total"
                    :page-size="pageSize"
                    @current-change="change"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        methods: {
            findById(id) {
                this.$router.push({path:'/update',query:{id:id}})
            },
            change(currentPage) {
                this.currentPage = currentPage
                const _this = this
                axios.get('http://localhost:8181/book/findByPage/'+currentPage).then(function (resp) {
                    _this.tableData = resp.data.data
                })
            },
            deleteById(row) {
                this.$confirm('确定删除《'+row.name+'》?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this = this
                    axios.delete('http://localhost:8181/book/deleteById/'+row.id).then(function (resp) {
                        if(resp.data == 1){
                            axios.get('http://localhost:8181/book/findByPage/'+_this.currentPage).then(function (resp) {
                                // _this.$message({
                                //     type: 'success',
                                //     message: '删除成功!'
                                // });
                                //_this.tableData = resp.data.data

                                _this.$alert('删除成功！', '', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.tableData = resp.data.data
                                    }
                                });

                            })
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            getFile(event){
                var file = event.target.files;
                for(var i = 0;i<file.length;i++){
                    //    上传类型判断
                    var imgName = file[i].name;
                    var idx = imgName.lastIndexOf(".");
                    if (idx != -1){
                        var ext = imgName.substr(idx+1).toUpperCase();
                        ext = ext.toLowerCase( );
                        if (ext!='pdf' && ext!='doc' && ext!='docx'){

                        }else{
                            this.addArr.push(file[i]);
                        }
                    }else{

                    }
                }
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
            },
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${ file.name }？`);
            }
        },

        data() {
            return {
                total:0,
                pageSize:5,
                tableData: [],
                currentPage:0,
                addArr:[],
                fileList: []
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/book/findByPage/1').then(function (resp) {
                console.log(resp.data)
                _this.pageSize = resp.data.pageSize
                _this.total = resp.data.total
                _this.tableData = resp.data.data
            })
        }
    }
</script>

<style scoped>

</style>
