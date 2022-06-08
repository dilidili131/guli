<template>
  <div>
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写" style="width: 100%"/>
      </el-form-item>
      <!--TODO: 所属分类-->

      <!--TODO: 课程讲师-->

      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>
      <!--TODO: 简介-->
      <el-form-item label="课程简介">
        <el-input v-model="courseInfo.description" aria-placeholder="请输入课程简介"></el-input>
      </el-form-item>
      <!--TODO: 课程封面-->

      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />元
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  export default {
  components: {},
  props: {},
  data () {
    return {
      active:1,
      saveBtnDisabled:false,
      courseInfo:{}
    }
  },
  computed: {},
  watch: {},
  methods: {
    next(){
      this.$router.push({path:'/course/chapter/1'})
    }
  },
  created () {

  },
  mounted () {

  },
}
</script>
<style scoped>

</style>
