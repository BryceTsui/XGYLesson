<template>
  <div style="position: absolute; top: 50px; left: 0px; padding-left: 20px;background-color: white;">
    <div v-for="item in projectInfo" style="float: left" :key="item">
      <el-card v-show="item.show" class="el_card" :body-style="{padding:'0px'}">
        <el-tag v-show="item.isContinue" style="position: absolute; left: 20px; top: 10px; color:white; width: 70px " color="#4CAF50">进行中...</el-tag>
        <el-tag v-show="item.isEnd" style="position: absolute; left: 20px; top: 10px; color:white;width: 60px " color="#E97506">已截止</el-tag>
        <img src="@/images/file_ppt.png" class="image" v-bind:id="item.missionname">
        <div class="imgOnClick">
          <span>已标注：2 张</span>
          <span>未标注：3 张</span>
          <el-progress type="circle" v-bind:percentage="item.percent" color="#8e71c7"></el-progress>
        </div>
        <div style="height: 130px;position:absolute; top: 150px; width: 97%; left: 1.5%; background-color: white">
          <span style="font-size: 20px; color: #4CAF50">{{item.missionname}}</span>
          <span class="time" style="position: absolute; left: 5px;top: 30px;font-size: 14px; color:black">时间：{{item.dateStart}} - {{item.dateEnd}}</span>
          <span style="position: absolute; left: 5px;top: 52px;font-size: 14px;">类型：{{item.type}}</span>
          <span style="position: absolute; left: 140px;top: 52px;font-size: 14px;">积分：{{item.counts}}</span>
          <el-button style="position: absolute; left: 50px;top: 75px;font-size: 14px;" type="text" @click="handleProjectDetails(item.id)">项目详情</el-button>
          <el-button style="position: absolute; left: 150px;top: 75px;font-size: 14px;" type="text" class="card_button" @click="openProject(item)">进入项目</el-button>
        </div>
      </el-card>
    </div>
  </div>
</template>


<script>
  export default {
    data() {
      return {
        projectInfo: [
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
        ],
      };
    }
  }
</script>

<style scoped>
  .imgOnClick {
    width: 100%;
    height: 60%;
    filter: alpha(Opacity=50);
    -moz-opacity: 0.6;
    opacity: 0.6;
    z-index: 100;
    background-color: #ffffff;
    display: none;
    position: absolute;
    left: 0px;
    top:5px;
  }

  .el_card{
    margin: 10px;
    padding: 5px;
    width: 260px;
    height:245px;
    float: left;
    position: relative;
  }
  .el_card:hover .imgOnClick{
    display:block;
  }
  .el_card:hover{

  }
  .time{
    left: 10px;
    font-size: 14px;
    color: #999;
  }
  .card_button{
    padding: 0px;
    float: right;
  }
  .image{
    width: 100%;
    display: block;
  }
  * {
    margin: 0;
    padding: 0;
  }

  .btn {
    display: inline-block;
    line-height: 1;
    white-space: nowrap;
    cursor: pointer;
    background: #fff;
    border: 1px solid #c0ccda;
    color: #1f2d3d;
    text-align: center;
    box-sizing: border-box;
    outline: none;
    margin:20px 10px 0px 0px;
    padding: 9px 15px;
    font-size: 14px;
    border-radius: 4px;
    color: #fff;
    background-color: #50bfff;
    border-color: #50bfff;
    transition: all .2s ease;
    text-decoration: none;
    user-select: none;
  }

  .show-info {
    margin-bottom: 50px;
  }

  .test {
    height: 300px;
    width: 400px;
  }

  .model img {
    display: block;
    margin: auto;
    max-width: 80%;
    user-select: none;
    background-position: 0px 0px, 10px 10px;
    background-size: 20px 20px;
    background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee 100%),linear-gradient(45deg, #eee 25%, white 25%, white 75%, #eee 75%, #eee 100%);
  }

  @keyframes slide {
    0%  {
      background-position: 0 0;
    }
    100% {
      background-position: -100% 0;
    }
  }

  #edit_name{
    display: none;
  }

  #account_info:hover #edit_name{
    display: block;
  }
  #edit_details{
    display: none;
  }

  #details_info:hover #edit_details{
    display: block;
  }
  #edit_connect{
    display: none;
  }

  #connect_info:hover #edit_connect{
    display: block;
  }
  .labelType{
    width:100px;
    padding-top: 3px;
    height: 24px;
    border-radius: 15px;
    color: white;
    background-color: #2ea2ff;
    font-size: 13px;
    cursor: pointer;
  }
  .labelType:hover{
    color:white;
    cursor: pointer;
  }
</style>

