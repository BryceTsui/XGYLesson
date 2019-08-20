<template>
  <el-table
    :data="tableData"
    stripe
    style="width: 100%">
    <el-table-column
      prop="Time"
      label=""

    >
    </el-table-column>
    <el-table-column
      prop="Monday"
      label="周一"
      >
    </el-table-column>
    <el-table-column
      prop="Tuesday"
      label="周二"
     >
    </el-table-column>
    <el-table-column
      prop="Wednesday"
      label="周三">
    </el-table-column>

    <el-table-column
      prop="Thursday"
      label="周四">
    </el-table-column>
    <el-table-column
      prop="Friday"
      label="周五">
    </el-table-column>
    <el-table-column
      prop="Saturday"
      label="周六">
    </el-table-column>
    <el-table-column
      prop="Sunday"
      label="周日">
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    name:"table",
    data() {
      return {
        tableData: []
      }
    }
  }
</script>

<style scoped>

</style>
