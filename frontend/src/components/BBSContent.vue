<template>
  <div >
    <div class='topic' v-for='item in list' :key='item.id' @click="gotoTopic(item.id)">
        <img :src='item.avator' alt="用户头像" />
      <span class="count">
        <em>xgy</em><em></em>
      </span>
      <el-tag :type='$tab[item.tab] && $tab[item.tab].type'>
        {{$tab[item.tab] && $tab[item.tab].name}}
      </el-tag>
      <a class="title" @click="gotoTopic(item.id)">
        {{item.title}}
      </a>
      <span class="time">
        {{$moment(item.last_reply_at, 'YYYY-MM-DD')
        .startOf('day')
        .fromNow()}}
      </span>
    </div>
  </div>
</template>

<script>
  /**
   * 会在首页使用的组件 :p
   */
  export default {
    /**
     * 给此组件起的名字，会显示在Vue的DevTool里面
     */
    name: "Topics",
    /**
     * 通过在这里定义变量，可以让父组件通过这些变量向此组件传递数据
     * P.S. https://cn.vuejs.org/v2/guide/components.html#%E9%80%9A%E8%BF%87-Prop-%E5%90%91%E5%AD%90%E7%BB%84%E4%BB%B6%E4%BC%A0%E9%80%92%E6%95%B0%E6%8D%AE
     */
    props: ["list"],
    /**
     * 当前组件的‘数据中心'
     */
    data() {
      return {
        article: []
      };
    },
    methods: {

      gotoTopic(id) {
        this.$emit("gotoDetail",id);
      }
    }
  };
</script>

<style lang='scss' scoped>



  .topic {
    margin: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    img {
      width: 50px;
      border-radius: 50%;
      height: 50px;
    }
    span.count {
      width: 50px;
      text-align: center;
      em:first-child {
        font-size: 1.2em;
        color: purple;
      }
      em:last-child {
        color: grey;
      }
    }
    .title {
      width: 60%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
      margin: 0;
      font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
      "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;

      text-align:left;
      font-size: 14px;
      -webkit-font-smoothing: antialiased;
      line-height: 1.6rem;
      letter-spacing: 0;

    }
    span.time {
      width: 70px;
      text-align: right;
    }
  }
  a {
    color: #42b983;
    font-weight: 600;
    padding: 0px 2px;
    text-decoration: none;
  }
</style>
