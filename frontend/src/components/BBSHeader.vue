<template>
  <div class="header">
    <div class='info'>
      <svg style="width: 40px;height: 50px" @click="gotoBBSHome"><use xlink:href="#icon-arrowleft"></use></svg>
      <span @click='dialogVisible = true'>关于</span>
    </div>
    <el-dialog title="关于本项目" :visible.sync="dialogVisible" width="35%">

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  /**
   * 网站顶部组件
   */
  export default {
    /**
     * 给此组件起的名字，会显示在Vue的DevTool里面
     */
    name: "Header",
    /**
     * 当前组件的‘数据中心'
     */
    data() {
      return {
        dialogVisible: false
      };
    },
    methods: {
      gotoBBSHome() {
        this.$emit("gotoBBSHome")
      }
    }

  };
</script>

<style scoped lang="scss">
  .header {
    width: 100%;
    height: 50px;
    background: #444;
    > div.info {
      width: 1200px;
      height: 100%;
      margin: auto;
      a {
        width: 120px;
        height: 50px;
        display: block;
        float: left;
        img {
          padding-top: 10px;
          display: block;
          width: 100%;
        }
      }
      span {
        float: right;
        height: 50px;
        line-height: 50px;
        color: #fff;
        cursor: pointer;
        font-size: 15px;
        font-weight: bold;
      }
    }
  }
</style>

<style lang='scss'>
  .header {
    .el-dialog__body {
      padding-top: 10px;
      padding-bottom: 0;
      .inside-dialog {
        p {
          margin: auto;
        }
        .el-icon-star-off::before {
          color: #eb2f96;
        }
      }
    }
  }
</style>
