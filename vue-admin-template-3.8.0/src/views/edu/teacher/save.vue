<template>
    <div class="app-container">
        <el-form ref="eduTeacherForm" :rules="eduTeacherRules" :model="eduTeacher" label-width="120px">
            <el-form-item label="教师名称" prop="name">
                <el-input v-model="eduTeacher.name"></el-input>
            </el-form-item>
            <el-form-item label="讲师排序" prop="sort">
                <el-input-number v-model="eduTeacher.sort" :min="0" controls-position="right"></el-input-number>
            </el-form-item>
            <el-form-item label="讲师头衔" prop="level">
                <el-select v-model="eduTeacher.level" placeholder="请选择">
                    <el-option label="高级讲师" value="1"></el-option>
                    <el-option label="首席讲师" value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="讲师资历" prop="career">
                <el-input v-model="eduTeacher.career"></el-input>
            </el-form-item>
            <el-form-item label="讲师简介" prop="intro">
                <el-input v-model="eduTeacher.intro" :rows="5" type="textarea"></el-input>
            </el-form-item>

            <!-- TODO 讲师头像 -->
             <el-form-item label="讲师头像">
                <!-- 头衔缩略图 -->
                <pan-thumb :image="eduTeacher.avatar" />
                <!-- 文件上传按钮 -->
                <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像</el-button>

                <!--
        v-show：是否显示上传组件
        :key：类似于id，如果一个页面多个图片上传控件，可以做区分
        :url：后台上传的url地址
        @close：关闭上传组件
                @crop-upload-success：上传成功后的回调-->
                <image-cropper
                    v-show="imagecropperShow"
                    :width="300"
                    :height="300"
                    :key="imagecropperKey"
                    :url="BASE_API+'/eduOss/fileOss/upload'"
                    field="file"
                    @close="close"
                    @crop-upload-success="cropSuccess"
                />
            </el-form-item>
            <el-form-item >
                <el-button type="primary" :disabled="saveBtnDisabled" @click="saveOrUpdate">保存</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import teacher from '@/api/edu/teacher'
import ImageCropper from '@/Components/ImageCropper'
import PanThumb from '@/components/PanThumb'
export default {
    name: 'Save',
    components:{ImageCropper,PanThumb},
    data() {
        return {
            saveBtnDisabled:false,
            eduTeacher:{
                name:'',
                sort:'',
                level:'',
                career:'',
                intro:'',
                avatar:''
            },
            eduTeacherRules:{
                name:[
                    { required: true, message: '请输入姓名', trigger: 'blur' },
                ],
                sort:[
                    { required: true, message: '请输入排名', trigger: 'blur' },
                ],
                level:[
                    { required: true, message: '请输入讲师头衔', trigger: 'blur' },
                ],
                career:[
                    { required: true, message: '请输入讲师资历', trigger: 'blur' },
                ],
                intro:[
                    { required: true, message: '请输入讲师简介', trigger: 'blur' },
                ]
            },
            imagecropperShow:false,
            imagecropperKey:0,
            BASE_API:process.env.BASE_API
        };
    },

    created() {
        this.init()
    },
    watch:{
        $route(to,from){
            this.init()
        }
    },
    methods: {
        init(){
            if(this.$route.params && this.$route.params.id){
                const id = this.$route.params.id
                this.getTeacherInfoById(id)
            }else{
                this.eduTeacher = {}
            }
        },
        saveOrUpdate(){
            if(this.eduTeacher.id){
                this.edit()
            }else{
                this.save()
            }
        },
        save(){
            this.$refs.eduTeacherForm.validate(item=>{
                if(item){
                    teacher.addTeacher(this.eduTeacher)
                    .then(res=>{
                        console.log(res);
                        this.$message({
                            type:'success',
                            message:'添加成功'
                        })
                        this.$refs.eduTeacherForm.resetFields()
                        this.$router.push({path:'/teacher/table'})
                    })
                    .catch(err=>{
                        console.log(err);
                    })
                }else{
                    this.$message({
                        type: 'danger',
                        message: '请完整填写信息'
                    });  
                }
            })
        },
        edit(){
            this.$refs.eduTeacherForm.validate(item=>{
                console.log(item);
                if(item){
                    teacher.updateTeacher(this.eduTeacher)
                    .then(res=>{
                        console.log(res);
                        this.$message({
                            type:'success',
                            message:'添加成功'
                        })
                        this.$refs.eduTeacherForm.resetFields()
                        this.$router.push({path:'/teacher/table'})
                    })
                    .catch(err=>{
                        console.log(err);
                    })
                }else{
                    this.$message({
                        type: 'danger',
                        message: '请完整填写信息'
                    });  
                }
            })
        },
        getTeacherInfoById(id){
              teacher.getTeacherById(id)
              .then(res=>{
                  this.eduTeacher = res.data.teacher
              })
              .catch(err=>{
                  console.log(err);
              })
        },
        close(){
            this.imagecropperShow = false
            this.imagecropperKey = this.imagecropperKey+1
        },
        cropSuccess(data){
            this.imagecropperShow = false
            this.eduTeacher.avatar = data.url
            this.imagecropperKey = this.imagecropperKey+1
        }
    },
};
</script>

<style lang="scss" scoped>

</style>