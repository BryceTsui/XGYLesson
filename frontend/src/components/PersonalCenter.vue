<template>
  <div>


    <div style="background-color: #E9E9E9;position:absolute; top: 0px; left: 0px; width: 100%;height:1700px"></div>

    <div style="background-color: #e9660a;position:absolute; top: 0px; left: 0px; width: 100%;height:60px"></div>

    <div style="background-color: #e9e81e;position:absolute; top: 50px; left: 0px; width: 50px;height:1650px"></div>

    <div style="background-color: #e9e81e;position:absolute; top: 70px; left: 70px; width: 500px;height:250px"></div>

    <div style="background-color: #e9e81e;position:absolute; top: 70px; left: 590px; width: 200px;height:250px"></div>

    <div style="background-color: #e9e81e;position:absolute; top: 70px; left: 810px; width: 400px;height:250px">
      <el-container style="top:0px;left:0px;height: 250px;width: 400px; border: 1px solid #ee2411">
      <el-container>
      <el-main>
        <el-table :data="tableData" style="top:0px;left:0px;height: 250px;width: 400px;border: 1px solid #ee2411">
          <el-table-column prop="date" label="日期" width="140">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="120">
          </el-table-column>
          <el-table-column prop="address" label="地址">
          </el-table-column>
        </el-table>
      </el-main>
      </el-container>
      </el-container>
      <el-button @click="download()">www</el-button>
      <a :href="'http://localhost:8080/j2ee/file/homework/downlaodall?courseid='+1+'&homeworkid='+1" onclick="click()">kkk</a>
      <el-upload
        class="upload-demo"
        drag
        action="https://jsonplaceholder.typicode.com/posts/"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
      </el-upload>
    </div>


  </div>
</template>

<script>
export default {
  name: 'PersonalCenter',
  data () {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    }
    return {
      url:"test3.jpg",
      tableData: Array(20).fill(item)
    }

      },
  methods:{

    download:function () {

      window.location.herf= "http://localhost:8080/j2ee/file/download";

    }
  }
}
</script>

<style scoped>

</style>
