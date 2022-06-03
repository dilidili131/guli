<template>
    <div class="app-container">
        <!-- 条件查询部分 -->
        <el-form ref="teacherQueryForm" :inline="true" :model="teacherQuery" class="demo-form-inline">
            <el-form-item label="姓名" prop="name">
                <el-input v-model="teacherQuery.name" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="头衔" prop="level">
                <el-select v-model="teacherQuery.level" placeholder="讲师头衔">
                    <el-option label="高级讲师" :value="1"></el-option>
                    <el-option label="首席讲师" :value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="起始时间" prop="begin">
                <el-date-picker 
                    type="datetime" 
                    placeholder="选择日期" 
                    v-model="teacherQuery.begin" 
                    style="width: 100%;"
                    value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间" prop="end">
                <el-date-picker 
                    type="datetime" 
                    placeholder="选择日期" 
                    v-model="teacherQuery.end" 
                    style="width: 100%;"
                    value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="clear">重置</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="getList()">查询</el-button>
            </el-form-item>
        </el-form>
        <!-- table部分 -->
        <el-table
            border
            fit
            :data="list"
            style="width: 100%">

            <el-table-column label="序号" width="70" align="center">
                <template slot-scope="scope">
                    {{(current-1) * limit + scope.$index + 1}}
                </template>
            </el-table-column>
            <el-table-column  prop="name"  label="名稱"  width="80"></el-table-column>
            <el-table-column  label="头衔"  width="80">
                <template slot-scope="scope">
                    {{scope.row.level === 1 ? "高级讲师" : "首席讲师"}}
                </template>
            </el-table-column>
            <el-table-column  prop="intro"  label="资历"></el-table-column>
            <el-table-column  prop="gmtCreate"  label="添加时间"  width="180"></el-table-column>
            <el-table-column  prop="sort" label="排序" width="60"></el-table-column>
            <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <router-link :to="'/teacher/edit/'+scope.row.id">
                        <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
                    </router-link>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
            
        </el-table>
        <!-- 分页部分 -->
        <div class="block">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="current"
                :page-sizes="[5, 10, 20, 50]"
                :page-size="limit"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
import teacher from '@/api/edu/teacher'
export default {
    name: 'List',
    data() {
        return {
            list:null,
            current:1,
            limit:10,
            total:0,
            teacherQuery:{
                name:'',
                level:'',
                begin:'',
                end:''
            }
        };
    },
    created() {
        this.getList()
    },
    methods: {
        getList(){
            teacher.getTeacherListPage(this.current,this.limit,this.teacherQuery)
            .then(res =>{
                // console.log(res);
                this.list = res.data.rows
                this.total = res.data.total
                console.log(this.list);
                console.log(this.total);
            })
            .catch(error =>{
                console.log(error);
            })
        },
        handleSizeChange(val){
            this.limit = val
            this.getList()
        },
        handleCurrentChange(val){
            this.current = val
            this.getList()
        },
        clear(){
            this.$refs.teacherQueryForm.resetFields()
            this.getList()
        },
        removeDataById(id){
            // console.log(id);
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {//点击确定
                
                teacher.removeTeacherById(id)
                .then(res=>{
                    console.log(res);
                    if(res.resultCode === 20000){
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        this.getList()
                    }
                }).catch(error=>{
                    console.log(error);
                })
                
                
            }).catch(() => {//点击取消
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        }
        
    }
};
</script>

<style lang="scss" scoped>
    .block{
        margin-top: 20px;
        text-align: right;
    }
</style>