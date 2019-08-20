<template>

  <div class="table-container">
    <el-table ref="productAttrCateTable"
              style="width: 100%"
              :data="list"
              v-loading="listLoading"
              border>
      <el-table-column label="编号" width="100" align="center">
        <template slot-scope="scope">{{scope.row.id}}</template>
      </el-table-column>
      <el-table-column label="课程名称" align="center">
        <template slot-scope="scope">{{scope.row.name}}</template>
      </el-table-column>
      <el-table-column label="学分" width="200" align="center">
        <template slot-scope="scope">{{scope.row.creditnum==null?0:scope.row.creditnum}}</template>
      </el-table-column>
      <el-table-column label="可选人数" width="200" align="center">
        <template slot-scope="scope">{{scope.row.studentnum==null?0:scope.row.studentnum}}</template>
      </el-table-column>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleUpdate(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handlePublish(scope.row)">发布
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="编辑信息"
      :visible.sync="dialogVisible"
      width="30%">
      <el-form ref="productAttrCatForm":model="productAttrCate" :rules="rules" label-width="120px">
        <el-form-item label="课程名称" prop="name">
          <el-input v-model="productAttrCate.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程时间" prop="time">
          <el-input v-model="productAttrCate.time" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="授课地点" prop="classroom">
          <el-input v-model="productAttrCate.classroom" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="学期" prop="term">
          <el-input v-model="productAttrCate.term" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>





      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleConfirm">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="确认添加" :visible.sync="ensurePublishVisible">

      <span>您确认发布该课程吗？</span>
      <el-button @click="publish">确认</el-button>
      <el-button @click="ensurePublishVisible=false">取消</el-button>
    </el-dialog>
  </div>

</template>



<script>

  export default {

    name: 'publish',

    components: {},

    data() {

      return {
        productAttrCate:{
          name:'',
          id:null,
          term:'',
          classroom:'',
          time:'',
        },
        dialogVisible:false,
        ensurePublishVisible:false,
        list:[
          {
            id:1,
            name:'xfy',
            creditnum:1,
            studentnum:0
          }
        ],

      };

    },

    created () {

      var xmlhttp = new XMLHttpRequest();

      var _this = this
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          _this.list = JSON.parse(xmlhttp.responseText)

        }
      }

      let formData = new FormData()

      formData.append('teachername', localStorage.getItem('username'))
      xmlhttp.open('POST', 'http://localhost:8080/j2ee/course/checkedcourse', true)
      xmlhttp.send(formData)

    },
    methods: {

      publish(){
        this.ensurePublishVisible = false;
        this.productAttrCate.canpublish = 1
        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          }
        }

        let formData = new FormData()

        formData.append('courseid', this.productAttrCate.id)
        console.log("publishId"+ JSON.stringify(this.productAttrCate))
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/course/publishcourse', true)
        xmlhttp.send(formData)


      },
      handlePublish(row){
        this.productAttrCate = row
        this.ensurePublishVisible = true;
      },

      handleUpdate(index, row) {
        this.dialogVisible = true;
        this.dialogTitle = "编辑类型";
        this.productAttrCate = row

      },

      handleConfirm(){

        this.dialogVisible = false;
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            _this.created()

          }
        }
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/course/updatecourse', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        xmlhttp.send(JSON.stringify(this.productAttrCate))


      }

    },

  }

</script>



<style>
  .table-container {
    margin-top: 20px;
  }

</style>
